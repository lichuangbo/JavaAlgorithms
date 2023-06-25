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
    // 暴力解法：提交超时
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int tmp = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (tmp < nums[j]) {
                    tmp = nums[j];
                }
            }
            res[i] = tmp;
        }
        return res;
    }

    // 使用双端队列，来维护一个单调递减队列
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口：只管入队
        for (int i = 0; i < k; i++) {
            // 针对新加入窗口的元素num：需要将队列中小于num的都出队来保证队列的递减；从尾部遍历是因为队列是递减的
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后：随着移动一边入队一边出队
        for (int i = k; i < nums.length; i++) {
            // 针对移出窗口的元素num：num就是队首元素，需要将队列中该元素也移除，在这里就是队首出队
            // 也可以参照方法3中，队列里存储的是元素下标，通过下标来定位是否位于滑动窗口中
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 优先队列
     * 时间O(nlogn)
     * 空间O(n)
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
