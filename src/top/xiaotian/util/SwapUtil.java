package top.xiaotian.util;

/**
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class SwapUtil {

    /***
     *
     * @param arr   数组
     * @param l     数组下标1
     * @param r     数组下标2
     */
    public static void swap(int[] arr, int l, int r) {
        if (arr == null || arr.length == 0)
            return;

        if (l < 0 || l >= arr.length || r < 0 || r >= arr.length)
            throw new IllegalArgumentException("index out of range");

        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}
