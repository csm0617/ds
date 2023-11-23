package com.csm.ds.class01;

import java.util.Arrays;

public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        /*
            异或性质：
            1） 0 ^ N == N     N ^ N ==0
            2)  交换律  结合律
                a^b == b^a    (a^b)^c==a^(b^c)
         */
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];  //arr[j] = (arr[i]^arr[j])^arr[j] == arr[i]^(arr[j]^arr[j]) == arr[i]
        arr[i] = arr[j] ^ arr[i];  //arr[i] = arr[i]^(arr[i]^arr[j]) == (arr[i]^arr[i])^arr[j] == arr[j]
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 5, 9, 10, 8, 7, 15};
        System.out.println(Arrays.toString(arr));
    }
}
