package top.xiaotian.algorithms.backtrack;

import java.util.Arrays;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/31
 */
public class SurroundedRegions {
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        rows = board.length;
        cols = board[0].length;

        // 从四处边界开始寻找O作为入口，开始渲染
        for (int i = 0; i < rows; i++) {
            help(board, i, 0);// 第一列
            help(board, i, cols - 1);// 最后一列
        }
        for (int i = 1; i < cols - 1; i++) {
            help(board, 0, i);// 第一行
            help(board, rows - 1, i);// 最后一行
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'A') {// 回退为O, 相当于不操作‘边界’
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 语义：将board边界上的O以及与其相连的O全部标记为A
    public void help(char[][] board, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return;
        }
        // 只针对O,不是O的返回
        if (board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'A';
        help(board, i, j + 1);// 右
        help(board, i + 1, j);// 下
        help(board, i, j - 1);// 左
        help(board, i - 1, j);// 上
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
