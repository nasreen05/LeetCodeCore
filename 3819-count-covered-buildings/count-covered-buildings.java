class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        
        // Track min/max y for each row and min/max x for each column
        int[] minRow = new int[n + 1];
        int[] maxRow = new int[n + 1];
        int[] minCol = new int[n + 1];
        int[] maxCol = new int[n + 1];
        
        // Initialize
        for (int i = 1; i <= n; i++) {
            minRow[i] = minCol[i] = Integer.MAX_VALUE;
            maxRow[i] = maxCol[i] = Integer.MIN_VALUE;
        }

        // Fill row/column boundaries
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            minRow[x] = Math.min(minRow[x], y);
            maxRow[x] = Math.max(maxRow[x], y);
            minCol[y] = Math.min(minCol[y], x);
            maxCol[y] = Math.max(maxCol[y], x);
        }

        int covered = 0;

        // Check each building
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            boolean hasUp = x > minCol[y];
            boolean hasDown = x < maxCol[y];
            boolean hasLeft = y > minRow[x];
            boolean hasRight = y < maxRow[x];

            if (hasUp && hasDown && hasLeft && hasRight)
                covered++;
        }

        return covered;
    }
}
