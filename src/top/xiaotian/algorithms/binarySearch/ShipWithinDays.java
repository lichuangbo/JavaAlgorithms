package top.xiaotian.algorithms.binarySearch;

import java.util.Arrays;

/**
 * 1011. �� D �����ʹ����������
 * ���ʹ��ϵİ��������� D ���ڴ�һ���ۿ����͵���һ���ۿڡ�
 *
 * ���ʹ��ϵĵ� i ������������Ϊ weights[i]��ÿһ�죬���Ƕ��ᰴ����������˳�������ʹ���װ�ذ���������װ�ص��������ᳬ�������������������
 *
 * �������� D ���ڽ����ʹ��ϵ����а����ʹ�Ĵ����������������
 *
 *
 *
 * ʾ�� 1��
 *
 * ���룺weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * �����15
 * ���ͣ�
 * ����������� 15 ���ܹ��� 5 �����ʹ����а�����������ʾ��
 * �� 1 �죺1, 2, 3, 4, 5
 * �� 2 �죺6, 7
 * �� 3 �죺8
 * �� 4 �죺9
 * �� 5 �죺10
 *
 * ��ע�⣬������밴�ո�����˳��װ�ˣ����ʹ����������Ϊ 14 �Ĵ���������װ�ֳ� (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) �ǲ�����ġ�
 */
public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int D) {
        // ȷ�����ֲ������ұ߽�,����Сװ�����ǻ��������ص�������n������Ҫ��n�������꣩�����װ���������л�������֮�ͣ�n������Ҫ��1�������꣩
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
            // need Ϊ��Ҫ���͵�����
            // cur Ϊ��ǰ��һ���Ѿ����͵İ�������֮��
            int need = 1, cur = 0;
            for (int weight : weights) {
                // ��ǰ��װ����
                if (cur + weight > mid) {
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }
            System.out.println("left=" + left + ", right="+ right + "  ��ת������Ϊ " + mid + " ʱ����Ҫ������Ϊ " + need);
            if (need <= D) {// ���������� (���� ���� �п���)����ζ�Ŵ����Բ���װ̫�أ�Ȼ��������˾��У�����������
                right = mid;// �𰸿��ܾ���mid�����ܹ�����Ϊmid-1
            } else {// ��װ��̫�ᣬ��Ӧ�ĸ���������
                left = mid + 1;// ���Ѿ�̫���ˣ����������������ܽ�����������(left-14��right-15)��mid+1
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
