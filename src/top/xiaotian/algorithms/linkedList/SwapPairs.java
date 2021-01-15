package top.xiaotian.algorithms.linkedList;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/15 14:03
 * @Description: 描述:      147 148
 */
public class SwapPairs {
    /**
     * 时间: O(n)
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null && prev.next.next != null) {
            ListNode node1 = prev.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            // 调整连接关系
            prev.next = node2;
            node2.next = node1;
            node1.next = next;

            // 挪动到下一对待处理节点的前一个节点位置上
            prev = node1;
        }
        return dummyHead.next;
    }

    // 25. K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (true) {
            ListNode node1 = prev.next;// 指向第一个节点
            // 定位第k个节点位置
            ListNode nodek = prev;
            for (int i = 0; i < k; i++) {
                nodek = nodek.next;
                if (nodek == null) {// 不够k，不再继续循环，直接返回结果
                    return dummyHead.next;
                }
            }
            ListNode next = nodek.next;// 指向第k个节点

            prev.next = nodek;
            // 局部翻转链表
            ListNode tPrev = next;
            ListNode curr = node1;
            while (curr != next) {
                ListNode tNext = curr.next;

                curr.next = tPrev;
                tPrev = curr;
                curr = tNext;
            }

            // 重新指向，循环
            prev = node1;
        }
    }
}
