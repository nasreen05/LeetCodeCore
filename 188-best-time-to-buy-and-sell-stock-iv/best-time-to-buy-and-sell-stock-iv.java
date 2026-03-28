// ✅ Approach 4 — Space Optimization (Best)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] after = new int[k + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            int[][] curr = new int[k + 1][2];

            for (int j = 1; j <= k; j++) {
                curr[j][1] = Math.max(-prices[i] + after[j][0], after[j][1]);
                curr[j][0] = Math.max(prices[i] + after[j - 1][1], after[j][0]);
            }

            after = curr;
        }

        return after[k][1];
    }
}

/*
// 🟢 Approach 3 — Tabulation (Bottom-Up DP)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][k + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][1] = Math.max(-prices[i] + dp[i + 1][j][0], dp[i + 1][j][1]);
                dp[i][j][0] = Math.max(prices[i] + dp[i + 1][j - 1][1], dp[i + 1][j][0]);
            }
        }

        return dp[0][k][1];
    }
}
*/

/*
// 🟡 Approach 2 — Memoization (Top-Down DP)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][k][2];

        for(int[][] row2D : dp)
            for(int[] row: row2D)
                Arrays.fill(row, -1);

        return findMax(dp, prices, 0, k, 1);
    }

    private int findMax(int[][][] dp, int[] prices, int i, int k, int canBuy) {
        if (i == prices.length || k == 0) return 0;

        if(dp[i][k-1][canBuy] != -1) return dp[i][k-1][canBuy];

        if (canBuy == 1) {
            return dp[i][k-1][canBuy] = Math.max(
                -prices[i] + findMax(dp, prices, i + 1, k, 0),
                findMax(dp, prices, i + 1, k, 1)
            );
        } else {
            return dp[i][k-1][canBuy] = Math.max(
                prices[i] + findMax(dp, prices, i + 1, k - 1, 1),
                findMax(dp, prices, i + 1, k, 0)
            );
        }
    }
}
*/

/*
// 🔴 Approach 1 — Pure Recursion (TLE @ testcase 201)
class Solution {
    public int maxProfit(int k, int[] prices) {
        return findMax(prices, 0, k, true);
    }

    private int findMax(int[] prices, int i, int k, boolean canBuy) {
        if (i == prices.length || k == 0) return 0;

        if (canBuy) {
            return Math.max(
                -prices[i] + findMax(prices, i + 1, k, false),
                findMax(prices, i + 1, k, true)
            );
        } else {
            return Math.max(
                prices[i] + findMax(prices, i + 1, k - 1, true),
                findMax(prices, i + 1, k, false)
            );
        }
    }
}
*/