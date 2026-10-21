#include <string>
#include <algorithm>

class Solution {
public:
    int minOperations(std::string s) {
        // 1. Base Case: Check if the string is already completely sorted
        if (std::is_sorted(s.begin(), s.end())) {
            return 0;
        }
        
        int n = s.length();
        
        // 2. Impossible Case: Length < 3 means we can't take a proper substring
        if (n < 3) {
            return -1;
        }
        
        // Find the absolute minimum and maximum characters
        // min_element and max_element return iterators, so we dereference them
        char minChar = *std::min_element(s.begin(), s.end());
        char maxChar = *std::max_element(s.begin(), s.end());
        
        // 3. The Lucky Case (1 Operation)
        // One of the extreme ends is already where it belongs
        if (s[0] == minChar || s[n - 1] == maxChar) {
            return 1;
        }
        
        // 4. The Worst-Case Trap (3 Operations)
        // Max is stuck at the start AND Min is stuck at the end.
        // We use find_last_of and find_first_of to safely handle duplicates.
        if (s[0] == maxChar && s.find_last_of(maxChar) == 0 &&
            s[n - 1] == minChar && s.find_first_of(minChar) == n - 1) {
            return 3;
        }
        
        // 5. Standard Case (2 Operations)
        // We pass the baton from one wand to the other
        return 2;
    }
};