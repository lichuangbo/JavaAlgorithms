package top.xiaotian.algorithms.linkedList;

import top.xiaotian.util.ListNode;

/**
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class RemoveElements {
    /***
     * 时间：遍历一遍链表，O(n)
     * 空间：O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        // 处理头结点匹配情况
        while (head != null && head.val == val) {
            head = head.next;
        }

        // 全匹配情况，直接返回
        if (head == null) {
            return head;
        }

        ListNode curr = head;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
                // 此时不可以移动curr指针，因为curr.next.next也可能等于val(如166)，移动后就会漏掉元素
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        // 使用虚拟头节点，可以简化处理逻辑，不用特殊考虑头节点匹配情况
        // 虚拟节点值设置为-1，因为题目中 1 <= Node.val <= 50，-1刚好是一个无效值
        ListNode dummyHead = new ListNode(-1, head);

        ListNode curr = dummyHead;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummyHead.next;
    }

    // 递归解法
    public ListNode removeElements3(ListNode head, int val) {
        // 最基本的问题：链表为空时，返回null
        if (head == null) {
            return null;
        }

        // 基于递归宏观语义处理子问题，拿到了基于子问题的一个头结点
        ListNode res = removeElements3(head.next, val);
        // 判断头结点和val是否相等，处理连接关系
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }


    public ListNode removeElements4(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call remove " + val + " in: " + head);
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return null;
        }

        ListNode res = removeElements4(head.next, val, depth + 1);

        System.out.print(depthString);
        System.out.println("After remove " + val + ":" + res);
        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        /**
         * 递归调用过程
         * Call remove 6 in: 1->2->6->3->4->5->6->NULL
         * --Call remove 6 in: 2->6->3->4->5->6->NULL
         * ----Call remove 6 in: 6->3->4->5->6->NULL
         * ------Call remove 6 in: 3->4->5->6->NULL
         * --------Call remove 6 in: 4->5->6->NULL
         * ----------Call remove 6 in: 5->6->NULL
         * ------------Call remove 6 in: 6->NULL
         * --------------Call remove 6 in: null
         * --------------Return: null           返回给上一层
         * ------------After remove 6:null      程序回到上一层时拿到的子问题结果
         * ------------Return: null             程序在这一层判断逻辑，返回
         * ----------After remove 6:null
         * ----------Return: 5->NULL
         * --------After remove 6:5->NULL
         * --------Return: 4->5->NULL
         * ------After remove 6:4->5->NULL
         * ------Return: 3->4->5->NULL
         * ----After remove 6:3->4->5->NULL
         * ----Return: 3->4->5->NULL
         * --After remove 6:3->4->5->NULL
         * --Return: 2->3->4->5->NULL
         * After remove 6:2->3->4->5->NULL
         * Return: 1->2->3->4->5->NULL
         */
        ListNode head = new ListNode(new int[]{1, 2, 6, 6, 4, 5, 6});
        ListNode node = new RemoveElements().removeElements4(head, 6, 0);
        System.out.println();
    }
}
