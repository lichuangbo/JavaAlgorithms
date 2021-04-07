package top.xiaotian.dataStructures.stack.practice;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/4/1 12:24
 * @Description: 描述:
 */
public class ClumsyFactorial {
    public int clumsy(int N) {
        if (N == 1) return 1;
        if (N == 2) return 2;
        if (N == 3) return 6;
        if (N == 4) return 7;
        int res = N * (N - 1) / (N - 2) + (N - 3);
        N -= 4;
        for (int i = N; i > 0; i -= 4) {
            /**
             * 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
             * ==> (10 * 9 / 8 + 7) - (6 * 5 / 4 - 3) - (2 * 1)
             */
            int tmp = i;
            if (i - 1 > 0) tmp = tmp * (i - 1);
            if (i - 2 > 0) tmp = tmp / (i - 2);
            if (i - 3 > 0) tmp = tmp - (i - 3);
            res = res - tmp;
        }
        return res;
    }

    public int clumsy2(int N) {
        int index = 1;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(N--);
        while (N > 0) {
            if (index % 4 == 1) {
                deque.addLast(deque.removeLast() * N);
            } else if (index % 4 == 2) {
                deque.addLast(deque.removeLast() / N);
            } else if (index % 4 == 3) {
                deque.addLast(N);
            } else {
                deque.addLast(-N);
            }
            N--;
            index++;
        }
        int res = 0;
        while (!deque.isEmpty()) {
            res += deque.removeLast();
        }
        return res;
    }

    public static void main(String[] args) {
       int clumsy = new ClumsyFactorial().clumsy(10);
        System.out.println(clumsy);
    }
}
