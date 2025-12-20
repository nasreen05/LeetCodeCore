class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        int deleteCount = 0;

        for (int j = 0; j < cols; j++) {
            for (int i = 1; i < rows; i++) {
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    deleteCount++;
                    break; // no need to check this column further
                }
            }
        }
        return deleteCount;
    }
}
