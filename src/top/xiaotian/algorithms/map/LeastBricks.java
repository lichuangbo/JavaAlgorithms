package top.xiaotian.algorithms.map;

import java.util.*;

/**
 * 554. 砖墙
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 *
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 *
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 */
public class LeastBricks {
    public int leastBricks(List<List<Integer>> wall) {
        // [
        //  [1,2, ,2, ,1],
        //  [3, , ,1,2, ],
        //  [1,3, , ,2, ],
        //  [2, ,4, , , ],
        //  [3, , ,1,2, ],
        //  [1,3, , ,1,1]
        // ]
        // 从第几个逗号开始穿
        // 1+1+1=3
        // 1+1+1+1+1=5
        // 1+1+1=3
        // 1+1=2
        // 1+1+1+1=4

        int n = wall.size();
        // map统计出 各间隙位置在整面墙上，出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, gapPos = 0; i < n; i++, gapPos = 0) {
            for (int curWid : wall.get(i)) {
                // 记录间隙位置
                gapPos += curWid;
                map.put(gapPos, map.getOrDefault(gapPos, 0) + 1);
            }
            map.remove(gapPos); // 不能从两边穿过，需要 remove 掉最后一个
        }
        // 遍历map，找出间隙位置出现次数最多的(间隙最多，就表明垂线穿过的砖块最少)
        int maxCount = 0;
        for (Integer item : map.values()) {
            maxCount = Math.max(item, maxCount);
        }
        return n - maxCount;
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(0, Arrays.asList(1, 2, 2, 1));
        wall.add(1, Arrays.asList(3, 1, 2));
        wall.add(2, Arrays.asList(1, 3, 2));
        wall.add(3, Arrays.asList(2, 4));
        wall.add(4, Arrays.asList(3, 1, 2));
        wall.add(5, Arrays.asList(1, 3, 1, 1));

        int res = new LeastBricks().leastBricks(wall);
        System.out.println(res);
    }
}
