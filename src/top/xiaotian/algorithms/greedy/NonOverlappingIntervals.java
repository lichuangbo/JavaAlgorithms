package top.xiaotian.algorithms.greedy;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/3/18 14:58
 * @Description: 描述:
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 从区间的开头开始排序
        Arrays.sort(intervals, (int[] a, int[]b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[a.length - 1] - b[b.length - 1];
            }
        });
        System.out.println(Arrays.deepToString(intervals));
        // 借鉴最长上升子序列思路
        // 状态定义：dp[i]表示使用intervals[0...i]的区间能构成的最长不重叠区间序列
        int[] dp = new int[intervals.length];
        // 初始值填充，不进行任何组合，长度为1
        Arrays.fill(dp, 1);
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                // 当前区间的首元素 大于 前边元素的尾元素，说明可以和它组成不重叠区间，+1
                if (intervals[i][0] >= intervals[j][intervals[j].length - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return intervals.length - res;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 从区间的结尾开始排序
        Arrays.sort(intervals, (int[] a, int[]b) -> {
            if (a[a.length - 1] != b[b.length - 1]) {
                return a[a.length - 1] - b[b.length - 1];
            } else {
                return a[0] - b[0];
            }
        });
        System.out.println(Arrays.deepToString(intervals));
        int res = 1;
        int pre = 0;// 前一个选择使用区间，初始为第0个区间
        for (int i = 1; i < intervals.length; i++) {
            // 当前区间起始值 大于等于 前一个区间结尾值， res++表示选择上这个区间  同时更新pre
            if (intervals[i][0] >= intervals[pre][intervals[pre].length - 1]) {
                res++;
                pre = i;
            }
        }
        return intervals.length - res;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        };
        int i = new NonOverlappingIntervals().eraseOverlapIntervals2(intervals);
        System.out.println(i);
    }
}
