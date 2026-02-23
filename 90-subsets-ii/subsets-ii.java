class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // Sort to handle duplicates
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        
        for (int num : nums) {
            int n = outer.size();
            for (int i = 0; i < n; i++) {
                List<Integer> internal = new ArrayList<>(outer.get(i));
                internal.add(num);
                if (!outer.contains(internal)) {
                    outer.add(internal);
                }
            }
        }
        
        return outer;
    }
}