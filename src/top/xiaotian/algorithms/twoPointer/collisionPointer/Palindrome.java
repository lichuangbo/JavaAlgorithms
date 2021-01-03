package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class Palindrome {
    /**
     * 对撞指针
     * 时间O(n)
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        char[] replace = s.toLowerCase().toCharArray();
        int l = 0, r = replace.length - 1;
        // l和r相撞时，这个字符算是回文
        while (l < r) {
            if (!isVaild(replace, l)) {
                l++;
                continue;
            }
            if (!isVaild(replace, r)) {
                r--;
                continue;
            }
            if (replace[l] != replace[r]) {
                return false;
            }
            l++; r--;
        }
        return true;
    }

    private boolean isVaild(char[] chars, int i) {
        if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= '0' && chars[i] <= '9') || (chars[i] >= 'A' && chars[i] <= 'Z')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean res = new Palindrome().isPalindrome(s);
        System.out.println(res);
    }
}
