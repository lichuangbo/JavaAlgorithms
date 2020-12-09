package top.xiaotian.dataStructures.linkedlist.practice;

/**
 * 环形链表的判断（快慢指针经典用法）
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/6
 */
public class CycleList {
    /**
     * （n为链表长度）
     * 无环情况下：fast走n步，slow走n/2步，总的来说是3n/2步，根据大O表示法应为O(n)
     * 有环情况下：假设环的入口距离起点是x，那么当慢指针走到入口，也就是走了x步的时候，快指针走了2x步，二者最大距离是(n - x)
     * 那么最多再迭代(n - x)次二者会相遇，所以时间复杂度是x + 2x + (n - x) + 2 * (n - x) =3∗n，根据大O表示法应为O(n)
     * 注：最大距离这个画图会清晰点；并且由于有环的存在，每一次迭代，快慢距离会缩短1
     * @param head
     * @return
     */
    public boolean hasCycle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node fast = head;
        Node slow = head;
        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            // 快慢指针相遇，即说明遇见了环
            if (fast == slow) {
                return true;
            }
            // 非环情况，及时返回
            if (fast == null || fast.next == null) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Node node2 = new Node("2", null);
        node2.next = new Node("0", new Node("-4", node2));
        Node head1 = new Node("3", node2);

        Node head2 = new Node("1", new Node("2", new Node("2", new Node("1", null))));

        System.out.println(new CycleList().hasCycle(head2));
    }
}
