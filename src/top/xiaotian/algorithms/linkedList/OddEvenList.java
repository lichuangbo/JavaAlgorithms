package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/14 17:03
 * @Description: 描述:
 */
public class OddEvenList {
    /**
     * 时间O(n)
     * 类似Partition操作
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode(-1), evenHead = new ListNode(-1);
        ListNode curr1 = oddHead, curr2 = evenHead;
        int k = 0;
        while (head != null) {
            k++;

            if (k % 2 == 1) {
                curr1.next = head;
                curr1 = curr1.next;
                head = head.next;
            } else {
                curr2.next = head;
                curr2 = curr2.next;
                head = head.next;
            }
        }
        curr1.next = evenHead.next;
        return oddHead.next;
    }
}
