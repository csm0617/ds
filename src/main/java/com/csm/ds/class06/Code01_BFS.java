package com.csm.ds.class06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {
    //从node出发，进行宽度优先遍历
    public static void bfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> set = new HashSet<>();//用来记录是否有节点访问过了，访问过的节点不能再加入到queue中被再次访问
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            /*
                对遍历的节点进行处理
             */
            System.out.println(cur.value);
            for (GraphNode next : cur.nexts) {//遍历cur的邻接点集
                if (!set.contains(next)) {//只有没被访问过的节点才加入到队列和set中
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
