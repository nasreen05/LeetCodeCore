/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root==null){
            return root;
        }
        Node leftMost=root;
        //traverse level by level
        while(leftMost.left != null){
            Node cur=leftMost;
            // Connect nodes across this level
            while(cur!=null){
                //internal both are same parent
                cur.left.next=cur.right;
                //both are different parents
                if(cur.next!=null){
                    cur.right.next=cur.next.left;
                }
                cur=cur.next;
            }
            // Move down one level
            leftMost=leftMost.left;
        }
        return root;
    }
}