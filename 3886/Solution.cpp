#include <vector>
#include <cmath>
#include <algorithm>
#include <climits>

using namespace std;

class Solution {
private:
    bool isValid(const vector<int>& nums, int k) {
        int n = nums.size();
        int blocks = n / k;
        int prevMax = INT_MIN;
        
        for (int block = 0; block < blocks; ++block) {
            int drops = 0;
            int currentMin = INT_MAX;
            int currentMax = INT_MIN;
            int start = block * k;
            int end = start + k;
            
            for (int i = start; i < end; ++i) {
                if (i > start && nums[i] < nums[i - 1]) {
                    drops++;
                }
                
                currentMin = min(currentMin, nums[i]);
                currentMax = max(currentMax, nums[i]);

                if (drops >= 2 || currentMin < prevMax) {
                    return false;
                }
            }
            
            if (drops == 1 && nums[end - 1] > nums[start]) {
                return false;
            }
            
            prevMax = currentMax;
        }
        return true;
    }

public:
    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();
        int N = sqrt(n);
        int res = 0;
        
        for (int k = 1; k <= N; ++k) {
            if (n % k != 0) continue;
            
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
};