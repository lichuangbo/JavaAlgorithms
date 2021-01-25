package top.xiaotian.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/18 16:44
 * @Description: 描述:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) { val = x; }

    public TreeNode(String[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums is empty");
        }

        this.val = Integer.parseInt(nums[0]);
        TreeNode curr = this;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        int i = 1;
        // 根据层序遍历结果重建二叉树
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i >= nums.length) break;
            if(!nums[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(nums[i]));
                queue.add(node.left);
            }
            i++;
            if (i >= nums.length) break;
            if(!nums[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(nums[i]));
                queue.add(node.right);
            }
            i++;
        }
    }
}
