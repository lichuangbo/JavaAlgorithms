package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 *
 * 示例 1:
 *
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/13 16:41
 * @Description: 描述:
 */
public class MaxPoints {
    public int maxPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                }
            }
        }
        return res;
    }

    private int gcd(int dy, int dx) {
        if (dx == 0) {
            return dy;
        } else {
            return gcd(dx, dy % dx);
        }
    }
}
