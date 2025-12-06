class Solution {
    private boolean isBinaryPalindrome(int x) {
        String s = Integer.toBinaryString(x);
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    public int[] minOperations(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int left = val, right = val;

            // Search downward
            while (left >= 1 && !isBinaryPalindrome(left)) left--;

            // Search upward
            while (!isBinaryPalindrome(right)) right++;

            ans[i] = Math.min(val - left, right - val);
        }

        return ans;
    }
}
