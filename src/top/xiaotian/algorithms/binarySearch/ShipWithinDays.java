package top.xiaotian.algorithms.binarySearch;

import java.util.Arrays;

/**
 * 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 *
 *
 * 示例 1：
 *
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 *
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 */
public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int D) {
        // 确定二分查找左右边界,船最小装载量是货物中最重的重量（n个货物要在n天内运完），最大装载量是所有货物重量之和（n个货物要在1天内运完）
        int max = Integer.MIN_VALUE, sum = 0;
        for (int weight : weights) {
            if (max < weight) {
                max = weight;
            }
            sum += weight;
        }
        int left = max, right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // need 为需要运送的天数
            // cur 为当前这一天已经运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                // 当前船装载量
                if (cur + weight > mid) {
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }
            System.out.println("left=" + left + ", right="+ right + "  船转载能力为 " + mid + " 时，需要的天数为 " + need);
            if (need <= D) {// 给定的天数 (正好 或者 有空余)，意味着船可以不用装太重，然后多跑两趟就行，更新右区间
                right = mid;// 答案可能就是mid，不能够更新为mid-1
            } else {// 船装的太轻，相应的更新左区间
                left = mid + 1;// 船已经太轻了，必须增加重量才能将天数降下来(left-14，right-15)，mid+1
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int res = new ShipWithinDays().shipWithinDays(weights, 5);
        System.out.println(res);
    }
}
