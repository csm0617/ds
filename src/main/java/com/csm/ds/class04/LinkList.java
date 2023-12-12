package com.csm.ds.class04;

public class LinkList {
    Node head;

    public LinkList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        head = new Node(arr[0]);
        Node p = head;
        for (int i = 1; i < arr.length; i++) {
            p.next = new Node(arr[i]);
            p = p.next;
        }
    }

    public LinkList(Node head) {
        this.head = head;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Linklist:" + "[");
        while (head != null) {
            if (head.next != null) {
                s.append(head.val).append(" -> ");
            } else {
                s.append(head.val).append("]");
                break;
            }
            head = head.next;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        LinkList linklist = new LinkList(new int[]{1, 8, 2, 3, 4, 5, 7, 6});
        System.out.println(linklist);
    }
}
