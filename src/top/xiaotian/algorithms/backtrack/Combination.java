package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/29 9:45
 * @Description: 描述: 39 40 216 78 90 401
 */
public class Combination {
    private List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }

        generateCombinations(n, k, 1, new LinkedList<>());
        return res;
    }

    // 求解C(n, k), 当前已经找到的组合存储在curr中，需要从start开始搜索新的元素
    private void generateCombinations(int n, int k, int start, LinkedList<Integer> curr) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        /**
         * 剪枝：现在i遍历区间[start...n]，但是当i=n时，在循环里继续递归还会+1，此时已经没有空位可选了
         * curr中存储着已经找到的元素，说明还有k-curr.size()个空位可以排列，i取多少，可以确保[i...n]区间中至少有k-curr.size个元素
         * 2个空位：k-curr.size=2,i应该取n-1       1个空位：k-curr.size=1,i应该取n
         * 观察：i最多取 n - (k - curr.size) + 1
         */
        for (int i = start; i <= n - (k - curr.size()) + 1; i++) {
            curr.addLast(i);
            generateCombinations(n, k, i + 1, curr);
            curr.removeLast();
        }
    }
}
