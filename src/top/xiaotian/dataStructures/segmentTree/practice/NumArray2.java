package top.xiaotian.dataStructures.segmentTree.practice;

import top.xiaotian.dataStructures.segmentTree.SegmentTree;

/**
 * 区域和检索 - 数组可修改
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class NumArray2 {
    private SegmentTree<Integer> segmentTree;

    public NumArray2(int[] nums) {
        if (nums != null && nums.length != 0) {
            Integer[] tmpNums = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                tmpNums[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(tmpNums, (a, b) -> a + b);
        }
    }

    /***
     * 在这类数据动态变化的场景下，线段树的优势就会体现出来了
     * @param i
     * @param val
     */
    public void update(int i, int val) {
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        return segmentTree.query(i, j);
    }
}
