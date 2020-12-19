package top.xiaotian.dataStructures.linkedlist;

/**
 * 链表实现
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class LinkedList<E> {
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
    // 虚拟头节点
    private final Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    // 递归写法
    public void addR(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed");

        addR(dummyHead, index, e);
    }

    private void addR(Node curr, int index, E e) {
        if (index == 0) {
            curr.next = new Node(e, curr.next);
            size++;
            return;
        }

        addR(curr.next, index - 1, e);
    }

    public void addFirst(E e) {
        addR(0, e);
    }

    public void addLast(E e) {
        addR(size, e);
    }

    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed");
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    public E getR(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed");

        return getR(dummyHead.next, index).e;
    }

    public Node getR(Node curr, int index) {
        if (index == 0) {
            return curr;
        }

        return getR(curr.next, index - 1);
    }

    public E getFirst() {
        return getR(0);
    }

    public E getLast() {
        return getR(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed");
//        Node curr = dummyHead.next;
//        for (int i = 0; i < index; i++) {
//            curr = curr.next;
//        }
//        curr.e = e;

        getR(dummyHead.next, index).e = e;
    }

    public boolean contains(E e) {
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.e.equals(e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;

        size--;
        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = dummyHead.next;
        while (curr != null) {
            sb.append(curr.e).append("->");
            curr = curr.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList);
        }

        linkedList.addFirst(0);
        System.out.println(linkedList);

        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.getSize());
        System.out.println(linkedList.contains(5));
        linkedList.set(4, 99);
        System.out.println(linkedList);

        System.out.println(linkedList.remove(2));
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
