package top.xiaotian.algorithms.twoPointer.fastSlowPointer;


import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」 定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 *
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
            if (set.contains(n)) {
                return false;
            }
            set.add(n);

            int sum = 0;
            while (n != 0) {
                int m = n % 10;
                sum += m * m;
                n = n / 10;
            }
            n = sum;
        }
        return true;
    }

    /***
     * 转化为链表问题，每个节点代表一个数，指向其各位平方和的下一个数
     * @see CycleList
     * 1. 非快乐数会出现循环，可以使用快慢指针来检测循环
     * 2. 快乐数最终会变为1
     * 快慢指针
     * 时间O(logn)
     */
    public boolean isHappy2(int n) {
        int fast = n, slow = n;
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
