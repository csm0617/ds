package com.csm.ds.class05;

import java.util.LinkedList;

/**
 * 判断是不是完全二叉树：
 * 1）任意一个节点，有右孩子，没有左孩子 false
 * 2) 在1）满足的条件下，如果遇到了第一个左右孩子不双全的节点，那么后面的节点都是叶子节点
 */

public class Code05_IsCBT {
    public static boolean isCBT(TreeNode head) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode l;
        TreeNode r;
        boolean leaf = false;//遇到第一个左右孩子不双全的节点了，打开开关，后面的节点必须都是叶子节点
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode pop = queue.poll();
            l = pop.left;
            r = pop.right;
            if (
                    (leaf && (l != null || r != null))//如果遇到了左右孩子不双全的节点之后，又发现当前节点有孩子
                            ||
                            (l == null && r != null)//节点有右孩子但是没有左孩子，也不满足，返回false
            ) {
                return false;
            }

            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;//遇到第一个左右孩子不双全的节点了，leaf开启
            }
        }
        return true;
    }
}
