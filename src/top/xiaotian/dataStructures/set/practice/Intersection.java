package top.xiaotian.dataStructures.set.practice;

import top.xiaotian.dataStructures.set.BSTSet;
import top.xiaotian.dataStructures.set.Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述: 两个数组的交集
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new BSTSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> list = new ArrayList<>();
        for (int value : nums2) {
            if (set.contains(value)) {
                list.add(value);
                set.remove(value);
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
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] res = new Intersection().intersection(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
