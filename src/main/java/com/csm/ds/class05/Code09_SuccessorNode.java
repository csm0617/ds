package com.csm.ds.class05;

import com.sun.org.apache.xpath.internal.operations.Neg;

/**
 * 给普通的node节点加一个parent指针,求后继节点
 * 后继节点就是中序遍历后节点的下一个
 */
public class Code09_SuccessorNode {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int val, TreeNode left, TreeNode right, TreeNode parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public TreeNode(int val) {
            this.val = val;
        }


    }

    public static TreeNode getSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }
        /*
            1.如果有右孩子，那么右孩子的左子树中最左的就是后继
         */
        if (node.right != null) {
            TreeNode s = node.right;
            while (s.left != null) {
                s = s.left;
            }
            return s;
        } else {
           /*
                2.如果没有右孩子，那么后继在第一个发生左拐的祖先那里
           */
            TreeNode parent = node.parent;
            while (parent != null && node != parent.left) {//找到第一个发生左拐的祖先节点
                node = parent;
                parent = node.parent;
            }
            return parent;
        }

    }

    public static void main(String[] args) {

        /*
                        1
                       / \
                      2   4
                      \   / \
                       3 5   6
            2 3 1 5 4 6
         */

        TreeNode o6 = new TreeNode(6);
        TreeNode o5 = new TreeNode(5);
        TreeNode o4 = new TreeNode(4, o5, o6, null);
        o5.parent = o4;
        o6.parent = o4;
        TreeNode o2 = new TreeNode(2);
        TreeNode o3 = new TreeNode(3);
        o2.right = o3;
        o3.parent = o2;
        TreeNode o1 = new TreeNode(1, o2, o4, null);
        o4.parent = o1;
        o2.parent = o1;
        // 2 3 1 5 4 6
        System.out.println(getSuccessor(o4).val);//6
//        System.out.println(getSuccessor(o6).val);//null
        System.out.println(getSuccessor(o2).val);//3
        System.out.println(getSuccessor(o5).val);//4
        System.out.println(getSuccessor(o1).val);//5
        System.out.println(getSuccessor(o3).val);//1
    }

}
