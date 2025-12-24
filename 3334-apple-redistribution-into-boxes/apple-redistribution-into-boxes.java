import java.util.Arrays;

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {

        // Step 1: Calculate total apples
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }

        // Step 2: Sort capacity array
        Arrays.sort(capacity);

        // Step 3: Pick largest boxes first
        int usedBoxes = 0;
        int currentCapacity = 0;

        for (int i = capacity.length - 1; i >= 0; i--) {
            currentCapacity += capacity[i];
            usedBoxes++;

            if (currentCapacity >= totalApples) {
                break;
            }
        }

        return usedBoxes;
    }

}