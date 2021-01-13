package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/13 11:23
 * @Description: 描述:
 */
public class NumberOfBoomerangs {
    /**
     * 时间O(n2)  n为是点的对数
     * 查找表法
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {// 确定一点
            Map<Integer, Integer> map = new HashMap<>();// 统计每段距离出现的次数：key为距离，value为距离出现频率
            for (int j = 0; j < points.length; j++) {
                /**
                 *    [
                 * i  [0, 0],  j
                 * i  [1, 0],  j
                 * i  [2, 0]   j
                 *    ]
                 */
                if (i != j) {// 统计任意两点间距离
                    map.compute(getDis(points[i], points[j]), (k , v) -> v == null ? 1 : v + 1);
                }
            }
            for (Integer value : map.values()) {
                // 距离小于1说明凑不成一个结果
                if (value >= 2) {
                    res += value * (value - 1);// 与顺序有关，进行排列组合
                }
            }
        }
        return res;
    }

    private int getDis(int[] point1, int[] point2) {
        return (point1[0] - point2[0]) * (point1[0] - point2[0]) + (point1[1] - point2[1]) * (point1[1] - point2[1]);
    }
}
