package com.csm.ds.class01;

import java.util.Arrays;

public class Code01_SelectionSort {
    /**
     * 选择排序：
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {//i ~ N-1
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {//i+1 ~ N-1
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (minIndex != i) {//minIndex改变了，才交换
                swap(arr, i, minIndex);
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 5, 9, 10, 8, 7, 15};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
