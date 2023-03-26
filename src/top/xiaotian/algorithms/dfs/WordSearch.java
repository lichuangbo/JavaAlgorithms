package top.xiaotian.algorithms.dfs;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 */
public class WordSearch {
  public boolean exist(char[][] board, String word) {
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    char[] chars = word.toCharArray();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (help(board, i, j, chars, 0, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean help(char[][] board, int i, int j, char[] chars, int index, boolean[][] visited) {
    if (index == chars.length) {
      return true;
    }
    if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || visited[i][j] || board[i][j] != chars[index]) {
      return false;
    }

    visited[i][j] = true;
    boolean flag = help(board, i, j + 1, chars, index + 1, visited) || help(board, i + 1, j, chars, index + 1, visited) || help(board, i, j - 1, chars, index + 1, visited) || help(board, i - 1, j, chars, index + 1, visited);
    visited[i][j] = false;
    return flag;
  }
}
