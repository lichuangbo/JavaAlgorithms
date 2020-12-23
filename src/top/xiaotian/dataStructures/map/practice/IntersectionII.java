package top.xiaotian.dataStructures.map.practice;


import top.xiaotian.dataStructures.map.BSTMap;
import top.xiaotian.dataStructures.map.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述: 两个数组的交集 II
 */
public class IntersectionII {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new BSTMap<>();
        for (int num : nums1) {
            if (map.contains(num)) {
                map.set(num, map.get(num) + 1);
            } else {
                map.add(num, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.contains(num)) {
                list.add(num);
                map.set(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            res[i++] = integer;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] intersection = new IntersectionII().intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }
}
