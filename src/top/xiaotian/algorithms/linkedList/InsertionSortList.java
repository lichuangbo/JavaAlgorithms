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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode res = new ListNode(head.val);// 初始化一个有序链表
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val < res.val) {// 追加到有序链表中
                ListNode tmp = new ListNode(curr.val);
                tmp.next = res;
                res = tmp;
            } else {// 和有序链表比较
                ListNode tCurr = res, prev = res;
                while (tCurr != null) {
                    if (tCurr.val < curr.val) {
                        prev = tCurr;
                        tCurr = tCurr.next;
                    } else {
                        ListNode tmp = new ListNode(curr.val);
                        prev.next = tmp;
                        tmp.next = tCurr;
                    }
                }
            }
            curr = curr.next;
        }
        return res;
    }

    public ListNode insertionSortList2(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode curr = dummyHead;
        while (curr.next != null) {
            // 有序直接跳过
            if (curr.val < curr.next.val) {
                curr = curr.next;
                continue;
            }

            ListNode prev = dummyHead.next;// 有序链表起点
            while (prev.val < curr.next.val) {
                prev = prev.next;
            }


        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 5, 3, 4, 0};
        ListNode res = new InsertionSortList().insertionSortList(new ListNode(nums));
        System.out.println(res);
    }
}
