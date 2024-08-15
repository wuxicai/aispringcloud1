package leetcode力扣算法题库.链表;

import org.junit.Test;

public class _反转链表 {
    @Test
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode listNode = reverseList(head.next);
        head.next.next=head;
        head.next=null;
        System.out.println(head);
        return listNode;
    }
}
