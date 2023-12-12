package com.csm.ds.class05;

import java.util.LinkedList;
import java.util.Stack;

public class Code02_BinaryTreeTraversal {


    /*
        对于二叉树的递归遍历注意   递归序  打印就可以了
     */

    /**
     * 中序遍历递归 (左 - 根 - 右)
     *
     * @param node
     */
    public static void inOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRecur(node.left);
        System.out.printf("%-4d", node.val);
        inOrderRecur(node.right);

    }

    /**
     * 前序遍历递归（根 - 左 - 右）
     *
     * @param node
     */
    public static void preOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.printf("%-4d", node.val);
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    /**
     * 后序遍历递归（左 - 右 - 根）
     *
     * @param node
     */
    public static void postOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderRecur(node.left);
        postOrderRecur(node.right);
        colorPrintln("" + node.val, 34);
    }

    /*
        二叉树的非递归遍历
     */

    /**
     * 中序遍历非递归
     *
     * @param root 根节点
     */
    public static void inOrderUnRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println("in-order print:");
        LinkedList<TreeNode> stack = new LinkedList<>();//存放节点
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                colorPrintln(""+pop.val,31);//中序遍历打印
                if (pop.right != null) {
                    cur = pop.right;
                }
            }
        }
    }

    /**
     * 前序遍历（非递归）
     *
     * @param root
     */
    public static void preOrderUnRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println("pre-order print:");
        LinkedList<TreeNode> stack = new LinkedList<>();//存放节点
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                colorPrintln(""+cur.val,32);//前序遍历打印
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                if (pop.right != null) {
                    cur = pop.right;
                }
            }
        }
    }

    /**
     * 后序遍历（非递归）
     *
     * @param root
     */
    public static void postOrderUnRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println("post-order print:");
        LinkedList<TreeNode> stack = new LinkedList<>();//存放节点
        TreeNode cur = root;
        TreeNode pop = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                //先不着急出栈
                //有右子树先处理右子树，没有右子树再弹栈
                TreeNode peek = stack.peek();
                if (peek.right == null) {
                    pop = stack.pop();
                    colorPrintln(""+pop.val,34);
                } else if (peek.right == pop) {//右子树处理完了
                    pop = stack.pop();
                    colorPrintln(""+pop.val,34);
                } else {//右子树还没有处理，处理右子树
                    cur = peek.right;
                }
            }
        }
    }
    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        6,
                        2,
                        7),
                1,
                new TreeNode(
                        4,
                        3,
                        5)
        );
        preOrderUnRecur(root);
        inOrderUnRecur(root);
        postOrderUnRecur(root);
    }


}
