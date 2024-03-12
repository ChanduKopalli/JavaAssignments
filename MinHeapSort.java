package com.companyname.stringquestions;

import java.util.*;
public class MinHeapSort {
    public static <T extends Comparable<T>> void heapSort(T[] array) {
        int n = array.length;

        // Build min heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Call heapify on the reduced heap
            heapify(array, i, 0);
        }
    }
    private static <T extends Comparable<T>> void heapify(T[] array, int n, int i) {
        int smallest = i; // Initialize smallest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is smaller than root
        if (left < n && array[left].compareTo(array[smallest]) < 0) {
            smallest = left;
        }

        // If right child is smaller than smallest so far
        if (right < n && array[right].compareTo(array[smallest]) < 0) {
            smallest = right;
        }

        // If smallest is not root
        if (smallest != i) {
            // Swap root with smallest
            T temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(array, n, smallest);
        }
    }
    public static void main(String[] args) {
        // Test the heap sort method
        Integer[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array: " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
