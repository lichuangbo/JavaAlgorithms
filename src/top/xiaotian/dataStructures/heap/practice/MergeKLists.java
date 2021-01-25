package top.xiaotian.dataStructures.heap.practice;


import top.xiaotian.algorithms.linkedList.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/19 13:53
 * @Description: 描述:
 */
public class MergeKLists {
    /**
     * 时间：O(Nlogk)  k为链表个数(每次堆中只存放k个节点)，N为链表的节点个数和
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                (o1, o2) -> o1.val - o2.val
        );
        // 将所有链表的首节点加入堆
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        while (!queue.isEmpty()) {
            curr.next = queue.poll();
            curr = curr.next;
            // 继续追加该链表的下一个节点
            if (curr.next != null) {
                queue.add(curr.next);
            }
        }
        return dummyHead.next;
    }
}
