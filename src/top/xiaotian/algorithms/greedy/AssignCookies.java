package top.xiaotian.algorithms.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 455. 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 *
 * 示例 1:
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/3/18 13:58
 * @Description: 描述: 392
 */
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        /**
         * 使用最大的饼干 分给 最贪心的小孩， 次大的饼干 分给 次贪心的小孩 。。。
         * 如果最大的饼干仍无法满足最贪心的小孩，那这个小孩无论如何都开心不了，跳过这个小孩
         */
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int gi = g.length - 1, si = s.length - 1;
        while (gi >= 0 && si >= 0) {
            if (s[si] >= g[gi]) {
                gi--;
                si--;
                res++;
            } else {
                gi--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 1, 9, 5};
        Arrays.sort(nums);

        // int型数组从大到小排序 需要转化为Integer
        Integer[] nums1 = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }
        Arrays.sort(nums1, (o1, o2) -> o2 - o1);
        System.out.println(Arrays.toString(nums1));
    }
}
