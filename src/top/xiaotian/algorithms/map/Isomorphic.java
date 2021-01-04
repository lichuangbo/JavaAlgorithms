package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 *
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * 示例 1:
 *
 * 输入：s = "egg", t = "add"
 * 输出：true
 *
 * 示例 3：
 *
 * 输入：s = "paper", t = "title"
 * 输出：true
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class Isomorphic {
    /***
     * 维护两张哈希表，双向判断映射关系
     * 时间O(n)
     * @param s  (ab, aa)
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        char[] sArr = s.toCharArray(), tArr = t.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            if ((s2t.containsKey(sArr[i]) && s2t.get(sArr[i]) != tArr[i]) || (t2s.containsKey(tArr[i]) && t2s.get(tArr[i]) != sArr[i])) {
                return false;
            }
            s2t.put(sArr[i], tArr[i]);
            t2s.put(tArr[i], sArr[i]);
        }
        return true;
    }
}
