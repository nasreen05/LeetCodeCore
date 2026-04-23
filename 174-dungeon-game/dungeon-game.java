import java.util.Arrays;

class Solution {
    int m, n;
    int[][] dp;

    public int calculateMinimumHP(int[][] dungeon) {
        m = dungeon.length;
        n = dungeon[0].length;

        dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(0, 0, dungeon);
    }

    private int solve(int i, int j, int[][] dungeon) {
        if (i >= m || j >= n) return Integer.MAX_VALUE;

        if (dp[i][j] != -1) return dp[i][j];

        if (i == m - 1 && j == n - 1) {
            return Math.max(1, 1 - dungeon[i][j]);
        }

        int right = solve(i, j + 1, dungeon);
        int down = solve(i + 1, j, dungeon);

        int nextHealth = Math.min(right, down);
        int healthNeeded = nextHealth - dungeon[i][j];

        return dp[i][j] = Math.max(1, healthNeeded);
    }
}