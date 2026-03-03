class Solution {
    private int n;
    private int[][] bottomUp;
    private int[] t;
    public int minCut(String str) {
        n = str.length();
        bottomUp = new int[n][n];

        t = new int[n];

        for(int i = 0; i < n; i++) {
            bottomUp[i][i] = 1;
        }

        for(int i = 0; i < n - 1; i++) {
            if(str.charAt(i) == str.charAt(i + 1)) {
                bottomUp[i][i + 1] = 1;
            }
        }

        for(int L = 3; L <= n; L++) {
            for(int i = 0; i < n - L + 1; i++) {
                int j = L + i - 1;
                if(str.charAt(i) == str.charAt(j) && bottomUp[i + 1][j - 1] == 1) 
                    bottomUp[i][j] = 1;
                else 
                    bottomUp[i][j] = 0;
            }
        }

        // we have an array now ok

        for(int i = 0; i < n; i++) {
            if(bottomUp[0][i] == 1) {
                t[i] = 0;
            } else {
                t[i] = Integer.MAX_VALUE;
                for(int cut = 0; cut < i; cut++) {
                    if((bottomUp[cut + 1][i] == 1) && 1 + t[cut] < t[i]) {
                        t[i] = 1 + t[cut];
                    }
                }
            }
        }

        return t[n - 1];
    }
}