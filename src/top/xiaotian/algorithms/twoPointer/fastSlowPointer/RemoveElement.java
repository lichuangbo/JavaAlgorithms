package top.xiaotian.algorithms.twoPointer.fastSlowPointer;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement {
  /**
   * fast指针指向和val不相等的元素，slow指向维护的新数组的最后一个元素，两个不断发生交换
   * 3 2 2 3   val=3
   * f=0,s=0 continue
   * f=1,s=0 2 2 2 3
   * f=2,s=1 2 2 2 3
   * f=3,s=2 continue
   * f=4     break
   */
  public int removeElement(int[] nums, int val) {
    int slow = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      if (val != nums[fast]) {
        nums[slow++] = nums[fast];
      }
    }
    return slow;
  }
}
