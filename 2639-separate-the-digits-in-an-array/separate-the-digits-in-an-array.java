class Solution {
    public int[] separateDigits(int[] nums) {
        int[] result = new int[0];
        ArrayList<Integer> resultList = new ArrayList<>(nums.length);
        for (int num : nums) {
            ArrayList<Integer> digits = new ArrayList<>();
            getDigits(digits, num);
            if (digits != null) {
                resultList.addAll(digits);
            }
        }
        return resultList.stream().mapToInt(i -> i).toArray();
    }

    private void getDigits(ArrayList<Integer> digits, int num) {
        if(num > 0) {
            getDigits(digits, num / 10);
            digits.add(num % 10);
        }
    }
}