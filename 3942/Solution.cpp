#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        
        int incIdx = getShiftIdx(nums, true);
        int decIdx = getShiftIdx(nums, false);

        // Neither pattern is valid
        if (incIdx == -1 && decIdx == -1) return -1;
        
        // Perfectly increasing
        if (incIdx == 0) return 0;
        
        // Perfectly decreasing (requires 1 reverse)
        if (decIdx == 0) return 1;

        // Shifted increasing sequence
        // min(Left Rotate, Reverse -> Rotate -> Reverse)
        if (incIdx != -1) {
            return min(incIdx, 2 + n - incIdx);
        }
        
        // Shifted decreasing sequence
        // min(Rotate -> Reverse, Reverse -> Rotate)
        return min(decIdx, n - decIdx) + 1;
    }
    
private:
    int getShiftIdx(const vector<int>& nums, bool isInc) {
        int n = nums.size();
        int drops = 0;
        int dropIdx = 0;

        for (int i = 1; i < n; i++) {
            bool isDrop = isInc ? (nums[i] < nums[i - 1]) : (nums[i] > nums[i - 1]);
            
            if (isDrop) {
                drops++;
                dropIdx = i;
            }
            
            // Over 1 break means the sequence cannot be cyclically valid
            if (drops > 1) return -1;
        }
        
        if (drops == 0) return 0;
        
        // Validate wrap-around between first and last elements
        bool validWrapAround = isInc ? (nums[0] > nums[n - 1]) : (nums[0] < nums[n - 1]);
        
        return validWrapAround ? dropIdx : -1;
    }
};