class Solution {
    public int minOperations(String s) {
        // 1. Base Case: Check if the string is already completely sorted
        if (isSorted(s)) {
            return 0;
        }
        
        int n = s.length();
        
        // 2. Impossible Case: If length is 1 or 2 and it's not sorted, 
        // we can't sort n-1 elements without sorting the whole thing.
        if (n < 3) {
            return -1;
        }
        
        // Find the absolute minimum and maximum characters in the string
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        
        for (int i = 1; i < n; i++) {
            char ch = s.charAt(i);
            if (ch < minChar) minChar = ch;
            if (ch > maxChar) maxChar = ch;
        }
        
        // 3. The Lucky Case (1 Operation)
        // If the smallest char is already at the start, sort the suffix [1, n-1].
        // If the largest char is already at the end, sort the prefix [0, n-2].
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        // 4. The Worst-Case Trap (3 Operations)
        // The max character is stuck in the left blind spot (0) AND 
        // the min character is stuck in the right blind spot (n-1).
        // (Note: indexOf checks handle edge cases with duplicate characters)
        if (s.charAt(0) == maxChar && s.lastIndexOf(maxChar) == 0 && 
            s.charAt(n - 1) == minChar && s.indexOf(minChar) == n - 1) {
            return 3;
        }
        
        // 5. Standard Case (2 Operations)
        // For everything else, it takes exactly 2 overlapping passes.
        return 2;
    }
    
    // Helper method to verify if the string is sorted in O(N) time
    private boolean isSorted(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}