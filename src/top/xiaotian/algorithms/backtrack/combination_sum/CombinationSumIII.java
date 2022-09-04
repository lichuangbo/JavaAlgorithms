package top.xiaotian.algorithms.backtrack.combination_sum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 */
public class CombinationSumIII {
  private List<List<Integer>> res;

  public List<List<Integer>> combinationSumIII(int k, int n) {
    res = new ArrayList<>();
    generateSumIII(k, n, 1, new LinkedList<>());
    return res;
  }

  private void generateSumIII(int k, int n, int start, LinkedList<Integer> curr) {
    // 找到一组解，需要满足两个条件：当前集合装满 并且 当前集合元素之和=n
    if (curr.size() == k && n == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = start; i <= 9; i++) {
      curr.addLast(i);
      generateSumIII(k, n - i, i + 1, curr);
      curr.removeLast();
    }
  }
}
