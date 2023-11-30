package com.csm.ds.class02;

import java.util.Arrays;

public class Code08_SortArrayDistanceLessK {
    /**
     * 最小K排序，时间复杂度n*logK，因为k远小于数组的长度，时间复杂度近似于O（n）
     *
     * @param arr
     * @param k
     */
    public static void sortArrayDistanceLessK(int[] arr, int k) {
        Heap heap = new Heap(k, false);
        //先让前K个数最小堆化
//        for (int i = 0; i < k; i++) {
//            heap.offer(arr[i]);
//        }
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.offer(arr[index]);
        }

        System.out.println(heap);
        //因为每个数在k的范围内，近乎有序。所以最小堆化以后堆顶元素就排好序了。
        //每次出队堆顶元素的，并在数组相应位置赋值，再把这入堆新元素，重新堆化
        int i = 0;
        System.out.println(index);
        for (; index < arr.length; index++, i++) {
            arr[i] = heap.poll();
            heap.offer(arr[index]);
//            System.out.println(heap);
        }
        //遍历完数组以后，剩下最大的七个数，依次把堆顶元素出队
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
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
        System.out.println(Arrays.toString(arr));
        sortArrayDistanceLessK(arr, 6);
    }

}
