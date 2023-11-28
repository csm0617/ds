package com.csm.ds.class02;

import java.util.Arrays;

/**
 * 经典快排：每次递归只排好一个随机的x
 */
public class Code05_QuickSort {
    static int[] arr = {1, 2, 4, 5, 6, 9, 8, 7, 10, 11, 6, 8, 5, 6, 7, 9};

    //经典随机快排
    public static void quickSort(int l, int r) {
        //l==r，只有一个数，不用排
        //l>r范围不存在，不用管
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        int mid = partition(l, r, x);
        System.out.println("X:" + x + " mid:" + mid + "  r:" + r);
        quickSort(l, mid - 1);
        quickSort(mid + 1, r);
    }

    //已知arr[l......r]范围上一定有x这个值;
    //划分数组<=x在左边，>x放在右边，并确保划分完成后<=x区域最后一个数组是x
    public static int partition(int l, int r, int x) {
        //a：arr[l...a-1]范围是<=x的区域
        //xi：记录在<=x的区域的任何一个x的位置，哪一个都可以
        int a = l, xi = 0;
        for (int i = l; i < r; i++) {//遍历整个区间
            if (arr[i] <= x) {//<=给出的数就交换
                swap(i, a);
                if (arr[a] == x) {//==就更新xi
                    xi = a;
                }
                a++;
            }
        }
        swap(xi, a - 1);//让a-1位置（<=x区间最后一个位置）为x
        System.out.println(Arrays.toString(arr));
        //<=x                >x
        //l.......a-1     a.......r
        return a - 1;//返回x的位置
    }
    public static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        quickSort(0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
