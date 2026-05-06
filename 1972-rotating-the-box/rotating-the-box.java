class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;

        // Step 1: Simulate gravity towards right
        for(int i = 0; i < m; i++) {

            int end = n - 1;

            for(int j = n - 1; j >= 0; j--) {

                if(boxGrid[i][j] == '*') {
                    end = j - 1;
                }

                else if(boxGrid[i][j] == '#') {

                    boxGrid[i][j] = '.';
                    boxGrid[i][end] = '#';

                    end--;
                }
            }
        }

        // Step 2: Rotate matrix clockwise
        char[][] ans = new char[n][m];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                ans[j][m - 1 - i] = boxGrid[i][j];
            }
        }

        return ans;
    }
}