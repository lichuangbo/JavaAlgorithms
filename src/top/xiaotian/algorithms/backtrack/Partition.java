package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6
 */
public class Partition {
    private List<List<String>> resList;

    public List<List<String>> partition(String s) {
        resList = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return resList;
        }

        help(s, 0, new LinkedList<>());
        return resList;
    }

    private void help(String s, int index, LinkedList<String> curr) {
        if (index == s.length()) {
            resList.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                continue;
            }
            curr.addLast(s.substring(index, i + 1));
            help(s, i + 1, curr);
            curr.removeLast();
        }
    }

    private boolean isValid(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++; r--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        Partition partition = new Partition();
        List<List<String>> res = partition.partition(s);
        System.out.println(res);
    }
}
