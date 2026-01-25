#include <stdlib.h>
#include <limits.h>

// Comparator function for qsort
int compare(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

int minimumDifference(int* nums, int numsSize, int k) {
    if (k == 1) {
        return 0;
    }

    // Sort the array
    qsort(nums, numsSize, sizeof(int), compare);

    int minDiff = INT_MAX;

    // Sliding window of size k
    for (int i = 0; i <= numsSize - k; i++) {
        int diff = nums[i + k - 1] - nums[i];
        if (diff < minDiff) {
            minDiff = diff;
        }
    }

    return minDiff;
}
