package ru.job4j.io.matrix;

import java.io.FileOutputStream;

public class MatrixSaveInFile {

    public static void main(String[] args) {
        int[][] array = Matrix.multiple(9);
        try (FileOutputStream file = new FileOutputStream("result.txt")) {
            for (int[] arrayElement : array) {
                String text = "";
                for (int element : arrayElement) {
                    text += "  " + element;
                }
                text = text.substring(2);
                text += System.lineSeparator();
                file.write(text.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
