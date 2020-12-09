package top.xiaotian.dataStructures.queue;

/**
 * 链式队列(尾指针：队尾插入，队首删除)
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class LinkedQueue<E> implements Queue<E> {
    private class Node {
        public E e;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enQueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("deQueue failed");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        // 队列只有一个节点时特殊处理
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front ");
        Node curr = head;
        while (curr != null) {
            sb.append(curr).append("->");
            curr = curr.next;
        }
        sb.append("NULL tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for (int i = 1; i <= 5; i++) {
            linkedQueue.enQueue(i);
            System.out.println(linkedQueue);
        }

        System.out.println(linkedQueue.deQueue());
        System.out.println(linkedQueue);

        linkedQueue.enQueue(10);
        System.out.println(linkedQueue);
    }
}
