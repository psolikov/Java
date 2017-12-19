package ru.spbau.solikov.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class that implements the Dependency Injection container.
 */
public class Injector {

    private static HashMap<Class<?>, Object> createdObjects;
    private static ArrayList<Class<?>> loadedClasses;

    /**
     * Creates object of given type or throws one of the exceptions.
     *
     * @param rootClassName type of object that we want to create
     * @param realizations  list of available realizations
     * @return implementation of given rootClassName
     */
    public static Object initialize(String rootClassName, List<String> realizations)
            throws Exception {

        loadedClasses = new ArrayList<>();
        createdObjects = new HashMap<>();

        for (String realization : realizations) {
            Class<?> realizationType = null;
            try {
                realizationType = Class.forName(realization);
            } catch (ClassNotFoundException e) {
                throw new ImplementationNotFoundException();
            }
            loadedClasses.add(realizationType);
        }

        Class<?> classRootClassName;
        try {
            classRootClassName = Class.forName(rootClassName);
        } catch (ClassNotFoundException e) {
            throw new ImplementationNotFoundException();
        }

        loadedClasses.add(classRootClassName);

        Object objectRootClassName = search(classRootClassName, new HashSet<>());

        createdObjects.clear();

        return objectRootClassName;
    }

    private static boolean checkImplementation(Class<?> implementation, Class<?> clazz) {
        if (implementation == null) {
            return false;
        }

        for (Class<?> interfazz : implementation.getInterfaces()) {
            if (checkImplementation(interfazz, clazz) || interfazz.equals(clazz)) {
                return true;
            }
        }

        return checkImplementation(implementation.getSuperclass(), clazz);
    }

    private static Class<?> implementationOf(Class<?> clazz) throws ImplementationNotFoundException, AmbiguousImplementationException {
        Class<?> implementation = null;

        for (Class<?> loadedClass : loadedClasses) {
            boolean found = false;

            if (loadedClass.equals(clazz)) {
                found = true;
            }

            if (clazz.isInterface()) {
                found = checkImplementation(loadedClass, clazz);
            }

            if (found == true) {
                if (implementation != null) {
                    throw new AmbiguousImplementationException();
                }

                implementation = loadedClass;
            }
        }

        if (implementation == null) {
            throw new ImplementationNotFoundException();
        }

        return implementation;
    }

    private static Object search(Class<?> rootClassName, HashSet<Class<?>> next)
            throws InjectionCycleException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, ImplementationNotFoundException,
            AmbiguousImplementationException {
        if (createdObjects.containsKey(rootClassName)) {
            return createdObjects.get(rootClassName);
        }

        if (next.contains(rootClassName)) {
            throw new InjectionCycleException();
        }

        next.add(rootClassName);

        Constructor<?> constructorRootClassName = rootClassName.getDeclaredConstructors()[0];
        Class<?>[] constructorRootClassNameDependencies = constructorRootClassName.getParameterTypes();

        if (constructorRootClassNameDependencies.length == 0) {
            Object instanceRootClassName = constructorRootClassName.newInstance();
            createdObjects.put(rootClassName, instanceRootClassName);
            next.remove(rootClassName);
            return constructorRootClassName.newInstance();
        }

        ArrayList<Object> parameters = new ArrayList<>();

        for (Class<?> dependency : constructorRootClassNameDependencies) {
            Class<?> dependencyImplementation = implementationOf(dependency);
            parameters.add(search(dependencyImplementation, next));
        }

        Object instanceRootClassName = constructorRootClassName.newInstance(parameters.toArray());
        createdObjects.put(rootClassName, instanceRootClassName);
        next.remove(rootClassName);
        return instanceRootClassName;
    }
}
