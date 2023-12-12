package com.csm.ds.class03;

import java.util.Arrays;

/**
 * 基数排序
 */
public class Code02_RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr 数组
     * @return 数组中最大的数，有几个十进制位
     */
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        //遍历
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            //统计位数
            res++;
            max = max / 10;
        }
        return res;
    }

    /**
     * 求一个十进制数从右往左数的第d位
     *
     * @param num 数
     * @param d   第d位
     * @return 十进制数从右往左数的第d位
     */
    public static int getDigit(int num, int d) {
        int N = 1;
        for (int i = 2; i <= d; i++) {
            N *= 10;
        }
        return (num / N) % 10;
    }

    /**
     * 基数排序
     *
     * @param arr   参与排序数组
     * @param L     范围L
     * @param R     范围R
     * @param digit 最大的数的有几个十进制位数（决定几轮循环）
     */
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;//排序的基数 0 ~ 9
        int i, j;
        //辅助空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) {//最大的数有多少个十进制位，就进桶出桶几次
            //count[i]是在当前位（d）位，是0~i的数字有多少个
            //count[0]是在当前位（d）位，是0的数字有多少个
            //count[1]是在当前位（d）位，是（0，1）的数字有多少个
            //count[2]是在当前位（d）位，是（0，1，2）的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];//统计每个位置的前缀和，计算除每个数字块在数组中的范围
            }
            //从右至左遍历，d位分类后的数依次放入辅助数组的位置count[j] - 1的位置，因为从左到右是先进先出
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            //把每次排序后的结果放回到数组中
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    public static int[] randomArray(int N, int V) {
        int len = (int) (Math.random() * N);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * V + 1);
        }
        return arr;
    }

    public static void main(String[] args) {

        int Times = (int) (Math.random() * 50000 + 1);
        for (int i = 0; i < Times; i++) {
            radixSort(randomArray(100, 1000));
        }

    }

}
