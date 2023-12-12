package com.csm.ds.class05;

import java.util.HashMap;
import java.util.HashSet;

public class Code08_LowestCommonAncestor {
    //o1和o2一定属于root为头的树
    //返回o1和o2的最低的公共祖先
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode o1, TreeNode o2) {
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
        fatherMap.put(root, root);//根节点的father设为自己
        //其他节点通过process递归调用放入fatherMap中
        process(fatherMap, root);
        TreeNode cur = o1;
        HashSet<TreeNode> o1Set = new HashSet<>();
        while (cur != fatherMap.get(cur)) {//只有根节点的father和自己相等，所以while循环是从o1一直追溯祖父到根
            o1Set.add(cur);//把o1的祖父全部加入到o1Set中
            cur = fatherMap.get(cur);
        }
        cur = o2;
        /*
          同样的一直向上追随o2的祖先，如果o2的祖先中和o1Set中的相同，
          说明这个就是最近的公共祖先（因为是向上回溯的，所以最先相等的一定是最近的）
         */
        while (cur != fatherMap.get(cur)) {
            if (o1Set.contains(cur)) {
                return cur;
            }
            cur = fatherMap.get(cur);
        }
        return root;//遍历过程中没有找到最近的公共祖先，那么最近的公共祖先只能是root了
    }

    /**
     * 递归调用，除根节点和null外，记录每个节点的father
     *
     * @param fatherMap map
     * @param node      节点
     */
    public static void process(HashMap<TreeNode, TreeNode> fatherMap, TreeNode node) {
        if (node == null) {
            return;
        }
        fatherMap.put(node.left, node);
        fatherMap.put(node.right, node);
        process(fatherMap, node.left);
        process(fatherMap, node.right);
    }


    /**
     * 寻找最近的公共祖先
     * 方法二：
     * 递归分别向左向右寻找o1和o2，遇到o1或者o2就向上返回，
     * 递归结果 左右两侧都有返回 肯定是在两侧 返回这个左右都有返回的节点就是最近公共祖先
     * 递归结果 都为null就向上返回，只有一侧有结果那么说明 两个节点在同一侧，第一个遇到的o1或o2就是最近公共祖先
     * @param node 递归查找的节点
     * @param o1  节点o1
     * @param o2  节点o2
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode node, TreeNode o1, TreeNode o2) {
        if (node == null || node == o1 || node == o2) {//遇到空返回空，遇到o1返回o1，遇到o2返回o2
            return node;
        }
        TreeNode leftResult = lowestCommonAncestor2(node.left, o1, o2);//向左找
        TreeNode rightResult = lowestCommonAncestor2(node.right, o1, o2);//向右找
        /**
         * 如果 o1和 o2在 同一侧，那么必然不会走下面的if语句
         * 既然不会走if语句，那么必然会先找到一个leftResult和leftResult不为空的场景，向上返回，
         * 因为在同一侧不管是先遇到的肯定是作为祖先返回
         */
        if (leftResult != null && rightResult != null) {//说明o1和o2分别在node节点的两侧，返回左右子树都有返回值的节点就是最近公共的节点
            return node;
        }
        //左右两颗树，并不都有返回值，向上返回有值的，没有值就返回null
        return leftResult != null ? leftResult : rightResult;
    }

    public static void main(String[] args) {

    }
}
