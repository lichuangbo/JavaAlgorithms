package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * 使用单链表实现LRU缓存淘汰算法
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/5
 */
public class LRU4LinkedNode {
    /**
     * 维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。
     * 当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
     *      1.如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
     *      2.如果此数据没有在缓存链表中，又可以分为两种情况：
     *          如果此时缓存未满，则将此结点直接插入到链表的头部；
     *          如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
     */
    private Node lruHead;

    private int capacity;

    private int size;

    public LRU4LinkedNode(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRU4LinkedNode lru4LinkedNode = new LRU4LinkedNode(5);
        lru4LinkedNode.addNode(new Node("1", null));
        lru4LinkedNode.addNode(new Node("2", null));
        lru4LinkedNode.addNode(new Node("3", null));
        lru4LinkedNode.addNode(new Node("5", null));
        System.out.print("before: ");
        lru4LinkedNode.printAll();

        lru4LinkedNode.adjustLRU(new Node("7", null));
        lru4LinkedNode.adjustLRU(new Node("8", null));
        System.out.print("after: ");
        lru4LinkedNode.printAll();
        /**
         * 测试用例：
         *   99     99->1->2->3->5->null
         *   1      1->2->3->5->null
         *   2      2->1->3->5->null
         *   3      3->1->2->5->null
         *   5      5->1->2->3->null
         *  7、8    8->7->1->2->3->null
         */
    }

    public void adjustLRU(Node targetNode) {
        Node specialNode = findSpecialNode(targetNode.value);
        if (specialNode != null) {// 该节点已经缓存在了链表中
            // 本身就在首节点不调整
            if (lruHead.value.equals(targetNode.value)) {
                return;
            }
            boolean flag = deleteSpecialNode(specialNode);
            if (flag) {
                lruHead = insertHead(targetNode);
            }
        } else {// 没有缓存在链表上
            if (size < capacity) {
                lruHead = insertHead(targetNode);
            } else {
                deleteTailNode();
                lruHead = insertHead(targetNode);
            }
        }
    }

    /**
     * 向链表中插入头结点,并返回
     * @param node 拟插入节点
     * @return 头结点
     */
    public Node insertHead(Node node) {
        node.next = lruHead;
        size++;
        return node;
    }


    /**
     * 查找给定value，并返回
     * @param value 查找的内容
     * @return 找到的节点，找不到返回null
     */
    public Node findSpecialNode(Object value) {
        Node tmp = lruHead;
        while (tmp != null) {
            if (tmp.value.equals(value)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    /**
     * 删除给定的节点
     * @param node  给定节点node
     * @return  删除成功true，删除失败false
     */
    public boolean deleteSpecialNode(Node node) {
        // 找到前置节点
        Node tmp = lruHead;
        while (tmp != null && tmp.next != node) {
            tmp = tmp.next;
        }
        if (tmp == null || tmp.next == null) {
            return false;
        }
        // 处理该节点
        tmp.next = tmp.next.next;
        size--;
        return true;
    }

    /**
     * 删除尾结点
     */
    public void deleteTailNode() {
        Node tmp = lruHead;
        while (tmp.next != null && tmp.next.next != null) {
            tmp = tmp.next;
        }
        tmp.next = null;
    }

    /**
     * 在尾部添加节点
     * @param node  节点
     */
    public void addNode(Node node) {
        Node tmp = lruHead;
        if (tmp == null) {
            lruHead = node;
        } else {
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        size++;
    }

    /**
     * 打印输出链表所有节点
     */
    public void printAll() {
        Node tmp = lruHead;
        while (tmp != null) {
            System.out.print(tmp.value + "->");
            tmp = tmp.next;
        }
        System.out.println("null");
    }
}
