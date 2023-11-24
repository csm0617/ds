package com.csm.ds.class02;

import java.util.Arrays;

/**
 * 归并排序时间复杂度N*(logN)
 */

public class Code01_MergeSort {
    public static void process(int[] arr, int L, int R) {
        if (L == R) {//只有一个元素了，结束递归
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];//辅助数组
        int i = 0;//数组赋值的辅助指针
        //创建两个变量p1,p2指向数组两块区域的开始部分
        int p1 = L;
        int p2 = M + 1;
        //只要p1和p2没出区域的右边界
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];//p1和p2指向的位置比较谁更小，小的就赋值到新数组中，并且指针++
        }
        //其中一个数组越界了，就把另一个数组中剩余的元素复制到辅助数组中
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //最后把辅助数组的值覆盖到原数组中
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];//这一步很关键
        }
        System.out.println(Arrays.toString(help));
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return;
        }
            process(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 9, 3, 6, 8, 1, 7, 33, 2, 5, 9, 12, 113, 16};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
