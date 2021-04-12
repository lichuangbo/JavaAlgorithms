package top.xiaotian.dataStructures.heap.practice;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/4/12 14:06
 * @Description: 描述:
 */
public class UglyNumber {
    public int nthUglyNumber(int n) {
        // dp[i]存储 第i个丑数
        int[] dp = new int[n + 1];
        // 初始值：第一个丑数为1
        dp[1] = 1;
        // 三个指针指向首个丑数
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            // 当前指针指向的丑数 * 对应的质数 就得到了 三个指针能够得到的丑数
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            // 三个丑数中最小的数 就是下一个要求解的丑数
            dp[i] = Math.min(num2, Math.min(num3, num5));
            // 更新对应的指针
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    public int nthUglyNumber2(int n) {
        int[] factors = {2, 3, 5};
        // 对堆中元素进行去重
        Set<Long> set = new HashSet<>();
        // 利用最小堆，堆顶元素是最小值的特性
        PriorityQueue<Long> heap = new PriorityQueue<>();
        set.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            // 第几次出堆，就是第几个丑数
            long curr = heap.poll();
            // 记录结果
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                // 堆中没有next值，就加入堆
                if (!set.contains(next)) {
                    heap.offer(next);
                    set.add(next);
                }
            }
        }
        return ugly;
    }

    // 263. 丑数
    public boolean isUgly(int n) {
        if (n <= 0) return false;
        if (n == 1)  return true;
        // n = 2^a * 3^b * 5^c， abc都是非负整数
        // 12 / 2 = 6   6 / 2 = 3   3 / 3 = 1  true
        // 11 false
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }


}
