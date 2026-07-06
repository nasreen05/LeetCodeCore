class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] flag = new boolean[1001];

        for (int num : nums1) {
            flag[num] = true;
        }

        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;

        for (int num : nums2) {
            if (flag[num]) {
                result[index++] = num;
                flag[num] = false;
            }
        }

        return Arrays.copyOf(result, index);
    }
}