package top.xiaotian.algorithms.dp;

/**
 * 背包问题
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/2/9 16:35
 * @Description: 描述:
 */
public class Knapsack {
    private int[][] momo;
    public int knapsack01(int[] w, int[] v, int C) {
        /**
         * f(n, C)表示考虑将n个物品放进容量为C的背包，使得背包中价值最大
         * f(i, c) = max(f(i-1, c), (v[i]+f(i-1, c-w[i]))   当前第i个物品不放背包，取i-1个物品的价值   当前物品i放入背包，更新价值
         */
        int n = w.length;
        momo = new int[n][C + 1];
        return bestValue(w, v, n - 1, C);
    }
    // 用[0...index]编号的物品，填充容积为c的背包的最大价值
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
    public int knapsack01_dp(int[] w, int[] v, int C) {
        /**
         * 三个物品 重量[1, 2, 3]  价值[6, 10, 12]  背包容量5
         * 列：物品编号  行：背包容量
         *      0   1   2   3   4   5
         *  0   0   6   6   6   6   6       第一行表示仅考虑第一个物品 对应第一列表示：容量为0时任何物品都放不了，背包价值为0   对应第二列表示：容量为1时可以放入，背包价值为6
         *  1   0   6  10  16  16  16       第二行表示考虑0,1两个物品 对应第二列表示：容量为1只能放入编号1物品，背包最大价值还为6  对应第三列表示：容量为2时,如果不放置1号收益为6，如果放置1号背包剩余容量为0，查表得到对应最大收益为0，背包价值取最大得到10
         *  2   0   6  10  16  18  22       最后一行22取值：不放置2号物品，得到16   放置2号物品(只放2)，此时背包容量还剩2，在表格中查看容量为2时且考虑0、1号背包的最大收益，得到10+自身12=22   取最大得到22
         */
        int n = w.length;
        if (n == 0) {
            return 0;
        }
        // 状态：
        int[][] dp = new int[n][C + 1];
        // 解决基本问题：计算将第一个物品放入背包，背包容量对应的最大价值
        for (int j = 0; j <= C; j++) {
            dp[0][j] = j >= w[0] ? v[0] : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑当前物品
                dp[i][j] = dp[i - 1][j];
                // 物品能够放置到该容量中，就将该物品纳入考虑
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[n - 1][C];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int C = 5;
        Knapsack knapsack = new Knapsack();
        int res = knapsack.knapsack01_dp(w, v, C);
        System.out.println(res);// 22
    }
}
