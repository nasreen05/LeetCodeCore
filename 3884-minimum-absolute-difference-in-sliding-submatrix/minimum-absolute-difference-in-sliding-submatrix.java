class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int[][] res = new int[n-k+1][m-k+1];

        for (int i = 0; i <= n-k; i++) {
            for (int j = 0; j <= m-k; j++) {

                // Collect all k*k elements in this window
                List<Integer> list = new ArrayList<>();
                for (int r = i; r < i+k; r++)
                    for (int c = j; c < j+k; c++)
                        list.add(grid[r][c]);

                // Sort and find min diff between adjacent distinct pairs
                Collections.sort(list);
                int diff = Integer.MAX_VALUE;
                for (int x = 0; x < list.size()-1; x++)
                    if (!list.get(x).equals(list.get(x+1)))
                        diff = Math.min(diff, list.get(x+1) - list.get(x));

                res[i][j] = (diff == Integer.MAX_VALUE) ? 0 : diff;
            }
        }
        return res;
    }
}