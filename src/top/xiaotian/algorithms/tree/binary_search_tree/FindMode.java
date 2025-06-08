package top.xiaotian.algorithms.tree.binary_search_tree;

import java.util.*;

import top.xiaotian.util.TreeNode;

/**
 * 501. 二叉搜索树中的众数
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 *
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 *
 * 假定 BST 满足如下定义：
 *
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindMode {

    /**
     * 暴力法
     * 时间 O(n)  中序遍历一趟 + 哈希表遍历两次 + 集合转化为数组
     * 空间 O(n)  递归树，O(h)+ 哈希表，最坏情况下，都是单次出现
     */
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        inorder(root, freqMap);
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        List<Integer> resList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (Objects.equals(entry.getValue(), max)) {
                resList.add(entry.getKey());
            }
        }
        return resList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void inorder(TreeNode root, Map<Integer, Integer> freqMap) {
        if (root == null) return;

        inorder(root.left, freqMap);
        freqMap.put(root.val, freqMap.getOrDefault(root.val, 0) + 1);
        inorder(root.right, freqMap);
    }

    /**
     * 时间 O(n)  中序遍历树，每个节点访问一次 + 把结果集从集合中挪到数组里
     * 空间 O(h)  最坏情况下h=n
     */
    int maxCount = 0;
    int currCount = 0;
    TreeNode prev = null;
    List<Integer> resList = new ArrayList<>();
    public int[] findMode2(TreeNode root) {
        help(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    private void help(TreeNode root) {
        if (root == null)   return;

        help(root.left);
        if (prev != null && prev.val == root.val) {
            currCount++;
        } else {
            currCount = 1;
        }
        // 出现新的频率更高的数字，把它纳入结果集，之前的不要了
        if (currCount > maxCount) {
            maxCount = currCount;
            resList.clear();
            resList.add(root.val);
        } else if (currCount == maxCount) {// 频次相同的众数
            resList.add(root.val);
        }
        prev = root;
        help(root.right);
    }
}
