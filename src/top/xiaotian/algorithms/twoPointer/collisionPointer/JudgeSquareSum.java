package top.xiaotian.algorithms.twoPointer.collisionPointer;

/**
 * 633. ƽ����֮��
 * ����һ���Ǹ����� c ����Ҫ�ж��Ƿ������������ a �� b��ʹ�� a2 + b2 = c ��
 *
 *
 *
 * ʾ�� 1��
 *
 * ���룺c = 5
 * �����true
 * ���ͣ�1 * 1 + 2 * 2 = 5
 */
public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        // a/b����ֻ������[0...��c]
        int a = 0, b = (int)Math.sqrt(c);
        // a/bָ�����ʱ��Ϊ��Ч�⣨2=1*1+1*1��
        while (a <= b) {
            int cur = a * a + b * b;
            if (cur == c) {
                return true;
            } else if (cur > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        int max = (int)Math.sqrt(c);
        for (int a = 0; a <= max; a++) {
            int b = (int)Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }
}
