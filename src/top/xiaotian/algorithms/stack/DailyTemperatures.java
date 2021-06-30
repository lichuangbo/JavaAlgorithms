package top.xiaotian.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperatures {

  /**
   * 栈中存储 (温度, 对应下标)元素, 存储温度是要判断栈顶温度和当前温度的大小关系，存储下标是要给对应的res下标赋值
   * 时间：
   *  O(n),遍历一遍array,同时每一个元素有一次入栈出栈操作
   */
  public int[] dailyTemperatures(int[] temperatures) {
    Deque<int[]> deque = new ArrayDeque<>();
    int[] res = new int[temperatures.length];
    for (int i = 0; i < temperatures.length; i++) {
      while (!deque.isEmpty() && deque.peekLast()[0] < temperatures[i]) {
        int[] peek = deque.removeLast();
        int pos = peek[1];
        res[pos] = i - pos;
      }
      deque.addLast(new int[]{temperatures[i], i});
    }
    return res;
  }

  /**
   * 可以省去当前温度的存储，因为可以通过temperatures数组拿到对应的温度值
   */
  public int[] dailyTemperatures2(int[] temperatures) {
    Deque<Integer> deque = new ArrayDeque<>();
    int[] res = new int[temperatures.length];
    for (int i = 0; i < temperatures.length; i++) {
      while (!deque.isEmpty() && temperatures[deque.peekLast()] < temperatures[i]) {
        int pos = deque.removeLast();
        res[pos] = i - pos;
      }
      deque.addLast(i);
    }
    return res;
  }

  public static void main(String[] args) {
    int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
    int[] res = new DailyTemperatures().dailyTemperatures(temperatures);
    System.out.println(Arrays.toString(res));
  }
}
