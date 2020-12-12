package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * 删除链表中等于给定值 val 的所有节点
 * 1->2->6->3->4->5->6, val = 6
 * 1->2->3->4->5
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class RemoveElements {
    /***
     * 时间：遍历一遍链表，O(n)
     * 空间：O(1)
     * @param head
     * @param val
     * @return
     */
    public Node removeElements(Node head, int val) {
        // 处理头节点匹配情况
        while (head != null && head.value.equals(val)) {
            Node delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 节点全匹配时，说明while循环已经处理完了，返回
        if (head == null) {
            return head;
        }

        // 处理中间节点
        Node prev = head;
        while (prev.next != null) {
            if (prev.next.value.equals(val)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                // 此时不能移动prev，因为prev也可能是匹配的
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public Node removeElements2(Node head, int val) {
        // 使用虚拟头节点可以简化处理逻辑
        Node dummy = new Node(-1, null);
        dummy.next = head;

        Node prev = dummy;
        while (prev.next != null) {
            if (prev.next.value.equals(val)) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }
}
