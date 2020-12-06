package top.xiaotian.dataStructures.linkedNode.practice;

import top.xiaotian.dataStructures.linkedNode.Node;

/**
 * 合并两个有序链表
 * 1->2->4, 1->3->4
 * 1->1->2->3->4->4
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 */
public class MergeSortList {
    /**
     * 时间：k=min(l1, l2), 两个链表各走k步，总共2k，O(2k)
     * 空间：一个辅助节点，O(1)
     * @param l1
     * @param l2
     * @return
     */
    public Node mergeTwoLists(Node l1, Node l2) {
        Node tmp = new Node("-1", null);
        Node curr = tmp;
        while (l1 != null && l2 != null) {
            if ((Integer)l1.value < (Integer) l2.value) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;
        return tmp.next;
    }
}
