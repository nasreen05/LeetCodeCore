class Solution {

    private ListNode curr;
    private TreeNode buildTree(int left, int right) {
        if (left > right)
            return null;

        int mid = left + (right - left) / 2;
        TreeNode leftChild = buildTree(left, mid - 1);
        TreeNode root = new TreeNode(curr.val);
        root.left = leftChild;
        curr = curr.next;
        root.right = buildTree(mid + 1, right);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        int nodeCount = getLength(head);
        curr = head;
        return buildTree(0, nodeCount - 1);
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}