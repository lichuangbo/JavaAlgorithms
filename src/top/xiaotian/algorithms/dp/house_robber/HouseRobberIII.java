package top.xiaotian.algorithms.dp.house_robber;


import top.xiaotian.util.TreeNode;

/**
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * 示例 1:
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 示例 2:
 *
 *
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 *
 * 提示：
 *
 * 树的节点数在[1, 104] 范围内
 * 0 <= Node.val <= 104
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // 方法语义：遍历以root为根的二叉树，将考虑偷取该节点和不偷取该节点的最大收益分别放置到数组0下标和1下标上
    private int[] dfs(TreeNode root) {
        // dfs出口
        if (root == null) {
            return new int[]{0, 0};
        }

        // 后序遍历：因为当前层的决定是由左右孩子决定的
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        // 当前层要做的事
        int[] dp = new int[2];
        // dp[0] 记录偷取当前节点（为了避免报警，只能选择不偷取其孩子节点的收益；同时孩子节点不相连，可以都偷取）
        dp[0] = root.val + left[1] + right[1];
        // dp[1] 记录不偷取当前节点（当前节点不偷取，那么孩子节点都可以偷取,也可以不偷取，需要取最大值）
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return dp;
    }
}
