package com.csm.ds.class05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树判满
 */
public class Code06_isFBT {

    /**
     * 满二叉树除了最后一层叶子节点，其他节点都必须有左右孩子
     * 所以节点的数目应该等于 2^n - 1 ,n是层数。
     * 疑问：只判断节点的数量，而不判断是否右左孩子就能确定是满二叉树吗
     * 答：可以，因为满二叉树的节点全部填满了不会多也不会少，
     * 如果一个二叉树树的节点数量和满二叉树的节点数目一样，
     * 但又不是满二叉树，那么他的高度一定比这棵完全二叉高。
     * 同理如果高度一样，那么节点数目一定会比满二叉树少
     */
    public static int count = 0;//统计节点的个数，因为是递归，所以只能定义一个全局变量

    /**
     * 求二叉树的最大高度
     *
     * @param node 节点
     * @return maxHeight
     */
    public static int getMaxHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        count++;
        int left = getMaxHeight(node.left);
        int right = getMaxHeight(node.right);
        return 1 + Math.max(left, right);
    }

    /**
     * 二叉树判满
     * 方法一：
     * 深度优先遍历求最大高度和节点的数目
     *
     * @param root
     * @return
     */
    public static boolean isFBT1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int height = getMaxHeight(root);
//        System.out.println("count:" + count);
//        System.out.println((1 << height) - 1);
        int sum = (1 << height) - 1;
        System.out.println("FBT1: \n count: " + count + " sum: " + sum);
        return count == (1 << height) - 1;
    }

    /**
     * 二叉树判满
     * 方法二:广度（宽度）优先遍历
     * 统计最大高度和节点的数目
     *
     * @param root
     * @return
     */
    public static boolean isFBT2(TreeNode root) {
        if (root == null) {
            return false;
        }
        int maxDepth = 0;//最大深度（高度）
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;//每次累加每层节点的数目
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            maxDepth++;//结束一层，高度就++
        }
        int sum = (1 << maxDepth) - 1;
        System.out.println("FBT2: \n count: " + count + " sum: " + sum);
        return (1 << maxDepth) - 1 == count;
    }

    /**
     * 递归树形dp求节点的最大高度和节点数量
     * @param root
     * @return
     */

    public static boolean isFBT3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Info info = process(root);
        return info.nodes == (1 << info.height - 1);
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }

    /**
     * 返回信息
     */

    public static class Info {
        int height;
        int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        1,
                        2,
                        3),
                4,
                new TreeNode(
                        5,
                        6,
                        7)
        );
        System.out.println(isFBT1(root));
        System.out.println(isFBT2(root));
    }

}
