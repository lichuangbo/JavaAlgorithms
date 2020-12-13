package top.xiaotian.algorithms.recursion;

/**
 * 递归入门：核心在于减小计算规模
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/12
 */
public class Sum {
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    /**
     * 计算arr[0...n-1]这个区间内所有数字的和
     * arr[0...n-1] = arr[0] + arr[1...n-1]
     * arr[1...n-1] = arr[1] + arr[2...n-1]
     * ...
     * arr[n-1] = arr[n-1]
     * @param arr   待计算数组
     * @param l     数组左边界
     * @return
     */
    private static int sum(int[] arr, int l) {
        // 求解最基本问题
        if (l == arr.length) {
            return 0;
        }

//        if (l == arr.length - 1) {// 本质是一样的，少一次递归
//            return arr[l];
//        }
        // 把原问题转化成更小的问题
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }
}
