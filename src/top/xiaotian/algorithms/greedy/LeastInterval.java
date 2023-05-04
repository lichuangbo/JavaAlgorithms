package top.xiaotian.algorithms.greedy;

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
 * 示例 2：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * 示例 3：
 *
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 *      A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *
 *
 * 提示：
 *
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 */
public class LeastInterval {
    /**
     * <a href="https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/">题解</a>
     * @see task_scheduler1.png task_scheduler2.png
     * tasks仅有一种任务[A, A, A, A, A, A]，冷却时间为n时，所需时间是(6-1)*(n+1) + 1   => 前5个任务需要2*(n+1)个冷却时间，最后一个不需要，只要消耗一个单位时间
     * tasks仅有多种任务[A, A, A, A, A, A, B, B, B, B, B, B, C, C]，冷却时间为n时，所需时间是(6-1)*(n+1) + 2
     *
     * 特殊case: tasks=["A","A","A","B","B","B", "C","C","C", "D", "D", "E"] n=2
     * 如果使用第一个桶放E元素，会存在空隙，引入了多余的冷却时间=9；如果使用第三个桶放E元素，不会存在空隙，同时也满足冷却时间=12
     *
     * 实际上，当任务总数不超过(n+1)×(max−1)+tot 时，我们总能将其他任务插到空闲时间中去，不会引入额外的冻结时间
     * 而当任务数超过该值时，我们可以在将其横向添加每个n+1 块的后面，同时不会引入额外的冻结时间
     */
    public int leastInterval(char[] tasks, int n) {
        // 统计每个任务的频率
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        // maxFreq记录任务出现频率的最高值，tot记录最高频率任务出现的个数(也就是最后应该+x)
        int maxFreq = 0, tot = 0;
        for (int i = 0; i < 26; i++) {
            maxFreq = Math.max(maxFreq, freq[i]);
        }
        for (int i = 0; i < 26; i++) {
            tot += (maxFreq == freq[i]) ? 1 : 0;
        }
        return Math.max(tasks.length, (n + 1) * (maxFreq - 1) + tot);
    }
}
