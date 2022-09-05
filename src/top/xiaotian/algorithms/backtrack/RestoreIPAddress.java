package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/4
 */
public class RestoreIPAddress {
    private List<String> resList;

    public List<String> restoreIpAddresses(String s) {
        resList = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return resList;
        }

        help(s, 0, new LinkedList<>());
        return resList;
    }

    private void help(String s, int index, LinkedList<String> curr) {
        // 当处理到最后一个字符 并且 得到了四个有效的ip段，说明得到了一组解
        if (index == s.length() && curr.size() == 4) {
            resList.add(String.join(".", curr));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                curr.addLast(s.substring(index, i + 1));
                help(s, i + 1, curr);
                curr.removeLast();
            }
        }
    }

    private boolean isValid(String s, int l, int r) {
        // 分段数字长度必须小于4（case3也可以过滤掉）
        if (r - l > 3) {
            return false;
        }
        // 分段数字不能以0打头
        if (s.charAt(l) == '0' && l != r) {
            return false;
        }
        // 分段数字必须小于等于255
        int tmp = 0;
        while (l <= r) {
            tmp = tmp * 10 + (s.charAt(l) - '0');
            if (tmp > 255) {
                return false;
            }
            l++;
        }
        return true;
    }
}
