package top.xiaotian.util;

import java.util.Random;

/**
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class RandomUtil {

    /***
     * 根据最小最大随机数产生一个长度为n的随机数组
     * @param n     随机数组长度
     * @param min   最小随机数
     * @param max   最大随机数
     * @return
     */
    public static int[] randomInt(int n, int min, int max) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");

        if (min > max)
            throw new IllegalArgumentException("min > max");

        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // 随机数规则：nextInt(m) 随机产生[0, m)的数字
            arr[i] = random.nextInt(max) % (max - min + 1) + min;
        }
        return arr;
    }

    public static int[] randomNearlySortInt(int n, int swapTime) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");

        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < swapTime; i++) {
            int posX = random.nextInt(n);
            int posY = random.nextInt(n);
            SwapUtil.swap(arr, posX, posY);
        }
        return arr;
    }
}
