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
        /*
         * dummy -> 1 -> 2 -> 3 -> nil    index=1,e=0,size=3
         *         prev
         *          1 -> 0 -> 2 -> 3 -> nil
         */
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

        addR(dummyHead, 0, index, e);
    }

    private void addR(Node curr, int currIndex, int index, E e) {
        if (currIndex == index) {
            curr.next = new Node(e, curr.next);
            size++;
            return;
        }

        addR(curr.next, currIndex + 1, index, e);
    }

    public void addFirst(E e) {
        // dummy -> nil    =>   dummy -> e -> nil
        addR(0, e);
    }

    public void addLast(E e) {
        // size对应index，prev指针(注意：prev最初指向的是dummy)会指向到最后一个元素，之后连接一个新节点
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

        return getR(dummyHead.next, 0, index).e;
    }

    public Node getR(Node curr, int currIndex, int index) {
        if (currIndex == index) {
            return curr;
        }

        return getR(curr.next, currIndex + 1, index);
    }

    public E getFirst() {
        return getR(0);
    }

    public E getLast() {
        return getR(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Set failed");
//        Node curr = dummyHead.next;
//        for (int i = 0; i < index; i++) {
//            curr = curr.next;
//        }
//        curr.e = e;

        getR(dummyHead.next, 0, index).e = e;
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
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 记录待删除元素节点
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
        // prev指针指向倒数第二个节点
        return remove(size - 1);
    }

    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            prev.next = prev.next.next;
        }
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

        linkedList.removeElement(99);
        System.out.println(linkedList);
    }
}
