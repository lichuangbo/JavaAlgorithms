package top.xiaotian.algorithms.twoPointer.fastSlowPointer;


import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，
 * 那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 示例：
 *
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class HappyNumber {
    /***
     * 集合判重
     * 时间O(logn)
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            // 集合中存在该数字，说明已经进入循环，返回false
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = getNextRoundSum(n);
        }
        return true;
    }

    /***
     * 快慢指针（变形）
     * 时间O(logn)
     */
    public boolean isHappy2(int n) {
        int fast = n;
        int slow = n;
        while (true) {
            fast = getNextRoundSum(getNextRoundSum(fast));
            slow = getNextRoundSum(slow);
            if (fast == 1) {
                return true;
            }
            if (fast == slow) {
                return false;
            }
        }
    }

    private int getNextRoundSum(int n) {
        int sum = 0;
        while (n > 0) {
            int m = n % 10;
            sum += m * m;
            n = n / 10;
        }
        return sum;
    }
}
