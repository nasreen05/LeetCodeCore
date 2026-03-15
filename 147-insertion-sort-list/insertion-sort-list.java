/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        // I don't know how to do this insertion way but here is the  sorting way
        ArrayList<Integer>list=new ArrayList<>();
        ListNode thead=head;
        while(thead!=null){
            list.add(thead.val);
            thead=thead.next;
        }
        list.sort(null);
        thead=head;
        int index=0;
        while(thead!=null){
            thead.val=list.get(index++);
            thead=thead.next;
        }
        return head;
    }
}