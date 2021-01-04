package top.xiaotian.algorithms.map;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class FrequencySort {
    /***
     * 时间 计数O(n)+最大堆维护O(nlogn)+结果收集O(nlogn)
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] sArr = s.toCharArray();
        for (char c : sArr) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> o2[1] - o1[1]
        );
        for(Map.Entry<Character, Integer> tmpMap : map.entrySet()) {
            Character m = tmpMap.getKey();
            Integer n = tmpMap.getValue();
            pq.offer(new int[]{m, n});
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            for (int i = 0; i < tmp[1]; i++) {
                sb.append((char)tmp[0]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        String res = new FrequencySort().frequencySort(s);
        System.out.println(res);
    }
}
