package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 剑指 Offer 66. 构建乘积数组
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * <p>
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * <p>
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * <p>
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 * <p>
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class ConstructArr {

    /**
     * 最容易想到的做法，但是题目说了不允许，先实现出来
     * 先将整体求乘积；然后遍历数组，用乘积/每个元素，很自然的得到了除这个元素外其他元素的乘积
     * 为了不让用除法，添加了很多边界测试用例，如负数 0
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf_0(int[] nums) {
        int length = nums.length;

        int product = 1;
        for (int i = 0; i < length; i++) {
            product = product * nums[i];
        }

        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = product / nums[i];
        }
        return res;
    }

    /**
     * 从上一个解中找思路，不能用除法，只能将两侧的数组元素分别求乘积；曲线救国来实现除法
     * 空间复杂度O(n2)   算法超时，有用例通不过
     */
    public int[] productExceptSelf_1(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            int leftProduct = 1;
            for (int j = 0; j <= i - 1; j++) {
                leftProduct = leftProduct * nums[j];
            }
            int rightProduct = 1;
            for (int k = i + 1; k < length; k++) {
                rightProduct = rightProduct * nums[k];
            }
            res[i] = leftProduct * rightProduct;
        }
        return res;
    }

    /**
     * 将两侧的数组元素分别求乘积，需要降低时间复杂度
     * step1:先从最左边开始遍历，把每个nums[i]左边的元素乘积记录下来
     * nums[0]=1，左边没有元素[]，所以乘积计1，
     * nums[1]=2，左边元素是[1]，所以乘积计1,
     * nums[2]=3，左边元素是[1, 2]，所以乘积计2,
     * nums[3]=4，左边元素是[1, 2, 3]，所以乘积计6.
     * 这样就得到了每个元素左边对应元素的乘积列表[1, 1, 2, 6]
     * step2:从该元素的右边开始遍历，把每个nums[i]右边的元素乘积全部记录下来
     * nums[0]=1,它右边的元素是[2, 3, 4],所以乘积计24，
     * nums[1]=2,它右边的元素是[3, 4],所以乘积计12，
     * nums[2]=3,它右边的元素是[4],所以乘积计4，
     * nums[3]=4,它右边没有元素[]，所以乘积计1，
     * 这样就得到了每个nums[i]右手边的元素乘积列表[24, 12, 4, 1]
     * 时间O(n)
     * 空间O(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        // 求解左侧  [1, 1, 2, 6]
        int[] tmp1 = new int[len];
        Arrays.fill(tmp1, 1);
        for (int i = 0; i < len - 1; i++) {
            tmp1[i + 1] = tmp1[i] * nums[i];
        }
        // 求解右侧  [24, 12, 4, 1]
        int[] tmp2 = new int[len];
        Arrays.fill(tmp2, 1);
        for (int i = len - 1; i > 0; i--) {
            tmp2[i - 1] = tmp2[i] * nums[i];
        }
        // 左右两侧相乘
        for (int i = 0; i < len; i++) {
            tmp1[i] = tmp1[i] * tmp2[i];
        }
        return tmp1;
    }

    /**
     * 进一步降低空间复杂度
     * 借助一个res结果数组，先存储左侧结果值，然后按照倒序，乘右侧乘积
     * 关键在于 right变量存储了右侧上一个的乘机值
     * 左侧乘积数组[1, 1, 2, 6]
     *                  right=1
     * res[3]=6*1=6     right=1*4=4
     * res[2]=2*4=8     right=4*3=12
     * res[1]=1*12      right=12*2=24
     * res[0]=1*24      right=24*1=24
     * 时间O(n)
     * 空间O(1)
     */
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, 1);
        // 求解左侧 [1, 1, 2, 6]
        for (int i = 0; i < len - 1; i++) {
            res[i + 1] = res[i] * nums[i];
        }
        // 求解右侧
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        int[] res = new ConstructArr().productExceptSelf2(nums);
        System.out.println(Arrays.toString(res));
    }
}
