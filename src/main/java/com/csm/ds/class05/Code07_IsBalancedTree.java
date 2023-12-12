package com.csm.ds.class05;

public class Code07_IsBalancedTree {
    public static boolean isBalanced(TreeNode root) {
        ReturnType result = process(root);
        System.out.println("height:" + result.height + "\t" + "isBalanced:" + result.isBalanced);
        return result.isBalanced;
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static ReturnType process(TreeNode node) {
        if (node == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(node.left);
        ReturnType rightData = process(node.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        1,
                        2,
                        3),
                4,
                new TreeNode(
                        new TreeNode(5),
                        6,
                        new TreeNode(new TreeNode(1),2,new TreeNode(4,5,6)))
        );
        System.out.println(isBalanced(root));
    }
}
