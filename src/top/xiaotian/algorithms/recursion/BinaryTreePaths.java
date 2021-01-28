package top.xiaotian.algorithms.recursion;

import top.xiaotian.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/26 8:55
 * @Description: 描述:
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 将根节点值添加到集合中
        if (root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
            return res;
        }

        // 递归遍历左子树，拿到一个结果集
        List<String> leftStr = binaryTreePaths(root.left);
        // 遍历左子树结果集，将以当前节点为根的左子树所有路径加入结果集
        for (int i = 0; i < leftStr.size(); i++) {
            res.add(root.val + "->" + leftStr.get(i));
        }
        List<String> rightStr = binaryTreePaths(root.right);
        for (int i = 0; i < rightStr.size(); i++) {
            res.add(root.val + "->" + rightStr.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        String[] nums = {"1", "2", "3", "null", "5"};
        TreeNode root = new TreeNode(nums);
        List<String> res = new BinaryTreePaths().binaryTreePaths(root);
        System.out.println(res);
    }
}
