package top.xiaotian.dataStructures.stack;

import top.xiaotian.dataStructures.array.Array;

/**
 * 顺序栈
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class ArrayStack<E> implements Stack<E> {
    private final Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] top");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(4);
        System.out.println("empty: " + arrayStack.isEmpty());
        for (int i = 1; i <= 4; i++) {
            arrayStack.push(i);
            System.out.println(arrayStack);
        }

        arrayStack.push(8);
        System.out.println(arrayStack);

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack);

        arrayStack.push(9);
        System.out.println(arrayStack);
    }
}
