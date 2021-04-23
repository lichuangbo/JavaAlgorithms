package top.xiaotian.algorithms.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 */
public class LargestDivisibleSubset {
    /**
     * [1, 2, 4, 8]数组为例：
     *      判断当前元素是不是 整除子集尾元素 的倍数，如果是的话就加入整除子集中（这样保证了整个子集都是有效的）
     *      这样的解法，只是得到了合法解，并没有得到最大整除子集解
     *      我们在判断一个元素要不要加入 整除子集 时，必须找到当前整除子集元素中最长的，之后再追加该元素
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // dp[i]表示以i结尾的最长 整除子集 的长度
        int[] dp = new int[n];
        // path记录由哪个状态转移而来
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            // 至少包含自身一个数，因此起始长度为 1，由自身转移而来
            int len = 1, prev = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    // 如果能接在更长的序列后面，则更新「最大长度」&「从何转移而来」
                    if (dp[j] + 1 > len) {
                        len = dp[j] + 1;
                        prev = j;
                    }
                }
            }
            // 记录「最终长度」&「从何转移而来」
            dp[i] = len;
            path[i] = prev;
        }
        System.out.println("dp: " + Arrays.toString(dp));
        System.out.println("path: " + Arrays.toString(path));

        // 遍历所有的 dp[i]，取得「最大长度」和「对应下标」
        int max = -1, idx = -1;
        for (int i = 0; i < n; i++) {
            if (max < dp[i]) {
                max = dp[i];
                idx = i;
            }
        }

        // 使用 path[] 数组回溯出具体方案
        List<Integer> ans = new ArrayList<>();
        while (ans.size() != max) {
            ans.add(0, nums[idx]);
            idx = path[idx];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {9, 18, 54, 90, 108, 180, 360, 540, 720};
        List<Integer> res = new LargestDivisibleSubset().largestDivisibleSubset(arr);
        System.out.println(res);
    }
}
