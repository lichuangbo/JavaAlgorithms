package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.*;

/**
 * 129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 *     1
 *   /  \
 *  2    3
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 *      4
 *     / \
 *    9   0
 *   / \
 *  5  1
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        List<String> paths = help(root);
        int res = 0;
        for (String path : paths) {
            char[] chars = path.toCharArray();
            int sum = 0;
            for (char ch : chars) {
                sum = (sum * 10) + (ch - '0');
            }
            res += sum;
        }
        return res;
    }

    /**
     * 基于二叉树的所有路径题目思想，先遍历出所有的路径
     * @see BinaryTreePaths
     */
    private List<String> help(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        if (root.left == null && root.right == null) {
            paths.add(String.valueOf(root.val));
            return paths;
        }
        List<String> leftPaths = help(root.left);
        for (String leftPath : leftPaths) {
            paths.add(root.val + leftPath);
        }
        List<String> rightPaths = help(root.right);
        for (String rightPath : rightPaths) {
            paths.add(root.val + rightPath);
        }
        return paths;
    }

    // 递归
    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * 左右子树的数字并不是直接从它们的根开始算，而是要带上从整棵树根到它们父节点的前缀
     * 所以递归函数，需要接收一个节点和当前从根到该节点父节点所形成的数字前缀，然后返回从这个节点出发的所有叶子路径代表的数字之和
     * 方法含义：给定节点 node 以及从根到 node 的父节点所构成的数字 currSum，返回从 node 出发（包括 node）到所有叶子节点的路径数字之和
     */
    public int dfs(TreeNode root, int currSum) {
        if (root == null) {
            return 0;
        }

        int sum = currSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    // 层序遍历
    public int sumNumbers3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodeQueue = new LinkedList<>();
        Deque<Integer> sumQueue = new LinkedList<>();
        int res = 0;
        nodeQueue.addLast(root);
        sumQueue.addLast(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode tmpNode = nodeQueue.pollLast();
            Integer tmpSum = sumQueue.pollLast();
            if (tmpNode.left == null && tmpNode.right == null) {
                res += tmpSum;
            } else {
                if (tmpNode.left != null) {
                    nodeQueue.addLast(tmpNode.left);
                    sumQueue.addLast(tmpSum * 10 + tmpNode.left.val);
                }
                if (tmpNode.right != null) {
                    nodeQueue.addLast(tmpNode.right);
                    sumQueue.addLast(tmpSum * 10 + tmpNode.right.val);
                }
            }
        }
        return res;
    }
}
