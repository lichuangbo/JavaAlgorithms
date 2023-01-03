package top.xiaotian.algorithms.backtrack.combination_sum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 216. 组合总和 III 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9 每个数字 最多使用一次 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7 输出: [[1,2,4]] 解释: 1 + 2 + 4 = 7 没有其他符合的组合了。
 */
public class CombinationSumIII {

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> resList = new ArrayList<>();
    help(k, n, 1, resList, new LinkedList<>(), 0);
    return resList;
  }

  private void help(int k, int n, int currN, List<List<Integer>> resList,
      LinkedList<Integer> currList, int sum) {
    if (sum > n) {
      return;
    }
    // 找到一组解，需要满足两个条件：当前集合装满 并且 当前集合元素之和=n
    if (currList.size() == k && sum == n) {
      resList.add(new ArrayList<>(currList));
      return;
    }

    for (int i = currN; i <= 9 - (k - currList.size()) + 1; i++) {
      currList.add(i);
      sum += i;
      help(k, n, i + 1, resList, currList, sum);
      currList.removeLast();
      sum -= i;
    }
  }
}
