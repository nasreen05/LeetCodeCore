
class Solution {
    public void reorderList(ListNode head) {
        ListNode left = head, slow = head, fast = head;
        int counter = 1;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            counter++;
        }

        ListNode[] secondHalf = new ListNode[counter];
        while (slow != null) {
            secondHalf[--counter] = slow;
            slow = slow.next;
        }

        while (left != null) {
            ListNode right = secondHalf[counter++], nextLeft = left.next;

            if (left == right || right == nextLeft) {
                right.next = null;
                return;
            }

            left.next = right;
            right.next = nextLeft;
            left = nextLeft;
        }

    }
}