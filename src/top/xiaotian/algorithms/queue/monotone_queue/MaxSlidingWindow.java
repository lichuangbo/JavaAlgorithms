package top.xiaotian.algorithms.queue.monotone_queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7      3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 */
public class MaxSlidingWindow {
    /**
     * 暴力解法：提交超时
     * 时间   O(n*k)
     * 空间   O(1)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            res[i] = max;
        }
        return res;
    }

    /**
     * 使用双端队列，来维护一个单调递减队列
     * 时间   O(n)
     * 空间   O(k)，长度为k的双端队列
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        /**
         * 滑动窗口的位置                      deque          操作原因                     结果
         * ---------------                 首-----尾
         * 1^  3  -1 -3  5  3  6  7         0
         * 1  3^  -1 -3  5  3  6  7         1           3大于队尾元素1，移除
         * 1  3  -1^ -3  5  3  6  7         1 2         2-3+1>=0,采集结果                 [3]
         * 1  3  -1 -3^  5  3  6  7         1 2 3       3-3+1>=0,采集结果                 [3,3]
         * 1  3  -1 -3  5^  3  6  7         4       5大于队尾元素-3，循环移除               [3,3,5]
         */
        Deque<Integer> deque = new LinkedList<>();
        int len = nums.length;
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            // 移除过期索引：检查队首索引是不是已经不在当前窗口范围内了，i-k+1代表的是窗口的最左端
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 维护单调递减性，移除不可能成为最大值的索引：移除队尾所有小于当前元素的索引，因为当前元素nums[i]比它们大且位置更新，被移除的索引不可能再成为后续窗口的最大值
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 将当前索引加入队尾
            deque.offerLast(i);

            // 当窗口形成时（i >= k-1），记录当前窗口最大值，也就是队首下标对应的元素
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 优先队列
     * 时间   O(nlogn)
     * 空间   O(n)
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int m = 0;
        for (int i = 0; i < len; i++) {
            if (pq.size() < k) {
                pq.offer(new int[]{nums[i], i});
                continue;
            }

            res[m++] = pq.peek()[0];
            // 优化处理：如果使用pq.remove(nums[i-k]);该操作时间复杂度过高，提交超时
            // 将元素的下标也放入堆中，不断判断最大值的下标在不在窗口内，不在的话就要移除掉，确保最大值始终在滑动窗口里
            while (!pq.isEmpty() && pq.peek()[1] <= i - k) {
                pq.poll();
            }
            pq.add(new int[]{nums[i], i});
        }
        res[m] = pq.peek()[0];
        return res;
    }
}
