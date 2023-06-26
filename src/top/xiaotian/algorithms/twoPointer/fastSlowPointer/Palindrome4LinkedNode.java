package top.xiaotian.algorithms.twoPointer.fastSlowPointer;

import top.xiaotian.util.ListNode;

/**
 * 234. 回文链表
 * 面试题 02.06. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/5
 */
public class Palindrome4LinkedNode {
    /**
     * 快慢指针
     * 时间O(n)
     * 空间O(1)
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            // 翻转
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;

            slow = next;
        }
        // 1 2 3 2 1 nil
        if (fast != null) {// 奇数个节点
            slow = slow.next;
        }
        while (prev != null && slow != null) {
            if (prev.val != slow.val) {
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }
}
