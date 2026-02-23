class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<String> seen = new HashSet<>();
        int target = 1 << k; // 2^k

        for (int i = 0; i <= s.length() - k; i++) {
            String substring = s.substring(i, i + k);
            seen.add(substring);
            if (seen.size() == target) {
                return true;
            }
        }

        return seen.size() == target;
    }
}