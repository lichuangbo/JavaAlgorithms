package top.xiaotian.algorithms.linkedList;

import java.util.HashSet;
import java.util.Set;
import top.xiaotian.util.ListNode;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 *
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 注意：
 *
 *     如果两个链表没有交点，返回 null.
 *     在返回结果后，两个链表仍须保持原有的结构。
 *     可假定整个链表结构中没有循环。
 *     程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class IntersectionNode {

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    Set<ListNode> set = new HashSet<>();
    ListNode curr = headA;
    while (curr != null) {
      set.add(curr);
      curr = curr.next;
    }

    curr = headB;
    while (curr != null) {
      if (set.contains(curr)) {
        return curr;
      }
      curr = curr.next;
    }
    return null;
  }

  public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    /**
     * 假设a链表长度为a, b链表长度为b，ab链表重叠部分长度为c
     * a链表从头到尾遍历一遍，再从b链表头部遍历到公共节点   a+(b-c)
     * b链表从头到尾遍历一遍，再从a链表头部遍历到公共节点   b+(a-c)
     * 两者是相等的，这意味着只要完成上述的遍历，如果存在公共节点，那么结束时一定位于公共节点上；否则为null
     */
    ListNode h1 = headA, h2 = headB;
    while (h1 != h2) {
      if (h1 == null) {
        h1 = headB;
      } else {
        h1 = h1.next;
      }

      if (h2 == null) {
        h2 = headA;
      } else {
        h2 = h2.next;
      }
    }
    return h1;
  }
}
