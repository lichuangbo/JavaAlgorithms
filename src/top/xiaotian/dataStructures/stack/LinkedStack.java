package top.xiaotian.dataStructures.stack;

import top.xiaotian.dataStructures.linkedlist.LinkedList;

/**
 * 链式栈
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class LinkedStack<E> implements Stack<E> {

    private final LinkedList<E> linkedList;

    public LinkedStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new IllegalArgumentException("stack is empty");

        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new IllegalArgumentException("stack is empty");

        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ");
        sb.append(linkedList);
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        System.out.println("empty: " + linkedStack.isEmpty());
        for (int i = 1; i <= 4; i++) {
            linkedStack.push(i);
            System.out.println(linkedStack);
        }

        System.out.println(linkedStack.pop());
        System.out.println(linkedStack);

        linkedStack.push(8);
        System.out.println(linkedStack);

        System.out.println(linkedStack.peek());
        System.out.println(linkedStack.getSize());
    }
}
