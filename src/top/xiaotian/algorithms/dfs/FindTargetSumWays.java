package top.xiaotian.algorithms.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 返回可以通过上述方法构造的、运算结果等于 target
 * 的不同 表达式 的数目。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3 输出：5 解释：一共有 5 种方法让最终目标和为 3 。 -1 + 1 + 1 + 1 + 1 = 3 +1 - 1 + 1
 * + 1 + 1 = 3 +1 + 1 - 1 + 1 + 1 = 3 +1 + 1 + 1 - 1 + 1 = 3 +1 + 1 + 1 + 1 - 1 = 3
 */
public class FindTargetSumWays {

  private int res = 0;

  private final Map<String, Integer> memo = new HashMap<>();

  /**
   * 暴力DFS：【使用全局变量维护】 时间：O(2^n)，每一个元素有两个选择，+或者- 空间：O(n),递归树深度不超过n
   */
  public int findTargetSumWays(int[] nums, int S) {
    help(nums, S, 0, 0);
    return res;
  }

  private void help(int[] nums, int s, int index, int curr) {
    if (index == nums.length) {
      if (curr == s) {
        res++;
      }
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
    // memo记忆化：减少重复搜索
    String key = index + "_" + curr;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    if (index == nums.length) {
      memo.put(key, curr == s ? 1 : 0);
      return memo.get(key);
    }
    int and = dfs2(nums, s, index + 1, curr + nums[index]);
    int minus = dfs2(nums, s, index + 1, curr - nums[index]);
    memo.put(key, and + minus);
    return and + minus;
  }

  public int findTargetSumWays4(int[] nums, int S) {
    /**
     * 加法和为a,减法和为b，那么a-b=S   同时a+b=sum，那么a=(S+sum)/2
     * 问题转化为01背包问题
     * 背包容量为a,物品在nums数组中，问有几种方法可以填满这个背包？
     * dp[i][j]表示在[0...i]内选择元素来填满j的容量，可行的方案数
     * dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
     */
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if ((S + sum) % 2 != 0) {
      return 0;
    }
    int len = nums.length;
    int cap = (S + sum) / 2;
    int[][] dp = new int[len][cap + 1];
    // 初始化： 恰好装满背包和不超过背包容量初始化是不一样的，恰好等于容量才是合法的
    dp[0][0] = 1;
    for (int j = 1; j <= cap; j++) {
      dp[0][j] = (nums[0] == j) ? 1 : 0;
    }
    System.out.println(Arrays.deepToString(dp));
    // 遍历
    for (int i = 1; i < len; i++) {
      for (int j = 0; j <= cap; j++) {
        if (nums[i] > j) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
        }
      }
    }
    System.out.println(Arrays.deepToString(dp));
    return dp[len - 1][cap];
  }

  public int findTargetSumWays5(int[] nums, int target) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if ((target + sum) % 2 != 0) {
      return 0;
    }
    int cap = (target + sum) / 2;
    if (cap < 0) {
      cap = -cap;
    }
    int[] dp = new int[cap + 1];
    dp[0] = 1;
//    for (int j = 1; j <= cap; j++) {
//      dp[j] = (nums[0] == j) ? 1 : 0;
//    }
    System.out.println(Arrays.toString(dp));
    for (int i = 0; i < nums.length; i++) {
      for (int j = cap; j >= nums[i]; j--) {
        dp[j] += dp[j - nums[i]];
      }
      System.out.println(Arrays.toString(dp));
    }
    return dp[cap];
  }

  public static void main(String[] args) {
    int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
    int res = new FindTargetSumWays().findTargetSumWays4(nums, 1);
    System.out.println(res);
  }
}
