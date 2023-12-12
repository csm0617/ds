package com.csm.ds.class04;

/**
 * 判断回文链表 时间复杂度O（n） 空间复杂度O（1）
 */
public class Code04_isPalindromeList {


    /**
     * 快慢指针
     *
     * @param head 链表头
     * @return
     */
    public static boolean isPalindromeList(Node head) {
        boolean isPalindrome = false;
        Node p1 = head;//慢指针
        Node p2 = head;//快指针
        Node n1 = null;//翻转链表的头节点
        Node o1 = head;//旧节点
        while (p2 != null && p2.next != null) {
            //慢指针每次走1步
            p1 = p1.next;
            //快指针每次走2步
            p2 = p2.next;
            p2 = p2.next;
            //p1每走一步就将链表反转（头插法）
            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }
        //如果p2!=null，说明结束条件是p2.next=null;那么是奇数个节点
        // 1 2 3 4 5
        System.out.println(p1.val);
        if (p2 != null) {
            //奇数个节点那么中点的下一位开始对比
            p1 = p1.next;
        }
        while (p1 != null && n1 != null) {
            if (p1.val != n1.val) {
                return false;
            } else {
                p1 = p1.next;
                n1 = n1.next;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isPalindromeList(new LinkList(new int[]{1, 2, 4, 4, 2, 1}).head));
        System.out.println(isPalindromeList(new LinkList(new int[]{1, 2, 4, 5, 4, 2, 1}).head));

    }

}
