package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * 判断一个字符串是否是回文字符串(其中字符串使用链表存储)
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/5
 */
public class Palindrome4LinkedNode {
    public static boolean isPalindromeStr(Node head) {
        Node fast = head;
        Node slow = head;
        Node prev = null;// 翻转链表用
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            // 寻找中点的同时，进行链表反转
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        // 处理奇数个节点情况，让slow再走一步
        if (fast != null) {
            slow = slow.next;
        }
        // 判断
        while (slow != null) {
            if (!slow.value.equals(prev.value)) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head1 = new Node("1", new Node("2", new Node("3", new Node("2", new Node("1", null)))));
        Node head2 = new Node("1", new Node("2", new Node("2", new Node("1", null))));
        Node head3 = new Node("1", new Node("2", new Node("3", new Node("1", null))));

        System.out.println(isPalindromeStr(head1));
        System.out.println(isPalindromeStr(head2));
        System.out.println(isPalindromeStr(head3));
    }
}
