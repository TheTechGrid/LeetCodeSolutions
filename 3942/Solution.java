class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Find the index of the "drop" for both possible valid patterns
        int incIdx = getShiftIdx(nums, true);
        int decIdx = getShiftIdx(nums, false);

        // If it belongs to neither pattern, it's impossible to sort
        if (incIdx == -1 && decIdx == -1) return -1;
        
        // Base cases: already perfectly sorted
        if (incIdx == 0) return 0;
        
        // Base case: perfectly decreasing (just needs 1 reverse)
        if (decIdx == 0) return 1;

        // If it's a shifted increasing array
        if (incIdx != -1) {
            // Option 1: Left rotate incIdx times
            // Option 2: Reverse, Left Rotate (n - incIdx) times, Reverse back
            return Math.min(incIdx, 2 + n - incIdx);
        }
        
        // If it's a shifted decreasing array
        // Option 1: Left rotate decIdx times, then Reverse (decIdx + 1)
        // Option 2: Reverse, then Left rotate the remaining distance (1 + n - decIdx)
        return Math.min(decIdx, n - decIdx) + 1;
    }
    
    private int getShiftIdx(int[] nums, boolean isInc) {
        int n = nums.length;
        int drops = 0;
        int dropIdx = 0;

        for (int i = 1; i < n; i++) {
            // A break in the pattern depends on what we are looking for
            boolean isDrop = isInc ? nums[i] < nums[i - 1] : nums[i] > nums[i - 1];
            
            if (isDrop) {
                drops++;
                dropIdx = i;
            }
            
            // Fail-fast: A valid cyclic sequence can have at most 1 break
            if (drops > 1) return -1;
        }
        
        // 0 drops means the array is already perfectly monotonic
        if (drops == 0) return 0;
        
        // 1 drop: Verify the cyclical boundary connects perfectly
        boolean validWrapAround = isInc ? nums[0] > nums[n - 1] : nums[0] < nums[n - 1];
        
        return validWrapAround ? dropIdx : -1;
    }
}