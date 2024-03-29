package top.xiaotian.algorithms.dp.knapsack01;

/**
 * @author lichuangbo
 * @date 2022/8/4
 */
public class LastStoneWeight {

  /**
   * 1049. 最后一块石头的重量 II
   * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
   *
   * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
   *
   * 如果 x == y，那么两块石头都会被完全粉碎；
   * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
   * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
   *
   *
   *
   * 示例 1：
   *
   * 输入：stones = [2,7,4,1,8,1]
   * 输出：1
   * 解释：
   * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
   * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
   * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
   * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
   * 示例 2：
   *
   * 输入：stones = [31,26,33,21,40]
   * 输出：5
   *
   *
   * 提示：
   *
   * 1 <= stones.length <= 30
   * 1 <= stones[i] <= 100
   */
  public int lastStoneWeightII(int[] stones) {
    /**
     * 将较大的石子放入正数堆，较小的石子放入负数堆，然后求解两堆的最小差值
     * 转化为01背包问题
     * 在背包容量为m/2的前提下，向背包中装入石头，使得背包价值最高
     */
    int sum = 0;
    for (int stone : stones) {
      sum += stone;
    }
    int len = stones.length;
    int[][] dp = new int[len][sum / 2 + 1];
    // 初始化
    for (int j = 0; j <= sum / 2; j++) {
      dp[0][j] = (stones[0] > j) ? 0 : stones[0];
    }
    // 遍历
    for (int i = 1; i < len; i++) {
      for (int j = 0; j <= sum / 2; j++) {
        if (stones[i] > j) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
        }
      }
    }
    // 总容量-最大背包数目=负数堆  负数堆-最大背包数目得到差值
    return Math.abs((sum - dp[len - 1][sum / 2]) - dp[len - 1][sum / 2]);
  }

  public int lastStoneWeightII2(int[] stones) {
    int sum = 0;
    for (int stone : stones) {
      sum += stone;
    }
    int len = stones.length;
    int[] dp = new int[sum / 2 + 1];
    // 初始化
    for (int j = 0; j <= sum / 2; j++) {
      dp[j] = (stones[0] > j) ? 0 : stones[0];
    }
    // 遍历
    for (int i = 1; i < len; i++) {
      for (int j = sum / 2; j >= stones[i]; j--) {
        dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
      }
    }
    return Math.abs((sum - dp[sum / 2]) - dp[sum / 2]);
  }
}
