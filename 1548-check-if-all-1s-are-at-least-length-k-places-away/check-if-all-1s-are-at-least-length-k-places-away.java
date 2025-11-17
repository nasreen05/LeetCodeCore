class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int lastIndex = -k - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i - lastIndex - 1 < k) {
                    return false; // Distance less than k
                }
                lastIndex = i; // Update last seen 1 index
            }
        }
        return true; // All 1s are at least k places apart
    }
}
