package top.xiaotian.dataStructures.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树实现
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/13
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        // 递归终止条件:程序走到node为空时，一定要创建新node
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            // 假如此时入参node.left是null，那么在向下递归时会返回新创建的node，此时用node.left接收。形成挂接关系
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    //================以下是二叉树遍历================

    public void preOrder() {
        preOrder(root);
    }

    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.print(curr.e + " ");

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        System.out.println();
    }

    // 前序遍历以node为根的二分搜索树
    private void preOrder(Node node) {
        if(node == null) {
            return;
        }

        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (!stack.isEmpty() || curr != null) {
            // 寻找以当前节点为根的最左节点
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            Node tmp = stack.pop();
            System.out.print(tmp.e + " ");
            // 变更curr，下一轮开始遍历右子树
            curr = tmp.right;
        }
        System.out.println();
    }

    // 中序遍历以node为根的二分搜索树
    private void inOrder(Node node) {
        if(node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    public void postOrderNR1() {
        Stack<Node> tmpStack = new Stack<>();
        Stack<Node> resStack = new Stack<>();
        // 借用前序遍历结果，前序：根-左-右，如果采用根-右-左的顺序放入栈中，在出栈时候顺序就变为了左-右-根
        tmpStack.push(root);
        while (!tmpStack.isEmpty()) {
            Node tmp = tmpStack.pop();
            if (tmp.left != null) {
                tmpStack.push(tmp.left);
            }
            if (tmp.right != null) {
                tmpStack.push(tmp.right);
            }

            resStack.push(tmp);
        }
        while(!resStack.isEmpty()) {
            Node tmp = resStack.pop();
            System.out.print(tmp.e + " ");
        }
        System.out.println();
    }

    public void postOrderNR2() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        // prev：最近一次弹栈的节点  top：当前栈顶节点
        Node prev = root, top = root;
        while (!stack.isEmpty()) {
            top = stack.peek();
            // top节点有左孩子 并且 最近一次弹栈的节点不是其左右孩子
            if (top.left != null && prev != top.left && prev != top.right) {
                stack.push(top.left);
            // top节点有右孩子 并且 最近一次弹栈的节点不是其右孩子
            } else if (top.right != null && prev != top.right) {
                stack.push(top.right);
            // top节点没有孩子节点 或者 孩子节点已经被弹出
            } else {
                Node tmp = stack.pop();
                System.out.print(tmp.e + " ");
                prev = top;
            }
        }
        System.out.println();
    }

    // 后序遍历以node为根的二分搜索树
    private void postOrder(Node node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.e + " ");

            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    public void levelOrder2() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                System.out.print(tmp.e + " ");
                if (tmp.left != null) queue.add(tmp.left);
                if (tmp.right != null) queue.add(tmp.right);
            }
        }
        System.out.println();
    }

    //================二叉树遍历结束================

    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    public E removeMin() {
        E ret = minimum();

        root = removeMin(root);
        return ret;
    }

    // 删除以node为根的二分搜索树中的最小节点，并返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        /**
         *      41
         *    /    \
         *   33     58
         *    \    /
         *     37 50
         */
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            // 递归语义中，node已经为根节点（可以仅考虑33-37子树），此时新的的根节点就应该为node.right
            return rightNode;
        }

        // 如果当前根节点还有左子树，就去删除以 其左节点 为根的二分搜索树最小节点
        node.left = removeMin(node.left);// 用node.left接收(可以仅考虑41-33-37子树)，形成挂接关系
        return node;
    }

    public E removeMax() {
        E ret = maximum();

        root = removeMax(root);
        return ret;
    }

    // 删除以node为根的二分搜索树中的最大节点，并返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除以node为根的二分搜索树中值为e的节点，并返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 左右子树都不为空
            // 找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right) ;
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBST(root, 0, sb);
        return sb.toString();
    }

    // 生成以node为根，深度为depth的描述二叉树的字符串
    private void generateBST(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth)).append("null\n");
            return;
        }
        sb.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBST(node.left, depth + 1, sb);
        generateBST(node.right, depth + 1, sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        /***
         *     5
         *   /   \
         *  3     6
         * / \     \
         *2  4      8
         */
        bst.remove(3);
        bst.remove(6);

        System.out.println("preOrder: ");
        bst.preOrder();
        System.out.println();
        bst.preOrderNR();

        System.out.println("inOrder: ");
        bst.inOrder();
        System.out.println();
        bst.inOrderNR();

        System.out.println("postOrder: ");
        bst.postOrder();
        System.out.println();
        bst.postOrderNR1();
        bst.postOrderNR2();

        System.out.println("levelOrder: ");
        bst.levelOrder();
        System.out.println();
        bst.levelOrder2();



//        System.out.println(bst);
        /**
         * 5
         * --3
         * ----2
         * ------null
         * ------null
         * ----4
         * ------null
         * ------null
         * --6
         * ----null
         * ----8
         * ------null
         * ------null
         */
    }
}
