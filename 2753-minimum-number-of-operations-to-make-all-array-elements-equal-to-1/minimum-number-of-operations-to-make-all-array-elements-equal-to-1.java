import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int overallGcd = nums[0];
        int count1 = 0;
        for (int num : nums) {
            overallGcd = gcd(overallGcd, num);
            if (num == 1) count1++;
        }
        if (overallGcd != 1) return -1;

        int minM = n;
        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i; j < n; j++) {
                if (j > i) g = gcd(g, nums[j]);
                if (g == 1) {
                    minM = Math.min(minM, j - i + 1);
                    break; // No need to continue for this i
                }
            }
        }

        if (count1 > 0) {
            return n - count1;
        } else {
            return minM + n - 2;
        }
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
