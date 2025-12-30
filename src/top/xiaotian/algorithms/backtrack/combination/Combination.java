package top.xiaotian.algorithms.backtrack.combination;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [1,2],
 * [1,3],
 * [1,4],
 * [2,3],
 * [2,4],
 * [3,4],
 * ]
 */
public class Combination {

    /**
     * 时间   O(n*2^n)
     * 空间   O(k)递归的深度等于组合的长度 k
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resList = new ArrayList<>();
        help2(n, k, 1, resList, new LinkedList<>());
        return resList;
    }

    private void help(int n, int k, int start, List<List<Integer>> resList, LinkedList<Integer> currList) {
        if (currList.size() == k) {
            resList.add(new ArrayList<>(currList));
            return;
        }

        for (int i = start; i <= n; i++) {
            currList.add(i);
            help(n, k, i + 1, resList, currList);
            currList.removeLast();
        }
    }

    private void help2(int n, int k, int start, List<List<Integer>> resList, LinkedList<Integer> currList) {
        if (currList.size() == k) {
            resList.add(new ArrayList<>(currList));
            return;
        }

        /**
         * 剪枝
         * n=4,k=4时，第一轮可以选择[1,4],假如选择了1，第二轮可以选择[2,4]，但是选择3或者4都是不可能凑成结果的，需要剪枝
         *
         * 如果剩余的数字数量 < 需要的数字数量，那么即使把后面所有数字都选上，也凑不齐一个长度为 k的组合，这个分支就没有必要继续搜索了
         * currList中存储着已经找到的元素，说明还有k-currList.size()个空位可以排列。那么问题转化为i取多少，可以确保[i...n]区间中至少有k-currList.size个元素
         * 取数的区间是[i,n]，一共有 n-i+1 个数，那么剩下的数一定要大于等于还要取的数即 n-i+1 >= k-currList.size()
         * 进一步推导出   i<=n-(k-path.size())+1
         */
        for (int i = start; i <= n - (k - currList.size()) + 1; i++) {
            currList.addLast(i);
            help2(n, k, i + 1, resList, currList);
            currList.removeLast();
        }
    }
}
