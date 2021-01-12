package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/12
 */
public class ThreeSumClosest {
    /**
     * 时间：排序O(nlogn) + 对撞指针O(n2)
     * 三数之和的思路
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = Integer.MAX_VALUE;// res[0]记录最短距离，res[1]记录对应的三数之和

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {// 等于0一定是最近距离，直接返回
                    return sum;
                }

                if (sum < target) {
                    l++;
                } else {
                    r--;
                }
                if (Math.abs(target - sum) < res[0]) {
                    res[0] = Math.abs(target - sum);
                    res[1] = sum;
                }
            }
        }
        return res[1];
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2};
        int res = new ThreeSumClosest().threeSumClosest(nums, 3);
        System.out.println(res);
    }
}
