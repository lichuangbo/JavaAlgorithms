package top.xiaotian.algorithms.linkedList;

/**
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/14 9:29
 * @Description: 描述:
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr is empty");

        this.val = arr[0];
        ListNode curr = this;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode curr = this;
        while (curr != null) {
            sb.append(curr.val).append("->");
            curr = curr.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
