class Solution 
{
    public int[] twoSum(int[] nums, int target)  // we do not need scanner and all that in this leetcode it automatic and priting satement is also not needed.
  {
        int c[]=new int[2];
        for(int i=0;i<nums.length-1;i++)
        {
            for (int j=i+1;j<nums.length;j++)
            {
                if((nums[i]+nums[j])==target)
                {
                    c[0]=i;
                    c[1]=j;
                    break;
                    
                }
            }

        }
        return c;
    }
}
