package top.xiaotian.dataStructures.stack;

import java.util.Arrays;

/**
 * 顺序栈
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class ArrayStack {
    private Object[] item;

    private int size;

    private int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.item = new Object[capacity];
        this.size = 0;
    }

    public boolean push(Object obj) {
        if (size == capacity) {
//            return false;
            // 扩容
            Object[] newItem = new Object[capacity * 2];
            System.arraycopy(item, 0, newItem, 0, capacity);
            item = newItem;
        }
        item[size] = obj;
        size++;
        return true;
    }

    public Object pop() {
        if (size == 0) {
            return null;
        }
        Object tmp = item[size - 1];
        size--;
        return tmp;
    }

    public void printAll() {
//        System.out.println(Arrays.toString(item));
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.printAll();

        boolean flag1 = arrayStack.push(8);
        System.out.println(flag1);
        arrayStack.printAll();

        Object pop = arrayStack.pop();
        System.out.println(pop);
        arrayStack.printAll();

        arrayStack.push(9);
        arrayStack.printAll();
    }
}
