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
public class IPAddress {
    private List<String> resList;

    public List<String> restoreIpAddresses(String s) {
        resList = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < 4 || s.length() > 12) {
            return resList;
        }

        generateIp(s, 0, new LinkedList<>());
        return resList;
    }

    private void generateIp(String s, int start, LinkedList<String> curr) {
        // 当处理到最后一个字符 并且 得到了四个有效的ip段，说明得到了一组解
        if (start == s.length() && curr.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (String num : curr) {
                sb.append(".").append(num);
            }
            resList.add(sb.toString().replaceFirst(".", ""));
            return;
        }

        // 剪枝1：剩余的字符 即使 按照剩下的ip段全放三位数都放不下，剪掉
        if (s.length() - start > 3 * (4 - curr.size())) {
            return;
        }
        // 剪枝2：剩余的字符 即使 按照剩下的ip段全放一位数都不够放，剪掉
        if (s.length() - start < 4 - curr.size()) {
            return;
        }

        int num = 0;
        for (int i = start; i < start + 3 && i < s.length(); i++) {
            // 从字符串上一位一位截取
            num = num * 10 + (s.charAt(i) - '0');
            // 剪枝3：每一个IP段组成的数字应该在[0...255]之间
            if (num < 0 || num > 255) {
                return;
            }
            // 剪枝4：每一个IP段不应该存在前缀0
            if (i > start && s.charAt(start) == '0') {
                return;
            }
            curr.addLast(s.substring(start, i + 1));
            generateIp(s, i + 1, curr);
            curr.removeLast();
        }
    }

    public static void main(String[] args) {
        String s = "012201";
        IPAddress ip = new IPAddress();
        List<String> res = ip.restoreIpAddresses(s);
        System.out.println(res);
    }
}
