package ru.spbau.solikov.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {
    }

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(s -> {
            try {
                return Files.lines(Paths.get(s));
            } catch (IOException e) {
                return Stream.empty();
            }
        }).filter(s -> s.contains(sequence)).collect(Collectors.toList());

    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random random = new Random();
        return Stream.generate(() -> Math.sqrt(Math.pow(random.nextDouble() - 0.5, 2)
                + Math.pow(random.nextDouble() - 0.5, 2)) < 0.5 ? 1 : 0)
                .limit(20000000).mapToInt(SecondPartTasks::applyAsInt).average().orElse(0);
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream().max(Comparator.comparing(stringListEntry ->
                stringListEntry.getValue().stream().mapToInt(String::length).sum()))
                .map(Map.Entry::getKey).orElse("Nothing");
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(stringIntegerMap -> stringIntegerMap.entrySet()
                .stream()).collect(Collectors.groupingBy(Map.Entry::getKey,
                Collectors.summingInt(Map.Entry::getValue)));
    }

    private static int applyAsInt(Integer x) {
        return x;
    }
}