package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列问题
 */
public class Permutation {

  private List<List<Integer>> res;

  /**
   * 46. 全排列
   *
   * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
   *
   *
   *
   * 示例 1：
   *
   * 输入：nums = [1,2,3] 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   */
  public List<List<Integer>> permute(int[] nums) {
    res = new ArrayList<>();
    help(nums, res, new LinkedList<>(), new boolean[nums.length]);
    return res;
  }

  private void help(int[] nums, List<List<Integer>> resList, LinkedList<Integer> currList, boolean[] visited) {
    if (currList.size() == nums.length) {
      resList.add(new ArrayList<>(currList));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }
      currList.add(nums[i]);
      visited[i] = true;
      help(nums, resList, currList, visited);
      currList.removeLast();
      visited[i] = false;
    }
  }

  /**
   * 47. 全排列 II
   *
   * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
   *
   *
   *
   * 示例 1：
   *
   * 输入：nums = [1,1,2] 输出： [[1,1,2], [1,2,1], [2,1,1]]
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    res = new ArrayList<>();
    Arrays.sort(nums);
    help2(nums, res, new LinkedList<>(), new boolean[nums.length]);
    return res;
  }

  private void help2(int[] nums, List<List<Integer>> resList, LinkedList<Integer> curr, boolean[] visited) {
    if (curr.size() == nums.length) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }
      // 为什么i>0&&nums[i]==nums[i-1]达不到过滤的效果？以[1,1,2]为例，选择1后向下递归，由于下标0已经标记访问过，开始访问下标1，下标1没有访问过但是满足了过滤条件，
      // 被拦住了，![visited[i-1]就是为了放开这种情况
      if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
        continue;
      }

      curr.add(nums[i]);
      visited[i] = true;
      help2(nums, resList, curr, visited);
      curr.removeLast();
      visited[i] = false;
    }
  }

  /**
   * 剑指 Offer 38. 字符串的排列
   *
   * 输入一个字符串，打印出该字符串中字符的所有排列。 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
   *
   * 示例: 输入：s = "abc" 输出：["abc","acb","bac","bca","cab","cba"]
   *
   * 限制： 1 <= s 的长度 <= 8
   */
  public String[] permutation(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    boolean[] visited = new boolean[chars.length];
    List<String> resList = new ArrayList<>();
    help(chars, visited, 0, new StringBuilder(), resList);

    String[] resArr = new String[resList.size()];
    for (int i = 0; i < resList.size(); i++) {
      resArr[i] = resList.get(i);
    }
    return resArr;
  }

  private void help(char[] chars, boolean[] visited, int index, StringBuilder curr,
      List<String> resList) {
    if (index == chars.length) {
      resList.add(curr.toString());
      return;
    }

    for (int i = 0; i < chars.length; i++) {
      if (i != 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
        continue;
      }

      if (!visited[i]) {
        visited[i] = true;
        curr.append(chars[i]);
        help(chars, visited, index + 1, curr, resList);
        curr.deleteCharAt(curr.length() - 1);
        visited[i] = false;
      }
    }
  }
}
