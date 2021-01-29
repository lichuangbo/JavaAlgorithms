package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = new Permutation().permute(nums);
        System.out.println(permute);
    }
}
