package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * <p>
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * 示例 2：
 * <p>
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/12
 */
public class FourSumCount {
    /**
     * 时间：O(n2)
     * 空间：O(n2) 存储所有 nums1[i] + nums2[j] 的可能值及其出现次数
     * 查找表法，减低时间复杂度
     * 同时stream流操作也可以减少执行时长
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length;
        // 将nums1[i]+nums2[j]的和作为键，出现的次数作为值，缓存起来
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.compute(nums1[i] + nums2[j], (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        int res = 0;
        // 遍历nums3[i]和nums4[j]，从缓存中查找是否存在能够组合为0的情况，统计出来
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map.containsKey(-nums3[i] - nums4[j])) {
                    res += map.get(-nums3[i] - nums4[j]);
                }
            }
        }
        return res;
    }
}
