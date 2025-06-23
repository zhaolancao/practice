package com.lancao.practice.algorithms.searching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.System.currentTimeMillis;

class SortAndSearchTest {

    @Test
    void binarySearch() {
        int[] elements = new int[]{1, 3, 5, 7, 8, 9, 10};
        int target = 9;
        int falseTarget = 2;
        long start = currentTimeMillis();
        Assertions.assertEquals(5, SortAndSearch.binarySearch(elements, target));
        Assertions.assertEquals(-1, SortAndSearch.binarySearch(elements, falseTarget));
        System.out.println("binarySearch cost: " + (currentTimeMillis() - start) + "ms");
    }

    @Test
    void mergeSort() {
        long start = currentTimeMillis();
        int[] elements = new int[]{9, 2, 0, 2, 5, 0, 6, 1, 3, 7};
        int[] result = SortAndSearch.mergeSort(elements);
        Arrays.stream(result).forEach(ele -> System.out.print(ele + " "));
        System.out.println();
        System.out.println("mergeSort cost: " + (currentTimeMillis() - start) + "ms");
    }

    @Test
    void whatever() {
        System.out.println((int) 'a');
        System.out.println((int) 'b');
        System.out.println((int) ('z' - 'a'));
    }

    @Test
    void quickSort() {
        long start = currentTimeMillis();
        int[] elements = new int[]{9, 2, 0, 2, 5, 0, 6, 1, 3, 7};
        int[] result = SortAndSearch.quickSort(elements);
        Arrays.stream(result).forEach(ele -> System.out.print(ele + " "));
        System.out.println();
        System.out.println("quickSort cost: " + (currentTimeMillis() - start) + "ms");
    }

    @Test
    void maxNeighbourDistance() {
        long start = currentTimeMillis();
        Assertions.assertEquals(2, SortAndSearch.maxNeighbourDistance(new int[]{9, 2, 0, 2, 5, 0, 6, 1, 3, 7}));
        Assertions.assertEquals(6, SortAndSearch.maxNeighbourDistance(new int[]{12, 16, 13, 14, 20, 19, 6}));
        Assertions.assertEquals(10, SortAndSearch.maxNeighbourDistance(new int[]{12, 16, 13, 14, 20, 19, 6, 30}));
        System.out.println("maxNeighbourDistance cost: " + (currentTimeMillis() - start) + "ms");
    }
}