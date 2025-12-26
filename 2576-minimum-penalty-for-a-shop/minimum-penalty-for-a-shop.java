class Solution {
    public int bestClosingTime(String customers) {
        int penalty = 0;

        // Step 1: initial penalty when shop closes at hour 0
        for (char c : customers.toCharArray()) {
            if (c == 'Y') {
                penalty++;
            }
        }

        int minPenalty = penalty;
        int bestHour = 0;

        // Step 2: try closing at each hour
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                penalty--;   // shop stays open → avoids closed penalty
            } else {
                penalty++;   // open but no customer → penalty
            }

            // Step 3: update minimum penalty
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestHour = i + 1;
            }
        }

        return bestHour;
    }
}
