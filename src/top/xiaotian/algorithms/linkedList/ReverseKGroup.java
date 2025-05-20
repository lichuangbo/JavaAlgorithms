package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * <p>
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class ReverseKGroup {
    /**
     * 整体写法思路参照 两两交换链表中的节点
     * @see SwapPairs
     * 局部翻转链表在 反转链表 题目基础上难度拔高
     * @see ReverseList
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode prev = dummyHead;
        while (true) {
            // node1
            ListNode node1 = prev.next;
            // 寻找nodeK
            ListNode nodek = prev;
            for (int i = 0; i < k; i++) {
                nodek = nodek.next;
                if (nodek == null) {// 不够k，不再继续循环，直接返回结果
                    return dummyHead.next;
                }
            }
            // nodeK的下一节点
            ListNode next = nodek.next;
            // 至此四个节点位置都标记好了

            prev.next = nodek;
            // 局部翻转链表
            /**
             * 假如是这个链表，翻转时候，tPrev直接指向null, curr指向1, 反方向链过来就行
             * 1 -> 2 -> 3 -> nil
             * 现在是这样的情况，-1已经链到3了，想要达到3 -> 2 -> 1 -> 4的效果
             * -1  1 -> 2 -> 3 -> 4 -> nil
             * tPrev指向4,就能实现1链到4的效果（翻转链表题目中，tPrev指向null，实现了1链到null的效果）
             * tCurr和tNext完全照搬翻转链表题目写法就行
             */
            ListNode tPrev = next;
            ListNode tCurr = node1;
            while (tCurr != next) {
                ListNode tNext = tCurr.next;

                tCurr.next = tPrev;
                tPrev = tCurr;
                tCurr = tNext;
            }

            // 重新指向，循环
            prev = node1;
        }
    }
}
