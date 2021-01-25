package top.xiaotian.dataStructures.queue.practice;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/18 16:46
 * @Description: 描述:
 */
public class ZigzagLevelOrder {
    /**
     * 时间：O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) return resList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> tmpList = new ArrayList<>();
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.poll();
                if (level % 2 == 0) {
                    tmpList.add(0, tmpNode.val);
                } else {
                    tmpList.add(tmpNode.val);
                }
                if (tmpNode.left != null) queue.add(tmpNode.left);
                if (tmpNode.right != null) queue.add(tmpNode.right);
            }
//            if (level % 2 == 0) {// 偶数行进行翻转
//                Integer[] items = tmpList.toArray(new Integer[0]);
//                int l = 0, r = items.length - 1;
//                while (l < r) {
//                    int tmp = tmpList.get(l);
//                    items[l] = items[r];
//                    items[r] = tmp;
//                    l++; r--;
//                }
//                tmpList = Arrays.asList(items);
//            }
            resList.add(tmpList);
        }
        return resList;
    }

    /**
     * 时间：O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) return resList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            Deque<Integer> deque = new LinkedList<>();// 双端队列，可以在队列两端入队和出队
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if (level % 2 == 0) {// 偶数层，推送到队首
                    deque.offerFirst(currNode.val);
                } else {// 奇数层，推送到队尾
                    deque.offerLast(currNode.val);
                }
                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
            resList.add(new LinkedList<>(deque));
        }
        return resList;
    }

    public static void main(String[] args) {
        String[] nums = {"3", "9", "20", "null", "null", "15", "7"};
        TreeNode root = new TreeNode(nums);
        List<List<Integer>> res = new ZigzagLevelOrder().zigzagLevelOrder2(root);
        System.out.println(res);
    }
}
