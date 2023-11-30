package com.csm.ds.class02;

import java.util.Arrays;

public class Heap {
    int size;
    int[] array;
    boolean max;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Heap:[ ");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(array[i] + "  ");
            } else {
                sb.append(array[i] + "");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public Heap(int capacity, boolean max) {
        this.array = new int[capacity];
        this.max = max;
    }

    public Heap(int[] array, boolean max) {
        this.array = array;
        this.size = array.length;
        this.max = max;
        this.heapify();//建堆
        System.out.println(Arrays.toString(array));
    }


    public void heapify() {//从最后一个非叶子节点开始逐个下潜
        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
            down(i);
        }
    }

    public boolean offer(int offered) {
        if (isFull()) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    private boolean isFull() {
        return size == array.length;
    }

    public void up(int offered) {
        int addIndex = size;
        while (addIndex > 0) {
            int parent = (addIndex - 1) / 2;
            boolean cmp = max ? array[parent] < offered : array[parent] > offered;
            if (cmp) {
                array[addIndex] = array[parent];
                addIndex = parent;
            } else {
                break;
            }

        }
        array[addIndex] = offered;
    }

    public int poll() {
        illegalIndex();
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    public int peek() {
        illegalIndex();
        return array[0];
    }

    public void illegalIndex() {
        if (isEmpty()) {
            throw new IllegalArgumentException("堆已经空了,返回0位置不合法");
        }
    }

    private void illegalIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(index + "位置索引不合法");
        }
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced
     * @return
     */
    public boolean replace(int replaced) {
        if (isEmpty()) {
            return false;
        }
        array[0] = replaced;
        down(0);
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void down(int parent) {
        int maxOrMin = parent;
        int leftChild = parent * 2 + 1;
        int rightChild = leftChild + 1;
        if (leftChild < size && (max ? array[leftChild] > array[maxOrMin] : array[leftChild] < array[maxOrMin])) {
            maxOrMin = leftChild;
        }
        if (rightChild < size && (max ? array[rightChild] > array[maxOrMin] : array[rightChild] < array[maxOrMin])) {
            maxOrMin = rightChild;
        }
        if (maxOrMin != parent) {
            swap(maxOrMin, parent);
            down(maxOrMin);
        }

    }


    private void swap(int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    public static void main(String[] args) {
        Heap heap = new Heap(new int[]{2,2,6,8,6,9,2,3,5}, false);
    }
}
