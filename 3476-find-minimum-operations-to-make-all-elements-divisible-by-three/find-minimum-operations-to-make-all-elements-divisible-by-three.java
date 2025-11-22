class Solution {
    public int minimumOperations(int[] nums) {
        int operations = 0;
        for (int num : nums) {
            int remainder = num % 3;
            // Minimum steps to next or previous multiple of 3
            operations += Math.min(remainder, 3 - remainder);
        }
        return operations;
    }
}
