class Solution {
    public int numSub(String s) {
        long count = 0, result = 0;
        long mod = 1_000_000_007;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;   
            } else {
             
                result = (result + count * (count + 1) / 2) % mod;
                count = 0;
            }
        }
        result = (result + count * (count + 1) / 2) % mod;

        return (int) result;
    }
}
