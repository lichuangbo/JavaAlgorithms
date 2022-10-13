package top.xiaotian.algorithms.dp.edit_distance;

/**
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 */
public class OneEditAway {

  // 动态规划
  public boolean oneEditAway(String first, String second) {
    char[] chars1 = first.toCharArray();
    char[] chars2 = second.toCharArray();
    int len1 = chars1.length;
    int len2 = chars2.length;
    // dp[i][j]表示以chars1[i-1]结尾，chars2[j-1]结尾的两个字符串，要达到相等需要的最少操作数
    int[][] dp = new int[len1 + 1][len2 + 1];
    // 初始化：chars1是空串，只能不断插入; chars2是空串，只能不断删除
    for (int i = 1; i <= len1; i++) {
      dp[i][0] = i;
    }
    for (int j = 1; j <= len2; j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (chars1[i - 1] == chars2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          // 插入
          // help(chars1, index1, chars2, index2 - 1);
          // help(chars1, index1 - 1, chars2, index2);
          // 删除
          // help(chars1, index1 - 1, chars2, index2);
          // help(chars1, index1, chars2, index2 - 1);
          // 替换
          // help(chars1, index1 - 1, chars2, index2 - 1);
          dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
        }
      }
    }
    return dp[len1][len2] < 1;
  }

  // 双指针解法：只需要看1次编辑后能不能相等就行，不用动态规划将最少编辑次数计算完再判断，用多余的计算
  public boolean oneEditAway2(String first, String second) {
    char[] chars1 = first.toCharArray();
    char[] chars2 = second.toCharArray();
    int len1 = chars1.length;
    int len2 = chars2.length;
    // 计算first 和 second 的长度差值
    int subLen = len1 - len2;
    if (Math.abs(subLen) > 1) {
      return false;
    }

    // 定义指针i, j, 分别指向first和second
    int editCount = 1;
    int i = 0;
    int j = 0;
    while (i < len1 && j < len2) {
      if (chars1[i] == chars2[j]) {
        i++;
        j++;
      } else {
        editCount--;
        if (subLen == 1) {// first长，first删除字符
          i++;
        } else if (subLen == -1) {// second长，second删除字符，相当于first增加字符
          j++;
        } else if (subLen == 0) {// 一样长,替换
          i++;
          j++;
        }
      }
      if (editCount < 0) {
        return false;
      }
    }
    return true;
  }
}
