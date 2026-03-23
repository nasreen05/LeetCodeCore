class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        final int MOD = 1_000_000_007;

        long[][] Min = new long[m][n];
        long[][] Max = new long[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                Min[i][j] = Long.MAX_VALUE;
                Max[i][j] = Long.MIN_VALUE;
            }

        Min[0][0] = Max[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) 
                    continue;

                long mn = Long.MAX_VALUE;
                long mx = Long.MIN_VALUE;

                if (i > 0) {
                    long a = Min[i-1][j] * grid[i][j];
                    long b = Max[i-1][j] * grid[i][j];
                    mn = Math.min(mn, Math.min(a, b));
                    mx = Math.max(mx, Math.max(a, b));
                }

                if (j > 0) {
                    long a = Min[i][j-1] * grid[i][j];
                    long b = Max[i][j-1] * grid[i][j];
                    mn = Math.min(mn, Math.min(a, b));
                    mx = Math.max(mx, Math.max(a, b));
                }

                Min[i][j] = mn;
                Max[i][j] = mx;
            }
        }

        long res = Max[m-1][n-1];
        return res >= 0 ? (int)(res % MOD) : -1;
    }
}