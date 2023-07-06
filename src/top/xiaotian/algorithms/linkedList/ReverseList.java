package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 */
public class ReverseList {
    /**
     * 迭代法  时间0(n)  空间O(1)
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归法
     */
    public ListNode reverseList2(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tmp = reverseList2(head.next);
        // 处理本级递归应该做的事情
        head.next.next = head;
        head.next = null;
        return tmp;
    }
}
