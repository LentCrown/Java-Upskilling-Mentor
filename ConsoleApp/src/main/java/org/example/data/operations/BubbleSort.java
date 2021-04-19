package org.example.data.operations;

public class BubbleSort {

    /**
     * @param <T> class that must implement Comparable interface and override compareTo method;
     * @param arr Array of objects
     * @param index1 Index1 from pair of indexes which values we should swap
     * @param index2 Index2 from pair of indexes which values we should swap
     */
    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * @param <T> class that must implement Comparable interface and override compareTo method;
     * @param arr2sort array to sort;
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr2sort) {
        if (arr2sort==null)
            throw new IllegalArgumentException("Array to sort must not be null");
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i< arr2sort.length-1; i++) {
                if (arr2sort[i].compareTo(arr2sort[i + 1])>0)  {
                    swap(arr2sort,i,i+1);
                    sorted = false;
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array == null) {
            return;
        }
        int n = array.length;
        int mid = n / 2;
        if (n < 2) {
            return;
        }

        //TODO: solve object cast problem
        T[] left_array = (T[])new Object[mid];
        T[] right_array = (T[])new Object[n - mid];

        System.arraycopy(array, 0, left_array, 0, mid);
        if (n - mid >= 0) System.arraycopy(array, mid, right_array, 0, n - mid);
        mergeSort(left_array);
        mergeSort(right_array);

        merge(array, left_array, right_array);
    }

    private static <T extends Comparable<T>> void merge(T[] result, T[] left_arr, T[] right_arr) {
        int left = left_arr.length;
        int right = right_arr.length;
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (left_arr[i].compareTo(right_arr[j])<=0) {
                result[k++] = left_arr[i++];
            }
            else {
                result[k++] = right_arr[j++];
            }
        }
        while (i < left) {
            result[k++] = left_arr[i++];
        }
        while (j < right) {
            result[k++] = right_arr[j++];
        }
    }
}