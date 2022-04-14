package ru.job4j.io.matrix;

public class Matrix {
    public static int[][] multiple(int size) {

        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }

        return array;
    }
}
