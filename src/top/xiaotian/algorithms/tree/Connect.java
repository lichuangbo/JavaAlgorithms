package top.xiaotian.algorithms.tree;


import top.xiaotian.util.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针 问题
 */
public class Connect {

  /**
   * 116. 填充每个节点的下一个右侧节点指针
   * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
   * <p>
   * struct Node {
   * int val;
   * Node *left;
   * Node *right;
   * Node *next;
   * }
   * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
   * <p>
   * 初始状态下，所有 next 指针都被设置为 NULL。
   */
  // 递归
  public Node connect(Node root) {
    if (root == null) {
      return null;
    }
    if (root.left != null) {
      root.left.next = root.right;
    }
    if (root.right != null) {
      root.right.next = (root.next == null) ? null : root.next.left;
    }
    connect(root.left);
    connect(root.right);
    return root;
  }

  // 层序遍历：非最优解，没有利用到之前构建的next信息
  public Node connect2(Node root) {
    if (root == null) {
      return null;
    }
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node tmpNode = queue.poll();
        if (i != size - 1) {
          tmpNode.next = queue.peek();
        }
        if (tmpNode.left != null) {
          queue.offer(tmpNode.left);
        }
        if (tmpNode.right != null) {
          queue.offer(tmpNode.right);
        }
      }
    }
    return root;
  }

  /**
   * 117. 填充每个节点的下一个右侧节点指针 II
   * 给定一个二叉树
   *
   * struct Node {
   *   int val;
   *   Node *left;
   *   Node *right;
   *   Node *next;
   * }
   * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
   *
   * 初始状态下，所有 next 指针都被设置为 NULL。
   */
  // 和上一题不同的是，它是二叉树，116的递归思路走不通，因为不确定上层节点是否有左子节点；但是层序遍历是一样适用的
}
