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
    private boolean[] col;// 表示col[i]列已经被占用
    private boolean[] dia1;// 表示从左下到右上的对角线dia1[i]被占用
    private boolean[] dia2;// 表示从左上到右下的对角线dia2[i]被占用

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        putQueen(n, 0, new ArrayList<>());
        return res;
    }

    /**
     * 在一个n皇后问题中，摆放第index行的皇后位置
     * @param n 皇后个数
     * @param index 处理到第几行
     * @param row 每一行皇后放置到第几列
     */
    private void putQueen(int n, int index, List<Integer> row) {
        // 处理到了最后一行，说明得到了一组解
        if (index == n) {
            res.add(generateBoard(n, row));
            return;
        }

        for (int j = 0; j < n; j++) {
            // 尝试将第index行的皇后摆放到第j列
            if (!col[j] && !dia1[index + j] && !dia2[index - j + n - 1]) {// 列和两条对角线都不冲突，就放置到该位置上
                row.add(j);
                col[j] = true;
                dia1[index + j] = true;
                dia2[index - j + n - 1] = true;
                putQueen(n, index + 1, row);
                col[j] = false;
                dia1[index + j] = false;
                dia2[index - j + n - 1] = false;
                row.remove(row.size() - 1);
            }
        }
    }

    private List<String> generateBoard(int n, List<Integer> row) {
        char[][] res = new char[n][n];
        // 初始化棋盘
        for (int i = 0; i < n; i++) {
            Arrays.fill(res[i], '.');
        }
        // 将皇后位置计算结果放置到棋盘中
        for (int i = 0; i < n; i++) {
            res[i][row.get(i)] = 'Q';
        }
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(res[i][j]);
            }
            resList.add(sb.toString());
        }
        return resList;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> res = nQueens.solveNQueens(8);
        for (List<String> list : res){
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println();
        }
    }
}
