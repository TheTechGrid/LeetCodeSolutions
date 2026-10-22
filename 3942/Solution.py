from typing import List

class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        
        def get_shift_idx(is_inc: bool) -> int:
            drops = 0
            drop_idx = 0
            
            for i in range(1, n):
                # Check for a break in the desired sequence pattern
                is_drop = (nums[i] < nums[i - 1]) if is_inc else (nums[i] > nums[i - 1])
                
                if is_drop:
                    drops += 1
                    drop_idx = i
                
                # Fail-fast: >1 break means it's not a valid shifted array
                if drops > 1:
                    return -1
            
            # Already monotonic
            if drops == 0:
                return 0
                
            # Check the cyclical wrap-around boundary
            valid_wrap = (nums[0] > nums[-1]) if is_inc else (nums[0] < nums[-1])
            
            return drop_idx if valid_wrap else -1

        inc_idx = get_shift_idx(True)
        dec_idx = get_shift_idx(False)

        # Impossible to sort
        if inc_idx == -1 and dec_idx == -1:
            return -1
            
        # Already perfectly sorted
        if inc_idx == 0:
            return 0
            
        # Perfectly decreasing, just 1 reverse needed
        if dec_idx == 0:
            return 1

        # Calculate min ops for a shifted increasing array
        if inc_idx != -1:
            return min(inc_idx, 2 + n - inc_idx)
            
        # Calculate min ops for a shifted decreasing array
        return min(dec_idx, n - dec_idx) + 1