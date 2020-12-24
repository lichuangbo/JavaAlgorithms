package top.xiaotian.dataStructures.segmentTree.practice;

/**
 * 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 *
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class NumArray_1 {

    private int[] sum;// sum[i]表示nums数组中前i个元素之和

    public NumArray_1(int[] nums) {
        if (nums != null && nums.length != 0) {
            sum = new int[nums.length + 1];
            sum[0] = 0;

            for (int i = 1; i <= nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
