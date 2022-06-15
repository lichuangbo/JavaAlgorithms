package top.xiaotian.algorithms.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 示例 1：
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]] 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000 Node.random 为空（null）或指向链表中的节点。 节点数目不超过 1000 。
 */
public class RandomList {

  class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  // 哈希表
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    Node curr = head;
    // 原节点->新节点映射
    Map<Node, Node> memoMap = new HashMap<>();
    while (curr != null) {
      memoMap.put(curr, new Node(curr.val));
      curr = curr.next;
    }
    curr = head;
    // 修正next、random指针指向
    while (curr != null) {
      Node currNewNode = memoMap.get(curr);
      currNewNode.next = memoMap.get(curr.next);
      currNewNode.random = memoMap.get(curr.random);
      curr = curr.next;
    }
    return memoMap.get(head);
  }

  // 原地修改
  public Node copyRandomList2(Node head) {
    if (head == null) {
      return null;
    }

    // 复制每一个节点：7 - 7 - 13 - 13 - 11 - 11 - 10 - 10 - 1 - 1 - nil
    Node curr = head;
    while (curr != null) {
      // 复制出来的节点插入到原链表中间
      Node tmpNode = new Node(curr.val);
      tmpNode.next = curr.next;
      curr.next = tmpNode;

      curr = tmpNode.next;
    }

    // 修正random指针指向
    curr = head;
    while (curr != null) {
      if (curr.random != null) {
        curr.next.random = curr.random.next;
      }
      curr = curr.next.next;
    }

    // 分隔链表：修正next指针指向
    curr = head;
    Node copyHead = head.next, currCopy = copyHead;
    while (curr != null) {
      curr.next = curr.next.next;
      curr = curr.next;
      if (currCopy.next != null) {
        currCopy.next = currCopy.next.next;
        currCopy = currCopy.next;
      }
    }
    return copyHead;
  }

}
