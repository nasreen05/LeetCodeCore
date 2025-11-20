import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Sort intervals by ascending end, if tie by descending start
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]) {
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            }
        });

        int ans = 0;
        int s = -1; // first largest element chosen for last interval
        int e = -1; // second largest element chosen for last interval

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // Case 1: no intersection with current chosen elements
            if (start > e) {
                // Add two elements: end-1 and end
                ans += 2;
                s = end - 1;
                e = end;
            }
            // Case 2: intersect with only one element (only e is included)
            else if (start > s) {
                // Add one element: end
                ans += 1;
                s = e;
                e = end;
            }
            // Case 3: interval already covered by s and e, no need to add elements
        }

        return ans;
    }
}
