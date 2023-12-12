package com.csm.ds.class05;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(TreeNode left, int val, TreeNode right) {
        this.left = left;
        this.val = val;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int left, int val, int right) {
        this.val = val;
        this.left = new TreeNode(left);
        this.right = new TreeNode(right);
    }
}
