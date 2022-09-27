package top.xiaotian.dataStructures.set.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 349. 两个数组的交集
 *
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2] 输出：[2]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述: 两个数组的交集
 */
public class Intersection {

  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums1) {
      set.add(num);
    }

    List<Integer> resList = new ArrayList<>();
    for (int num : nums2) {
      if (set.contains(num)) {
        resList.add(num);
        // remove是为了去重
        set.remove(num);
      }
    }

    return resList.stream().mapToInt(Integer::intValue).toArray();
  }

  // 先排序，后双指针
  public int[] intersection2(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int len1 = nums1.length, len2 = nums2.length;
    List<Integer> resList = new ArrayList<>();
    int prev = -1;
    // 4 5 9
    // 4 4 8 9 9
    int i = 0, j = 0;
    while (i < len1 && j < len2) {
      int num1 = nums1[i], num2 = nums2[j];
      if (num1 == num2) {
        // 保证加入元素的唯一性
        if (prev != num1) {
          resList.add(num1);
          prev = num1;
        }
        i++;
        j++;
      } else if (num1 < num2) {
        i++;
      } else {
        j++;
      }
    }
    return resList.stream().mapToInt(Integer::intValue).toArray();
  }
}
