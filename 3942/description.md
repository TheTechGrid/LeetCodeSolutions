# LeetCode 3942: Minimum Operations to Sort a Permutation

**Difficulty:** Medium  
**Time Complexity:** O(N)  
**Space Complexity:** O(1)  

## Problem Intuition
The allowed operations are **Left Rotate** and **Reverse**. Both of these operations strictly preserve the cyclical relative ordering of the elements. Therefore, it is mathematically impossible to sort the array unless it is already a shifted version of a sorted array, or a shifted version of a reversed array.

## Approach
1. **Identify the Sequence Family:** We traverse the array to count the number of "drops" (breaks in monotonicity). 
   - A valid cyclical array will have at most **1 drop**. 
   - If there is 1 drop, we must also verify the "wrap-around" boundary condition (e.g., for an increasing shift, the first element must be greater than the last element).
2. **Calculate Minimum Operations:**
   - **Increasing Family (`incIdx != -1`):** We can either left rotate until sorted (cost: `incIdx`), or simulate a right shift by reversing, left rotating the remaining distance, and reversing back (cost: `2 + n - incIdx`).
   - **Decreasing Family (`decIdx != -1`):** We can rotate left until it's perfectly decreasing and reverse once (cost: `decIdx + 1`), or reverse first and left rotate the remaining distance (cost: `1 + n - decIdx`). This simplifies to `min(decIdx, n - decIdx) + 1`.