class Solution {
    public int numMagicSquaresInside(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Iterate through all possible 3x3 subgrids
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {

                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isMagic(int[][] g, int r, int c) {

        // Center must be 5
        if (g[r + 1][c + 1] != 5) return false;

        boolean[] seen = new boolean[10];

        // Check numbers 1 to 9 and uniqueness
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int val = g[i][j];
                if (val < 1 || val > 9 || seen[val]) {
                    return false;
                }
                seen[val] = true;
            }
        }

        int sum = g[r][c] + g[r][c + 1] + g[r][c + 2];

        // Rows
        for (int i = 0; i < 3; i++) {
            if (g[r + i][c] + g[r + i][c + 1] + g[r + i][c + 2] != sum)
                return false;
        }

        // Columns
        for (int j = 0; j < 3; j++) {
            if (g[r][c + j] + g[r + 1][c + j] + g[r + 2][c + j] != sum)
                return false;
        }

        // Diagonals
        if (g[r][c] + g[r + 1][c + 1] + g[r + 2][c + 2] != sum)
            return false;

        if (g[r][c + 2] + g[r + 1][c + 1] + g[r + 2][c] != sum)
            return false;

        return true;
    }
}
