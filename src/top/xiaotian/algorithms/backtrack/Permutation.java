package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/29 8:45
 * @Description: 描述: 47
 */
public class Permutation {
    List<List<Integer>> res;
    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        if (nums == null || nums.length == 0) {
            return res;
        }

        generatePermutation(nums, 0, new LinkedList<>());
        return res;
    }

    // curr保存了有index个元素的排列
    private void generatePermutation(int[] nums, int index, LinkedList<Integer> curr) {
        System.out.println(index + " : " + curr);
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            System.out.println("get " + curr);
            return;
        }

        // 基于1，递归向下遍历数组，由于1使用过了，选定2，递归向下遍历数组，由于1,2使用过了，选定3，递归向下，此时由于index到达边界，得到了一组解[1,2,3]
        // 状态回退，3不选，没有其他选择，继续回退，上步的2不选，选定3，递归向下遍历数组，由于1,3使用过了，选定2，递归向下，此时由于index到达边界，得到一组解[1,3,2]
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.addLast(nums[i]);
                visited[i] = true;
                System.out.println("nums[" + index + "] = " + nums[index] + " , use " + nums[i]);
                generatePermutation(nums, index + 1, curr);
                curr.removeLast();
                visited[i] = false;
            }
        }
    }

    // 47. 全排列 II
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        generatePermutationII(nums, 0, new LinkedList<>());
        return res;
    }

    private void generatePermutationII(int[] nums, int index, LinkedList<Integer> curr) {
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            /**
             * 剪枝条件： 当前值与前一个值相同,并且前一个值没有访问过，就跳过
             * 观察到 在回溯遍历过程中，如果紧挨着的两个相等元素，第一个元素对应的分支正常进行，第二个元素对应分支是重复的，
             * 在处理第二个元素时，他将第一个元素撤销选择，在向下递归时会再次选中第一个元素，这就导致了重复排列的产生
             */
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;

            if (!visited[i]) {
                curr.addLast(nums[i]);
                visited[i] = true;
                generatePermutationII(nums, index + 1, curr);
                curr.removeLast();
                visited[i] = false;
            }
        }
    }

    /**
     * 剑指 Offer 38. 字符串的排列
     *
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     *
     * 限制：
     * 1 <= s 的长度 <= 8
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

  private void help(char[] chars, boolean[] visited, int index, StringBuilder curr, List<String> resList) {
    if (index == chars.length) {
      resList.add(curr.toString());
      return;
    }

    for (int i = 0; i < chars.length; i++) {
      if (i != 0 && chars[i] == chars[i - 1] && !visited[i - 1])
        continue;

      if (!visited[i]) {
        visited[i] = true;
        curr.append(chars[i]);
        help(chars, visited, index + 1, curr, resList);
        curr.deleteCharAt(curr.length() - 1);
        visited[i] = false;
      }
    }
  }

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
        int[] nums = {1, 1, 2};
        Permutation permutation = new Permutation();
//        List<List<Integer>> permute = permutation.permute(nums);
        List<List<Integer>> permute = permutation.permuteUnique(nums);
        System.out.println(permute);
    }
}
