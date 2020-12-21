package top.xiaotian.dataStructures.set.practice;

import java.util.Set;
import java.util.TreeSet;

/**
 * 唯一摩尔斯密码词
 * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。
 * 例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + ".-" + "-..." 字符串的结合)。
 * 我们将这样一个连接过程称作单词翻译。
 * 返回我们可以获得所有词不同单词翻译的数量。
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/20
 */
public class UniqueMorseRepresentations {
    /**
     * 时间：双重遍历，O(n*len) n为数组长度，len为字符串平均长度
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        Set<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        int res = new UniqueMorseRepresentations().uniqueMorseRepresentations(words);
        System.out.println(res);
    }
}
