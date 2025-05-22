package top.xiaotian.algorithms.map;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/13 10:34
 * @Description: 描述:
 */
public class GroupAnagrams {
    /**
     * 时间：O(n * k) 其中k是字符串数组中字符串平均长度
     * 空间：O(n*k)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] freq = calcFreq(str.toCharArray());
            map.computeIfAbsent(new String(freq), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private char[] calcFreq(char[] chars) {
        char[] freq = new char[26];
        for (char ch : chars) {
            freq[ch - 'a']++;
        }
        return freq;
    }

    /**
     * 时间：O(n* klogk) 其中k是字符串数组中字符串平均长度
     * 空间：O(n*k) 哈希表存储 n 个字符串的键值对，每个键是排序后的字符串（长度 k）
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            map.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
