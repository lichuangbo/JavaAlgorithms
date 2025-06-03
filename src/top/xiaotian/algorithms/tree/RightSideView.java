package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,3,null,5,null,4]
 *
 * 输出：[1,3,4]
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,null,null,null,5]
 *
 * 输出：[1,3,4,5]
 *
 * 解释：
 *
 *
 *
 * 示例 3：
 *
 * 输入：root = [1,null,3]
 *
 * 输出：[1,3]
 *
 * 示例 4：
 *
 * 输入：root = []
 *
 * 输出：[]
 *
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/19 10:06
 * @Description: 描述:
 */
public class RightSideView {
    /**
     * 时间: O(n)
     * 空间：O(n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        List<Integer> resList = new ArrayList<>();
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = deque.pollFirst();
                if (i == 0) {
                    resList.add(tmpNode.val);
                }
                if (tmpNode.right != null) {
                    deque.offerLast(tmpNode.right);
                }
                if (tmpNode.left != null) {
                    deque.offerLast(tmpNode.left);
                }
            }
        }
        return resList;
    }
}
