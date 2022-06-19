package top.xiaotian.algorithms.dfs;

/**
 * 剑指 Offer 12. 矩阵中的路径
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
    if (board == null || board.length == 0) {
      return false;
    }
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (help(board, word, i, j, 0, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean help(char[][] board, String word, int i, int j, int k, boolean[][] visited) {
    if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j]) {
      return false;
    }
    if (board[i][j] != word.charAt(k)) {
      return false;
    }
    if (k == word.length() - 1) {
      return true;
    }

    visited[i][j] = true;
    boolean flag = help(board, word, i + 1, j, k + 1, visited) ||
            help(board, word, i - 1, j, k + 1, visited) ||
            help(board, word, i, j + 1, k + 1, visited) ||
            help(board, word, i, j - 1, k + 1, visited);
    visited[i][j] = false;
    return flag;
  }
}
