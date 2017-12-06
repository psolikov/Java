package ru.spbau.solikov.streams;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static ru.spbau.solikov.streams.SecondPartTasks.findPrinter;
import static ru.spbau.solikov.streams.SecondPartTasks.findQuotes;
import static ru.spbau.solikov.streams.SecondPartTasks.piDividedBy4;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Well, the clock says it's time to close now ");
        lines.add("Street lights share their hollow glow ");
        lines.add("I've got you deep in the heart of me.");
        lines.add("Here comes the story of the Hurricane");
        lines.add("The man the authorities came to blame");
        lines.add("The champion of the world.");
        ArrayList<String> src = new ArrayList<>();
        src.add("src/test/resources/1");
        src.add("src/test/resources/2");
        src.add("src/test/resources/3");
        assertEquals(lines, findQuotes(src, "the"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(piDividedBy4(), Math.PI / 4, 0.0001);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> printers = new HashMap<>();
        ArrayList<String> write = new ArrayList<>(Arrays.asList("112131231", "dwqdqwdk", "12q k3pqwd iqndn"));
        printers.put("1", write);
        write = new ArrayList<>(Arrays.asList("1", "2", "3", "hello"));
        printers.put("2", write);
        write = new ArrayList<>(Collections.singletonList("An Englishman, an Irishman and a Scotsman walk into a bar. The bartender turns to them, takes one look, and says, \"What is this — some kind of joke?\""));
        printers.put("3", write);
        assertEquals("3", findPrinter(printers));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> order1 = new HashMap<>();
        Map<String, Integer> order2 = new HashMap<>();
        Map<String, Integer> order3 = new HashMap<>();
        order1.put("die Zitrone", 10);
        order1.put("die Tomate", 25);
        order1.put("die Pizza", 2);
        order1.put("die Kartoffel", 40);
        order2.put("die Kartoffel", 30);
        order2.put("die Melone", 7);
        order2.put("der Apfelsaft", 2);
        order2.put("der Rotwein", 1);
        order2.put("die Tomate", 3);
        order3.put("die Pizza", 1);
        order3.put("die Pommes frites", 1);
        order3.put("das Bier", 2);
        Map<String, Integer> overall = new HashMap<>();
        overall.put("die Zitrone", 10);
        overall.put("die Melone", 7);
        overall.put("der Apfelsaft", 2);
        overall.put("der Rotwein", 1);
        overall.put("die Pommes frites", 1);
        overall.put("das Bier", 2);
        overall.put("die Pizza", 3);
        overall.put("die Tomate", 28);
        overall.put("die Kartoffel", 70);
        assertEquals(SecondPartTasks.calculateGlobalOrder(
                new ArrayList<>(Arrays.asList(order1, order2, order3))), overall);
    }
}