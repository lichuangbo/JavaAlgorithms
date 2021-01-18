package top.xiaotian.algorithms.linkedList;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/18 13:45
 * @Description: 描述:
 */
public class ReorderList {
    /**
     * 时间：中间节点O(n)+翻转O(n/2)+连接O(n/2)
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 寻找中间节点
        ListNode fast = head, slow = head, last = head;
        while (fast != null && fast.next != null) {
            last = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        last.next = null;

        // 翻转后半部分
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // 重新连接
        ListNode dummyHead = new ListNode(-1);
        ListNode res = dummyHead;
        ListNode curr1 = head, curr2 = prev;
        boolean flag = true;
        while (curr1 != null && curr2 != null) {
            if (flag) {
                res.next = curr1;
                curr1 = curr1.next;
                flag = false;
            } else {
                res.next = curr2;
                curr2 = curr2.next;
                flag = true;
            }
            res = res.next;
        }
        res.next = curr1 == null ? curr2 : curr1;
        head = dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(nums);
        new ReorderList().reorderList(listNode);
        System.out.println(listNode);
    }
}
