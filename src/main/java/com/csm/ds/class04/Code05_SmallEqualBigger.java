package com.csm.ds.class04;

/**
 * 类似于quicksort的partition，给定一个值，划分单向链表的 < = > 区域
 */
public class Code05_SmallEqualBigger {
    /**
     * 方法一：用 node[] ,把链表所有的node都放到数组上，再给数组做partition，最后把数组串起来
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        //遍历链表，统计长度
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        //根据长度创建nodeArr
        Node[] nodeArr = new Node[i];
        //遍历整个链表，复制到nodeArr
        cur = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);

        for (i = 0; i < nodeArr.length - 1; i++) {
            nodeArr[i].next = nodeArr[i + 1];
//            System.out.println(nodeArr[i].val);
        }
        nodeArr[nodeArr.length - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int first = 0;
        int last = nodeArr.length - 1;
        int i = 0;
        while (i < last) {//!!!每次在这里都会犯错误，遍历的右边界是last
            if (nodeArr[i].val < pivot) {
                swap(nodeArr, first, i);
                first++;
                i++;
            } else if (nodeArr[i].val > pivot) {
                swap(nodeArr, last, i);
                last--;
            } else {
                i++;
            }
        }
    }

    public static void swap(Node[] nodeArr, int i, int j) {
        Node temp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = temp;
    }


    /**
     * （可以保持稳定性）
     * 方法二：定义有限几个变量 sH sT eH eT mH mT 指针 来划分 <  =  >区域的，
     * 将属于各自部分的串起来，最后再把这几个区域拼接
     */
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;// save next node
        while (head != null) {
            next = head.next;//记录下一个节点
            head.next = null;//断开当前节点与下一个节点的连接， 不然最后三块区域还是衔接的
            if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.val > pivot) {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            } else {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }
            head = next;
        }
        if (sT != null) { //有小于区域
            sT.next = eH;//那么小于区域的尾 去连 等于区域的头
            eT = eT == null ? sT : eT;//如果等于区域不存在 那么 就让小于区域的 尾 成为等于区域的尾
        }
        if (eT != null) {//小于，等于区域，有一个存在那么就把尾和大于区域的头相连
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);//从左往右，哪个区域不为空，就返回那哪个区域的头
    }


    public static void main(String[] args) {
        LinkList list1 = new LinkList(new int[]{1, 3, 5, 9, 6, 8, 4, 0, 3, 5, 2, 1, 1, 11, 1, 2, 6});
        list1.head = listPartition1(list1.head, 7);
        System.out.println(list1);
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
        LinkList list2 = new LinkList(new int[]{1, 3, 5, 5, 5, 65, 8, 4, 6, 9, 3, 6, 4, 1, 2, 6,});
        list2.head = listPartition2(list2.head, 7);
        System.out.println(list2);
    }
}
