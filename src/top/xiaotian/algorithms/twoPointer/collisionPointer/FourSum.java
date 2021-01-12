package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/10
 */
public class FourSum {
    /**
     * 时间O(n3)  在三数之和的基础上添加一重遍历
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {// 固定第一个数
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < length - 1; j++) {// 固定第二个数
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1, r = length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
                        l++;
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        r--;
                        while (r > l && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    } else if (sum < target) {
                        l++;
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                    } else {
                        r--;
                        while (r > l && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0, 4, -5, 2, -2, 4, 2, -1, 4};
        List<List<Integer>> res = new FourSum().fourSum(nums, 12);
        System.out.println(res);
    }
}
