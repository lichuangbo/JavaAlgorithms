### 基本数据结构

#### 数组
+ 动态数组实现
    + add, set, remove, get, resize, contains等关键操作

#### 链表
+ 手写链表
    + 最基本的链表结构
        1. 虚拟头结点作用
        2. 链表相关的关键方法实现
        3. 链表和递归的关联
+ 练习题目：
    1. 翻转链表（迭代和递归方式）   
    2. 删除倒数第N个节点
    3. 合并两个有序链表
    4. 环形链表判断
    5. LRU缓存淘汰算法实现
    6. 回文链表判断
    7. 删除链表中等于给定值 val 的所有节点(递归和迭代写法的对照)

#### 栈
+ 手写栈
    + 顺序栈实现
    + 链式栈实现
+ 练习题目
    1. 四则运算简单实现
    2. 括号匹配

#### 队列
+ 手写队列
    + 顺序队列实现
    + 链式队列实现
    + 循环队列实现
+ 练习题目

#### 树
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
    
#### 映射
+ 映射的实现
    + 基于二分搜索树
    + 基于链表
+ 练习题目
    1. 两个数组的交集II
    
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

#### 栈
* [394. 字符串解码](src/top/xiaotian/algorithms/stack/DecodeString.java)

##### 单调栈
* [739. 每日温度](src/top/xiaotian/algorithms/stack/monotone_stack/DailyTemperatures.java)

#### 队列

##### 优先队列
* [347. 前 K 个高频元素](src/top/xiaotian/algorithms/queue/priority_queue/TopKFrequent.java)

#### 树
* [297. 二叉树的序列化与反序列化](src/top/xiaotian/algorithms/tree/SerialDeserialBinaryTree.java)

#### 动态规划
* [198./213./337. 打家劫舍问题](src/top/xiaotian/algorithms/dp/HouseRobber.java)

* [309. 股票买卖问题](src/top/xiaotian/algorithms/dp/StockSell.java)

* [494. 目标和](src/top/xiaotian/algorithms/dfs/FindTargetSumWays.java)

* [647. 回文子串](src/top/xiaotian/algorithms/dp/palindromic/PalindromicSubstrings.java)

##### 01背包
* [416. 分割等和子集](src/top/xiaotian/algorithms/dp/knapsack01/CanPartition.java)

##### 完全背包
* [322. 零钱兑换](src/top/xiaotian/algorithms/dp/knapsacktotal/CoinChange.java)

#### 贪心
* [406. 根据身高重建队列](src/top/xiaotian/algorithms/greedy/ReconstructQueue.java)

* [621. 任务调度器](src/top/xiaotian/algorithms/greedy/LeastInterval.java)

#### 递归

##### 树
* [538. 把二叉搜索树转换为累加树](src/top/xiaotian/algorithms/tree/binary_search_tree/ConvertBST.java)

* [543. 二叉树的直径](src/top/xiaotian/algorithms/tree/DiameterOfBinaryTree.java)

* [617. 合并二叉树](src/top/xiaotian/algorithms/tree/MergeTrees.java)

#### 双指针

##### 对撞指针
* [581. 最短无序连续子数组](src/top/xiaotian/algorithms/twoPointer/collisionPointer/UnsortedSubarray.java)

##### 滑动窗口
* [3. 无重复字符的最长子串](src/top/xiaotian/algorithms/twoPointer/slidingWindow/LongestSubstring.java)

* [438. 找到字符串中所有字母异位词](src/top/xiaotian/algorithms/twoPointer/slidingWindow/FindAnagrams.java)

#### 查找表法
* [560. 和为 K 的子数组](src/top/xiaotian/algorithms/map/SubarraySum.java)

#### 数学
* [338. 比特位计数](src/top/xiaotian/algorithms/math/CountBits.java)

* [448. 找到所有数组中消失的数字](src/top/xiaotian/algorithms/math/DisappearedNumbers.java)

* [461. 汉明距离](src/top/xiaotian/algorithms/math/HammingDistance.java)

#### 前缀和
* [437. 路径总和 III](src/top/xiaotian/algorithms/prefixSum/PathSumIII.java)

#### 二分法
* [287. 寻找重复数](src/top/xiaotian/algorithms/binarySearch/FindDuplicate.java)


