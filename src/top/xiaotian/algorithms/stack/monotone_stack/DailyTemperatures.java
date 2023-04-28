package top.xiaotian.algorithms.stack.monotone_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

  /**
   * 暴力-双重for循环，超时
   * 时间O(n2)
   */
  public int[] dailyTemperatures(int[] temperatures) {
    int len = temperatures.length;
    int[] res = new int[len];
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        if (temperatures[i] < temperatures[j]) {
          res[i] = j - i;
          break;
        }
      }
    }
    return res;
  }

  /**
   * 单调栈：栈顶到栈底，元素单调递增的顺序
   * stack: |69|
   *        |71|
   *        |75|
   */
  public int[] dailyTemperatures2(int[] temperatures) {
    Deque<Integer> deque = new ArrayDeque<>();
    int len = temperatures.length;
    int[] res = new int[len];
    for (int i = 0; i < len; i++) {
      // 栈中存放的是元素的下标（因为题目问的其实是下标的间隔长度）
      while (!deque.isEmpty() && temperatures[deque.peekLast()] < temperatures[i]) {
        // 在每一次出栈时，拿到出栈元素的下标pos，此时进行更新res[pos]
        int pos = deque.pollLast();
        // 因为此时是遍历到下标i时，才出的栈（说明: temperatures[i]是第一个大于temperatures[pos]的元素），记录下res[pos]=两者下标间距
        res[pos] = i - pos;
      }
      deque.addLast(i);
    }
    return res;
  }
}
