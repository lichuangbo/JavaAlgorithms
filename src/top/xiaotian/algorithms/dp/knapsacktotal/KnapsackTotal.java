package top.xiaotian.algorithms.dp.knapsacktotal;

/**
 * 完全背包问题
 * 和01背包基本一致，不过完全背包问题中，每一个物品都有无限个
 */
public class KnapsackTotal {

    /**
     * 对于第i种物品（i>=1），在容量j的情况下，我们有两种选择：
     *       a. 不选第i种物品：则 dp[i][j] = dp[i-1][j]
     *       b. 选k件第i种物品（k>=1）：需要预留 k * w[i] 的容量，剩下的容量 j - k*w[i] 用于前i-1种物品，所以价值为 dp[i-1][j - k*w[i]] + k*v[i]
     * dp[i][j] = max( dp[i-1][j],  max{ dp[i-1][j - k*w[i]] + k*v[i] for k in [1, j/w[i]] } )
     * 时间   O(n*c*c)    最内层循环,尝试物品 i 的件数 k，最坏情况下v[i]=1时，能放k=j件，所以内层循环也算C
     * 空间   O(n*c)
     */
    public int maxValue(int[] w, int[] v, int C) {
        int len = w.length;
        int[][] dp = new int[len][C + 1];

        // 先预处理第一件物品
        for (int j = 0; j <= C; j++) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件
            int maxK = j / w[0];
            dp[0][j] = maxK * v[0];
        }

        // 处理剩余物品
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑第 i 件物品的情况
                int no = dp[i - 1][j];
                // 考虑第 i 件物品的情况
                int yes = 0;
                for (int k = 1 ;; k++) {
                    if (j < k * w[i]) {
                        break;
                    }
                    yes = Math.max(yes, dp[i - 1][j - k * w[i]] + k * v[i]);
                }
                dp[i][j] = Math.max(no, yes);
            }
        }
        return dp[len - 1][C];
    }

    /**
     * 时间   O(n*c*c)
     * 空间   O(c)
     */
    public int maxValue2(int[] w, int[] v, int C) {
        int len = w.length;
        int[][] dp = new int[2][C + 1];

        // 先预处理第一件物品
        for (int j = 0; j <= C; j++) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件
            int maxK = j / w[0];
            dp[0][j] = maxK * v[0];
        }

        // 处理剩余物品
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑第 i 件物品的情况
                int no = dp[(i - 1) % 2][j];
                // 考虑第 i 件物品的情况
                int yes = 0;
                for (int k = 1 ;; k++) {
                    if (j < w[i] * k) {
                        break;
                    }
                    yes = Math.max(yes, dp[(i - 1) % 2][j - k * w[i]] + k * v[i]);
                }
                dp[i][j] = Math.max(no, yes);
            }
        }
        return dp[(len - 1) % 2][C];
    }

    /**
     * 时间   O(n*c)
     * 空间   O(c)
     */
    public int maxValue3(int[] w, int[] v, int C) {
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
