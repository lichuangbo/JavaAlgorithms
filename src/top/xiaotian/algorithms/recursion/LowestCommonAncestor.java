package top.xiaotian.algorithms.recursion;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/27 11:21
 * @Description: 描述:
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (p.val < root.val && q.val < root.val) {// p、q节点都位于根的左子树，在左子树中寻找
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {// p、q节点都位于根的右子树，在右子树中寻找
            return lowestCommonAncestor(root.right, p, q);
        }
        /**
         *     2
         *    / \
         *   1   3
         * [2, 1, 3] 类型二叉搜索树，p=1&&q=3  p=1&&q==2  p==2&&q==1 都是直接返回根节点即可
         */
        return root;
    }

    // 236. 二叉树的最近公共祖先  ****
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 遍历到叶子节点说明没有找到，返回null
        if (root == null) {
            return null;
        }
        // 遍历到p节点或者q节点，向上层返回
        if (root == p || root == q) {
            return root;
        }
        // 递归处理左子树和右子树
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        // 1.p, q其中有一个在root的右子树中，此时返回的right指向q或q    2.p, q两节点都在root的右子树中，此时返回的right就是一个最近公共祖先
        if (left == null) {
            return right;
        }
        // 同上
        if (right == null) {
            return left;
        }
        // p和q分布于root的两侧，说明root就是p, q的最近公共祖先
        return root;
    }

    // 98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        // 错误！！
//        if (root == null) {
//            return true;
//        }
//        if (root.left == null && root.right == null) {// 走到叶子节点，返回true
//            return true;
//        }
//        if (root.left != null && root.right != null && (root.left.val > root.val || root.right.val < root.val)) {
//            return false;
//        }
//
//        return isValidBST(root.left) && isValidBST(root.right);
        return help(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 递归语义：自顶向下看，以root为根的二叉搜索树中，判断树中左子节点值和右子节点值是否同时符合二叉搜索树特性
    private boolean help(TreeNode root, long l, long r) {
        if (root == null) {
            return true;
        }

        // root节点不在区间内，直接返回false
        if (root.val <= l || root.val >= r) {
            return false;
        }
        // 左子树的上界是根节点值(小于根节点)， 右子树的下界是根节点值(大于根节点)
        return help(root.left, l, root.val) && help(root.right, root.val, r);
    }

    // 450. 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            TreeNode successor = minimum(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;

            return successor;
        }
    }

    // 寻找以node为根节点的最小数的TreeNode节点
    private TreeNode minimum(TreeNode node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    // 删除以node为根节点的最小值后，返回新的根节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 时间：O(n), 节点全部遍历
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<TreeNode> treeNodes = inOrder(root, new ArrayList<>());
        return treeNodes.get(k - 1).val;
    }

    // 递归语义：返回以root为根节点的中序遍历的结果
    private ArrayList<TreeNode> inOrder(TreeNode root, ArrayList<TreeNode> currList) {
        if (root == null) {
            return currList;
        }

        // 递归取树中最小节点值
        inOrder(root.left, currList);
        // 将他加入到集合中
        currList.add(root);
        inOrder(root.right, currList);
        return currList;
    }

    // 时间：O(H+k) H指树高，因为即使k=1，也要遍历到最小节点，并且每加入一个节点都要这样遍历； 遍历到第k个节点时可以退出
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (true) {
            while (root != null) {
                deque.addLast(root);
                root = root.left;
            }

            TreeNode tmp = deque.removeLast();
            if (--k == 0) {
                return tmp.val;
            }
            root = tmp.right;
        }
    }

    public static void main(String[] args) {
//        String[] nums = {"2", "1", "3"};
//        String[] nums = {"5", "1", "4", "null", "null", "3", "6"};
        String[] nums = {"5", "3", "6", "2", "4", "null", "null", "1"};
        TreeNode root = new TreeNode(nums);
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
//        TreeNode res = lowestCommonAncestor.lowestCommonAncestor(root, new TreeNode(1), new TreeNode(2));
//        System.out.println(res.val);

//        boolean validBST = lowestCommonAncestor.isValidBST(root);
//        System.out.println(validBST);

//        TreeNode res = lowestCommonAncestor.deleteNode(root, 1);
//        TreeNode res = lowestCommonAncestor.removeMin(root);
//        System.out.println(res);

        int res = lowestCommonAncestor.kthSmallest2(root, 3);
        System.out.println(res);
    }
}
