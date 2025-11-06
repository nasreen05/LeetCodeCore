class Solution {
    public String countAndSay(int n) {
        // Base case
        if (n == 1) return "1";

        String current = "1";
        // Generate sequence from 2 to n
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;
            for (int j = 1; j < current.length(); j++) {
                if (current.charAt(j) == current.charAt(j - 1)) {
                    count++;
                } else {
                    next.append(count);
                    next.append(current.charAt(j - 1));
                    count = 1;
                }
            }
            // Append last group
            next.append(count);
            next.append(current.charAt(current.length() - 1));
            current = next.toString();
        }
        return current;
    }
}
