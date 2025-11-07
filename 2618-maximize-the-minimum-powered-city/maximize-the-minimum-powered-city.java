class Solution {
    private boolean check(long[] power, int r, long k, long x, int n) {
        long[] add_effect = new long[n + 1];
        long current_covered = 0;
        long total_add = 0;
        for (int i = 0; i < n; i++) {
            current_covered += add_effect[i];
            long current = power[i] + current_covered;
            if (current < x) {
                long need = x - current;
                total_add += need;
                if (total_add > k) return false;
                int pos = Math.min(n - 1, i + r);
                int end = Math.min(n - 1, pos + r);
                current_covered += need;
                if (end + 1 < n) add_effect[end + 1] -= need;
            }
        }
        return total_add <= k;
    }

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + stations[i];
        long[] power = new long[n];
        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n - 1, i + r);
            power[i] = prefix[right + 1] - prefix[left];
        }
        long low = 0;
        long high = prefix[n] + k;
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (check(power, r, k, mid, n)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
