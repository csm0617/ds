package com.csm.ds.class05;

/**
 * 折纸凹痕问题：
 * 分析后发现
 * 1.第一次的折痕是凹折痕1
 * 2.第二次对折以后在凹折痕1的左侧会生成 凹折痕2 和 右侧会生成凸折痕
 * 3.在凸折痕的左侧 生成的一定是凹折痕 ，凸折痕右侧生成的一定是是 凸折痕
 * 所以可以转化成二叉树
 * 凹
 * /      \
 * 凹        凸
 * /   \     /  \
 * 凹    凸   凹    凸
 * 也不一定非要用二叉树去构建，打印的时候中序遍历
 * 如果采用二叉树去构建，那么需要2^n-1的空间复杂度，用递归模拟中序遍历压栈只需要O（N）
 */
@SuppressWarnings("all")
public class Code11_PaperFloding {
    public static void printAllFlods(int N) {
        printProcess(1, N, true);//从第一层开始
    }

    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        if (i == 1) {
            System.out.printf(down ? "凹" : "凸");
            System.out.println("  中间 ");
        }else {
            System.out.println(down ? "凹" : "凸");
        }
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFlods(6);
    }
}
