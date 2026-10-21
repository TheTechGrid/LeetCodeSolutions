# 3863. Minimum Operations to Sort a String

## Problem Intuition
You are given a string and can perform an operation that sorts either the prefix of length `n-1` or the suffix of length `n-1`. What is the minimum number of operations required to sort the entire string?

This is a classic "Ad-hoc" problem. The key to solving it is tracking the "bottlenecks": the absolute maximum and minimum characters. Because our operation can only sort $n-1$ characters at a time, it has "blind spots" at index `0` and index `n-1`.

### The 4 Cases:
1. **0 Operations:** The string is already perfectly sorted.
2. **-1 (Impossible):** The length is less than 3, and it's not already sorted. We can't pick a proper substring of length 2 to sort without touching the whole string.
3. **1 Operation:** Either the absolute minimum character is already safe at index `0`, or the absolute maximum character is already safe at index `n-1`. If one of these is true, we just sort the *other* side, and the whole string is fixed.
4. **3 Operations (The Trap):** The absolute maximum is stuck at index `0` **AND** the absolute minimum is stuck at index `n-1`. They are trapping each other in the blind spots. It takes 1 move just to "unstick" one of them, bringing us to a 2-move scenario (1 + 2 = 3). 
5. **2 Operations:** Every other scenario! It takes one move to bring a character to the "doorstep", and a second move to push it through the door.

---

### Complexity
- **Time Complexity:** $\mathcal{O}(N)$ - We only need to iterate through the string a few times to check if it's sorted and to find the min/max characters.
- **Space Complexity:** $\mathcal{O}(1)$ - We only store a few variables for the characters, requiring no extra scaling memory.