package top.xiaotian.algorithms.stack;

/**
 * 面试题 03.01. 三合一
 * 三合一。描述如何只用一个数组来实现三个栈。
 * <p>
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 * <p>
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 * <p>
 * 示例1:
 * <p>
 * 输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 * 输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 */
class TripleInOne {
  int N = 3;
  int[] data;
  int[] locations;
  int size;

  public TripleInOne(int stackSize) {
    size = stackSize;
    data = new int[size * N];
    locations = new int[N];
    for (int i = 0; i < N; i++) {
      locations[i] = i * size;
    }
  }

  public void push(int stackNum, int value) {
    int idx = locations[stackNum];
    if (idx < (stackNum + 1) * size) {
      data[idx] = value;
      locations[stackNum]++;
    }
  }

  public int pop(int stackNum) {
    int idx = locations[stackNum];
    if (idx > stackNum * size) {
      locations[stackNum]--;
      return data[idx - 1];
    } else {
      return -1;
    }
  }

  public int peek(int stackNum) {
    int idx = locations[stackNum];
    if (idx > stackNum * size) {
      return data[idx - 1];
    } else {
      return -1;
    }
  }

  public boolean isEmpty(int stackNum) {
    return locations[stackNum] == stackNum * size;
  }
}
