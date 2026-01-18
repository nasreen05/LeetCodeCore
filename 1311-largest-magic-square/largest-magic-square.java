class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Prefix sums for rows and columns
        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }

        // Try largest k first
        for (int k = Math.min(m, n); k >= 2; k--) {
            for (int r = 0; r + k <= m; r++) {
                for (int c = 0; c + k <= n; c++) {

                    int target = rowSum[r][c + k] - rowSum[r][c];
                    boolean isMagic = true;

                    // Check rows
                    for (int i = r; i < r + k; i++) {
                        if (rowSum[i][c + k] - rowSum[i][c] != target) {
                            isMagic = false;
                            break;
                        }
                    }

                    // Check columns
                    for (int j = c; j < c + k && isMagic; j++) {
                        if (colSum[r + k][j] - colSum[r][j] != target) {
                            isMagic = false;
                            break;
                        }
                    }

                    // Check diagonals
                    int d1 = 0, d2 = 0;
                    for (int i = 0; i < k && isMagic; i++) {
                        d1 += grid[r + i][c + i];
                        d2 += grid[r + i][c + k - 1 - i];
                    }

                    if (isMagic && d1 == target && d2 == target) {
                        return k;
                    }
                }
            }
        }

        return 1; // At least 1x1 is always magic
    }
}
