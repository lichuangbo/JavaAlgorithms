package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 * <p>
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 示例：
 * <p>
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/30
 */
public class BinaryWatch {
    private List<String> res;
    private int[] times;

    public List<String> readBinaryWatch(int num) {
        res = new ArrayList<>();
        if (num < 0) {
            return res;
        }

        //LED灯，前四个为小时，后六个为分钟
        times = new int[]{8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
        backTrack(num, 0, 0, 0);
        return res;
    }

    public void backTrack(int num, int start, int hour, int minute) {
        // 灯用完了，说明得到一组解，将它加入结果集
        if (num == 0) {
            //判断时间是否正确
            if (hour > 11 || minute > 59)
                return;
            StringBuilder tmp = new StringBuilder();
            //小时
            tmp.append(hour);
            tmp.append(":");
            //分钟
            if (minute < 10) {
                tmp.append("0");
            }
            tmp.append(minute);
            res.add(tmp.toString());
            return;
        }

        for (int i = start; i < times.length; i++) {
            if (i < 4)//小时
                hour += times[i];
            else
                minute += times[i];
            backTrack(num - 1, i + 1, hour, minute);
            if (i < 4)//小时
                hour -= times[i];
            else
                minute -= times[i];
        }
    }
}
