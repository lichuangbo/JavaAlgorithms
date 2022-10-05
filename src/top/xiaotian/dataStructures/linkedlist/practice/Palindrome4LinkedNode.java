package top.xiaotian.dataStructures.linkedlist.practice;

import top.xiaotian.util.ListNode;

/**
 * 234. 回文链表
 * 面试题 02.06. 回文链表
 * 编写一个函数，检查输入的链表是否是回文的。
 *
 *
 *
 * 示例 1：
 *
 * 输入： 1->2
 * 输出： false
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/5
 */
public class Palindrome4LinkedNode {
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
