import math
from typing import List

class Solution:
    def sortableIntegers(self, nums: List[int]) -> int:
        n = len(nums)
        limit = math.isqrt(n)
        res = 0
        
        def is_valid(k: int) -> bool:
            blocks = n // k
            prev_max = float('-inf')
            
            for block in range(blocks):
                drops = 0
                min_val = float('inf')
                max_val = float('-inf')
                start = block * k
                end = start + k
                
                for i in range(start, end):
                    if i > start and nums[i] < nums[i - 1]:
                        drops += 1
                        
                    if nums[i] < min_val: min_val = nums[i]
                    if nums[i] > max_val: max_val = nums[i]
                        
                    if drops >= 2 or min_val < prev_max:
                        return False
                        
                # Cyclic boundary check
                if drops == 1 and nums[end - 1] > nums[start]:
                    return False
                    
                prev_max = max_val
                
            return True

        for k in range(1, limit + 1):
            if n % k != 0:
                continue
                
            other_div = n // k
            if is_valid(k):
                res += k
                
            if other_div != k and is_valid(other_div):
                res += other_div
                
        return res