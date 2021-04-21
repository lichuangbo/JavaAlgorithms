package top.xiaotian.algorithms.dp;

import java.util.Arrays;

/**
 * 面试题 17.21. 直方图的水量
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/4/2 12:47
 * @Description: 描述:
 */
public class VolumeOfHistogramLcci {
    public int trap(int[] height) {
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int lHeight = height[i], rHeight = height[i];
            // 从当前位置出发，寻找右区间中最高柱子
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > rHeight) {
                    rHeight = height[j];
                }
            }
            // 寻找左区间中最高柱子
            for (int k = i - 1; k >= 0; k--) {
                if (height[k] > lHeight) {
                    lHeight = height[k];
                }
            }
            // 以第4列为例，他左侧最高柱子高度为2，右侧最高柱子高度为3，那么第4列能放的水量为（max(2,3)-1）=1
            sum += Math.min(lHeight, rHeight) - height[i];
        }
        return sum;
    }

    public int trap2(int[] height) {
        int len = height.length;
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        // 方法一中寻找左右区间存在重复计算，使用动态规划进行优化
        // maxLeft[i]记录 [0...i-1]列中最大值
        maxLeft[0] = height[0];
        for (int i = 1; i < len; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        System.out.println(Arrays.toString(maxLeft));
        // maxLeft[i]记录 [i+1...len-1]列中最大值
        maxRight[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }
        System.out.println(Arrays.toString(maxRight));

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = new VolumeOfHistogramLcci().trap2(height);
        System.out.println(trap);
    }
}
