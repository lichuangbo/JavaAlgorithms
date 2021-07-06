package top.xiaotian.algorithms.greedy;

import java.util.*;

/**
 * 621. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 */
public class LeastInterval {
    /**
     * 选择任务策略：优先选择剩余执行次数最多的任务，这样做将每种任务的剩余执行次数尽可能平均，使得 CPU 处于待命状态的时间尽可能少
     */
    public int leastInterval(char[] tasks, int n) {
        // 统计任务频率
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // 任务类型总数
        int m = freq.size();
        // 初始化辅助变量：nextValid[i]表示第i个任务最早能执行的时间，rest[i]表示第i个任务剩余执行次数
        List<Integer> nextValid = new ArrayList<>(), rest = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            int value = entry.getValue();
            nextValid.add(1);// 初始为1
            rest.add(value);// 初始为任务频率
        }

        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            time++;
            // 计算最小任务执行时间
            int minNextValid = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (rest.get(j) != 0) {
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            // 将 time 更新为所有 nextValid 中的最小值，直接「跳过」待命状态，保证每一次对二元组的遍历都是有效的
            time = Math.max(time, minNextValid);
            int best = -1;
            for (int j = 0; j < m; j++) {
                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                    if (best == -1 || rest.get(j) > rest.get(best)) {
                        best = j;
                    }
                }
            }
            // 更新选中的best对应的nextValid和rest的值
            nextValid.set(best, time + n + 1);
            rest.set(best, rest.get(best) - 1);
        }
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int res = new LeastInterval().leastInterval(tasks, 2);
        System.out.println(res);
    }
}
