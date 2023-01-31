package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例:
 * <p>
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/28 93 131  46
 */
public class LetterCombinations {

  Map<Character, String> map = new HashMap<>() {{
    put('2', "abc");
    put('3', "def");
    put('4', "ghi");
    put('5', "jkl");
    put('6', "mno");
    put('7', "pqrs");
    put('8', "tuv");
    put('9', "wxyz");
  }};

  // 回溯，时间O(3^rows)
  public List<String> letterCombinations(String digits) {
    List<String> resList = new ArrayList<>();
    if (digits == null || digits.length() == 0) {
      return resList;
    }
    char[] digitChars = digits.toCharArray();
    help(digitChars, 0, resList, new StringBuilder());
    return resList;
  }

  // sb保存从digits[0...index-1]翻译得到的一个字母字符串，然后继续寻找和digits[index]匹配的字母
  // (index指向的是digits字符串的遍历进度，比如说2的按键组合遍历完了，继续看3的按键组合)
  private void help(char[] digitChars, int index, List<String> resList, StringBuilder sb) {
      // 当递归到数字串的最后时，说明找到了一组解，加入结果集
      if (index == digitChars.length) {
      resList.add(sb.toString());
      return;
    }

    char[] chars = map.get(digitChars[index]).toCharArray();
    for (char ch : chars) {
      // 基于选定的这一个字符，向下递归：去处理下一个数字键代表的字符
      sb.append(ch);
      help(digitChars, index + 1, resList, sb);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
