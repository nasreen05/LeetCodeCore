/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int count = 1; // root
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.right!=null){
            countNodes(root.right);
            count++;
        }
        if(root.left!=null){
            countNodes(root.left);
            count++;
        }
        return count;
    }
}