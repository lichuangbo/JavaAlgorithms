package top.xiaotian.algorithms.sort;

/**
 * 插入排序
 * 在未排序区间选择一个基准元素，将该元素和它左侧已排序区间的元素一一对比，并将该元素插入到正确位置上
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class InsertionSort {

    /**
     * 时间 O(n2)  最好O(n)
     * 空间 O(1)
     * 稳定排序算法：两元素相等时，因为采用的是从后向前遍历，未排序元素插入在排序元素的后面
     */
    // 效率高, 在近乎有序的序列中性能更好
    public void insertSort(int[] arr) {
        // 首元素假设为有序的
        for (int i = 1; i < arr.length; i++) {// 外层for循环往后走
            int base = arr[i];// 待插入元素
            int j = i - 1;
            while (j >= 0 && arr[j] > base) {// 内层for循环，从后向前寻找插入位置（待插入元素比有序集合元素小）
                // 将arr[j]向右移动一位
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = base;
        }
    }
}
