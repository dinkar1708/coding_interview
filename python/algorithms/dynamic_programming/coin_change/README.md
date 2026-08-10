# Coin Change Problems

## Overview
Coin change problems are a subset of the unbounded knapsack problem. Given coins of different denominations, find ways to make a target amount.

## Problem Variants

### 1. Minimum Coins (Optimization)
**Problem**: Find minimum number of coins to make amount N

**Approach**: Bottom-up DP
```python
def min_coins(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0

    for i in range(1, amount + 1):
        for coin in coins:
            if coin <= i:
                dp[i] = min(dp[i], dp[i - coin] + 1)

    return dp[amount] if dp[amount] != float('inf') else -1
```

- **Time Complexity**: O(amount × len(coins))
- **Space Complexity**: O(amount)
- **Type**: Unbounded knapsack optimization

### 2. Count Ways (Combinations)
**Problem**: Count number of ways to make amount N (order doesn't matter)

**Example**: For coins=[1,2,5] and amount=5
- Valid: [5], [2,2,1], [2,1,1,1], [1,1,1,1,1]
- Count = 4

```python
def coin_change_ways(coins, amount):
    dp = [0] * (amount + 1)
    dp[0] = 1

    for coin in coins:  # iterate coins first!
        for i in range(coin, amount + 1):
            dp[i] += dp[i - coin]

    return dp[amount]
```

- **Time Complexity**: O(amount × len(coins))
- **Space Complexity**: O(amount)

### 3. Count Permutations
**Problem**: Count number of ways where order matters

**Example**: For coins=[1,2] and amount=3
- Valid: [1,1,1], [1,2], [2,1]
- Count = 3 (note: [1,2] and [2,1] counted separately)

```python
def coin_change_permutations(coins, amount):
    dp = [0] * (amount + 1)
    dp[0] = 1

    for i in range(1, amount + 1):  # iterate amount first!
        for coin in coins:
            if coin <= i:
                dp[i] += dp[i - coin]

    return dp[amount]
```

## Key Differences

### Combinations vs Permutations

**Combinations** (order doesn't matter):
- Outer loop: **coins**
- Inner loop: amount
- Use when: "different ways to make change"

**Permutations** (order matters):
- Outer loop: **amount**
- Inner loop: coins
- Use when: "count sequences", "arrange coins"

## Common Patterns

### Pattern 1: Minimum/Maximum (Optimization)
```python
dp[i] = min/max(dp[i], dp[i-coin] + cost)
```

### Pattern 2: Count Ways
```python
dp[i] += dp[i-coin]
```

### Pattern 3: Possibility (True/False)
```python
dp[i] = dp[i] or dp[i-coin]
```

## Related Problems
- Coin change I (minimum coins)
- Coin change II (number of combinations)
- Perfect squares (min squares to sum to N)
- Climbing stairs with variable steps
- Decode ways
- Partition equal subset sum

## Interview Tips
1. **Clarify**: Combinations or permutations?
2. **Clarify**: Can you use each coin unlimited times?
3. **Edge cases**:
   - amount = 0 (usually return 1 or 0)
   - No solution exists
   - Negative amounts
4. **Optimization**: Can often reduce to O(amount) space
5. **Variation**: Sometimes limited coins of each type

## Complexity Analysis
For most coin change problems:
- **Time**: O(amount × number of coins)
- **Space**: O(amount) with optimization
- Unbounded knapsack variant
