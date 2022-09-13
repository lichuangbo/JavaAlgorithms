package top.xiaotian.algorithms.stack.monotone_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. 每日温度 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperatures {

  public int[] dailyTemperatures(int[] temperatures) {
    Deque<Integer> deque = new ArrayDeque<>();
    int len = temperatures.length;
    int[] res = new int[len];
    for (int i = 0; i < len; i++) {
      // 维护一个单调栈，栈中存放的是元素的下标（因为题目问的其实是下标的间隔长度）
      // 栈顶到栈底：元素单调递增的顺序
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
