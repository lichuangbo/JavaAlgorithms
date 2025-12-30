### 基本数据结构

#### 数组 array
+ 线性数据结构，可以将相同类型的元素存储在连续的内存空间
+ 数组应用
  + 利用数组下标随机访问特性，随机抽取样本
  + 排序和搜索
  + 查找表，将大小写字母或者ASCII码值映射到数组中，可以快速查找到元素
  + 可实现栈、队列、哈希表、堆、图等数据结构
+ [动态数组实现](src/top/xiaotian/dataStructures/array/Array.java)

#### 链表 linked list
+ 线性数据结构，使用链表存储可以使得各个数据节点分散在内存各处，不要求内存地址物理上连续
+ 链表类型
  + 单向链表
  + 双向链表
  + 环形链表
+ 链表应用
  + 单向链表常见于栈、队列、哈希表冲突解决方案、图邻接表解决方案
  + 双向链表常见于高级数据结构如红黑树、B树、Java中LinkedList集合；LRU最近最少使用算法
  + 环形链表常见于周期性操作，如时间片轮转调度算法
+ [链表实现](src/top/xiaotian/dataStructures/linkedlist/LinkedList.java)
+ 练习题目：
  1. 翻转链表（迭代和递归方式）
  2. 删除倒数第N个节点
  3. 合并两个有序链表
  4. 环形链表判断
  5. LRU缓存淘汰算法实现
  6. 回文链表判断
  7. 删除链表中等于给定值 val 的所有节点(递归和迭代写法的对照)

#### 栈 stack
+ 受限制的线性数据结构，有先进后出的特性
+ 手写栈
  + [顺序栈实现](src/top/xiaotian/dataStructures/stack/ArrayStack.java)
  + [链式栈实现](src/top/xiaotian/dataStructures/stack/LinkedStack.java)
+ 练习题目
  1. 四则运算简单实现
  2. 括号匹配

#### 队列 queue
+ 受限制的线性数据结构，有先进先出的特性
+ 手写队列
  + [顺序队列实现](src/top/xiaotian/dataStructures/queue/ArrayQueue.java)
  + [链式队列实现](src/top/xiaotian/dataStructures/queue/LinkedQueue.java)
  + [循环队列实现](src/top/xiaotian/dataStructures/queue/CycleQueue.java)
  + [优先队列实现](src/top/xiaotian/dataStructures/queue/PriorityQueue.java)

#### 哈希表 hash table
+ 通过建立键 key 与值 value 之间的映射，实现高效的元素查询
+ 哈希表的实现
  + [基于二分搜索树](src/top/xiaotian/dataStructures/map/BSTMap.java)
  + [基于链表](src/top/xiaotian/dataStructures/map/LinkedListMap.java)
  + [基于链式地址法解决哈希冲突](src/top/xiaotian/dataStructures/map/HashMapChaining.java)
  + [基于开放地址法解决哈希冲突](src/top/xiaotian/dataStructures/map/HashMapOpenAddressing.java)
+ 练习题目
  1. 两个数组的交集II

#### 树 tree
+ 手写二叉搜索树
  1. 维护一棵二叉搜索树，add, remove, contains, minimum, maximum方法
  2. 深度优先遍历和广度优先遍历实现（递归和迭代两种写法）
  3. 打印二叉树递归建树过程

#### 集合
+ 集合的实现
  + 基于二叉搜索树
  + 基于链表
  + 两者性能上的比较，时间复杂度的分析
+ 练习题目
  1. 唯一摩尔斯密码词
  2. 两个数组的交集

#### 堆
+ 堆的实现
  1. 数组堆化headify, 上浮siftUp, 下沉siftDown, 替换replace等关键操作
  2. 基于堆实现优先队列
+ 练习题目
  1. 前 K 个高频元素

#### 线段树
+ 线段树实现
  1. 构建线段树、区间检索、更新
+ 练习题目
  1. 区域和检索 - 数组不可变
  2. 区域和检索 - 数组可修改（在数据动态变化的场景下更能体现优势）

#### 字典树
+ 字典树实现
  1. Node节点的设计（map, isWord）
  2. add添加, contains搜索, isPrefix前缀搜索的递归与非递归写法
+ 练习题目
  1. 添加与搜索单词 - 数据结构设计
  2. 键值映射

#### 并查集
+ 并查集实现
  1. isConnected是否关联，unionElements元素合并
  2. 基本实现quick find,查询O(1),合并O(n)
  3. quick union,查询O(h),合并O(h) h为合并树的高度
  4. 基于size优化，元素个数少的集合合并到元素个数多的集合上
  5. 基于rank优化，元素对应树深度低的的集合合并到深度高的集合上
  6. 路径压缩，在find查找根元素同时将p指向的父节点断开，转而指向p的爷爷节点
  7. 路径压缩II，在数据压缩基础上，将节点一次性指向根节点，树变为两层

### 算法题分类

#### 数组
* [238. 除自身以外数组的乘积](src/top/xiaotian/algorithms/array/ConstructArr.java)

* [59. 螺旋矩阵II/54. 螺旋矩阵](src/top/xiaotian/algorithms/array/SpiralMatrix.java)

* [118. 杨辉三角](src/top/xiaotian/algorithms/array/PascalsTriangle.java)

* [3446. 按对角线进行矩阵排序](src/top/xiaotian/algorithms/array/SortMatrixByDiagonals.java)


#### 链表
* [206. 反转链表](src/top/xiaotian/algorithms/linkedList/ReverseList.java)

* [203. 移除链表元素](src/top/xiaotian/algorithms/linkedList/RemoveElements.java)

* [24. 两两交换链表中的节点](src/top/xiaotian/algorithms/linkedList/SwapPairs.java)

* [25. K 个一组翻转链表](src/top/xiaotian/algorithms/linkedList/ReverseKGroup.java)

* [19. 删除链表的倒数第 N 个结点](src/top/xiaotian/algorithms/linkedList/RemoveNthFromEnd.java)


#### 栈
* [394. 字符串解码](src/top/xiaotian/algorithms/stack/DecodeString.java)

* [20. 有效的括号](src/top/xiaotian/algorithms/stack/ValidParentheses.java)

* [1047. 删除字符串中的所有相邻重复项](src/top/xiaotian/algorithms/stack/RemoveDuplicates.java)

* [150. 逆波兰表达式求值](src/top/xiaotian/algorithms/stack/EvalRPN.java)

##### 单调栈
* [739. 每日温度](src/top/xiaotian/algorithms/stack/monotone_stack/DailyTemperatures.java)


#### 队列

##### 优先队列
* [面试题 17.14. 最小K个数](src/top/xiaotian/algorithms/queue/priority_queue/SmallestK.java)

* [347. 前 K 个高频元素](src/top/xiaotian/algorithms/queue/priority_queue/TopKFrequent.java)

* [295. 数据流的中位数](src/top/xiaotian/algorithms/queue/priority_queue/MedianFinder.java)

##### 单调队列
* [239. 滑动窗口最大值](src/top/xiaotian/algorithms/queue/monotone_queue/MaxSlidingWindow.java)


#### 树
* [199. 二叉树的右视图](src/top/xiaotian/algorithms/tree/RightSideView.java)

* [637. 二叉树的层平均值](src/top/xiaotian/algorithms/tree/AverageOfLevels.java)

* [429. N 叉树的层序遍历](src/top/xiaotian/algorithms/tree/NAryLevelOrder.java)

* [515. 在每个树行中找最大值](src/top/xiaotian/algorithms/tree/LargestValues.java)

* [513. 找树左下角的值](src/top/xiaotian/algorithms/tree/FindBottomLeftValue.java)

* [104. 二叉树的最大深度](src/top/xiaotian/algorithms/tree/MaxDepth.java)

* [111. 二叉树的最小深度](src/top/xiaotian/algorithms/tree/MinDepth.java)

* [226. 翻转二叉树](src/top/xiaotian/algorithms/tree/InvertTree.java)

* [617. 合并二叉树](src/top/xiaotian/algorithms/tree/MergeTrees.java)

* [297. 二叉树的序列化与反序列化](src/top/xiaotian/algorithms/tree/SerialDeserialBinaryTree.java)

* [543. 二叉树的直径](src/top/xiaotian/algorithms/tree/DiameterOfBinaryTree.java)

* [617. 合并二叉树](src/top/xiaotian/algorithms/tree/MergeTrees.java)

* [116. 填充每个节点的下一个右侧节点指针/117. 填充每个节点的下一个右侧节点指针 II](src/top/xiaotian/algorithms/tree/Connect.java)

* [101. 对称二叉树](src/top/xiaotian/algorithms/tree/SymmetricTree.java)

* [100. 相同的树](src/top/xiaotian/algorithms/tree/SameTree.java)

* [572. 另一棵树的子树](src/top/xiaotian/algorithms/tree/IsSubtree.java)

* [222. 完全二叉树的节点个数](src/top/xiaotian/algorithms/tree/CountCompleteTreeNodes.java)

* [110. 平衡二叉树](src/top/xiaotian/algorithms/tree/BalancedBinaryTree.java)

* [257. 二叉树的所有路径](src/top/xiaotian/algorithms/tree/BinaryTreePaths.java)

* [404. 左叶子之和](src/top/xiaotian/algorithms/tree/SumOfLeftLeaves.java)

* [112. 路径总和/113. 路径总和 II](src/top/xiaotian/algorithms/tree/PathSum.java)

* [106. 从中序与后序遍历序列构造二叉树/105. 从前序与中序遍历序列构造二叉树](src/top/xiaotian/algorithms/tree/ConstructBinaryTree.java)

* [654. 最大二叉树](src/top/xiaotian/algorithms/tree/MaximumBinaryTree.java)

* [98. 验证二叉搜索树](src/top/xiaotian/algorithms/tree/binary_search_tree/ValidBST.java)

* [501. 二叉搜索树中的众数](src/top/xiaotian/algorithms/tree/binary_search_tree/FindMode.java)

* [235. 二叉搜索树的最近公共祖先/236. 二叉树的最近公共祖先](src/top/xiaotian/algorithms/tree/LowestCommonAncestor.java)

* [701. 二叉搜索树中的插入操作](src/top/xiaotian/algorithms/tree/binary_search_tree/InsertIntoBST.java)

* [450. 删除二叉搜索树中的节点](src/top/xiaotian/algorithms/tree/binary_search_tree/DeleteNode.java)

* [669. 修剪二叉搜索树](src/top/xiaotian/algorithms/tree/binary_search_tree/TrimBST.java)

* [108. 将有序数组转换为二叉搜索树](src/top/xiaotian/algorithms/tree/binary_search_tree/SortedArrayToBST.java)

* [538. 把二叉搜索树转换为累加树](src/top/xiaotian/algorithms/tree/binary_search_tree/ConvertBST.java)

#### 动态规划
* [509. 斐波那契数](src/top/xiaotian/algorithms/dp/FibonacciNumber.java)

* [70. 爬楼梯](src/top/xiaotian/algorithms/dp/ClimbStairs.java)

* [746. 使用最小花费爬楼梯](src/top/xiaotian/algorithms/dp/MinCostClimbingStairs.java)

* [221. 最大正方形](src/top/xiaotian/algorithms/dp/MaximalSquare.java)

##### 路径问题
* [62. 不同路径/63. 不同路径 II](src/top/xiaotian/algorithms/dp/path/UniquePaths.java)

##### 分割问题
* [343. 整数拆分](src/top/xiaotian/algorithms/dp/split/IntegerBreak.java)

* [279. 完全平方数](src/top/xiaotian/algorithms/dp/split/NumSquares.java)

* [96. 不同的二叉搜索树](src/top/xiaotian/algorithms/dp/split/NumTrees.java)

* [139. 单词拆分](src/top/xiaotian/algorithms/dp/split/WordBreak.java)

##### 子序列问题
* [300. 最长递增子序列](src/top/xiaotian/algorithms/dp/sub_sequence/LongestIncreaseSubsequence.java)

* [674. 最长连续递增序列](src/top/xiaotian/algorithms/dp/sub_sequence/LongestContinueIncreaseSubsequence.java)

* [718. 最长重复子数组](src/top/xiaotian/algorithms/dp/sub_sequence/MaxLenOfRepeatedSubArray.java)

* [1143. 最长公共子序列](src/top/xiaotian/algorithms/dp/sub_sequence/LongestCommonSubsequence.java)

* [1035. 不相交的线](src/top/xiaotian/algorithms/dp/sub_sequence/UncrossedLine.java)

##### 编辑距离问题
* [392. 判断子序列](src/top/xiaotian/algorithms/dp/edit_distance/IsSubsequence.java)

* [115. 不同的子序列](src/top/xiaotian/algorithms/dp/edit_distance/DistinctSubsequences.java)

* [583. 两个字符串的删除操作](src/top/xiaotian/algorithms/dp/edit_distance/MinDistance.java)

* [72. 编辑距离](src/top/xiaotian/algorithms/dp/edit_distance/EditDistance.java)

##### 区间动规问题
* [647. 回文子串](src/top/xiaotian/algorithms/dp/interval/PalindromicSubstrings.java)

* [516. 最长回文子序列](src/top/xiaotian/algorithms/dp/interval/LongestPalindromicSubsequence.java)

##### 打家劫舍问题
* [198. 打家劫舍](src/top/xiaotian/algorithms/dp/house_robber/HouseRobber.java)

* [213. 打家劫舍 II](src/top/xiaotian/algorithms/dp/house_robber/HouseRobberII.java)

* [337. 打家劫舍 III](src/top/xiaotian/algorithms/dp/house_robber/HouseRobberIII.java)

##### 股票买卖问题
* [122. 买卖股票的最佳时机 II](src/top/xiaotian/algorithms/dp/stock/StockSellII.java)

* [123. 买卖股票的最佳时机 III](src/top/xiaotian/algorithms/dp/stock/StockSellIII.java)

* [188. 买卖股票的最佳时机 IV](src/top/xiaotian/algorithms/dp/stock/StockSellIV.java)

* [309. 买卖股票的最佳时机含冷冻期](src/top/xiaotian/algorithms/dp/stock/StockSellV.java)

* [714. 买卖股票的最佳时机含手续费](src/top/xiaotian/algorithms/dp/stock/StockSellVI.java)

##### 01背包问题
* [01背包问题](src/top/xiaotian/algorithms/dp/knapsack01/Knapsack01.java)

* [416. 分割等和子集](src/top/xiaotian/algorithms/dp/knapsack01/CanPartition.java)

* [1049. 最后一块石头的重量 II](src/top/xiaotian/algorithms/dp/knapsack01/LastStoneWeight.java)

* [494. 目标和](src/top/xiaotian/algorithms/dfs/FindTargetSumWays.java)

* [474. 一和零](src/top/xiaotian/algorithms/dp/knapsack01/OneAndZeroes.java)

##### 完全背包问题
* [完全背包](src/top/xiaotian/algorithms/dp/knapsacktotal/KnapsackTotal.java)

* [322. 零钱兑换/518. 零钱兑换 II](src/top/xiaotian/algorithms/dp/knapsacktotal/CoinChange.java)

#### 回溯
* [77. 组合](src/top/xiaotian/algorithms/backtrack/combination/Combination.java)

* [216. 组合总和 III](src/top/xiaotian/algorithms/backtrack/combination/CombinationSumIII.java)

* [17. 电话号码的字母组合](src/top/xiaotian/algorithms/backtrack/combination/LetterCombinations.java)

* [39. 组合总和](src/top/xiaotian/algorithms/backtrack/combination/CombinationSum.java)

* [40. 组合总和 II](src/top/xiaotian/algorithms/backtrack/combination/CombinationSumII.java)

#### 贪心
* [121. 买卖股票的最佳时机](src/top/xiaotian/algorithms/dp/stock/StockSell.java)

* [406. 根据身高重建队列](src/top/xiaotian/algorithms/greedy/ReconstructQueue.java)

* [621. 任务调度器](src/top/xiaotian/algorithms/greedy/LeastInterval.java)


#### 双指针
* [844. 比较含退格的字符串](src/top/xiaotian/algorithms/twoPointer/BackspaceCompare.java)


##### 对撞指针
* [344. 反转字符串](src/top/xiaotian/algorithms/twoPointer/collisionPointer/ReverseString.java)

* [541. 反转字符串 II](src/top/xiaotian/algorithms/twoPointer/collisionPointer/ReverseStr.java)

* [151. 反转字符串中的单词](src/top/xiaotian/algorithms/twoPointer/collisionPointer/ReverseWords.java)

* [581. 最短无序连续子数组](src/top/xiaotian/algorithms/twoPointer/collisionPointer/UnsortedSubarray.java)

* [977. 有序数组的平方](src/top/xiaotian/algorithms/twoPointer/collisionPointer/SortedSquares.java)

* [160. 相交链表](src/top/xiaotian/algorithms/twoPointer/collisionPointer/IntersectionNode.java)

* [15. 三数之和](src/top/xiaotian/algorithms/twoPointer/collisionPointer/ThreeSum.java)

* [18. 四数之和](src/top/xiaotian/algorithms/twoPointer/collisionPointer/FourSum.java)

##### 快慢指针
* [234. 回文链表](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/Palindrome4LinkedNode.java)

* [283. 移动零](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/MoveZeros.java)

* [27. 移除元素](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/RemoveElement.java)

* [26. 删除有序数组中的重复项](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/RemoveDuplicatesFromSortedArray.java)

* [80. 删除有序数组中的重复项 II](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/RemoveDuplicatesII.java)

* [19. 删除链表的倒数第 N 个结点](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/RemoveNthFromEnd.java)

* [141. 环形链表/142. 环形链表 II](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/CycleList.java)

* [202. 快乐数](src/top/xiaotian/algorithms/twoPointer/fastSlowPointer/HappyNumber.java)

##### 滑动窗口
* [3. 无重复字符的最长子串](src/top/xiaotian/algorithms/twoPointer/slidingWindow/LongestSubstring.java)

* [438. 找到字符串中所有字母异位词](src/top/xiaotian/algorithms/twoPointer/slidingWindow/FindAnagrams.java)

* [209. 长度最小的子数组](src/top/xiaotian/algorithms/twoPointer/slidingWindow/MinSubArrayLen.java)

* [904. 水果成篮](src/top/xiaotian/algorithms/twoPointer/slidingWindow/TotalFruit.java)

* [76. 最小覆盖子串](src/top/xiaotian/algorithms/twoPointer/slidingWindow/MinimumWindowSubstring.java)


#### 查找表法
* [560. 和为 K 的子数组](src/top/xiaotian/algorithms/map/SubarraySum.java)

* [242. 有效的字母异位词](src/top/xiaotian/algorithms/map/ValidAnagram.java)

* [383. 赎金信](src/top/xiaotian/algorithms/map/RansomNote.java)

* [49. 字母异位词分组](src/top/xiaotian/algorithms/map/GroupAnagrams.java)

* [349. 两个数组的交集/350. 两个数组的交集 II](src/top/xiaotian/algorithms/map/Intersection.java)

* [1. 两数之和](src/top/xiaotian/algorithms/map/TwoSum.java)

* [454. 四数相加 II](src/top/xiaotian/algorithms/map/FourSumCount.java)

#### 数学
* [169. 多数元素](src/top/xiaotian/algorithms/math/MajorityElement.java)

* [338. 比特位计数](src/top/xiaotian/algorithms/math/CountBits.java)

* [448. 找到所有数组中消失的数字](src/top/xiaotian/algorithms/math/DisappearedNumbers.java)

* [461. 汉明距离](src/top/xiaotian/algorithms/math/HammingDistance.java)


#### 前缀和
* [437. 路径总和 III](src/top/xiaotian/algorithms/prefixSum/PathSumIII.java)


#### 二分法
* [704. 二分查找](src/top/xiaotian/algorithms/binarySearch/BinarySearch.java)

* [35. 搜索插入位置](src/top/xiaotian/algorithms/binarySearch/SearchInsert.java)

* [34. 在排序数组中查找元素的第一个和最后一个位置](src/top/xiaotian/algorithms/binarySearch/FindFirstAndLastPositionOfElementInSortedArray.java)

* [240. 搜索二维矩阵 II](src/top/xiaotian/algorithms/binarySearch/NumberIn2DArray.java)

* [287. 寻找重复数](src/top/xiaotian/algorithms/binarySearch/FindDuplicate.java)

#### 二分法在非数组中的变种
* [69. x 的平方根](src/top/xiaotian/algorithms/binarySearch/MySqrt.java)

* [367. 有效的完全平方数](src/top/xiaotian/algorithms/binarySearch/ValidPerfectSquare.java)


#### 排序算法
* [215. 数组中的第K个最大元素](src/top/xiaotian/algorithms/sort/practice/FindNth.java)


#### DFS深度优先遍历
* [200. 岛屿数量](src/top/xiaotian/algorithms/dfs/LandNum.java)

* [377. 组合总和 Ⅳ](src/top/xiaotian/algorithms/backtrack/combination_sum/CombinationSumIV.java)


#### BFS广度优先遍历


#### 设计
* [208. 实现 Trie (前缀树)](src/top/xiaotian/dataStructures/trie/practice/ImplementTrie.java)

* [707. 设计链表](src/top/xiaotian/dataStructures/linkedlist/practice/MyLinkedList.java)

* [232. 用栈实现队列](src/top/xiaotian/dataStructures/queue/practice/MyQueue.java)

* [225. 用队列实现栈](src/top/xiaotian/dataStructures/stack/practice/MyStack.java)
