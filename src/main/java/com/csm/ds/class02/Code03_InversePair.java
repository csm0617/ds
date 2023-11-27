package com.csm.ds.class02;

/**
 * 归并排序的拓展2：求逆序对
 * 在一个数组中，左边的数比右边的数大，则两个数构成一个逆序对,请打印所有逆序对
 */
public class Code03_InversePair {
    public static void InversePair(int[] nums) {
        process(nums, 0, nums.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }


    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] > arr[p2]) {
                System.out.println("(" + arr[p1] + "," + arr[p2] + ")");//在比较完合并的的时候，可以做很多事情，比如打印逆序对，统计小和
            }
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //拷贝回原数组
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 9, 3, 6, 8, 1, 7, 33, 2, 5, 9, 12, 113, 16};
        InversePair(arr);
    }

}
