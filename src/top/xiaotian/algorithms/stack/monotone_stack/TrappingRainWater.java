package top.xiaotian.algorithms.stack.monotone_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42. 接雨水 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1] 输出：6 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接
 * 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * @author lichuangbo
 * @date 2022/9/13
 */
public class TrappingRainWater {

  // 双指针
  public int trap(int[] height) {
    int res = 0;
    int len = height.length;
    for (int i = 0; i < len; i++) {
      // 求解当前柱子左侧最高的柱子
      int leftHigh = 0;
      for (int j = 0; j < i; j++) {
        if (height[j] > leftHigh) {
          leftHigh = height[j];
        }
      }
      // 求解当前柱子右侧最高的柱子
      int rightHigh = 0;
      for (int j = i + 1; j < len; j++) {
        if (height[j] > rightHigh) {
          rightHigh = height[j];
        }
      }

      // 当左右侧柱子最高高度为0，必然接不了雨水；左右侧柱子高度最小值小于当前柱子，说明是一个凸起，也接不了雨水
      int minHigh = Math.min(leftHigh, rightHigh);
      if (leftHigh != 0 && rightHigh != 0 && minHigh > height[i]) {
        res += minHigh - height[i];
      }
    }

    return res;
  }

  // 动态规划
  public int trap2(int[] height) {
    int res = 0;
    int len = height.length;

    // dpLeft[i]表示在height[i]元素前的最大值
    int[] dpLeft = new int[len];
    // dpRight[i]表示在height[i]元素后的最大值
    int[] dpRight = new int[len];
    dpLeft[0] = height[0];
    for (int i = 1; i < len; i++) {
      dpLeft[i] = Math.max(height[i], dpLeft[i - 1]);
    }
    dpRight[len - 1] = height[len - 1];
    for (int i = len - 2; i >= 0; i--) {
      dpRight[i] = Math.max(height[i], dpRight[i + 1]);
    }

    for (int i = 0; i < len; i++) {
      int leftHigh = dpLeft[i];
      int rightHigh = dpRight[i];
      // 当左右侧柱子最高高度为0，必然接不了雨水；左右侧柱子高度最小值小于当前柱子，说明是一个凸起，也接不了雨水
      int minHigh = Math.min(leftHigh, rightHigh);
      if (leftHigh != 0 && rightHigh != 0 && minHigh > height[i]) {
        res += minHigh - height[i];
      }
    }

    return res;
  }

  public int trap3(int[] height) {
    int res = 0;
    // 单调栈，栈中存放的是柱子的下标
    // 栈顶到栈底：元素单调递增
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < height.length; i++) {
      while (!deque.isEmpty() && height[deque.peekLast()] < height[i]) {
        int h = height[deque.pollLast()];
        if (deque.isEmpty()) {
          break;
        }
        int distance = i - deque.peekLast() - 1;
        int min = Math.min(height[deque.peekLast()], height[i]);
        res += distance * (min - h);
      }
      deque.addLast(i);
    }
    return res;
  }

}
