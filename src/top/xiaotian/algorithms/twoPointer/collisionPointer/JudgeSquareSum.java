package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 */
public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        // a/b区间只可能在[0...√c]
        int a = 0, b = (int)Math.sqrt(c);
        // a/b指针相等时，为有效解（2=1*1+1*1）
        while (a <= b) {
            int cur = a * a + b * b;
            if (cur == c) {
                return true;
            } else if (cur > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        int max = (int)Math.sqrt(c);
        for (int a = 0; a <= max; a++) {
            int b = (int)Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }
}
