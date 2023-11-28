package com.csm.ds.class02;

/*
    leetcode75:颜色分类（荷兰国旗）
 */
public class Code04_NetherlandsFlag {
    public static int first;
    public static int last;

    public void sortColors(int[] nums) {
        partition(nums,0,nums.length-1,1);
    }

    public void partition(int[] nums, int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (nums[i] == x) {
                i++;
            } else if (nums[i] < x) {
                swap(nums, i++, first++);
            } else {
                swap(nums, i, last--);
            }
        }

    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
