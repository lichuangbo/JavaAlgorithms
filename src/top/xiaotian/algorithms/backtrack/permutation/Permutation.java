package top.xiaotian.algorithms.backtrack.permutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permutation {
    private List<List<Integer>> res;
    private boolean[] visited;

    /**
     * 时间   O(n*n!) n个元素可以实现n!个排列，复制操作O(n)
     * 空间   O(n) 递归栈
     */
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        visited = new boolean[nums.length];
        help(nums, 0, new LinkedList<>());
        return res;
    }

    /**
     * 树形问题
     *              [1,2,3]
     *         1/     2|      3\
     *       [2,3]   [1,3]    [1,2]
     *      2/  3\   ...
     *     [3]   [2]
     * 当选择了2，子问题变成了[1,3]的全排列，所以要使用数组记录当前使用过了元素2；如果使用过了就应该跳过这次选择
     */
    private void help(int[] nums, int index, LinkedList<Integer> curr) {
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                help(nums, index + 1, curr);
                visited[i] = false;
                curr.removeLast();
            }
        }
    }
}
