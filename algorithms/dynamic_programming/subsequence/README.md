# Subsequence & Sequence Problems

## Overview
Subsequence problems involve finding patterns in sequences where elements don't need to be contiguous. A subsequence maintains the relative order of elements from the original sequence.

**Note**: Subsequence ≠ Subarray
- **Subsequence**: Elements in order but not necessarily contiguous [1,3,5] from [1,2,3,4,5]
- **Subarray**: Contiguous elements [2,3,4] from [1,2,3,4,5]

## Classic Problems

### 1. Longest Common Subsequence (LCS)
**Problem**: Find longest subsequence common to two strings

**Example**:
- s1 = "ABCDGH"
- s2 = "AEDFHR"
- LCS = "ADH" (length = 3)

**Recurrence**:
```python
if s1[i] == s2[j]:
    dp[i][j] = 1 + dp[i-1][j-1]
else:
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

**Complexity**: O(m × n) time, O(m × n) space

**Variations**:
- Longest common substring (must be contiguous)
- Shortest common supersequence
- Edit distance (Levenshtein distance)

### 2. Longest Increasing Subsequence (LIS)
**Problem**: Find longest strictly increasing subsequence

**Example**: [10,9,2,5,3,7,101,18] → [2,3,7,101] (length = 4)

**Approaches**:
- DP: O(n²) time, O(n) space
- Binary Search: O(n log n) time, O(n) space (optimal)

### 3. House Robber (Max Non-Adjacent Sum)
**Problem**: Rob houses to maximize money, can't rob adjacent houses

**Recurrence**:
```python
dp[i] = max(
    dp[i-1],           # skip house i
    dp[i-2] + nums[i]  # rob house i
)
```

**Complexity**: O(n) time, O(1) space (optimized)

**Pattern**: Choose or skip with constraints

### 4. Best Time to Buy/Sell Stock
**Problem**: Maximize profit from stock transactions

**Variants**:
- One transaction: O(n) with single pass
- Multiple transactions: Sum all positive differences
- K transactions: DP with states
- With cooldown: State machine DP

### 5. Maximum Subarray Sum (Kadane's)
**Problem**: Find contiguous subarray with largest sum

**Algorithm**:
```python
max_sum = current_sum = arr[0]
for num in arr[1:]:
    current_sum = max(num, current_sum + num)
    max_sum = max(max_sum, current_sum)
```

**Complexity**: O(n) time, O(1) space

## Common DP Patterns

### 1. Linear DP (1D)
```python
dp[i] = f(dp[i-1], dp[i-2], ...)
```
Examples: Fibonacci, House Robber, Climbing Stairs

### 2. String DP (2D)
```python
dp[i][j] = f(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
```
Examples: LCS, Edit Distance, Palindrome problems

### 3. State Machine DP
Track multiple states (buy/sell, hold/not hold)
```python
dp[i][state] = f(dp[i-1][other_states])
```
Examples: Stock problems, Paint House

### 4. Interval DP
```python
dp[i][j] = f(dp[i][k], dp[k+1][j]) for all k
```
Examples: Matrix chain multiplication, Burst balloons

## Problem-Solving Strategy

1. **Identify the state**: What changes as you solve subproblems?
2. **Define recurrence**: How does current state depend on previous states?
3. **Base cases**: What are the trivial cases?
4. **Order of computation**: Bottom-up or top-down?
5. **Optimize space**: Can you reduce dimensions?

## Common Optimization Techniques

### Space Optimization
- If dp[i] only depends on dp[i-1], use O(1) space
- If 2D DP, sometimes can reduce to 1D
- Rolling array technique

### Time Optimization
- Memoization to avoid recomputation
- Binary search for LIS-like problems
- Monotonic stack/queue for range problems

## Related Problems
- Edit distance (Levenshtein)
- Palindrome subsequence/substring
- Wildcard/regex matching
- Interleaving strings
- Distinct subsequences
- Russian doll envelopes
- Maximum profit problems

## Interview Tips
1. Draw the DP table for small examples
2. Identify if problem has optimal substructure
3. Check for overlapping subproblems
4. Consider both top-down and bottom-up approaches
5. Always optimize space if possible
6. Practice reconstructing the actual sequence, not just the length/value
