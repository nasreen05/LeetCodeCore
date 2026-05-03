class Solution {
    private void addToList(List<List<Integer>> ans, List<Integer> tmp){
        if(ans.isEmpty() || !ans.get(ans.size()-1).get(0).equals(tmp.get(0)))
            ans.add(tmp);
        else if (ans.get(ans.size()-1).get(1) < tmp.get(1))
            ans.set(ans.size()-1, tmp);
    }

    private void handleEndEvt(PriorityQueue<int[]> endEvts, 
            PriorityQueue<int[]> heights, List<List<Integer>> ans){
        int[] endEvt = endEvts.poll();
        while(!heights.isEmpty() && heights.peek()[1] <= endEvt[0])
            heights.poll(); // remove already overlapped

        if(!heights.isEmpty()){
            if(heights.peek()[0] >= endEvt[1]) return;
            else addToList(ans, List.of(endEvt[0], heights.peek()[0]));
        } else addToList(ans, List.of(endEvt[0], 0));
    }

    public List<List<Integer>> getSkyline(int[][] A) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> endEvts 
            = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> heights 
            = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        for(int[] a: A){
            // process endEvts before this building
            while(!endEvts.isEmpty() && endEvts.peek()[0] < a[0])
                handleEndEvt(endEvts, heights, ans);

            // remove heights ended before this start
            while(!heights.isEmpty() && heights.peek()[1] < a[0])
                heights.poll();

            // add starting if greater than current height
            if(heights.isEmpty() || heights.peek()[0] < a[2]) 
                addToList(ans, List.of(a[0], a[2]));

            heights.offer(new int[]{a[2], a[1]}); // height, end_x
            endEvts.offer(new int[]{a[1], a[2]}); // end_x, height
        }

        // process remaining endEvts 
        while(!endEvts.isEmpty())
            handleEndEvt(endEvts, heights, ans);

        return ans;
    }
}