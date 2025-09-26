package top.xiaotian.algorithms.queue.priority_queue;

import java.util.PriorityQueue;

/**
 * 面试题 17.14. 最小K个数
 * 提示
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 * <p>
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 */
public class SmallestK {

    /**
     * 时间   O(n*logk)
     * 空间   O(k),长度为k的堆
     */
    public int[] smallestK(int[] arr, int k) {
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k) {
                pq.add(arr[i]);
            } else if (!pq.isEmpty() && pq.peek() > arr[i]) {// 最大值比当前遍历元素大，说明当前元素是较小值，更新上边界
                pq.poll();
                pq.add(arr[i]);
            }
        }

        int[] res = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }
}
