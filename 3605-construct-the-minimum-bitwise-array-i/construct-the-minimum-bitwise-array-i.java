class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);

            // Only even prime
            if (num == 2) {
                ans[i] = -1;
                continue;
            }

            int temp = num;
            int power = 1;

            // Count trailing 1s
            while ((temp & 1) == 1) {
                temp >>= 1;
                power <<= 1;
            }

            // Remove the highest trailing 1
            ans[i] = num - (power >> 1);
        }
        return ans;
    }
}
