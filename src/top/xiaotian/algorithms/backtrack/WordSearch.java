package top.xiaotian.algorithms.backtrack;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/30
 */
public class WordSearch {
    private boolean[][] visited;
    private final int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (help(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean help(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i >= 0 && i < board.length && j >= 0 && j < board[i].length && !visited[i][j] && board[i][j] == word.charAt(index)) {
            visited[i][j] = true;
            for (int m = 0; m < 4; m++) {
                int newx = i + d[m][0];
                int newy = j + d[m][1];
                if (help(board, newx, newy, word, index + 1)) {
                    return true;
                }
            }
            visited[i][j] = false;
        }
        return false;
    }
}
