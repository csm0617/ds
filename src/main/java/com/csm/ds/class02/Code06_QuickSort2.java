package com.csm.ds.class02;

/**
 * 快排优化：每次递归把随机等于x的都排好
 */
public class Code06_QuickSort2 {
    public static int first;
    public static int last;
    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = nums[l + (int) (Math.random() * (r - l + 1))];
        partition(nums, l, r, x);
        int left = first;
        int right = last;
        quickSort(nums, l, left - 1);
        quickSort(nums, right + 1, r);
    }

    public static void partition(int[] arr, int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (arr[i] < x) {
                swap(arr, first++, i++);
            } else if (arr[i] == x) {
                i++;
            } else if (arr[i] > x) {
                swap(arr, last--, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
