package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/18 11:16
 * @Description: 描述:
 */
public class RotateRight {
    /**
     * 时间：统计个数O(n)+快慢遍历O(n)
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 注意点1
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 统计链表中节点个数
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }

        // 寻找首节点位置
        ListNode fast = head, slow = head;
        // 注意点2
        k = k % count;
        if (k == 0) {
            return head;
        }
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;

        // 处理连接关系
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        ListNode listNode = new RotateRight().rotateRight(new ListNode(nums), 2);
        System.out.println(listNode);
    }
}
