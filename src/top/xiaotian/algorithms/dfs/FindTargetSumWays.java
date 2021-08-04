package top.xiaotian.algorithms.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 */
public class FindTargetSumWays {

  private int res = 0;

  private final Map<String, Integer> mono = new HashMap<>();

  /**
   * 暴力DFS：【使用全局变量维护】
   * 时间：O(2^n)，每一个元素有两个选择，+或者-
   * 空间：O(n),递归树深度不超过n
   */
  public int findTargetSumWays(int[] nums, int S) {
    help(nums, S, 0, 0);
    return res;
  }

  private void help(int[] nums, int s, int index, int curr) {
    if (index == nums.length) {
      if (curr == s) res++;
      return;
    }
    // 当前元素选+
    help(nums, s, index + 1, curr + nums[index]);
    // 当前元素选-
    help(nums, s, index + 1, curr - nums[index]);
  }

  /**
   * 暴力DFS：【接收返回值处理】
   */
  public int findTargetSumWays2(int[] nums, int S) {
    return dfs(nums, S, 0, 0);
  }

  int dfs(int[] nums, int s, int index, int curr) {
    if (index == nums.length) {
      return curr == s ? 1 : 0;
    }
    // 选择+
    int and = dfs(nums, s, index + 1, curr + nums[index]);
    // 选择-
    int minus = dfs(nums, s, index + 1, curr - nums[index]);
    return and + minus;
  }

  /**
   * DFS:记忆化搜索
   */
  public int findTargetSumWays3(int[] nums, int S) {
    return dfs2(nums, S, 0, 0);
  }

  int dfs2(int[] nums, int s, int index, int curr) {
    // mono记忆化：减少重复搜索
    String key = index + "_" + curr;
    if (mono.containsKey(key)) {
      return mono.get(key);
    }
    if (index == nums.length) {
      mono.put(key, curr == s ? 1 : 0);
      return mono.get(key);
    }
    int and = dfs2(nums, s, index + 1, curr + nums[index]);
    int minus = dfs2(nums, s, index + 1, curr - nums[index]);
    mono.put(key, and + minus);
    return mono.get(key);
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 1, 1, 1};
    int res = new FindTargetSumWays().findTargetSumWays3(nums, 3);
    System.out.println(res);
  }
}
