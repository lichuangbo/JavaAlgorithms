package top.xiaotian.dataStructures.linkedNode.practice;

import top.xiaotian.dataStructures.linkedNode.Node;

/**
 * 单链表翻转
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 */
public class ReverseList {
    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    /**
     * 迭代法  时间0(n)  空间O(1)
     * @param head
     * @return
     */
    public Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归法
     * @param head
     * @return
     */
    public Node reverseList2(Node head) {
        // 终止条件
        if (head == null || head.next == null)  return head;
        Node tmp = reverseList2(head.next);
        // 处理本级递归应该做的事情
        head.next.next = head;
        head.next = null;
        return tmp;
    }

    public static void main(String[] args) {
        Node head1 = new Node("1", new Node("2", new Node("3", new Node("4", new Node("5", null)))));

        ReverseList reverseList = new ReverseList();
//        Node node1 = reverseList.reverseList(head1);

        Node node2 = reverseList.reverseList2(head1);
        System.out.println();
    }
}
