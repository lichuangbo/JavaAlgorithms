package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/4/30 15:20
 * @Description: 描述:
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] != nums[i + 1]) return nums[i];
            } else if (i == nums.length - 1) {
                if (nums[i] != nums[i - 1]) return nums[i];
            } else {
                if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) return nums[i];
            }
        }
        return -1;
    }

    public int singleNumber2(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((cnt[i] % 3 & 1) == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {2,2,3,2};
        int res = new SingleNumber().singleNumber(nums);
        System.out.println(res);
    }
}
