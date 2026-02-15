class Solution {
    public int removeDuplicates(int[] nums) 
    {
        int cnt=1;
        int j=1;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]==nums[i-1]) cnt++;
            else cnt=1;
            if(cnt<3)
            {
                nums[j]=nums[i];
                j++;
            }
        }
        return j;
    }
}