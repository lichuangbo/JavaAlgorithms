package top.xiaotian.algorithms.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {
 * 	"etc": {
 * 		"kubernetes": {
 * 			"ssl": {
 * 				"certs": {}
 *      }
 *    },
 * 		"hosts": {}
 * 	},
 * 	"root": {}
 * }
 */
public class PathListToMap {

//  public static void main(String[] args) {
//    List<String> pathList = Arrays.asList(
//            "/etc/hosts",
//            "/etc/kubernetes/ssl/certs",
//            "/root",
//            "/etc/ssl"
//    );
//    Map<String, Map> resMap = new HashMap<>();
//    for (String path : pathList) {
//      String[] pathArr = path.substring(1).split("/");
//      for (int i = 0; i < pathArr.length; i++) {
//        if (i == 0) {
//          if (!resMap.containsKey(pathArr[0])) {
//            resMap.put(pathArr[0], new HashMap());
//          }
//        } else {
//          Map map = help(resMap, pathArr[i - 1]);
//          map.put(pathArr[i], new HashMap<>());
//        }
//      }
//    }
//    System.out.println(resMap);
//  }
//
//  // 在resMap中寻找键为targetKey的map
//  private static Map<String, Map> help(Map<String, Map> resMap, String targetKey) {
//    for (Map.Entry<String, Map> stringMapEntry : resMap.entrySet()) {
//      if (stringMapEntry.getKey().equals(targetKey)) {
//        return stringMapEntry.getValue();
//      } else {
//        return help(stringMapEntry.getValue(), targetKey);
//      }
//    }
//    return null;
//  }

  public static void main(String[] args) {
    List<String> pathList = Arrays.asList(
            "/etc/hosts",
            "/etc/kubernetes/ssl/certs",
            "/root"
    );
    Map<String, Map> map = new HashMap<>();
    for (String path : pathList) {
      path = path.substring(1);
      dfs(path, map);
    }
    System.out.println(map);
  }

  private static void dfs(String path, Map<String, Map> map) {
    int i = path.indexOf("/");
    if (i == -1) {
      map.putIfAbsent(path, new HashMap());
      return;
    }
    String cur = path.substring(0, i);
    map.putIfAbsent(cur, new HashMap());
    dfs(path.substring(i + 1), map.get(cur));
  }
}
