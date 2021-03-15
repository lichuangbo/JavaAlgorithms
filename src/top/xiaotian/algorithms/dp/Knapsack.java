package top.xiaotian.algorithms.dp;

/**
 * 背包问题
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/2/9 16:35
 * @Description: 描述:
 * 有一个背包，他的容量为C，现在有n种不同的物品，编号为0->n-1, 其中每一件物品的重量为w[i], 价值为v[i]
 * 请问：可以向这个背包中盛放那些物品，使得在不超过背包容量的基础上，物品的总价值最大
 */
public class Knapsack {
    private int[][] momo;
    public int knapsack01(int[] w, int[] v, int C) {
        /**
         * 状态的定义： f(n, C)表示考虑将n个物品放进容量为C（物品放入背包后，背包容量会变小）的背包，使得背包中价值最大
         * f(i, c) = max(f(i-1, c), (v[i]+f(i-1, c-w[i]))   当前第i个物品不放背包，取前i-1个物品的价值   当前物品i放入背包，更新价值
         */
        int n = w.length;
        momo = new int[n][C + 1];
        return bestValue(w, v, n - 1, C);
    }
    // 自顶向下： 用[0...index]编号的物品，填充容积为c的背包的最大价值
    private int bestValue(int[] w, int[] v, int index, int c) {
        // 基本条件，没有物品或者是容量已经用尽，返回0
        if (index < 0 || c <= 0) {
            return 0;
        }

        if (momo[index][c] != 0) {
            return momo[index][c];
        }
        // 当前物品不放入背包
        int res = bestValue(w, v, index - 1, c);
        // 当前物品放入背包
        if (c >= w[index]) {
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }
        momo[index][c] = res;
        return res;
    }

    // 自底向上：
    public int knapsack01_dp(int[] w, int[] v, int C) {
        /**
         * 三个物品 重量[1, 2, 3]  价值[6, 10, 12]  背包容量5
         * 列：物品编号  行：背包容量
         *      0   1   2   3   4   5
         *  0   0   6   6   6   6   6       第一行表示仅考虑0号物品 对应第一列表示：容量为0时任何物品都放不了，背包价值为0   对应第二列表示：容量为1时且仅考虑0号物品，那么他的背包价值为6  其他列虽然容量大于1，但是由于仅考虑0号物品，价值始终为6
         *  1   0   6  10  16  16  16       第二行表示考虑0,1两个物品 对应第二列表示：容量为1只能放入0号物品，背包最大价值还为6  对应第三列表示：如果不考虑1号物品，那么他价值为6  如果考虑1号物品，1号物品重量为2，他要取(0, 2-2)的值再加上1号物品重量为10 最后取最大值为10
         *  2   0   6  10  16  18  22
         */
        int n = w.length;
        int[][] dp = new int[n][C + 1];
        // 基础问题解决
        for (int j = 0; j <= C; j++) {
            dp[0][j] = (j >= w[0]) ? v[0] : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑当前物品
                int res = dp[i - 1][j];
                // 考虑当前物品
                if (j >= w[i]) {
                    res = Math.max(res, v[i] + dp[i - 1][j - w[i]]);
                }
                dp[i][j] = res;
            }
        }
        return dp[n - 1][C];
    }

    /**
     * 空间上优化,使用两行空间
     * 初始化第一行，第一行值推到得到第二行   第三行，覆盖掉第一行，复用
     */
    public int knapsack01_dp2(int[] w, int[] v, int C) {
        int n = w.length;
        int[][] dp = new int[2][C + 1];
        // 基础问题解决
        for (int j = 0; j <= C; j++) {
            dp[0][j] = (j >= w[0]) ? v[0] : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑当前物品
                int res = dp[(i - 1) % 2][j];
                // 考虑当前物品
                if (j >= w[i]) {
                    res = Math.max(res, v[i] + dp[(i - 1) % 2][j - w[i]]);
                }
                dp[i % 2][j] = res;
            }
        }
        return dp[(n - 1) % 2][C];
    }

    /**
     * 空间上优化，使用一行空间   填充表格时发现，下一行值只使用上一行值的左边和上边
     * 每一行从后向前填充表格
     */
    public int knapsack01_dp3(int[] w, int[] v, int C) {
        int n = w.length;
        int[] dp = new int[C + 1];
        // 基础问题解决
        for (int j = 0; j <= C; j++) {
            dp[j] = (j >= w[0]) ? v[0] : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = C; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[C];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int C = 5;
        Knapsack knapsack = new Knapsack();
        int res = knapsack.knapsack01_dp3(w, v, C);
        System.out.println(res);// 22
    }
}
