package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/2
 */
public class TwoSumII {
    /**
     * 二分法
     * 时间O(nlogn)
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int tmp = findTarget(numbers, i + 1, target - numbers[i]);
            if (tmp != -1) {
                res[0] = i + 1;
                res[1] = tmp + 1;
                break;
            }
        }
        return res;
    }

    private int findTarget(int[] numbers, int left, int target) {
        int l = left, r = numbers.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (numbers[m] < target) {
                l = m + 1;
            } else if (numbers[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * 对撞指针
     * 时间O(n)
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        // l和r撞在一起，在题目中不符合的
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            }

            if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }
}
