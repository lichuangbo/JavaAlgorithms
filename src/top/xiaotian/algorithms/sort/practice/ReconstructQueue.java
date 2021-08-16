package top.xiaotian.algorithms.sort.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，
 * 前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 * 示例 1：
 *
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 *
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 *
 * 示例 2：
 *
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 * @author lichuangbo
 * @date 2021/8/16
 */
public class ReconstructQueue {

  /**
   * 从低到高考虑
   */
  public int[][] reconstructQueue(int[][] people) {
    int len = people.length;
    int[][] res = new int[len][2];
    // tmp，辅助统计索引下标占用情况
    List<Integer> tmpList = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      tmpList.add(i);
    }
    // 排序，身高升序（如果身高相等，按照k值降序排列）
    Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
    // 索引list,记录元素放置位置
    List<Integer> indexList = new ArrayList<>();
    List<Integer> collect = Arrays.stream(people).map(a -> a[1]).collect(Collectors.toList());
    for (int i = 0; i < collect.size(); i++) {
      Integer k = tmpList.get(collect.get(i));
      indexList.add(k);
      tmpList.remove(k);
    }
    // 将元素摆放到正确的位置
    int i = 0;
    for (int[] person : people) {
      res[indexList.get(i)] = person;
      i++;
    }
    return res;
  }

  /**
   * 从高到低考虑
   * 时间O(n2)
   */
  public int[][] reconstructQueue2(int[][] people) {
    Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
    List<int[]> res = new ArrayList<>();
    for (int i = 0; i < people.length; i++) {
      res.add(people[i][1], people[i]);
    }
    return res.toArray(new int[res.size()][2]);
  }

  public static void main(String[] args) {
    int[][] person = new int[][]{
        {7,0},
        {4,4},
        {7,1},
        {5,0},
        {6,1},
        {5,2}
    };
    int[][] ints = new ReconstructQueue().reconstructQueue(person);

  }
}
