package top.xiaotian.algorithms.array;

import java.util.*;

/**
 * 3446. 按对角线进行矩阵排序
 * 给你一个大小为 n x n 的整数方阵 grid。返回一个经过如下调整的矩阵：
 * <p>
 * 左下角三角形（包括中间对角线）的对角线按 非递增顺序 排序。
 * 右上角三角形 的对角线按 非递减顺序 排序。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入： grid = [[1,7,3],[9,8,2],[4,5,6]]
 * <p>
 * 输出： [[8,2,3],[9,6,7],[4,5,1]]
 * <p>
 * 解释：
 * <p>
 * <p>
 * <p>
 * 标有黑色箭头的对角线（左下角三角形）应按非递增顺序排序：
 * <p>
 * [1, 8, 6] 变为 [8, 6, 1]。
 * [9, 5] 和 [4] 保持不变。
 * 标有蓝色箭头的对角线（右上角三角形）应按非递减顺序排序：
 * <p>
 * [7, 2] 变为 [2, 7]。
 * [3] 保持不变。
 * 示例 2：
 * <p>
 * 输入： grid = [[0,1],[1,2]]
 * <p>
 * 输出： [[2,1],[1,0]]
 * <p>
 * 解释：
 * <p>
 * <p>
 * <p>
 * 标有黑色箭头的对角线必须按非递增顺序排序，因此 [0, 2] 变为 [2, 0]。其他对角线已经符合要求。
 * <p>
 * 示例 3：
 * <p>
 * 输入： grid = [[1]]
 * <p>
 * 输出： [[1]]
 * <p>
 * 解释：
 * <p>
 * 只有一个元素的对角线已经符合要求，因此无需修改。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * grid.length == grid[i].length == n
 * 1 <= n <= 10
 * -105 <= grid[i][j] <= 105
 */
public class SortMatrixByDiagonals {
    public int[][] sortMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 左下角
        List<List<Integer>> list = new ArrayList<>();
        for (int m = 0; m < rows; m++) {
            List<Integer> tmp = new ArrayList<>();
            int i = m;
            int j = 0;
            while (i < rows && j < cols) {
                tmp.add(grid[i++][j++]);
            }
            list.add(tmp);
        }
        for (List<Integer> integers : list) {
            integers.sort(Comparator.comparing(Integer::intValue).reversed());
        }
        for (int m = 0; m < rows; m++) {
            int i = m;
            int j = 0;
            int k = 0;
            while (i < rows && j < cols) {
                grid[i][j] = list.get(m).get(k++);
                i++;
                j++;
            }
        }

        // 右上角
        List<List<Integer>> list2 = new ArrayList<>();
        for (int n = 0; n < rows; n++) {
            List<Integer> tmp = new ArrayList<>();
            int i = 0;
            int j = n;
            while (i < rows && j < cols) {
                tmp.add(grid[i++][j++]);
            }
            list2.add(tmp);
        }
        for (List<Integer> integers : list2) {
            integers.sort(Comparator.comparing(Integer::intValue));
        }
        for (int n = 1; n < cols; n++) {
            int i = 0;
            int j = n;
            int k = 0;
            while (i < rows && j < cols) {
                grid[i][j] = list2.get(n).get(k++);
                i++;
                j++;
            }
        }

        return grid;
    }

    /**
     * 模拟
     * 时间   O(n^2)
     * 空间   O(n^2)
     */
    public int[][] sortMatrix2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 对角线中，i-j值固定
        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int key = i - j;
                diagonalMap.putIfAbsent(key, new ArrayList<>());
                diagonalMap.get(key).add(grid[i][j]);
            }
        }

        // 下三角递减，上三角递增
        diagonalMap.forEach((k, v) -> {
            if (k >= 0) {
                v.sort(Comparator.comparing(Integer::intValue).reversed());
            } else {
                v.sort(Comparator.comparing(Integer::intValue));
            }
        });

        // 填充
        Map<Integer, Integer> pointers = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int key = i - j;
                List<Integer> list = diagonalMap.get(key);
                int index = pointers.getOrDefault(key, 0);
                grid[i][j] = list.get(index);
                pointers.put(key, index + 1);
            }
        }

        return grid;
    }
}
