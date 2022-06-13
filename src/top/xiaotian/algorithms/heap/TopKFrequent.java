package top.xiaotian.algorithms.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *  示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class TopKFrequent {
    /**
     * 时间： map遍历计数O(n) + 遍历计数map,维护堆，O(nlogk)
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.compute(num, (k1, v1) -> v1 == null ? 1 : v1 + 1);
        }

        // Java中默认的优先队列是最小堆
        // 求解前k大问题，使用小顶堆（小顶堆容易得知最小的元素是堆顶，相当于知道了区间的下界，之后不断在调整下界）；求解前k小问题，使用最大堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> o1[1] - o2[1]
        );
        for(Map.Entry<Integer, Integer> tmpMap : map.entrySet()) {
            Integer m = tmpMap.getKey(), n = tmpMap.getValue();
            // 维护一个大小为K的小顶堆; 如果新添加元素比堆顶元素大，我们就把堆顶元素删除，并且将这个元素插入到堆中
            if (pq.size() < k) {
                pq.offer(new int[]{m, n});
            } else if (pq.peek() != null && n > pq.peek()[1]) {
                pq.poll();
                pq.offer(new int[]{m, n});
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
}
