package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 */
public class GenerateParenthesis {
  public List<String> generateParenthesis(int n) {
    List<String> resList = new ArrayList<>();
    help(n, n, resList, new StringBuilder());
    return resList;
  }

  private void help(int left, int right, List<String> resList, StringBuilder curr) {
    // 左右括号都用尽加入结果集
    if (left == 0 && right == 0) {
      resList.add(curr.toString());
      return;
    }

    // 使用右括号是有前提的，必须有左括号：那么一次chose操作后发现左括号数量大于右括号数量，就可以剪掉了
    if (left > right) {
      return;
    }

    if (left > 0) {
      curr.append('(');// chose
      help(left - 1, right, resList, curr);
      curr.deleteCharAt(curr.length() - 1);// unchose
    }
    if (right > 0) {
      curr.append(')');
      help(left, right - 1, resList, curr);
      curr.deleteCharAt(curr.length() - 1);
    }
  }
}
