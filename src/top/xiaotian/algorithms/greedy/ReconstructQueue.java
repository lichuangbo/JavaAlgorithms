package top.xiaotian.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * 示例 2：
 * <p>
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= people.length <= 2000
 * 0 <= hi <= 106
 * 0 <= ki < people.length
 * 题目数据确保队列可以被重建
 *
 * @author lichuangbo
 * @date 2021/8/16
 */
public class ReconstructQueue {

    /**
     * 贪心算法
     * 时间O(nlogn + n^2)  n^2是因为有一层遍历，同时还有数组元素的移动
     */
    public int[][] reconstructQueue(int[][] people) {
        /**
         * 假设以k开始从小到大排列，当k相同时h按照从小到大排列（因为更接近结果）
         * [5,0] [7,0] [6,1] [7,1] [5,2] [4,4]
         * 观察结果：前四个都是符合条件的, [5,2]开始不符合了，要求前边有2个大于等于5的但是现在有4个，而且h的顺序也没有确定，不知道将这个元素调整到哪里
         *
         * 假设以h开始从大到小排列，当h相同时k按照从小到大排列（因为更接近结果）
         * [7,0] [7,1] [6,1] [5,0] [5,2] [4,4]
         * 观察结果：[6,1]开始不符合了，但是我们可以将[6,1]调整到第二位，并且这个调整是有效的。（首先[6,1]是符合的，前边有一个大于等于它；其次[7,1]也是符合的，前边有一个大于等于它；
         * 这是因为我们按照h排好序了，知道[6,1]的移动不会影响到[7,1]语义的变动）
         */
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            int index = people[i][1];
            res.add(index, people[i]);
        }
        return res.toArray(new int[res.size()][2]);
    }
}
