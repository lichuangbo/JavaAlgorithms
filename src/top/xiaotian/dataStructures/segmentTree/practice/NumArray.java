package top.xiaotian.dataStructures.segmentTree.practice;

import top.xiaotian.dataStructures.segmentTree.SegmentTree;

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
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums != null && nums.length != 0) {
            Integer[] tmpNums = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                tmpNums[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(tmpNums, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        return segmentTree.query(i, j);
    }
}
