package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 * @see top.xiaotian.algorithms.queue.priority_queue.MergeKLists
 */
public class MergeSortList {

  /**
   * 时间：k=min(l1, l2), 两个链表各走k步，总共2k，O(2k)
   * 空间：一个辅助节点，O(1)
   */
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }

    ListNode curr1 = list1;
    ListNode curr2 = list2;
    ListNode dummyHead = new ListNode(-101);
    ListNode curr = dummyHead;
    while (curr1 != null && curr2 != null) {
      if (curr1.val <= curr2.val) {
        curr.next = curr1;
        curr1 = curr1.next;
        curr = curr.next;
      } else {
        curr.next = curr2;
        curr2 = curr2.next;
        curr = curr.next;
      }
    }
    if (curr1 != null) {
      curr.next = curr1;
    }
    if (curr2 != null) {
      curr.next = curr2;
    }
    return dummyHead.next;
  }

  // 递归
  private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    if (l1.val < l2.val) {
      l1.next = mergeTwoLists2(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists2(l1, l2.next);
      return l2;
    }
  }
}
