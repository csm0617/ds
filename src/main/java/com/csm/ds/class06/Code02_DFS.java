package com.csm.ds.class06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class Code02_DFS {

    /**
     * 思路：1.访问当前节点cur，并压栈，hashset中记录，处理当前节点
     *      2.遍历当前的所有邻接节点，如果没有在hashset中，就把当前节点cur和邻接next压栈，处理，并记录,break
     *      3.当栈不为空弹出的next作为新的cur 继续循环，
     *
     * 为什么可以做到深度优先遍历：
     *  因为每次取出的没访问过的next，压栈，然后break,继续以next作为cur向后探索，一直往后走除非自己没有邻接节点，或者邻接节点被访问过了
     *  栈才会弹出，也就是往回走，看看还有没有没走过的路，有就继续向前探索
     *
     * @param node
     */
    public static void dfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Stack<GraphNode> stack = new Stack<>();
        HashSet<GraphNode> set = new HashSet<>();//记录访问过的节点
        stack.push(node);
        set.add(node);
        /*
            deal with node
         */
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            for (GraphNode next : cur.nexts) {
                if (!set.contains(next)) {//如果邻接节点中有没访问过的
                    stack.push(cur);//把当刚刚弹出的前节点入栈
                    stack.push(next);//再把没访问过的节点入栈
                    set.add(next);//加入set中标记为访问过
                    /*
                        处理节点
                     */
                    System.out.println(next.value);
                    break;//很重要，只要找到一个没访问的邻接节点就break;
                }
            }
        }
    }
}
