import java.util.*;

class Solution {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        int n = technique1.length;
        long[][] diff = new long[n][2]; // [gain, index]
        
        for (int i = 0; i < n; i++) {
            diff[i][0] = technique1[i] - technique2[i];
            diff[i][1] = i;
        }
        
        // Sort by gain descending
        Arrays.sort(diff, (a, b) -> Long.compare(b[0], a[0]));
        
        long result = 0;
        
        // First pick top k elements → use technique1
        for (int i = 0; i < k; i++) {
            int idx = (int) diff[i][1];
            result += technique1[idx];
        }
        
        // For remaining, pick the better technique
        for (int i = 0; i < n; i++) {
            if (i < k) continue;
            int idx = (int) diff[i][1];
            if (diff[i][0] > 0) result += technique1[idx];
            else result += technique2[idx];
        }
        
        return result;
    }
}
