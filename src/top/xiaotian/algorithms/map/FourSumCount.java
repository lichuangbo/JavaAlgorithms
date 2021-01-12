package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/12
 */
public class FourSumCount {
    /**
     * 时间：O(n2)
     * 查找表法，减低时间复杂度
     * 同时stream流操作也可以减少执行时长
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
//                if (map.containsKey(C[i] + D[j])) {
//                    map.put(C[i] + D[j], map.get(C[i] + D[j]) + 1);
//                } else {
//                    map.put(C[i] + D[j], 1);
//                }
                map.compute(C[i] + D[j], (k , v) -> v == null ? 1 : v + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
//                if (map.containsKey(0 - A[i] - B[j])) {
//                    res += map.get(0 - A[i] - B[j]);
//                }
                res += map.getOrDefault(0 - A[i] - B[j], 0);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {0, 1, -1}, B = {-1, 1, 0}, C = {0, 0, 1}, D = {-1, 1, 1};
        int res = new FourSumCount().fourSumCount(A, B, C, D);
        System.out.println(res);
    }
}
