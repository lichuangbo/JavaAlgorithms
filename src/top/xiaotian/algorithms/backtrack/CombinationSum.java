package top.xiaotian.algorithms.backtrack;

import java.util.Arrays;

public class CombinationSum {
    /**
     * 377. ����ܺ� ��
     * ����һ���� ��ͬ ������ɵ����� nums ����һ��Ŀ������ target ������� nums ���ҳ��������ܺ�Ϊ target ��Ԫ����ϵĸ�����
     *
     * ��Ŀ���ݱ�֤�𰸷��� 32 λ������Χ��
     * ʾ�� 1��
     *
     * ���룺nums = [1,2,3], target = 4
     * �����7
     * ���ͣ�
     * ���п��ܵ����Ϊ��
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * ��ע�⣬˳��ͬ�����б�������ͬ����ϡ�
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] mono = new int[target + 1];
        Arrays.fill(mono, -1);
        return help(nums, target, mono);

        // ��̬�滮
        // ״̬���壺dp[i]��ʾѡȡ��Ԫ��֮��Ϊi�ķ�����
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        for (int i = 1; i < target + 1; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (nums[j] <= i) {
//                    dp[i] += dp[i - nums[j]];
//                }
//            }
//        }
//        return dp[target];
    }

    private int help(int[] nums, int target, int[] mono) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }

        if (mono[target] != -1) {
            return mono[target];
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += help(nums, target - nums[i], mono);
        }
        mono[target] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        int res = new CombinationSum().combinationSum4(nums, 35);
        System.out.println(res);
    }
}
