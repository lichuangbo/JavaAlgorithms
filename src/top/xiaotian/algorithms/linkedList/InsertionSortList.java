package top.xiaotian.algorithms.linkedList;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/15 15:33
 * @Description: 描述:
 */
public class InsertionSortList {
    /**
     * 时间：O(n2)
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode curr = dummyHead.next;
        while (curr.next != null) {
            // 有序直接跳过
            if (curr.val < curr.next.val) {
                curr = curr.next;
                continue;
            }

            ListNode tmpNode = curr.next;// 暂存节点
            curr.next = curr.next.next;// 删除tmpNode

            // 寻找插入位置
            ListNode prev = dummyHead;// 有序链表起点
            while (prev.next.val < tmpNode.val) {
                prev = prev.next;
            }

            // 连接链表
            tmpNode.next = prev.next;
            prev.next = tmpNode;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 5, 3, 4, 0};
        ListNode res = new InsertionSortList().insertionSortList(new ListNode(nums));
        System.out.println(res);
    }
}
