package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 02.01. 移除重复节点
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 */
public class RemoveDuplicateNodes {
  public ListNode removeDuplicateNodes(ListNode head) {
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    Set<Integer> set = new HashSet<>();
    ListNode prev = dummyHead;
    while (prev.next != null) {
      int val = prev.next.val;
      if (set.contains(val)) {
        prev.next = prev.next.next;
      } else {
        set.add(val);
        prev = prev.next;
      }
    }
    return dummyHead.next;
  }

  public ListNode removeDuplicateNodes2(ListNode head) {
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    ListNode prev = dummyHead;
    while (prev.next != null) {
      // 每遍历一个节点，都将剩余链表中和该节点值相等的移除掉，prev只管连接
      ListNode curr = prev.next;
      while (curr.next != null) {
        if (prev.next.val == curr.next.val) {
          curr.next = curr.next.next;
        } else {
          curr = curr.next;
        }
      }
      prev = prev.next;
    }
    return dummyHead.next;
  }
}
