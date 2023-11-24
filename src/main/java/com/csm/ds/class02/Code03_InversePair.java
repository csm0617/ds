package com.csm.ds.class02;

/**
 * 归并排序的拓展2：求逆序对
 * 在一个数组中，左边的数比右边的数大，则两个数构成一个逆序对,请打印所有逆序对
 */
public class Code03_InversePair {
    public static void InversePair(int[] nums) {

    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + (R - L) >> 1;
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }


    public static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
    }

}
