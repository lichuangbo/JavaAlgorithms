package top.xiaotian.algorithms.recursion;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class LowestCommonAncestor {

    // 450. 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            TreeNode successor = minimum(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;

            return successor;
        }
    }

    // 寻找以node为根节点的最小数的TreeNode节点
    private TreeNode minimum(TreeNode node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    // 删除以node为根节点的最小值后，返回新的根节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 时间：O(n), 节点全部遍历
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<TreeNode> treeNodes = inOrder(root, new ArrayList<>());
        return treeNodes.get(k - 1).val;
    }

    // 递归语义：返回以root为根节点的中序遍历的结果
    private ArrayList<TreeNode> inOrder(TreeNode root, ArrayList<TreeNode> currList) {
        if (root == null) {
            return currList;
        }

        // 递归取树中最小节点值
        inOrder(root.left, currList);
        // 将他加入到集合中
        currList.add(root);
        inOrder(root.right, currList);
        return currList;
    }

    // 时间：O(H+k) H指树高，因为即使k=1，也要遍历到最小节点，并且每加入一个节点都要这样遍历； 遍历到第k个节点时可以退出
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (true) {
            while (root != null) {
                deque.addLast(root);
                root = root.left;
            }

            TreeNode tmp = deque.removeLast();
            if (--k == 0) {
                return tmp.val;
            }
            root = tmp.right;
        }
    }

    public static void main(String[] args) {
//        String[] nums = {"2", "1", "3"};
//        String[] nums = {"5", "1", "4", "null", "null", "3", "6"};
        String[] nums = {"5", "3", "6", "2", "4", "null", "null", "1"};
        TreeNode root = new TreeNode(nums);
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
//        TreeNode res = lowestCommonAncestor.lowestCommonAncestor(root, new TreeNode(1), new TreeNode(2));
//        System.out.println(res.val);

//        boolean validBST = lowestCommonAncestor.isValidBST(root);
//        System.out.println(validBST);

//        TreeNode res = lowestCommonAncestor.deleteNode(root, 1);
//        TreeNode res = lowestCommonAncestor.removeMin(root);
//        System.out.println(res);

        int res = lowestCommonAncestor.kthSmallest2(root, 3);
        System.out.println(res);
    }
}
