package top.xiaotian.algorithms.dp.sub_sequence;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class EditDistance {
  public int minDistance(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();
    char[] chars1 = word1.toCharArray();
    char[] chars2 = word2.toCharArray();
    // dp[i][j]表示以chars1[i-1]结尾，chars2[j-1]结尾的两个字符串，要达到相等chars1需要的最少操作数
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
        if (chars1[i - 1] == chars2[j - 1]) {// 相等直接取子串，因为charts1并没有任何编辑操作
          dp[i][j] = dp[i - 1][j - 1];
        } else {// chars1: 插入一个字符   删除一个字符   替换一个字符
          // 插入字符如何理解？  rs ros，此时chars1要插入一个o，这时候chars1其实没有变，chars2因为补充的o，需要向前移动
          dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
        }
      }
    }
    return dp[len1][len2];
  }
}
