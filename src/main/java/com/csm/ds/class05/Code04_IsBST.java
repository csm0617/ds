package com.csm.ds.class05;

import java.util.LinkedList;

/**
 * 判断一颗树是不是搜索二叉树
 * 三种解题思路：
 * 1.二叉树的中序遍历得到的一定是升序序列
 * 2.上下限法：得到每个节点的上下限，并传递给左右孩子，判断孩子的val是否满足上下限
 * 3.递归中判断左右子树
 */
public class Code04_IsBST {
    /**
     * 中序遍历非递归
     *
     * @param head
     * @return
     */
    public static boolean isBST1(TreeNode head) {
        if (head == null) {
            return true;
        }
        int prev = Integer.MIN_VALUE;//存放前一个节点的值
        LinkedList<TreeNode> stack = new LinkedList<>(); //记录走过的路径
        TreeNode cur = head;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.println(pop.val);
                if (pop.val >= prev) {
                    prev = pop.val;
                } else {
                    return false;
                }
                cur = pop.right;
            }
        }
        return true;
    }

    public static int preValue = Integer.MIN_VALUE;

    /**
     * 中序遍历递归
     *
     * @param node
     * @return
     */
    public static boolean isBST2(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean isLeftBST = isBST2(node.left);
        if (!isLeftBST) {
            return false;
        }
        if (preValue < node.val) {
            preValue = node.val;
        } else {
            return false;
        }
        return isBST2(node.right);
    }

    /**
     * 上下限递归法
     *
     * @param node
     * @return
     */
    public static boolean isBST3(TreeNode node, int min, int max) {
        //max和min是父节点携带的上下限
        if (node == null) {
            return true;
        }
        //判断节点
        if (node.val <= min || node.val >= max) {
            return false;
        }
        //判断左子树
        boolean isLeftBST = isBST3(node.left, min, node.val);
        //左子树不满足了就可以返回了
        if (!isLeftBST) {
            return false;
        }
        //判断右子树
        return isBST3(node.right, node.val, max);
    }

    /**
     * 树形dp
     *
     * @param root
     * @return
     */
    public static boolean isBST4(TreeNode root) {
        return process(root).isBST;

    }

    /**
     * 递归返回左右子树的信息，交给父节点处理
     * left.max < val < right.min
     *
     * @param node
     * @return
     */
    public static ReturnData process(TreeNode node) {
        if (node == null) {
            return null;
        }
        ReturnData leftData = process(node.left);
        ReturnData rightData = process(node.right);
        int min = node.val;//包含当前节点整个子树的最小值
        int max = node.val;//包含当前节点整个子树的最大值
        /*
            更新当前节点的最大，最小值
         */
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.min(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.min(max, rightData.max);
        }
        boolean isBST = true;//假设一开始就是ture
        if (leftData != null && (!leftData.isBST || leftData.max >= node.val)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || rightData.min <= node.val)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);
    }

    /**
     * 构造的每一个非空node需要返回的信息给父节点
     */
    public static class ReturnData {
        boolean isBST;
        int min;//当前节点整个子树的最小值
        int max;//当前节点整个子树的最大值

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
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
                        1,
                        7)
        );
        System.out.println(isBST1(root));
        System.out.println(isBST2(root));
        System.out.println(isBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
//        System.out.println(isBST1(root) == isBST2(root)==isBST3(root,min,max));
    }


}
