package com.csm.ds.class07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定两个数组 int[] profits 表示项目的净利润 ，int[] capital 表示项目的花费
 * 给一个整型k表示可以做的项目数
 * 给一个启动资金w去做项目
 * 请返回做k个项目后的资金w
 */
public class Code05_IPO {
    public static class Project {
        int p;//利润
        int c;//花费

        public Project(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    //比较器：返回正数，第二个参数（小）排前面，返回负数第一个参数（小）排前面

    /**
     * 最小花费比较器
     */
    public static class MinCostComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.c - o2.c;
        }
    }

    /**
     * 最大利润比较器
     */
    public static class MaxProfitComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Project> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        //把所有项目都放到最小花费组织的池中锁住
        for (int i = 0; i < Profits.length; i++) {
            minCostQueue.add(new Project(Profits[i], Capital[i]));
        }
        //进行k个项目
        for (int i = 0; i < k; i++) {
            //能力所及的项目全部结锁，放入最大利润的池中
            while (!minCostQueue.isEmpty() && minCostQueue.peek().c <= W) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            //解锁的项目一定是能做的项目，如果没有能做的项目了（做不完k个），就直接返回
            if (maxProfitQueue.isEmpty()) {
                return W;
            }
            //否则优先做利润最大项目，累加本金
            W += maxProfitQueue.poll().p;
        }
        return W;
    }


}
