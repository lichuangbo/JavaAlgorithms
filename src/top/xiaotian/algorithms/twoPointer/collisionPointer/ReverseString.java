package top.xiaotian.algorithms.twoPointer.collisionPointer;

import top.xiaotian.util.SwapUtil;

/**
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class ReverseString {
    /**
     * 对撞指针
     * 时间O(n)
     * @param s
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
            l++; r--;
        }
    }
}
