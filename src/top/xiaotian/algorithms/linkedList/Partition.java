package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 86. 分隔链表
 * 面试题 02.04. 分割链表
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例：
 *
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：1->2->2->4->3->5  小于3的节点[1,2,2]   大于或等于3的节点[4,3,5]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/14 14:48
 * @Description: 描述:
 */
public class Partition {
    /**
     * 时间O(n)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(Integer.MIN_VALUE);
        ListNode dummyHead2 = new ListNode(Integer.MIN_VALUE);

        ListNode curr1 = dummyHead1;
        ListNode curr2 = dummyHead2;
        while (head != null) {
            if (head.val < x) {
                curr1.next = head;
                head = head.next;
                curr1 = curr1.next;
                curr1.next = null;
            } else {
                curr2.next = head;
                head = head.next;
                curr2 = curr2.next;
                curr2.next = null;
            }
        }
        curr1.next = dummyHead2.next;
        return dummyHead1.next;
    }

/*    public ListNode partition2(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(-101);
        ListNode dummyHead2 = new ListNode(-101);
        ListNode curr1 = dummyHead1;
        ListNode curr2 = dummyHead2;
        while (head != null) {
            // 以下写法链表在连接时会成环，必须将curr1、curr2尾端置空
            // 同时由于要尾端置空，head指针的移动必须放在if-else里，否则head找不到后继节点，程序就直接退出了
            if (head.val < x) {
                curr1.next = head;
                curr1 = curr1.next;
            } else {
                curr2.next = head;
                curr2 = curr2.next;
            }
            head = head.next;
        }
        curr1.next = dummyHead2.next;
        return dummyHead1.next;
    }*/
}
