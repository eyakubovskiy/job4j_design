package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = List.of();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            result = br.lines()
                    .filter(m -> "404".equals(m.split(" ")[8]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}