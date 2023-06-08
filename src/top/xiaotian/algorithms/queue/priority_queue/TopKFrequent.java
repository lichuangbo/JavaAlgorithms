package top.xiaotian.algorithms.queue.priority_queue;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class TopKFrequent {
    /**
     * 时间： map遍历计数O(n) + 遍历计数map,维护堆，O(nlogk)
     * 空间：O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        // key是数组中元素，value是该元素在数组中出现的次数
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 小顶堆
        // Java中默认的优先队列是最小堆
        // 求解前k大问题，使用小顶堆（小顶堆容易得知最小的元素是堆顶，相当于知道了区间的下界，之后不断在调整下界）；求解前k小问题，使用最大堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (Map.Entry<Integer, Integer> tmpMap : freqMap.entrySet()) {
            Integer num = tmpMap.getKey();
            Integer freq = tmpMap.getValue();
            if (pq.size() < k) {
                pq.offer(new int[]{num, freq});
            } else if (pq.peek() != null && pq.peek()[1] < freq) {// 堆最小值比现有词频小，说明出现了频次更高的元素，更新下边界
                pq.poll();
                pq.offer(new int[]{num, freq});
            }
        }

        // 组装结果
        int[] res = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll()[0];
        }
        return res;
    }

    /**
     * 桶排序
     */
    public int[] topKFrequent2(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if (max == min) return new int[]{nums[0]};
        // freqs数组记录每个元素出现的频次
        int[] freqs = new int[max - min + 1];
        for (int num : nums) {
            freqs[num - min]++;//记录各个数出现的次数
        }
        // 最小到最大出现频次依次为：3 3 1

        // 把频率展开看，下标为3的桶存储的是频率为3的元素
        int len = freqs.length;
        ArrayList<Integer>[] buckets = new ArrayList[len + 1];
        for (int i = 0; i < len; i++) {
            int freq = freqs[i];
            if (freq == 0) continue;

            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList();
            }
            // 相同次数的放在同一个桶里，如1, 1, 1, 2, 2, 2, 3  1和2都放在3号桶中
            // i+min是为了还原偏移量，得到num本身
            buckets[freq].add(i + min);
        }

        // 从后向前遍历桶，拿到k个结果就终止
        int[] res = new int[k];
        for (int i = len, j = 0; i >= 0 && j < k; i--) {
            ArrayList<Integer> bucket = buckets[i];
            if (bucket == null) continue;

            while (!bucket.isEmpty()) {
                res[j++] = bucket.remove(bucket.size() - 1);
            }
        }
        return res;
    }
}
