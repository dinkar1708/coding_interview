# Prefix Sum

## Overview
Prefix sum (also called cumulative sum) is a preprocessing technique that enables answering range sum queries in O(1) time after O(n) preprocessing.

## Basic Concept

**Prefix Sum Array**:
```
arr    = [3, 1, 4, 1, 5, 9, 2, 6]
prefix = [3, 4, 8, 9, 14, 23, 25, 31]
         prefix[i] = sum of arr[0...i]
```

**Range Sum Query**:
```python
sum(arr[L:R+1]) = prefix[R] - prefix[L-1]
```

## Implementation

### 1D Prefix Sum
```python
def build_prefix_sum(arr):
    prefix = [0] * (len(arr) + 1)
    for i in range(len(arr)):
        prefix[i + 1] = prefix[i] + arr[i]
    return prefix

def range_sum(prefix, left, right):
    return prefix[right + 1] - prefix[left]
```

### 2D Prefix Sum (Matrix)
```python
def build_2d_prefix(matrix):
    if not matrix:
        return []

    m, n = len(matrix), len(matrix[0])
    prefix = [[0] * (n + 1) for _ in range(m + 1)]

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            prefix[i][j] = (matrix[i-1][j-1] +
                           prefix[i-1][j] +
                           prefix[i][j-1] -
                           prefix[i-1][j-1])
    return prefix

def region_sum(prefix, r1, c1, r2, c2):
    return (prefix[r2+1][c2+1] -
            prefix[r1][c2+1] -
            prefix[r2+1][c1] +
            prefix[r1][c1])
```

## Common Applications

### 1. Subarray Sum Equals K
**Problem**: Count subarrays with sum equal to K

**Approach**: Hash map + prefix sum
```python
def subarray_sum(nums, k):
    count = 0
    prefix_sum = 0
    sum_count = {0: 1}  # prefix_sum: count

    for num in nums:
        prefix_sum += num
        # Check if prefix_sum - k exists
        if prefix_sum - k in sum_count:
            count += sum_count[prefix_sum - k]
        sum_count[prefix_sum] = sum_count.get(prefix_sum, 0) + 1

    return count
```

### 2. Minimum Average of Subarray
Find subarray with minimum average

**Key Insight**: Use prefix sums to compute averages efficiently

### 3. Range Query Problems
- Sum of elements in range [L, R]
- Count of divisible numbers in range
- Product of range (use log)

### 4. Genomic Range Query
Count occurrences of characters in substring

**Technique**: Multiple prefix sum arrays (one per character type)

### 5. Equilibrium Index
Find index where sum of left elements equals sum of right elements

```python
def equilibrium_index(arr):
    total = sum(arr)
    left_sum = 0

    for i, num in enumerate(arr):
        # right_sum = total - left_sum - num
        if left_sum == total - left_sum - num:
            return i
        left_sum += num

    return -1
```

## Variations

### Difference Array
Inverse of prefix sum - useful for range updates

```python
def range_add(arr, queries):
    """
    queries: [(left, right, val), ...]
    Add val to all elements in arr[left:right+1]
    """
    diff = [0] * (len(arr) + 1)

    for left, right, val in queries:
        diff[left] += val
        diff[right + 1] -= val

    # Apply differences
    current = 0
    for i in range(len(arr)):
        current += diff[i]
        arr[i] += current

    return arr
```

### Prefix XOR
For XOR operations instead of sum

### Prefix GCD/LCM
For GCD or LCM queries

### Prefix Modulo
Count elements divisible by K in range

## Problem Patterns

### Pattern 1: Direct Range Sum
Build prefix array, answer queries in O(1)

### Pattern 2: Subarray with Target Sum
Use hash map to store prefix sums
```python
if (prefix_sum - target) in map:
    found subarray
```

### Pattern 3: Count Frequency in Range
Multiple prefix arrays (one per element/category)

### Pattern 4: Running Balance
Prefix sum represents running balance/state

## Complexity Analysis

| Operation | Without Prefix | With Prefix |
|-----------|---------------|-------------|
| Build | - | O(n) |
| Range Sum Query | O(n) | O(1) |
| Q Range Queries | O(Q × n) | O(n + Q) |

**Space**: O(n) for prefix array

## Common Problems

1. **Range Sum Query** - Basic prefix sum
2. **Subarray Sum Equals K** - Prefix + hash map
3. **Continuous Subarray Sum** - Modulo + hash map
4. **Minimum Size Subarray Sum** - Prefix + binary search
5. **Max Sum of Rectangle** - 2D prefix sum + Kadane's
6. **Passing Cars** - Count pairs using prefix
7. **Genomic Range Query** - Multiple prefix arrays
8. **Count Divisors in Range** - Prefix count arrays

## Interview Tips

1. **Identify when to use**:
   - Multiple range queries
   - Subarray sum problems
   - Need O(1) query time

2. **Common variants**:
   - 1D vs 2D prefix sum
   - Prefix sum with hash map for target sum
   - Prefix XOR for XOR queries

3. **Watch out for**:
   - Off-by-one errors (use size n+1)
   - Integer overflow with large sums
   - Modulo arithmetic in some problems

4. **Optimization**:
   - For single query, don't need prefix array
   - Can often combine with hash map for O(n) solutions
   - 2D can be memory-intensive, consider sparse matrix

5. **Edge cases**:
   - Empty array
   - Single element
   - All negative numbers
   - Range out of bounds

## Related Concepts
- Fenwick Tree (Binary Indexed Tree) - for updates
- Segment Tree - for both updates and range queries
- Difference Array - for range updates
- Cumulative Frequency - statistics
