package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。(元音字母不包含字母 "y")
 *
 * 示例 1：
 *
 * 输入："hello"
 * 输出："holle"
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class ReverseVowels {
    /**
     * 对撞指针
     * 时间O(n)
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        // l和r碰撞同样不需要处理
        while (l < r) {
            if (!isVowel(chars, l)) {
                l++;
                continue;
            }
            if (!isVowel(chars, r)) {
                r--;
                continue;
            }
            char tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
            l++; r--;
        }
        return new String(chars);
    }

    private boolean isVowel(char[] chars, int i) {
        if (chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u' ||
                chars[i] == 'A' || chars[i] == 'E' || chars[i] == 'I' || chars[i] == 'O' || chars[i] == 'U') {
            return true;
        }
        return false;
    }
}
