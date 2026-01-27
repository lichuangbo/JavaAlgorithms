package top.xiaotian.algorithms.backtrack.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. 全排列 II
 * <p>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2] 输出： [[1,1,2], [1,2,1], [2,1,1]]
 */
public class PermutationII {

    private List<List<Integer>> res;
    private boolean[] visited;

    /**
     * 时间   O(n*n!) n个元素可以实现n!个排列，复制操作O(n)
     * 空间   O(n) 递归栈
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        help(nums, 0, new LinkedList<>());
        return res;
    }

    /**
     *                [1,1,2]
     *            1/    1|×    2\
     *          [1,2]          [1,1]
     *         1/  2\          1/  1\×
     *        [2]  [1]        [1]
     * 选择1后向下递归，由于下标0已经标记访问过，开始访问下标1，下标1没有访问过但是满足了过滤条件，会跳过
     * 添加![visited[i-1]条件就是为了放开这种情况
     * 去重逻辑
     * @see top.xiaotian.algorithms.twoPointer.collisionPointer.ThreeSum
     */
    private void help(int[] nums, int index, LinkedList<Integer> curr) {
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            curr.add(nums[i]);
            visited[i] = true;
            help(nums, index + 1, curr);
            curr.removeLast();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        new PermutationII().permuteUnique(new int[]{1, 1, 2});
    }
}
