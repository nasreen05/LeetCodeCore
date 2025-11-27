class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // prefixSum[i] = sum of nums[0..i-1]
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // minPrefixSumByMod stores minimum prefixSum for each mod class (0..k-1)
        long[] minPrefixSumByMod = new long[k];
        for (int i = 0; i < k; i++) {
            minPrefixSumByMod[i] = Long.MAX_VALUE;
        }

        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            int mod = i % k;
            if (minPrefixSumByMod[mod] != Long.MAX_VALUE) {
                // max subarray sum = prefixSum[i] - min prefixSum with same mod
                maxSum = Math.max(maxSum, prefixSum[i] - minPrefixSumByMod[mod]);
            }
            // update min prefix sum for this mod
            minPrefixSumByMod[mod] = Math.min(minPrefixSumByMod[mod], prefixSum[i]);
        }
        return maxSum;
    }
}
