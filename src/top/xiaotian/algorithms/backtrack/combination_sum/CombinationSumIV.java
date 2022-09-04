package top.xiaotian.algorithms.backtrack.combination_sum;

import java.util.Arrays;

public class CombinationSumIV {
  /**
   * 377. ����ܺ� ��
   * ����һ���� ��ͬ ������ɵ����� nums ����һ��Ŀ������ target ������� nums ���ҳ��������ܺ�Ϊ target ��Ԫ����ϵĸ�����
   * <p>
   * ��Ŀ���ݱ�֤�𰸷��� 32 λ������Χ��
   * ʾ�� 1��
   * <p>
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
   */
  public int combinationSum4_1(int[] nums, int target) {
    if (target < 0) {
      return 0;
    }
    if (target == 0) {
      return 1;
    }

    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      res += combinationSum4_1(nums, target - nums[i]);
    }
    return res;
  }

  public int combinationSum4_2(int[] nums, int target) {
    int[] memo = new int[target + 1];
    Arrays.fill(memo, -1);
    return help(nums, target, memo);
  }

  private int help(int[] nums, int target, int[] memo) {
    if (target < 0) {
      return 0;
    }
    if (target == 0) {
      return 1;
    }

    if (memo[target] != -1) {
      return memo[target];
    }

    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      res += help(nums, target - nums[i], memo);
    }
    memo[target] = res;
    return res;
  }

  public int combinationSum4_3(int[] nums, int target) {
    // ��̬�滮
    // ״̬���壺dp[i]��ʾѡȡ��Ԫ��֮��Ϊi�ķ�����
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 1; i <= target; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[j] <= i) {
          dp[i] += dp[i - nums[j]];
        }
      }
    }
    return dp[target];
  }
}
