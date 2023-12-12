package com.csm.ds.class05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树的最大宽度和最大深度
 */
public class Code03_TreeMaxWidthAndDepth {

    public static Map<String, Integer> getMaxWidthAndDepth(TreeNode root) {
        /*
            定义一个map来返回最大深度和最大宽度
         */
        Map<String, Integer> map = new HashMap<>();
        int maxWidth = 0;
        int maxDepth = 0;
        if (root == null) {
            map.put("maxWidth", maxWidth);
            map.put("maxDepth", maxDepth);
            return map;
        }
        /*
           定义一个辅助队列来存放每一层的节点
         */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//特别注意，要把size提出来，不然在queue.offer的时候更新queue的值了，size记录的应该是下层的值
            //每层开始前比较最大层数
            maxWidth = Math.max(size, maxWidth);
            for (int i = 0; i < size; i++) {//控制将每层节点的孩子节点加入queue
                TreeNode poll = queue.poll();
                System.out.printf("%-4d", poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            //结束一层换行,深度++
            System.out.println();
            maxDepth++;
        }
        map.put("maxDepth", maxDepth);
        map.put("maxWidth", maxWidth);
        return map;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        6,
                        3,
                        7),
                1,
                new TreeNode(
                        4,
                        2,
                        5)
        );
        System.out.println(getMaxWidthAndDepth(root));
    }

}
