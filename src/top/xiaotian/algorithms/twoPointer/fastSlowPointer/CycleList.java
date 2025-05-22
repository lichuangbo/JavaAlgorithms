package top.xiaotian.algorithms.twoPointer.fastSlowPointer;

import top.xiaotian.util.ListNode;

/**
 * 环形链表的判断（快慢指针经典用法）
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 */
public class CycleList {
    /**
     * 141. 环形链表
     * 时间：O(n)
     * 假设head到入环节点长度为a,环长度为b,总节点长度n=a+b
     * 无环情况下：fast走n步，slow走n/2步，总的来说是3n/2步，根据大O表示法应为O(n)
     * 有环情况下：那么当慢指针走到入口(a步)，快指针走了2a步，二者最大距离是b
     * 那么最多再迭代b次二者会相遇，所以时间复杂度是(a + 2a) + (b + 2b) =3n
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            // 非环情况，及时返回
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            // 快慢指针相遇，即说明遇见了环
            if (fast == slow) {
                return true;
            }
        }
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * <p>
     * 说明：不允许修改给定的链表。
     * <p>
     * 原题解：<a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/">...</a>
     * 1. fast走的步数为slow步数的两倍    f = 2 * s           =>   f = 2 * nb， 即fast走的步数是2n个环长度，slow走的步数是n个环长度
     * 2. fast比slow多走了n个环的长度    f = s + nb          =>   s = nb
     * 3. 不管是fast还是slow走到环入口的步数为 k = a + nb
     * 问题：当fast/slow相遇时，slow走了nb步，他还要走a步才能到达环入口位置（a未知！）
     * 解决：重新找一个指针，从head开始向后移动，他到达环入口刚好要走a步，那么只需要和slow保持速率同步，相遇时节点就是环入口
     * <p>
     * 时间：
     * 在快慢指针相遇复杂度基础上，各要走a步，3n+a+a,根据大O表示法还是O(n)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            // 无环，根据题目返回null
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            // 发现环，同时fast slow站在了一起
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
