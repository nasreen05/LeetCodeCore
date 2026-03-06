class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder();
        int n = columnNumber;
        while(n > 0){
            n--;
            ans.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return ans.toString();
    }
}