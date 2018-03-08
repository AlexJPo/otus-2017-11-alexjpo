package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySort {
    private int[] array;
    private List<Thread> threadList = new ArrayList<>();
    private static final int MAX_THREADS = 4;
    private static final int SHORT_ARRAY_SIZE = 8;

    public void setArray(int[] array) {
        this.array = array;
    }

    private boolean isShortArray() {
        return array.length <= SHORT_ARRAY_SIZE;
    }

    public int[] sort() {
        if (isShortArray()) {
            Arrays.sort(array);
            return this.array;
        }

        int[][] arrParts = splitArray();
        for (int i = 0; i < arrParts.length; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> Arrays.sort(arrParts[finalI]));
            thread.setName("Part of array: " + i);
            threadList.add(thread);
        }

        iniTreads();
        return mergeParts(arrParts);
    }

    private void iniTreads() {
        threadList.forEach(Thread::start);
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int[][] splitArray() {
        int step = array.length / MAX_THREADS;
        int[][] arrParts = new int[MAX_THREADS][];

        for (int i = 0; i < MAX_THREADS; i++) {
            int from = i * step;
            int to = (i + 1) * step;
            if (i == MAX_THREADS - 1) {
                to = array.length;
            }
            arrParts[i] = Arrays.copyOfRange(array, from, to);
        }
        return arrParts;
    }

    private int[] mergeParts(int[][] arrParts) {
        int[] result = arrParts[0];
        for (int i = 0; i < arrParts.length-1; i++) {
            result = merge(result, arrParts[i+1]);
        }
        return result;
    }

    public int[] merge(int[] part1, int[] part2) {
        int i1 = 0;
        int i2 = 0;
        int[] result = new int[part1.length + part2.length];

        for (int i = 0; i < result.length; i++) {
            if (i2 >= part1.length || (i1 < part2.length && part2[i1] <= part1[i2])) {
                result[i] = part2[i1];
                i1++;
            } else {
                result[i] = part1[i2];
                i2++;
            }
        }
        return result;
    }

}
