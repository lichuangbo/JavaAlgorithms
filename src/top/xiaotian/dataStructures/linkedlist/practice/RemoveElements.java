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

    // 递归解法
    public Node removeElements3(Node head, int val) {
        // 最基本的问题：链表为空时，返回null
        if (head == null) {
            return null;
        }
        // 基于递归宏观语义处理子问题，拿到了基于子问题的一个头结点
        Node res = removeElements3(head.next, val);
        // 片段头结点和val是否相等，处理连接关系
        if (head.value.equals(val)) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }


    public Node removeElements4(Node head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call remove " + val + " in: " + head);
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return null;
        }

        Node res = removeElements4(head.next, val, depth + 1);

        System.out.print(depthString);
        System.out.println("After remove " + val + ":" + res);
        Node ret = null;
        if (head.value.equals(val)) {
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
        Node head1 = new Node("1", new Node("2", new Node("6", new Node("3", new Node("4", new Node("5", new Node("6", null)))))));
        new RemoveElements().removeElements4(head1, 6, 0);
    }
}
