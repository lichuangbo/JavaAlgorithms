package top.xiaotian.algorithms.map;

import java.util.*;

/**
 * 554. שǽ
 * �����ǰ��һ�¾��εġ��� n ��ש����ɵ�שǽ����Щש��߶���ͬ��Ҳ����һ����λ�ߣ����ǿ�Ȳ�ͬ��ÿһ��ש��Ŀ��֮��Ӧ����ȡ�
 *
 * ������Ҫ��һ�� �Զ����� �ġ����� ���� ש��Ĵ��ߡ�����㻭����ֻ�Ǵ�ש��ı�Ե�������Ͳ��㴩�����ש���㲻������ǽ��������ֱ��Ե֮һ���ߣ�������Ȼ��û�д���һ��ש�ġ�
 *
 * ����һ����ά���� wall ��������������ǽ�������Ϣ�����У�wall[i] ��һ�������������ÿ��ש�Ŀ�ȵ����顣����Ҫ�ҳ�����������ʹ������ ������ש���������� �����ҷ��� ������ש������ ��
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
        // �ӵڼ������ſ�ʼ��
        // 1+1+1=3
        // 1+1+1+1+1=5
        // 1+1+1=3
        // 1+1=2
        // 1+1+1+1=4

        int n = wall.size();
        // mapͳ�Ƴ� ����϶λ��������ǽ�ϣ����ֵĴ���
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, gapPos = 0; i < n; i++, gapPos = 0) {
            for (int curWid : wall.get(i)) {
                // ��¼��϶λ��
                gapPos += curWid;
                map.put(gapPos, map.getOrDefault(gapPos, 0) + 1);
            }
            map.remove(gapPos); // ���ܴ����ߴ�������Ҫ remove �����һ��
        }
        // ����map���ҳ���϶λ�ó��ִ�������(��϶��࣬�ͱ������ߴ�����ש������)
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
