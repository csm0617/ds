package com.csm.ds.class04;
/**
 * 相交节点：（链表带环）
 * 找到两个相交链表的的第一个相交节点，没有相交节点返回null
 */
public class Code07_FindFirstIntersectNode {
    /**
     * 两个链表都没有环的情况下
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node bothNoLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        //n记录两个链表的差值
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        //此时cur1和cur2分别来到了链表的最后一个位置
        //如果有相交节点那么，自相交节点开始，一直到链表结束，后面都是两个链表的公共部分
        //最后一个节点一定相等
        //否则两个链表都没有相交节点，返回Null
        if (cur1 != cur2) {
            return null;
        }

        //存在相交节点
        cur1 = n > 0 ? head1 : head2; //链表A和B,谁长谁是cur1
        cur2 = cur1 == head1 ? head2 : head1;//谁短，谁是cur2
        n = Math.abs(n);
        //链表长的先走n步
        while (n > 0) {
            cur1 = cur1.next;
            n--;
        }
        //一定会有内存地址相等的地方，返回第一个相等的地方
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /*
        不存在一个链表有环，另一个链表无环，但是这两个链表还相交的情况，画图
     */

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        //情况1两个链表的环一样，两个链表在环的位置重合一定有相交节点
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            //n记录两个链表的差值
            int n = 0;
            while (cur1.next != loop1) {//遍历到环的位置就可以了
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop1) {
                n--;
                cur2 = cur2.next;
            }
            //此时cur1和cur2分别来到了链表的最后一个位置
            //如果有相交节点那么，自相交节点开始，一直到链表结束，后面都是两个链表的公共部分
            //最后一个节点一定相等
            //否则两个链表都没有相交节点，返回Null
            if (cur1 != cur2) {
                return null;
            }

            //存在相交节点
            cur1 = n > 0 ? head1 : head2; //链表A和B,谁长谁是cur1
            cur2 = cur1 == head1 ? head2 : head1;//谁短，谁是cur2
            n = Math.abs(n);
            //链表长的先走n步
            while (n > 0) {
                cur1 = cur1.next;
                n--;
            }
            //一定会有内存地址相等的地方，返回第一个相等的地方
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//两个环不相等
            cur1 = loop1.next;//链表1从环的下一个位置，开始围着loop1的环走一圈，如果和loop2相遇，说明有相交节点，相交节点就是相遇的节点，一般有两个，返回一个就行
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }
            //走完一圈环还没有发现相交，那就说明两个链表是平行的
            return null;
        }

    }


    /**
     * 快慢指针法
     * 寻找入环节点
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        Node fast = head;//快指针
        Node slow = head;//慢指针
        while (fast != null && fast.next != null) {
            //快指针走两步
            fast = fast.next.next;
            //慢指针走一步
            slow = slow.next;
            if (slow == fast) {//相遇说明有环
                slow = head;//让慢指针或者快指针回到起点，快慢指针都一步一步走，在走两圈的步数范围内必定再次相遇
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static Node findFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
//        printLoopNode(loop1);
        Node loop2 = getLoopNode(head2);
//        printLoopNode(loop2);
        return loop1 == null && loop2 == null ? bothNoLoop(head1, head2) : loop1 == null || loop2 == null ? null : bothLoop(head1, loop1, head2, loop2);
    }

    public static void printLoopNode(Node loopNode) {
        if (loopNode == null) {
            System.out.println("LinkedList has no cycle!");
        } else {
            System.out.println(loopNode.val);
        }
    }

    public static void main(String[] args) {
        Node o5 = new Node(5);
        Node o4 = new Node(4, o5);
        Node o3 = new Node(3, o4);
        Node o2 = new Node(2, o3);
        Node o1 = new Node(1, o2);
        o5.next = o3;
        LinkList linkList = new LinkList(new int[]{1, 2, 3, 4, 5, 6});
        printLoopNode(getLoopNode(linkList.head));
        printLoopNode(getLoopNode(o1));

        //两个环相等和不相等的情况
        //1.两个环不相等
        Node s8 = new Node(8);
        Node s7 = new Node(7, s8);
        Node s6 = new Node(6, s7);
        Node s5 = new Node(5, s6);
        Node s4 = new Node(4, s5);
        Node s3 = new Node(3, s4);
        Node s2 = new Node(2, s3);
        Node s1 = new Node(1, s2);
        s8.next = s4;
        Node n14 = new Node(14, s7);
        Node n13 = new Node(13, n14);
        Node n12 = new Node(12, n13);
        Node n11 = new Node(11, n12);
        System.out.println(findFirstIntersectNode(s1, n11).val);

        //两个环相等
        Node p18 = new Node(18);
        Node p17 = new Node(17, p18);
        Node p16 = new Node(16, p17);
        p18.next = o2;
        System.out.println(findFirstIntersectNode(o1,p16).val);
    }
}
