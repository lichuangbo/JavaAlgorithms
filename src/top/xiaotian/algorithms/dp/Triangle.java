package top.xiaotian.algorithms.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] dp = new int[len + 1][len + 1];
        /**
         *  2
         *^ 3 4
         *| 6 5 7
         *i 4 1 8 3
         *  0 0 0 0 0
         *  j ->
         */
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int len = triangle.size();
        int []dp = new int[len];
        // 初始化：最后一行的最小值时自身
        for (int j = 0; j < len; j++) {
            dp[j] = triangle.get(len - 1).get(j);
        }
        /**
         *^ 2
         *| 3 4
         *i 6 5 7
         *  4 1 8 3
         *  j ->
         */
        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        int res = new Triangle().minimumTotal(triangle);
        System.out.println(res);
    }
}
