# Subarray Problems

## Overview
Subarray problems involve finding contiguous subsequences within an array that satisfy certain conditions. These are among the most common array problems in coding interviews.

## Key Concepts
- **Subarray**: Contiguous elements [i...j] where 0 ≤ i ≤ j < n
- **Maximum Subarray**: Finding subarray with largest sum/product
- **Subset Sum**: Finding if subset exists with given sum

## Famous Algorithms

### 1. Kadane's Algorithm
**Purpose**: Find maximum sum subarray
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- **Key Idea**: Keep running sum, reset when negative

```python
max_sum = current_sum = arr[0]
for num in arr[1:]:
    current_sum = max(num, current_sum + num)
    max_sum = max(max_sum, current_sum)
```

### 2. Prefix Sum Technique
**Purpose**: Answer range sum queries efficiently
- **Time Complexity**: O(n) preprocessing, O(1) per query
- **Space Complexity**: O(n)
- Build cumulative sum array

### 3. Sliding Window
**Purpose**: Fixed or variable size subarray problems
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- Maintain window and slide through array

### 4. Two Pointers
**Purpose**: Find subarrays meeting criteria
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)

## Common Problem Types

### Maximum/Minimum Sum
- Maximum subarray sum (Kadane's)
- Minimum subarray sum
- Maximum product subarray

### Counting Subarrays
- Count subarrays with sum = K
- Count subarrays with average ≥ threshold
- Count distinct elements in all subarrays

### Subset Problems
- Subset with sum equal to half of total
- Equal partition problem
- Subset sum equals target

### Special Subarrays
- Maximum double slice sum
- Subarray with equal 0s and 1s
- Longest subarray with sum ≤ K

## Problem-Solving Tips
1. **Try Kadane's** for max/min sum problems
2. **Use prefix sums** if multiple range queries needed
3. **Consider dynamic programming** for subset sum problems
4. **Sliding window** works great for fixed-size or monotonic problems
5. **Always check** if problem allows negative numbers

## Complexity Goals
- Most subarray problems can be solved in O(n) or O(n²)
- O(n³) brute force solutions are rarely acceptable
- Try to achieve O(1) or O(n) space complexity
