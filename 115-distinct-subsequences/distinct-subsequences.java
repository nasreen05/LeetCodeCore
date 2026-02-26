class Solution {
    public int numDistinct(String s, String t) {
        int[][]arr=new int[s.length()][t.length()];
        for(int[]row:arr)Arrays.fill(row,-1);
        return helper(s,t,arr,s.length()-1,t.length()-1);
    }

    private static int helper(String str1, String str2, int[][] dp, int i, int j) {
        if(j<0)return 1;
        if(i<0)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        if(str1.charAt(i)==str2.charAt(j)){
            return dp[i][j]=helper(str1,str2,dp,i-1,j-1)+helper(str1,str2,dp,i-1,j);
        }
        return dp[i][j]=helper(str1,str2,dp,i-1,j);
    }
}