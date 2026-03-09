package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 *     1
 *   /   \
 *  2     3
 *   \
 *    5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/26 8:55
 * @Description: 描述:
 */
public class BinaryTreePaths {

    /**
     * 分治思想（自底向上）
     * 递归先深入到叶子节点，叶子节点直接返回包含自身值的路径（如 ["5"]）
     * 然后在回溯过程中，将当前节点的值拼接到子路径的前面，形成从当前节点到叶子的完整路径（如 "2->5"）
     * 最终根节点将左右子树返回的路径分别加上自己的值，得到所有根到叶子的路径
     * 时间   O(n^2)  每个节点遍历一次，路径字符串拼接操作O(n)
     * 空间   O(n^2)  递归操作取决于递归栈深度，最坏情况下树退化为单链表  存储路径的空间开销O(n)
     */
    // 方法语义：返回以root为根节点的二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 递归终止条件：将根节点值添加到集合中
        if (root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
            return res;
        }

        // 递归遍历左子树，拿到左子树的结果集，如["2->5"]
        List<String> leftStr = binaryTreePaths(root.left);
        // 把root节点拼接进左子树的路径中
        for (String path : leftStr) {
            StringBuilder sb = new StringBuilder();
            sb.append(root.val).append("->").append(path);
            res.add(sb.toString());
        }
        // 递归遍历右子树，拿到右子树的结果集，如["3"]
        List<String> rightStr = binaryTreePaths(root.right);
        for (String path : rightStr) {
            StringBuilder sb = new StringBuilder();
            sb.append(root.val).append("->").append(path);
            res.add(sb.toString());
        }
        return res;
    }

    // 层序遍历
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(String.valueOf(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {
                paths.add(path);
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                pathQueue.offer(path + "->" + node.left.val);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(path + "->" + node.right.val);
            }
        }
        return paths;
    }

    /**
     * 回溯（自顶向下）
     * 通过一个 pathList 记录从根节点到当前节点的路径，递归过程中不断添加当前节点值，到达叶子时构建完整路径并加入结果，然后回溯时移除
     * 当到达叶子节点时，将 pathList 中记录的路径与当前叶子节点值拼接成完整路径，直接加入结果集
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        help(root, res, new LinkedList<>());
        return res;
    }

    private void help(TreeNode root, List<String> resList, LinkedList<Integer> pathList) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (Integer path : pathList) {
                sb.append(path).append("->");
            }
            sb.append(root.val);
            resList.add(sb.toString());
            return;
        }

        pathList.add(root.val);
        help(root.left, resList, pathList);
        help(root.right, resList, pathList);
        pathList.removeLast();
    }

    public List<String> binaryTreePaths4(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        buildPaths(root, "", result);
        return result;
    }

    /**
     * 含义：从当前节点node出发，收集所有从根到叶子节点的完整路径。由于路径是从根开始的，所以在向下递归时需要知道从根到当前节点已经形成的路径前缀，即path
     */
    private void buildPaths(TreeNode node, String path, List<String> result) {
        if (node == null) {
            return;
        }
        // 将当前节点值加入路径
        String currPath = path + node.val;
        // 如果是叶子节点，将完整路径加入结果
        if (node.left == null && node.right == null) {
            result.add(currPath);
        } else {
            // 非叶子节点，继续递归，并加上 "->" 分隔符
            buildPaths(node.left, currPath + "->", result);
            buildPaths(node.right, currPath + "->", result);
        }
    }
}
