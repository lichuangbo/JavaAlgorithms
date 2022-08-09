package top.xiaotian.algorithms.dp;

/**
 * 完全背包问题
 * 和01背包基本一致，不过完全背包问题中，每一个物品都有无限个
 */
public class KnapsackTotal {
  public int knapsackTotal_dp(int[] w, int[] v, int C) {
    /**
     * 三个物品 重量[1, 2, 3]  价值[6, 10, 12]  背包容量5, 3*6的二维数组标识状态
     * 列：物品编号  行：背包容量
     *      0   1    2    3    4    5
     *  0   0   6   12   18   24   30      第一行表示仅考虑0号物品 对应第0列表示：容量为0时任何物品都放不了，背包价值为0   对应第1列表示：容量为1时且仅考虑0号物品，那么他的背包价值为6  其他列容量大于1，可以选择多个0号物品
     *  1   0   6   12   18   24   30      第二行表示考虑0,1两个物品 对应第1列表示：容量为1只能放入0号物品，背包最大价值还为6  对应第2列表示：如果不考虑1号物品，那么他价值为(0, 2)-12  如果考虑1号物品，1号物品重量为2，他要取(0, 2-2)的值再加上1号物品重量为10 最后取最大值为12
     *  2   0   6   12   18   24   30
     */
    int[] dp = new int[C + 1];
    for (int i = 0; i < w.length; i++) { // 遍历物品
      for (int j = w[i]; j <= C; j++) { // 遍历背包容量
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
      }
    }
    return dp[C];
  }
}