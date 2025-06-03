package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 344. 反转字符串
 * 提示
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s[i] 都是 ASCII 码表中的可打印字符
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class ReverseString {
    /**
     * 对撞指针
     * 时间O(n)
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        int l = 0, r = s.length - 1;
        // l和r碰撞时，这个字符不用翻转
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}
