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
    private int rows;
    private int cols;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];
        // 每一个二维点都有可能作为起点，所以要遍历每一个二维点坐标
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (help(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean help(char[][] board, int i, int j, String word, int index) {
        // 探测到board边界时，返回
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return false;
        }
        // 访问过的路径 或者 当前路径的字符和字符串中待寻找的字符不相同 返回
        if (visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        // 已经寻找得到字符串最后一个字符，说明得到一条正确路径，返回true
        if (index == word.length() - 1) {
            return true;
        }

        visited[i][j] = true;
        // 在四个方向探测
        if (help(board, i, j + 1, word, index + 1) ||// 右
                help(board, i + 1, j, word, index + 1) ||// 下
                help(board, i, j - 1, word, index + 1) ||// 左
                help(board, i - 1, j, word, index + 1)) { // 上
            return true;
        }
        visited[i][j] = false;

        return false;
    }
}
