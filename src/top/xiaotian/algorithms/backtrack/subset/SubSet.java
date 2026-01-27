package top.xiaotian.algorithms.backtrack.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class SubSet {
    private List<List<Integer>> resList;

    /**
     * 时间 O(n*2^n) n个元素会产生2^n个子集，resList.add复制操作是O(n)
     * 空间 O(n)递归栈
     */
    public List<List<Integer>> subsets(int[] nums) {
        resList = new ArrayList<>();
        help(nums, 0, new LinkedList<>());
        return resList;
    }

    /**
     * 每个节点都是有效子集，所以每次递归入口都加入结果集
     *         []
     *      1/    2\
     *     [1]     [2]
     *    2/
     *  [1,2]
     */
    private void help(int[] nums, int index, LinkedList<Integer> curr) {
        resList.add(new ArrayList<>(curr));

        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            help(nums, i + 1, curr);
            curr.removeLast();
        }
    }
}
