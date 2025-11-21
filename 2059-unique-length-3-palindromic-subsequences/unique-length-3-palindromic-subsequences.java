import java.util.*;

class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        Map<Character, int[]> firstLast = new HashMap<>();
        
        // STEP-1: Get first & last occurrence for every character
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (!firstLast.containsKey(c)) {
                firstLast.put(c, new int[]{i, i});
            } else {
                firstLast.get(c)[1] = i;
            }
        }
        
        int count = 0;
        // STEP-2: For each character that appears at least twice,
        // count unique middle letters between its first and last appearance
        for (Map.Entry<Character, int[]> entry : firstLast.entrySet()) {
            int[] range = entry.getValue();
            if (range[0] == range[1]) continue; // occurs only once
            Set<Character> uniqueChars = new HashSet<>();
            for (int i = range[0] + 1; i < range[1]; ++i) {
                uniqueChars.add(s.charAt(i));
            }
            count += uniqueChars.size();
        }
        return count;
    }
}
