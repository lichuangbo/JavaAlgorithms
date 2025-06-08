package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点； 如果找到了，删除它。
 *
 *
 *
 * 示例 1:
 *
 * 输入：root = [5,3,6,2,4,null,7], key = 3 输出：[5,4,6,2,null,null,7] 解释：给定需要删除的节点值是 3，所以我们首先找到 3
 * 这个节点，然后删除它。 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 * @author lichuangbo
 * @date 2022/9/26
 */
public class DeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {// 已经找到了待删除的节点
            TreeNode left = root.left;
            TreeNode right = root.right;
            // 寻找比待删除节点大的最小节点，也就是寻找待删除节点右子树最小的叶子节点
            while (right != null && right.left != null) {
                right = right.left;
            }
            // 如果存在右侧最小的叶子节点，将root的左子树拼接到右侧最小叶子节点的左子树(如删2,当前层要做的是将1挂到4的左子树上，然后将4返回)
            /**
             *        5                    5                  5
             *      /   \                /   \              /   \
             *     2     7      =>      2     7    =>      4     7
             *   /  \   /                \   /            /     /
             *  1    4 6                  4 6            3     6
             *      /                    /              /
             *     3                    3              1
             *                         /
             *                        1
             */
            if (right != null) {
                right.left = left;
                // 不能return right; right是root.right的最左子节点，而root.right是整个右子树的根, 返回root.right可以保留完整的右子树
                return root.right;
            } else {// 如果不存在右侧最小的叶子节点，root的右子树为空，直接返回左子树(如删7,当前层要做的就是将6返回)
                /**
                 *        5                    5
                 *      /   \                /   \
                 *     2     7      =>      2     6
                 *   /  \   /             /  \
                 *  1    4 6             1    4
                 *      /                    /
                 *     3                    3
                 */
                return left;
            }
        }
        return root;
    }

}
