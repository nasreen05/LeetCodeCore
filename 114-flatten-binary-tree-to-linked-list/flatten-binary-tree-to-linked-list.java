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
   //TC=o(n) SC=o(1) MORRIS TRAVERSAL
    public void flatten(TreeNode root) {
        TreeNode curr=root;
        while(curr!=null){
            if(curr.left!=null){
                TreeNode pred=curr.left;
                while(pred.right!=null)pred=pred.right;
                pred.right=curr.right;
                curr.right=curr.left;
                curr.left=null;
            }
            curr=curr.right;
        }
    }
}



//1. REVERSE PREORDER [DFS]

// class Solution {
//         TreeNode prev=null;
//     public void flatten(TreeNode root) {
//         if(root==null)return;
//         flatten(root.right);
//         flatten(root.left);
//         root.right=prev;
//         root.left=null;
//         prev=root;
//     }
// }