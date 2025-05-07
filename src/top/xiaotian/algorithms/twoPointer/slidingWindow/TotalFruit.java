package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 904. 水果成篮
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 * <p>
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 * <p>
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 * <p>
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 * <p>
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 * <p>
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 */
public class TotalFruit {
    /**
     * 题目解析
     * 1. fruits[i]表示这棵树水果种类，一棵树只有一种水果类型   说明[1,2,1]这个示例中，总共有2种水果，水果1和水果2
     * 2. 走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。  说明出现两个类型之外的类型就要停止
     * 说明我们要找的是最长连续的子数组，并且子数组中得包含两种不同的元素
     */

    /**
     * 滑动窗口
     * 时间 O(n)
     * 空间 O(1)
     */
    public int totalFruit(int[] fruits) {
        // 滑动窗口内维护两个类型的水果   key是水果种类，value是能采集这种水果的个数
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0, left = 0, right = 0;
        while (right < fruits.length) {
            // 扩展右边界    确定采摘这棵树，并且采摘一个
            int rightFruit = fruits[right];
            map.put(rightFruit, map.getOrDefault(rightFruit, 0) + 1);
            // 收缩左边界
            while (map.size() > 2) {
                // 窗口范围内最左边那棵树刨除掉，最左边那个水果也就不要了
                int leftFruit = fruits[left];
                map.put(leftFruit, map.get(leftFruit) - 1);
                // 窗口范围内最左边那棵树刨除掉，采集这个水果的个数变为0，就不要这种水果了，为后续的水果种类留下一个篮子的位置
                if (map.get(leftFruit) == 0) {
                    map.remove(leftFruit);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 滑动窗口，优化
     */
    public int totalFruit2(int[] fruits) {
        // 利用题目中的 0 <= fruits[i] < fruits.length 条件，用一个freq数组来统计元素的频率
        int[] freq = new int[fruits.length];
        // 水果种类
        int fruitCategory = 0;
        int res = 0, left = 0, right = 0;
        while (right < fruits.length) {
            int rightFruit = fruits[right];
            freq[rightFruit]++;
            // 这个水果第一次出现，水果种类自增1
            if (freq[rightFruit] == 1) {
                fruitCategory++;
            }
            while (fruitCategory > 2) {
                int leftFruit = fruits[left];
                freq[leftFruit]--;
                if (freq[leftFruit] == 0) {
                    fruitCategory--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

}
