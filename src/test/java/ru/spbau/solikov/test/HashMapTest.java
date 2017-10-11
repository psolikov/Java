package ru.spbau.solikov.test;

import ru.spbau.solikov.src.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        System.out.println(hashMap.put("1","2"));
        System.out.println(hashMap.get("1"));
    }
}
