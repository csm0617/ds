package com.csm.ds.class02;

import java.util.Arrays;

public class Code08_SortArrayDistanceLessK {
    /**
     * 最小K排序，时间复杂度n*logK，因为k远小于数组的长度，时间复杂度近似于O（n）
     * @param arr
     * @param k
     */
    public static void sortArrayDistanceLessK(int[] arr, int k) {
        Heap heap = new Heap(k, false);
        for (int i = 0; i < k; i++) {
            heap.offer(arr[i]);
        }
        System.out.println(heap);
        for (int i = k; i < arr.length; i++) {
            arr[i - k] = heap.poll();
            heap.offer(arr[i]);
            System.out.println(heap);
        }
        for (int i = arr.length - k; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 随机生成一个长度>k的数组，并且每个元素的距离在k之内有序（保持几乎有序）
     *
     * @param k
     * @return
     */
    public static int[] creatArrayDistanceLessK(int k) {
        int N = 100;
        int length = (int) (Math.random() * N) + k;
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            int value = (int) (Math.random() * k) + i;
            arr[i] = value;
        }
        return arr;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(creatArrayDistanceLessK(6)));
        int[] arr = creatArrayDistanceLessK(6);
        sortArrayDistanceLessK(arr, 6);
    }

}
