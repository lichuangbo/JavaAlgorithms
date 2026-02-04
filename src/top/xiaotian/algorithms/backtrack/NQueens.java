package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/1
 */
public class NQueens {
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        // 同行的皇后会攻击，那么就一行一行放置皇后
        help(board, 0);
        return res;
    }

    private void help(char[][] board, int rowIndex) {
        // 棋盘最后一行填充完，说明得到了一组填充方法，收集到结果中
        if (rowIndex == board.length) {
            res.add(generateBoard(board));
            return;
        }

        // 在当前行的每一列尝试放置
        for (int colIndex = 0; colIndex < board.length; colIndex++) {
            // 只有当前棋盘是有效的填法，才继续后续的放置
            if (isValid(board, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                help(board, rowIndex + 1);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    /**
     * 四皇后填法1
     * . Q . .
     * . . . Q
     * Q . . .
     * . . Q .
     * 四皇后填法2
     * . . Q .
     * Q . . .
     * . . . Q
     * . Q . .
     */
    private boolean isValid(char[][] board, int rowIndex, int colIndex) {
        // 竖向判断：从当前位置向垂直上边检查（不包含当前坐标，因为当前坐标是待填充点）
        for (int i = 0; i < rowIndex; i++) {
            if (board[i][colIndex] == 'Q') {
                return false;
            }
        }

        // 对角判断：从当前位置往左上角检查（同理）
        int i = rowIndex - 1;
        int j = colIndex - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        // 反对角判断：从当前位置向右上角检查（同理）
        i = rowIndex - 1;
        j = colIndex + 1;
        while (i >= 0 && j < board.length) {
            if (board[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }

    private List<String> generateBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        int len = board.length;
        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append(board[i][j]);
            }
            res.add(sb.toString());
        }
        return res;
    }
}
