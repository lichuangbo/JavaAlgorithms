package top.xiaotian.algorithms.map;

import java.util.*;

/**
 * 349. 两个数组的交集
 * 给定两个数组 nums1 和 nums2 ，返回 它们的 交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述: 两个数组的交集
 */
public class Intersection {

    /**
     * 时间 O(max(m,n))
     * 空间 O(min(m,n)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        Set<Integer> resSet = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                resSet.add(num);
            }
        }
        return resSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 先排序，后双指针
     * 时间 O(m+n)+O(klogk) k是nums1和nums2中平均长度，用于排序
     * 空间 O(min(m,n))
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        Set<Integer> resSet = new HashSet<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resSet.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return resSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 350. 两个数组的交集 II
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 1000
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     */
    public int[] intersectionII(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> resList = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                resList.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        return resList.stream().mapToInt(Integer::intValue).toArray();
    }
}
