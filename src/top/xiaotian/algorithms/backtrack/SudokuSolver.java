package top.xiaotian.algorithms.backtrack;

/**
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class SudokuSolver {
  // 三个布尔数组表明 行, 列, 块的哪些数字被使用过
  // rowUsed[i][num]表示第i行有没有使用过num这个数字
  private boolean[][] rowUsed;
  // colUsed[j][num]表示第j列有没有使用过num这个数字
  private boolean[][] colUsed;
  // boxUsed[i][j][num]表示[0,1,2]行[0,1,2]列组合的这九个格子有没有使用过num这个数字
  private boolean[][][] boxUsed;

  public void solveSudoku(char[][] board) {
    // 多使用一个空间，来表示1-9
    rowUsed = new boolean[9][10];
    colUsed = new boolean[9][10];
    boxUsed = new boolean[3][3][10];
    // 初始化
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        int num = board[i][j] - '0';
        if (1 <= num && num <= 9) {
          rowUsed[i][num] = true;
          colUsed[j][num] = true;
          boxUsed[i / 3][j / 3][num] = true;
        }
      }
    }
    // 递归尝试填充数组
    help(board, 0, 0);

//    help(board);
  }

  // 递归思路
  private boolean help(char[][] board, int row, int col) {
    if (col == board[0].length) {
      // 一行的每一列都填充完后，继续尝试填充下一行，同时列重置
      col = 0;
      row++;
      // 所有行列都已经填充完，返回true
      if (row == board.length) {
        return true;
      }
    }
    // 是空则尝试填充, 否则跳过继续尝试填充该行的下一个列
    if (board[row][col] == '.') {
      // 尝试填充1~9
      for (int num = 1; num <= 9; num++) {
        boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row / 3][col / 3][num]);
        if (canUsed) {
          rowUsed[row][num] = true;
          colUsed[col][num] = true;
          boxUsed[row / 3][col / 3][num] = true;
          board[row][col] = (char) ('0' + num);

          if (help(board, row, col + 1)) {
            return true;
          }

          board[row][col] = '.';
          rowUsed[row][num] = false;
          colUsed[col][num] = false;
          boxUsed[row / 3][col / 3][num] = false;
        }
      }
    } else {
      return help(board, row, col + 1);
    }
    return false;
  }

  // 循环思路
  private boolean help(char[][] board) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          for (int num = 1; num <= 9; num++) {
            boolean isValid = !(rowUsed[i][num] || colUsed[j][num] || boxUsed[i / 3][j / 3][num]);
            if (isValid) {
              rowUsed[i][num] = true;
              colUsed[j][num] = true;
              boxUsed[i / 3][j / 3][num] = true;
              board[i][j] = (char) (num + '0');

              if (help(board)) {
                return true;
              }

              board[i][j] = '.';
              rowUsed[i][num] = false;
              colUsed[j][num] = false;
              boxUsed[i / 3][j / 3][num] = false;
            }
          }
          return false;
        }
      }
    }
    return true;
  }
}
