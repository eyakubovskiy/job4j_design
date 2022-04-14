package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        String template = "Текущее число %d является %s ";
        try (FileInputStream file = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = file.read()) != -1) {
                text.append((char) read);
            }
            String[] array = text.toString().split(System.lineSeparator());
            for (String element : array) {
                int valInt = Integer.parseInt(element);
                String valString = (valInt % 2) == 0 ? "четным" : "нечетным";
                String info = String.format(template, valInt, valString);
                System.out.println(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}