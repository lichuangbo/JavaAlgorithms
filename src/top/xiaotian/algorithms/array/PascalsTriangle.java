package top.xiaotian.algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= numRows <= 30
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        /**
         *     1
         *   1  1
         *  1  2  1
         * 1  3  3  1
         *
         * 1
         * 1  1
         * 1  2  1
         * 1  3  3  1
         */
        List<List<Integer>> res = new ArrayList<>();
        int[][] arr = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List<Integer> levelList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                // 处理边界
                if (i == 0 || j == 0) {
                    arr[i][j] = 1;
                } else {// 按规则填充
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
                levelList.add(arr[i][j]);
            }
            res.add(levelList);
        }
        return res;
    }
}
