package top.xiaotian.algorithms.queue.priority_queue;

import java.util.PriorityQueue;
import top.xiaotian.util.ListNode;

/**
 * 23. 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
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
 * @author lichuangbo
 * @date 2023/2/6
 * @see top.xiaotian.algorithms.linkedList.MergeSortList
 */
public class MergeKLists {

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    // 最小堆
    PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
    // 将所有链表的首节点加入堆
    for (ListNode listNode : lists) {
      // bad case: [[]]
      if (listNode != null) {
        pq.offer(listNode);
      }
    }
    ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
    ListNode curr = dummyHead;
    while (!pq.isEmpty()) {
      ListNode tmpNode = pq.poll();
      curr.next = tmpNode;
      curr = curr.next;
      // 继续追加该链表的下一个节点
      if (tmpNode.next != null) {
        pq.offer(tmpNode.next);
      }
    }
    return dummyHead.next;
  }
}
