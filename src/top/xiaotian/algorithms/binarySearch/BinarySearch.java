package top.xiaotian.algorithms.binarySearch;

import java.util.Arrays;
import java.util.Random;

/**
 * 二分查找法实现
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:26  80
 */
public class BinarySearch {
    public int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;// 循环不变量：在[l...r]区间中寻找值等于target的下标
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return mid;
            }

            // 在循环中维护循环不变量
            if (arr[mid] > target) {// 此时已经明确知道mid代表的值大于目标值，就需要在[l...mid-1]区间中寻找
                r = mid - 1;
            } else {// 此时已经明确知道mid代表的值小于目标值，就需要在[mid+1...r]区间中寻找
                l = mid + 1;
            }
        }
        return -1;
    }

    // 二分法另一种写法：循环不变量含义发生变化
    public int binarySearch2(int[] arr, int target) {
        int l = 0, r = arr.length;// 循环不变量：在[l...r)区间中寻找值等于target的下标
        while (l < r) {// l=r时，已经不能代表一个区间了，使用l<r可以代表一个区间 [42, 42)
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return mid;
            }

            // 在循环中维护循环不变量
            if (arr[mid] > target) {// 右区间是开区间，就需要在[l...mid)区间中寻找
                r = mid;
            } else {// 此时已经明确知道mid代表的值小于目标值，就需要在[mid+1...r)区间中寻找
                l = mid + 1;
            }
        }
        return -1;
    }

    // 二分法变形1：在存在重复元素的有序序列中，查找第一个值等于给定值的元素下标
    public int binarySearch3(int[] arr, int target) {
        int l = 0, r = arr.length - 1;// [l...r]
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] > target) {// [l...mid-1]
                r = mid - 1;
            } else if (arr[mid] < target) {// [mid+1...r]
                l = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != target) {// 前一个值不等于目标值，说明找见了
                    return mid;
                } else {// 说明不是第一个，向前找
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    // 二分法变形2：在存在重复元素的有序序列中，查找最后一个值等于给定值的元素下标
    public int binarySearch4(int[] arr, int target) {
        int l = 0, r = arr.length - 1;// [l...r]
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] > target) {// [l...mid-1]
                r = mid - 1;
            } else if (arr[mid] < target) {// [mid+1...r]
                l = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != target) {// 后一个值不等于目标值，说明找见了
                    return mid;
                } else {// 说明不是最后一个，向后找
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    // 二分法变形3：在存在重复元素的有序序列中，查找第一个大于等于给定值的元素下标
    public int binarySearch5(int[] arr, int target) {
        int l = 0, r = arr.length - 1;// [l...r]
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < target) {// [mid+1...r]
                l = mid + 1;
            } else {// arr[mid] >= target
                if (mid == 0 || arr[mid - 1] < target) {// 前一个元素小于目标值，说明处于临界点上，找见了第一个大于的
                    return mid;
                } else {// 第一个大于的还在左边
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    // 二分法变形4：在存在重复元素的有序序列中，查找第一个小于等于给定值的元素下标
    public int binarySearch6(int[] arr, int target) {
        int l = 0, r = arr.length - 1;// [l...r]
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] > target) {// [l...mid-1]
                r = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] > target) {// 后一个元素大于目标值，说明找见了第一个小于的
                    return mid;
                } else {// 不是第一个小于的，向后找
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}
