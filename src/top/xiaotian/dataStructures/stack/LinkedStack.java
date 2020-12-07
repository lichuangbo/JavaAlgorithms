package top.xiaotian.dataStructures.stack;

import top.xiaotian.dataStructures.linkedNode.Node;

/**
 * 链式栈
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class LinkedStack {
    private Node stackHead;

    private Node tail;

    public LinkedStack() {
        tail = stackHead;
    }

    public boolean push(Object obj) {
        if (stackHead == null) {
            stackHead = new Node(obj, null);
            tail = stackHead;
        } else {
            tail.next = new Node(obj, null);
            tail = tail.next;
        }
        return true;
    }

    public Object pop() {
        if (stackHead == null) {
            return null;
        }
        Object value = tail.value;
        // 删除最后一个节点
        Node tmp = stackHead;
        while (tmp.next != tail) {
            tmp = tmp.next;
        }
        tmp.next = null;
        // 更新tail指向
        tail = tmp;
        return value;
    }

    public void printAll() {
        Node tmp = stackHead;
        while (tmp != null) {
            System.out.print(tmp.value + "->");
            tmp = tmp.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.printAll();

        boolean flag1 = linkedStack.push(8);
        System.out.println(flag1);
        linkedStack.printAll();

        Object pop = linkedStack.pop();
        System.out.println(pop);
        linkedStack.printAll();

        linkedStack.push(9);
        linkedStack.printAll();
    }
}
