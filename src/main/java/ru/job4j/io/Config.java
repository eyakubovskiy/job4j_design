package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(m -> !m.startsWith("#") && m.trim().length() > 0)
                    .forEach(
                            x -> {
                                String[] splited = x.split("=");
                                boolean correctLength = splited.length > 1;
                                boolean keyValueFilled = false;
                                if (correctLength) {
                                    String key = splited[0].trim();
                                    String value = splited[1].trim();
                                    keyValueFilled = key.length() > 0 && value.length() > 0;
                                    if (keyValueFilled) {
                                        values.put(key, value);
                                    }
                                }
                                if (!correctLength || !keyValueFilled) {
                                    throw new IllegalArgumentException();
                                }
                            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
