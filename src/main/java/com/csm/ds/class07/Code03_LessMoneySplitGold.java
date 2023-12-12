package com.csm.ds.class07;

import java.util.PriorityQueue;

/**
 * 花费最少的钱分割金条（贪心）
 * 假设分割金条的长度和花费的钱相等，请给出花费最少的钱分割金条
 * 给定一个数组{10，20，30} 说明金条总长度60 可以先分割成 10 50 ，再把50分割成20 30共花费60 + 50 == 110
 * 也可以分割成 30 30 再把其中一个30分割成10 20 共花费60 + 30 = 90
 */
public class Code03_LessMoneySplitGold {
    public static int lessMoney(int[] arr) {
        //建立小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        //把金条的长度加入小根堆
        for (int i : arr) {
            priorityQueue.add(i);
        }
        int cur = 0;
        int sum = 0;
        /*
         * 从小顶堆中不断选出两个数累加，把累加后的结果重新放入到小顶堆中（哈夫曼编码问题）
         */
        while (priorityQueue.size() > 1) {
            cur = priorityQueue.poll() + priorityQueue.poll();
            sum += cur;
            priorityQueue.add(cur);
        }
        return sum;

    }

    public static void main(String[] args) {

    }
}
