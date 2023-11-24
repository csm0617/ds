package com.csm.ds.class01;

public class Code08_GetMax {

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Integer.max(leftMax, rightMax);
    }

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 1, 2, 9, 8};
        System.out.println(getMax(arr));
    }
}
