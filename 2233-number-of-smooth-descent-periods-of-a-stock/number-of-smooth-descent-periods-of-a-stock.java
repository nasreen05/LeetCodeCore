class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 1;     // first day itself
        long count = 1;   // current descent length

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                count++;        // continue descent
            } else {
                count = 1;      // reset
            }
            ans += count;
        }

        return ans;
    }
}
