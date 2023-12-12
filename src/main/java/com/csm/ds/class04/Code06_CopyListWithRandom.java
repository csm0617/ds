package com.csm.ds.class04;

import lombok.ToString;

/**
 * 比传统的Node节点多了一个random指针指向链表中的节点或者null，
 * 请在时间复杂度O（n） 额外空间复杂度O（1）的条件下完成对链表的复制
 */
public class Code06_CopyListWithRandom {
    public static class Node {
        int value;
        Node next;
        Node random;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next, Node random) {
            this.value = value;
            this.next = next;
            this.random = random;
        }
    }

    //时间复杂度O（n） 额外空间复杂度O（1）
    //1 -> 2 -> ...先copy List,  1 -> 1' -> 2 -> 2'->...
    public static Node copyListWithRandom(Node head) {
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;//先记录下一个节点
            cur.next = new Node(cur.value);//1 -> 1'
            cur.next.next = next;//1 - > 1' -> 2
            cur = next;//从2开始继续复制
        }

        cur = head;
        //遍历老的数组，在遍历的过程中，完成新链表random指针的指向
        while (cur != null) {
            Node next = cur.next.next;  //记录 1 -> 1' -> 2 中的2
            Node curCopy = cur.next; //1‘
            curCopy.random = cur.random != null ? cur.random.next : null; //把1的random的next，有也就是random '给1’的random 赋值
            cur = next;//接着从2开始
        }
        //把新链表中旧链表方向的next指针全部断开
        Node res = head.next;//1'开始
        cur = head;//1开始
        //split
        while (cur != null) {
            Node next = cur.next.next;//记录2
            Node curCopy = cur.next;//1’
            cur.next = next;//把1->1'->2->2'->... 改成 1->2->2'
            curCopy.next = next != null ? next.next : null; //  1‘->2'
            cur = next;//从2开始继续下一轮循环，继续断开连接
        }
        return res;
    }

    public static void main(String[] args) {
        Node o5 = new Node(5);
        Node o4 = new Node(4, o5, null);
        Node o3 = new Node(3, o4, o5);
        Node o2 = new Node(2, o3, o4);
        Node o1 = new Node(1, o2, o3);
        Node copyHead = copyListWithRandom(o1);
        while (copyHead != null) {
            System.out.print("cur: " + copyHead.value + "\t");
            if (copyHead.random == null) {
                System.out.println();
            } else {
                System.out.println("random: " + copyHead.random.value);
            }
            copyHead = copyHead.next;
        }
    }

    //todo 借用hashtable来复制，时间复杂度O（n）,空间复杂度O（n）
}
