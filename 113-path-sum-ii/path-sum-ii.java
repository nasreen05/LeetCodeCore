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
    void putValues(TreeNode root, int target,int currSum,List<Integer> list,List<List<Integer>> ans){
        if(root == null) return;

        currSum += root.val;
        list.add(root.val);

        if(root.left == null && root.right == null){
            if(currSum == target){
                ans.add(new ArrayList<>(list));
            }
            // return;
        }

        putValues(root.left,target,currSum,list,ans);
        putValues(root.right,target,currSum,list,ans);
        list.remove(list.size()-1); // not btw those two

    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        putValues(root,targetSum,0,list,ans);
        return ans;
    }
}

// ~~~ this code fails bcz suppose 7 ke left men gye kuch nhe tha udher tou right ka jab call lagaoge tou 7 he hat jyega .... **

// class Solution {
//     void putValues(TreeNode root, int target,int currSum,List<Integer> list,List<List<Integer>> ans){
//         if(root == null) return;

//         currSum += root.val;
//         list.add(root.val);

//         if(root.left == null && root.right == null){
//             if(currSum == target){
//                 ans.add(list);
//             }
//             // return;
//         }

//         putValues(root.left,target,currSum,list,ans);
        
//         list.remove(list.size()-1); **.... wy not here

//         putValues(root.right,target,currSum,list,ans);

//     }
//     public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//         List<Integer> list = new ArrayList<>();
//         List<List<Integer>> ans = new ArrayList<>();

//         putValues(root,targetSum,0,list,ans);
//         return ans;
//     }
// }