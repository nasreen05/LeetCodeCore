class Solution {

    public String findTheString(int[][] lcp) {

        int n = lcp.length;
        char[] word = new char[n];

        for (int i = 0; i < n; i++) {
            word[i] = '?';
        }

        char ch = 'a';

        // Step 1: Assign characters greedily
        for (int i = 0; i < n; i++) {

            if (word[i] == '?') {

                if (ch > 'z') {
                    return "";
                }

                for (int j = i; j < n; j++) {

                    if (lcp[i][j] > 0) {
                        word[j] = ch;
                    }
                }

                ch++;
            }
        }

        // Step 2: Validate LCP matrix correctness
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                if (word[i] == word[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 0;
                }

                if (dp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}
