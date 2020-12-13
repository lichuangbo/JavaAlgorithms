package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/5
 */
public class Node {
    public Object value;

    public Node next;

    public Node() {

    }

    public Node(Object value) {
        this(value, null);
    }

    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node(Object[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr is empty");

        this.value = arr[0];
        Node curr = this;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = this;
        while (curr != null) {
            sb.append(curr.value).append("->");
            curr = curr.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
