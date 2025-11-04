class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    // Find the leftmost (first) position of the target
    private int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int pos = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                pos = mid;
                right = mid - 1; // move left to find earlier occurrence
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return pos;
    }

    // Find the rightmost (last) position of the target
    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int pos = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                pos = mid;
                left = mid + 1; // move right to find later occurrence
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return pos;
    }
}
