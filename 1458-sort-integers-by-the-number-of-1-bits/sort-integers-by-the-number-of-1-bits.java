import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        // Convert to Integer[] to use custom comparator
        Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        
        Arrays.sort(boxed, (a, b) -> {
            int bitCompare = Integer.bitCount(a) - Integer.bitCount(b);
            if (bitCompare != 0) {
                return bitCompare;
            }
            return a - b;  // If same bit count, sort by value
        });
        
        // Convert back to int[]
        return Arrays.stream(boxed).mapToInt(Integer::intValue).toArray();
    }
}
