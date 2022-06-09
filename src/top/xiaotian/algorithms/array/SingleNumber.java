package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/4/30 15:20
 * @Description: 描述:
 */
public class SingleNumber {
  public int singleNumber(int[] nums) {
    // 最少数组元素为4
    if (nums == null || nums.length < 4) {
      return -1;
    }
    Arrays.sort(nums);
    // 首
    if (nums[0] != nums[1]) {
      return nums[0];
    }
    // 尾
    int len = nums.length;
    if (nums[len - 1] != nums[len - 2]) {
      return nums[len - 1];
    }
    // 中间
    int i = 3;
    while (i < len - 3) {
      if (nums[i - 1] != nums[i] && nums[i] != nums[i + 1]) {
        return nums[i];
      } else {
        i += 3;
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

  /**
   * 剑指 Offer 56 - I. 数组中数字出现的次数
   * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
   * <p>
   * <p>
   * <p>
   * 示例 1：
   * <p>
   * 输入：nums = [4,1,4,6]
   * 输出：[1,6] 或 [6,1]
   * 示例 2：
   * <p>
   * 输入：nums = [1,2,10,4,1,4,3,3]
   * 输出：[2,10] 或 [10,2]
   * <p>
   * <p>
   * 限制：
   * <p>
   * 2 <= nums.length <= 10000
   */
  public int[] singleNumbers(int[] nums) {
    /**
     * 将原数组分成两组：两个独特的数字分成不同的组，同时相同的数字分到相同的组,这样的话，每一组进行异或就得到两个独特的数字
     * 怎么分组？
     * n=x^y(x,y假设为两个独特的数字)   思考n用二进制表示的含义,n(i) = x(i) ^ y(i), 当n(i)=0时表示x(i)和y(i)相等，n(i)=1时，两者不相等
     * 那么，随机选择一个i(n(i)=1)和数组中每一个数字异或就可以满足
     *  条件2:相同的数字二进制位在i上相同，所以一定会分到同一组
     *  条件1:选取的i刚好是两个独特数字二进制位不同的，一定可以分到两个组中
     */

    // 计算异或结果n
    int n = 0;
    for (int num : nums) {
      n ^= num;
    }
    // n和1进行与运算，找到n的二进制表示中最低位的1的位置
    int m = 1;
    while ((n & m) == 0) {
      m <<= 1;
    }
    // 将原数组分成两个数组
    int x = 0, y = 0;
    for (int num : nums) {
      if ((num & m) != 0) {
        x ^= num;
      } else {
        y ^= num;
      }
    }
    return new int[]{x, y};
  }
}
