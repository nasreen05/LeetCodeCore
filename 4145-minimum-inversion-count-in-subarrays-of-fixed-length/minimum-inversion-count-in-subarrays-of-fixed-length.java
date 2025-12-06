import java.util.*;

class Solution {
    public long minInversionCount(int[] nums, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Coordinate compression
        Map<Integer, Integer> comp = new HashMap<>();
        int id = 1;
        for (int x : sorted) {
            if (!comp.containsKey(x)) comp.put(x, id++);
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = comp.get(nums[i]);

        int maxVal = id + 5; // add safe buffer
        Fenwick bit = new Fenwick(maxVal);

        long currInv = 0;

        // First window inversion count
        for (int i = 0; i < k; i++) {
            currInv += (i - bit.sum(arr[i])); // elements greater than arr[i]
            bit.add(arr[i], 1);
        }

        long ans = currInv;

        // Slide window
        for (int i = k; i < n; i++) {
            int remove = arr[i - k];
            int add = arr[i];

            // Remove old element contribution
            bit.add(remove, -1);
            currInv -= bit.sum(remove - 1);

            // Add new element contribution
            currInv += bit.sum(maxVal) - bit.sum(add);
            bit.add(add, 1);

            ans = Math.min(ans, currInv);
        }

        return ans;
    }

    class Fenwick {
        long[] tree;
        int n;
        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }
        void add(int i, long v) {
            while (i <= n) {
                tree[i] += v;
                i += i & -i;
            }
        }
        long sum(int i) {
            long s = 0;
            while (i > 0) {
                s += tree[i];
                i -= i & -i;
            }
            return s;
        }
    }
}
