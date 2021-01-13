package top.xiaotian.algorithms.map;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/13 10:34
 * @Description: 描述:
 */
public class GroupAnagrams {
    /**
     * 时间：O(n * klogk)(其中字符串数组最长字符为k，)
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
//            String freq = Arrays.toString(getFreq(strs[i]));
//            map.computeIfAbsent(freq, k -> new ArrayList<>()).add(strs[i]);

            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            map.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    private int[] getFreq(String str) {
        char[] chars = str.toCharArray();
        int[] freq = new int[26];
        for (char ch : chars) {
            freq[ch - 'a']++;
        }
        return freq;
    }
}
