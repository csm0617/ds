package com.csm.ds.class05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Code10_SerializeAndReconstructTree {
    /**
     * 把一颗树序列成字符串返回（采用前序遍历构建字符串）
     *
     * @param node 节点
     * @return 序列化后的字符串
     */
    public static String serialByPre(TreeNode node) {
        //#代表空节点
        if (node == null) {
            return "#_";
        }
        //以下划线 _ 结束一个节点的遍历
        String res = node.val + "_";
        res += serialByPre(node.left);
        res += serialByPre(node.right);
        return res;
    }

    /**
     * 分割字符串为value的队列，调用方法建树
     * @param preStr
     * @return
     */

    public static TreeNode reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        //分割后的字符串数组加入队列
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return reconPreOrder(queue);
    }

    /**
     * 递归调用，建立当前节点和左右子树的关系
     * @param queue 先序遍历的value序列队列
     * @return 建树后的头节点
     */
    public static TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if ("#".equals(value)) {
            return null;
        }
        //先序序列建树，先建立根，再递归调用建立左和右
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left=reconPreOrder(queue);
        node.right=reconPreOrder(queue);
        return node;
    }

    public static void main(String[] args) {

    }
}
