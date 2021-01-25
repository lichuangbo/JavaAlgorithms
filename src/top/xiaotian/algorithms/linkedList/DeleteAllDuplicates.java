package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/14 14:31
 * @Description: 描述:
 */
public class DeleteAllDuplicates {
    /**
     * 时间O(n)
     * 类似RemoveElements（删除链表中等于给定值 val 的所有节点）
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.val == prev.next.val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    // 递归写法
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = deleteDuplicates2(head.next);
        if (head.val == head.next.val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

    // 82. 删除排序链表中的重复元素 II（重复节点不保留）
    /**
     * 示例 1:
     *
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     */
    public ListNode deleteDuplicates3(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                // 循环去除相等元素
                ListNode curr = prev.next;
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    // 递归解法
    public ListNode deleteDuplicates4(ListNode head) {
        // 链表为空或者仅有一个节点，是不可能重复的，直接返回自身
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val == head.next.val) {
            // 相等，循环去除相等元素
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates4(head.next);
        } else {
            // 不等，连接上首节点返回
            head.next = deleteDuplicates4(head.next);
            return head;
        }
    }
}
