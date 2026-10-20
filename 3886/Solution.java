class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int N = (int) Math.sqrt(n);
        int res = 0;
        
        for (int k = 1; k <= N; k++) {
            if (n % k != 0) {
                continue;
            }
            
            int otherDiv = n / k;
            if (isValid(nums, k)) {
                res += k;
            }
            
            if (otherDiv != k && isValid(nums, otherDiv)) {
                res += otherDiv;
            }
        }
        return res;
    }
    
    private boolean isValid(int[] nums, int k) {
        int n = nums.length;
        int blocks = n / k;
        int prevMax = Integer.MIN_VALUE;
        
        for (int block = 0; block < blocks; block++) {
            int drops = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int start = block * k;
            int end = start + k;
            
            for (int i = start; i < end; i++) {
                if (i > start && nums[i] < nums[i - 1]) {
                    drops++;
                }
                
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);

                if (drops >= 2 || min < prevMax) {
                    return false;
                }
            }
            
            if (drops == 1 && nums[end - 1] > nums[start]) {
                return false;
            }
            
            prevMax = max;
        }
        return true;
    }
}