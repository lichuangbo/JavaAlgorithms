package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * 删除链表的倒数第N个节点
 * 1->2->3->4->5,n = 2.
 * 1->2->3->5.
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 */
public class RemoveNthFromEnd {
    /**
     * 时间：fast走k步,slow走(k-n)步,时间复杂度为O(2k-n),其中k为链表长度
     * 空间：O(1)
     * @param head
     * @param n
     * @return
     */
    public Node removeNthFromEnd(Node head, int n) {
        Node fast = head, slow = head;
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }
        // 删除首元素时
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        Node head2 = new Node("1", new Node("2", new Node("3", new Node("4", new Node("5", null)))));

        new RemoveNthFromEnd().removeNthFromEnd(head2, 2);
    }
}
