package top.xiaotian.dataStructures.heap.practice;

import java.util.*;

/**
 * 前 K 个高频元素
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
    static class Freq implements Comparable<Freq> {
        public int e;
        public int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq ano) {
            // 频次越低优先级越高
            if (this.freq > ano.freq) {
                return -1;
            } else if (this.freq < ano.freq) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for(Map.Entry<Integer, Integer> tmpMap : map.entrySet()) {
            Integer m = tmpMap.getKey(), n = tmpMap.getValue();
            if (pq.size() < k) {
                pq.offer(new int[]{m, n});
            } else if (pq.peek() != null && n > pq.peek()[1]) {
                pq.poll();
                pq.offer(new int[]{m, n});
            }
        }

        int[] res = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int[] res = new TopKFrequent().topKFrequent(nums, 2);
        System.out.println(Arrays.toString(res));
    }
}
