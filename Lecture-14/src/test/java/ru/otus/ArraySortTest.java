package ru.otus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArraySortTest {
    /**
     * Написать приложение, которое сортирует массив чисел в 4 потоках с использованием библиотеки или без нее
     * */

    private ArraySort arraySort;

    @Before
    public void beforeTest() {
        arraySort = new ArraySort();
    }

    @Test
    public void shouldSortArray() {
        int[] arr = new int[] {4,3,1,2,7,9,6,5};
        int[] result = new int[] {1,2,3,4,5,6,7,9};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldMergeArrays() {
        int[] arr1 = new int[] {1,2,3};
        int[] arr2 = new int[] {4,5,6};
        int[] result = new int[] {1, 2, 3, 4, 5, 6};

        int[] arr3 = arraySort.merge(arr1, arr2);

        assertEquals(arr3.length, result.length);
        for (int i = 0; i < arr3.length; i++) {
            assertEquals(arr3[i], result[i]);
        }
    }
}