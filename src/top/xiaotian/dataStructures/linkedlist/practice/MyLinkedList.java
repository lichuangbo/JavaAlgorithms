package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * 707. 设计链表
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
 * <p>
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * <p>
 * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
 * <p>
 * 实现 MyLinkedList 类：
 * <p>
 * MyLinkedList() 初始化 MyLinkedList 对象。
 * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
 * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
 * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
 * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
 * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * 输出
 * [null, null, null, null, 2, null, 3]
 * <p>
 * 解释
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
 * myLinkedList.get(1);              // 返回 2
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
 * myLinkedList.get(1);              // 返回 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= index, val <= 1000
 * 请不要使用内置的 LinkedList 库。
 * 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。
 */
public class MyLinkedList {

    private Node head;
    private int size;
    private final Node dummyHead = new Node(-1, head);

    private static class Node {
        int val;
        Node next;

        public Node() {

        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public MyLinkedList() {
        head = new Node();
        size = 0;
    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        }

        // 按下标查找：需要寻找的是当前元素，初始应该指向head
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        // 插入：需要寻找的是上一个元素，初始应该指向dummyHead
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(val, prev.next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }

        // 删除：需要寻找的是上一个元素，初始应该指向dummyHead
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
        size--;
    }
}
