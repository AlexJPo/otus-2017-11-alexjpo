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

    @Test
    public void shouldNotSortVeryShortArray() {
        int[] arr = new int[] {4};
        int[] result = new int[] {4};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldSortShortArray() {
        int[] arr = new int[] {4,3};
        int[] result = new int[] {3,4};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldSortEvenArray() {
        int[] arr = new int[] {4,3,1,2};
        int[] result = new int[] {1,2,3,4};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldSortOddArray() {
        int[] arr = new int[] {4,3,1,2,7};
        int[] result = new int[] {1,2,3,4,7};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldBigEvenArray() {
        int[] arr = new int[] {4,3,1,2,9,7,15,10,14,20,11,6};
        int[] result = new int[] {1,2,3,4,6,7,9,10,11,14,15,20};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldBigOddArray() {
        int[] arr = new int[] {4,3,1,2,9,7,15,10,14,20,11,6,88};
        int[] result = new int[] {1,2,3,4,6,7,9,10,11,14,15,20,88};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldSortArrayOfNineElements() {
        int[] arr = new int[]{4, 10, 3, 1, 2, 7, 9, 6, 5};
        int[] result = new int[]{1, 2, 3, 4, 5, 6, 7, 9, 10};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldSplitTenElementsArray() {
        int[] arr = new int[] {4,10,3,1,2,7,9,6,5,11};
        int[] result = new int[] {1,2,3,4,5,6,7,9,10,11};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }

    @Test
    public void shouldSplit12ElementsArray() {
        int[] arr = new int[] {4,10,3,1,2,7,9,6,5,11,8,12};
        int[] result = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};

        arraySort = new ArraySort();
        arraySort.setArray(arr);
        int[] sortResult = arraySort.sort();

        assertArrayEquals(result, sortResult);
    }
}