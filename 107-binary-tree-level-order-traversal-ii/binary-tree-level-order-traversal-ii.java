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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        if(root == null){
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){

            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for(int i = 0; i < size; i++){

                TreeNode curr = queue.remove();

                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);

                list.add(curr.val);
            }

            ans.add(0, list);   // insert at beginning
        }

        return ans;
    }
}