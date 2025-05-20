package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/15 14:03
 * @Description: 描述:      147 148
 */
public class SwapPairs {
    /**
     * 时间: O(n)
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode prev = dummyHead;
        // 需要标记四个节点（-1,1,2,3），至少要保证prev.next prev.next.next不为空   prev.next.next.next可以为空，因为在循环内部没有用他的next值，不会空指针异常
        while (prev.next != null && prev.next.next != null) {
            ListNode node1 = prev.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            // 调整连接关系（-1连2,2连1,1连3 1在循环内部没有想着直接去连4，因为连了4后循环不起来了）
            prev.next = node2;
            node2.next = node1;
            node1.next = next;

            // 挪动到下一对待处理节点的前一个节点位置上（p应该直系那个1，链表拉直来看）
            prev = node1;
        }
        return dummyHead.next;
    }
}
