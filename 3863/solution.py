class Solution:
    def minOperations(self, s: str) -> int:
        # 1. Base Case: Check if the string is already sorted
        # In Python, we can do this by converting to a list, sorting, and joining,
        # or checking pairwise characters. Pairwise is faster O(N).
        if all(s[i] <= s[i+1] for i in range(len(s) - 1)):
            return 0
            
        n = len(s)
        
        # 2. Impossible Case
        if n < 3:
            return -1
            
        # Find the absolute minimum and maximum characters
        min_char = min(s)
        max_char = max(s)
        
        # 3. The Lucky Case (1 Operation)
        # Bottlenecks are naturally resolved on at least one side
        if s[0] == min_char or s[-1] == max_char:
            return 1
            
        # 4. The Worst-Case Trap (3 Operations)
        # Max is trapped at the start, Min is trapped at the end.
        # rfind() and find() ensure we account for duplicate character edge cases.
        if (s[0] == max_char and s.rfind(max_char) == 0 and 
            s[-1] == min_char and s.find(min_char) == n - 1):
            return 3
            
        # 5. Standard Case (2 Operations)
        return 2