package com.csm.ds.class02;

import java.util.Arrays;

public class Code07_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //建立大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        System.out.println(Arrays.toString(arr));
        //堆排序
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);//把0和最后一个位置交换
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);//交换后堆化
            swap(arr, 0, --heapSize);//heapSize>0就一直交换，堆化

        }
        System.out.println(Arrays.toString(arr));
    }

    public static void heapInsert(int[] arr, int index) {
        int parent = (index - 1) / 2;
        while (arr[index] > arr[parent]) {
            swap(arr, index, parent);
            index = parent;
            parent = (parent - 1) / 2;
        }
    }

    //初始化建堆
    public static void heapify(int[] arr, int index, int heapSize) {
        //左孩子
        int left = index * 2 + 1;
        while (left < heapSize) {//还有孩子的时候
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//左右孩子中最大的下标
            largest = arr[largest] > arr[index] ? largest : index;
            if (arr[largest] == arr[index]) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        heapSort(new int[]{1, 5, 4, 6, 98, 4, 8, 3, 7});
    }

}
