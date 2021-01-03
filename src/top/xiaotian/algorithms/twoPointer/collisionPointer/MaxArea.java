package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class MaxArea {
    /**
     * 对撞指针!!
     * 时间O(n)
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            int width = r - l;
            int minHeight = Math.min(height[l], height[r]);
            if (minHeight * width > res)
                res = minHeight * width;

            // 不管左移还是右移，底都会减一；但是高是有区别的，应该放弃掉较低的，保留较高的
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }
}
