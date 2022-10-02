package top.xiaotian.algorithms.stack.monotone_stack;

import java.util.Arrays;

/**
 * 84. 柱状图中最大的矩形 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author lichuangbo
 * @date 2022/9/15
 */
public class LargestRectangleInHistogram {

  public int largestRectangleArea(int[] heights) {
    int res = 0;
    for (int i = 0; i < heights.length; i++) {
      // 寻找左侧小于当前柱子的第一个柱子
      int left;
      for (left = i; left >= 0; left--) {
        if (heights[left] < heights[i]) {
          break;
        }
      }
      // 寻找右侧小于当前柱子的第一个柱子
      int right;
      for (right = i; right < heights.length; right++) {
        if (heights[right] < heights[i]) {
          break;
        }
      }

      int width = right - left - 1;
      int height = heights[i];
      res = Math.max(res, width * height);
    }
    return res;
  }

  public int largestRectangleArea2(int[] heights) {
    int len = heights.length;
    // dpLeft[i]表示heights[i]元素左侧比它大的第一个柱子的下标
    int[] dpLeft = new int[len];
    // dpRight[i]表示heights[i]元素右侧比它大的第一个柱子的下标
    int[] dpRight = new int[len];
    dpLeft[0] = -1;
    for (int i = 1; i < len; i++) {
      int left = i - 1;
      while (left >= 0 && heights[left] >= heights[i]) {
        left = dpLeft[left];
      }
      dpLeft[i] = left;
    }
    dpRight[len - 1] = len;
    for (int i = len - 2; i >= 0; i--) {
      int right = i + 1;
      while (right < len && heights[right] >= heights[i]) {
        right = dpRight[right];
      }
      dpRight[i] = right;
    }

    int res = 0;
    for (int i = 0; i < len; i++) {
      int width = dpRight[i] - dpLeft[i] - 1;
      int height = heights[i];
      res = Math.max(res, width * height);
    }
    return res;
  }
}
