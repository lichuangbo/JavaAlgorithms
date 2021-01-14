package top.xiaotian.algorithms.linkedList;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/14 9:29
 * @Description: 描述: 2 445
 */
public class ReverseBetween {
    /**
     * 时间: O(n)
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // prev记录待反转的前一个节点（m-1位置）
        ListNode prev = dummyHead;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }

        // 翻转m-n之间的链表：tPrev记录[m...n]之间反转后的头节点， next记录代反转链表后一个节点（n+1位置）
        ListNode tPrev = prev.next;
        ListNode curr = tPrev.next;
        ListNode next = curr;
        for (int i = m; i < n; i++) {
            next = curr.next;

            curr.next = tPrev;
            tPrev = curr;
            curr = next;
        }

        // 关键节点位置都已记录，开始连接
        prev.next.next = next;
        prev.next = tPrev;
        return dummyHead.next;
    }


    // https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
    private ListNode successor = null; // 后驱节点
    // 反转以head为链表起点的 n 个节点，并返回新的头结点
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第n+1个节点, 按reverseN(head, 3)来看，head此时指向3
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        head.next = successor;
        return last;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween2(head.next, m - 1, n - 1);
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode listNode = new ReverseBetween().reverseBetween2(new ListNode(arr), 2, 4);
        System.out.println(listNode);
    }
}
