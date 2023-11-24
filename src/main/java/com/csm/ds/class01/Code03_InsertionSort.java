package com.csm.ds.class01;

import java.util.Arrays;

public class Code03_InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        //0~0位置已经有序了
        //0~i位置想有序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 5, 9, 10, 8, 7, 15};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
