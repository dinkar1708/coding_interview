# Knapsack Problems

## Overview
The knapsack problem is a classic optimization problem: given items with weights and values, select items to maximize value while staying within weight capacity.

## Problem Variants

### 1. 0/1 Knapsack
- Each item can be taken **0 or 1 time**
- Most common variant

**Recurrence**:
```
dp[i][w] = max(
    dp[i-1][w],              # don't take item i
    dp[i-1][w-weight[i]] + value[i]  # take item i
)
```

**Complexity**:
- **Time**: O(n × W) where W is capacity
- **Space**: O(n × W), can be optimized to O(W)

### 2. Unbounded Knapsack
- Each item can be taken **unlimited times**
- Example: Coin change (maximize coins)

**Recurrence**:
```
dp[i][w] = max(
    dp[i-1][w],              # don't take item i
    dp[i][w-weight[i]] + value[i]  # take item i again
)
```

### 3. Bounded Knapsack
- Each item can be taken up to **k times**
- Generalization of 0/1 knapsack

### 4. Fractional Knapsack
- Can take **fractions** of items
- Solved using **greedy algorithm** (not DP!)
- Sort by value/weight ratio

## Top-Down vs Bottom-Up

### Top-Down (Memoization)
```python
def knapsack(i, w, memo):
    if i == 0 or w == 0: return 0
    if (i, w) in memo: return memo[(i, w)]

    # don't take
    result = knapsack(i-1, w, memo)

    # take if possible
    if weight[i-1] <= w:
        result = max(result,
                    value[i-1] + knapsack(i-1, w-weight[i-1], memo))

    memo[(i, w)] = result
    return result
```

### Bottom-Up (Tabulation)
```python
def knapsack(weights, values, W):
    n = len(weights)
    dp = [[0] * (W + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        for w in range(W + 1):
            dp[i][w] = dp[i-1][w]  # don't take
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i][w],
                              dp[i-1][w-weights[i-1]] + values[i-1])

    return dp[n][W]
```

## Space Optimization
Can reduce from O(n × W) to O(W) by using 1D array:
```python
dp = [0] * (W + 1)
for i in range(n):
    for w in range(W, weights[i]-1, -1):  # iterate backwards!
        dp[w] = max(dp[w], dp[w-weights[i]] + values[i])
```

## Related Problems
- Subset sum (value = weight, check if sum equals W)
- Equal partition (split array into two equal sums)
- Target sum (assign +/- to reach target)
- Coin change (count ways or minimum coins)
- Rod cutting
- Partition problem

## Interview Tips
1. Identify if it's 0/1 or unbounded
2. Start with top-down if recursion is clearer
3. Optimize space if asked
4. Know how to reconstruct solution (which items taken)
5. Watch for integer overflow with large values
