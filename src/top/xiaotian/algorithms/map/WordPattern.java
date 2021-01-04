package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class WordPattern {
    /***
     * 时间O(m*n)
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        char[] patterns = pattern.toCharArray();
        String[] sArr = s.split(" ");
        if (patterns.length != sArr.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        int i = 0;
        while (i < patterns.length) {
            if (map.containsKey(patterns[i])) {
                // 键存在，判断map中对应value是否匹配字符串对应值
                if (!map.get(patterns[i]).equals(sArr[i])) {
                    return false;
                }
            } else {
                // 键不存在，但是map中却存在相同value，返回false；否则添加到map中
                if (map.containsValue(sArr[i])) {
                    return false;
                }
                map.put(patterns[i], sArr[i]);
            }
            i++;
        }
        return true;
    }

    /***
     * 时间O(m+n)
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern2(String pattern, String str) {
        char[] patterns = pattern.toCharArray();
        String[] words = str.split(" ");
        if (words.length != patterns.length) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (Integer i = 0; i < words.length; i++) {
            /*
                如果key不存在，插入成功，返回null；如果key存在，返回之前对应的value。

                以pattern = "abba", str = "dog cat cat dog"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("dog",3)返回0，两者相等，
                结果为 true。

                以pattern = "abba", str = "dog cat cat fish"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("fish",3)返回null，两者不相等，
                结果为 false。
            */
            if (map.put(patterns[i], i) != map.put(words[i], i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        boolean res = new WordPattern().wordPattern2(pattern, s);
        System.out.println(res);
    }
}
