import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            // Found valid combination
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            // Exceeded the sum, stop exploration
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            // Not incrementing i because we can reuse same element
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
