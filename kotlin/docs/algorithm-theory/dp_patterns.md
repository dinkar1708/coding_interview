# Dynamic Programming Patterns

## What is Dynamic Programming?

Dynamic Programming (DP) is an optimization technique that solves complex problems by breaking them down into simpler subproblems and storing their solutions to avoid redundant calculations.

**Key Characteristics:**
1. **Optimal Substructure** - Optimal solution contains optimal solutions to subproblems
2. **Overlapping Subproblems** - Same subproblems are solved multiple times

## Two Approaches

### 1. Top-Down (Memoization)
- Start from main problem
- Recursively break down
- Store results in cache (HashMap/Array)

### 2. Bottom-Up (Tabulation)
- Start from base cases
- Build up to final solution
- Use array/table to store results

## Common DP Patterns

## 1. Fibonacci Pattern

**Characteristics:**
- Current state depends on previous states
- Linear dependency

**Template:**
```kotlin
// Top-Down
fun fib(n: Int, memo: MutableMap<Int, Long> = mutableMapOf()): Long {
    if (n <= 1) return n.toLong()
    if (n in memo) return memo[n]!!

    memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
    return memo[n]!!
}

// Bottom-Up
fun fib(n: Int): Long {
    if (n <= 1) return n.toLong()

    val dp = LongArray(n + 1)
    dp[0] = 0
    dp[1] = 1

    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    return dp[n]
}

// Space Optimized
fun fib(n: Int): Long {
    if (n <= 1) return n.toLong()

    var prev = 0L
    var curr = 1L

    repeat(n - 1) {
        val next = prev + curr
        prev = curr
        curr = next
    }

    return curr
}
```

**Examples:**
- Climbing Stairs
- House Robber
- Decode Ways
- Min Cost Climbing Stairs

## 2. 0/1 Knapsack Pattern

**Characteristics:**
- Include/exclude decision for each item
- Constrained optimization
- Cannot use item more than once

**Template:**
```kotlin
fun knapsack(weights: IntArray, values: IntArray, capacity: Int): Int {
    val n = weights.size
    val dp = Array(n + 1) { IntArray(capacity + 1) }

    for (i in 1..n) {
        for (w in 1..capacity) {
            if (weights[i - 1] <= w) {
                dp[i][w] = maxOf(
                    dp[i - 1][w],  // Don't take
                    dp[i - 1][w - weights[i - 1]] + values[i - 1]  // Take
                )
            } else {
                dp[i][w] = dp[i - 1][w]
            }
        }
    }

    return dp[n][capacity]
}

// Space Optimized
fun knapsack(weights: IntArray, values: IntArray, capacity: Int): Int {
    val dp = IntArray(capacity + 1)

    for (i in weights.indices) {
        for (w in capacity downTo weights[i]) {
            dp[w] = maxOf(dp[w], dp[w - weights[i]] + values[i])
        }
    }

    return dp[capacity]
}
```

**Examples:**
- Subset Sum
- Partition Equal Subset Sum
- Target Sum
- Last Stone Weight II

## 3. Unbounded Knapsack Pattern

**Characteristics:**
- Can use items unlimited times
- Similar to 0/1 but different iteration order

**Template:**
```kotlin
fun unboundedKnapsack(weights: IntArray, values: IntArray, capacity: Int): Int {
    val dp = IntArray(capacity + 1)

    for (i in weights.indices) {
        for (w in weights[i]..capacity) {  // Forward iteration
            dp[w] = maxOf(dp[w], dp[w - weights[i]] + values[i])
        }
    }

    return dp[capacity]
}
```

**Examples:**
- Coin Change
- Coin Change 2
- Rod Cutting
- Minimum Cost For Tickets

## 4. Longest Common Subsequence (LCS)

**Characteristics:**
- Compare two sequences
- Find optimal alignment

**Template:**
```kotlin
fun lcs(s1: String, s2: String): Int {
    val m = s1.length
    val n = s2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    for (i in 1..m) {
        for (j in 1..n) {
            if (s1[i - 1] == s2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[m][n]
}

// Space Optimized
fun lcs(s1: String, s2: String): Int {
    val n = s2.length
    var prev = IntArray(n + 1)
    var curr = IntArray(n + 1)

    for (i in 1..s1.length) {
        for (j in 1..n) {
            if (s1[i - 1] == s2[j - 1]) {
                curr[j] = prev[j - 1] + 1
            } else {
                curr[j] = maxOf(prev[j], curr[j - 1])
            }
        }
        prev = curr.also { curr = prev }
    }

    return prev[n]
}
```

**Examples:**
- Longest Common Subsequence
- Edit Distance
- Distinct Subsequences
- Shortest Common Supersequence

## 5. Longest Increasing Subsequence (LIS)

**Characteristics:**
- Find increasing pattern
- Each element compared with all previous

**Template:**
```kotlin
// O(n²) DP solution
fun lengthOfLIS(nums: IntArray): Int {
    val n = nums.size
    val dp = IntArray(n) { 1 }
    var maxLen = 1

    for (i in 1 until n) {
        for (j in 0 until i) {
            if (nums[i] > nums[j]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
        maxLen = maxOf(maxLen, dp[i])
    }

    return maxLen
}

// O(n log n) Binary Search + DP
fun lengthOfLIS(nums: IntArray): Int {
    val tails = mutableListOf<Int>()

    for (num in nums) {
        val pos = tails.binarySearch(num)
        val insertPos = if (pos < 0) -(pos + 1) else pos

        if (insertPos == tails.size) {
            tails.add(num)
        } else {
            tails[insertPos] = num
        }
    }

    return tails.size
}
```

**Examples:**
- Longest Increasing Subsequence
- Russian Doll Envelopes
- Maximum Length of Pair Chain
- Number of LIS

## 6. Palindrome Pattern

**Characteristics:**
- String reads same forward and backward
- Expand from center or compare ends

**Template:**
```kotlin
fun longestPalindromicSubsequence(s: String): Int {
    val n = s.length
    val dp = Array(n) { IntArray(n) }

    // Base case: single character
    for (i in 0 until n) {
        dp[i][i] = 1
    }

    // Fill for substrings of length 2 to n
    for (len in 2..n) {
        for (i in 0..n - len) {
            val j = i + len - 1
            if (s[i] == s[j]) {
                dp[i][j] = dp[i + 1][j - 1] + 2
            } else {
                dp[i][j] = maxOf(dp[i + 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[0][n - 1]
}
```

**Examples:**
- Longest Palindromic Subsequence
- Longest Palindromic Substring
- Palindromic Substrings
- Minimum Insertion Steps

## 7. Matrix Chain Multiplication (MCM)

**Characteristics:**
- Partition problem
- Try all possible splits
- Combine results

**Template:**
```kotlin
fun mcm(arr: IntArray): Int {
    val n = arr.size
    val dp = Array(n) { IntArray(n) }

    for (len in 2 until n) {
        for (i in 1 until n - len + 1) {
            val j = i + len - 1
            dp[i][j] = Int.MAX_VALUE

            for (k in i until j) {
                val cost = dp[i][k] + dp[k + 1][j] +
                          arr[i - 1] * arr[k] * arr[j]
                dp[i][j] = minOf(dp[i][j], cost)
            }
        }
    }

    return dp[1][n - 1]
}
```

**Examples:**
- Matrix Chain Multiplication
- Burst Balloons
- Palindrome Partitioning II
- Scramble String

## 8. DP on Grids

**Characteristics:**
- 2D grid/matrix
- Move from one cell to another
- Path-based problems

**Template:**
```kotlin
fun uniquePaths(m: Int, n: Int): Int {
    val dp = Array(m) { IntArray(n) { 1 } }

    for (i in 1 until m) {
        for (j in 1 until n) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        }
    }

    return dp[m - 1][n - 1]
}

// Space Optimized
fun uniquePaths(m: Int, n: Int): Int {
    val dp = IntArray(n) { 1 }

    for (i in 1 until m) {
        for (j in 1 until n) {
            dp[j] += dp[j - 1]
        }
    }

    return dp[n - 1]
}
```

**Examples:**
- Unique Paths
- Minimum Path Sum
- Dungeon Game
- Cherry Pickup

## 9. State Machine DP

**Characteristics:**
- Multiple states at each step
- Transition between states
- Track best for each state

**Template:**
```kotlin
// Stock Buy/Sell problem
fun maxProfit(prices: IntArray): Int {
    var hold = -prices[0]
    var sold = 0

    for (i in 1 until prices.size) {
        val newHold = maxOf(hold, sold - prices[i])
        val newSold = maxOf(sold, hold + prices[i])
        hold = newHold
        sold = newSold
    }

    return sold
}
```

**Examples:**
- Best Time to Buy and Sell Stock series
- House Robber II (circular)
- Delete and Earn

## DP Problem Recognition Guide

| Clue in Problem | Likely Pattern |
|-----------------|----------------|
| "Maximum/Minimum" | Optimization DP |
| "Count ways" | Combinatorial DP |
| "Subsequence" | LCS or LIS |
| "Two strings" | LCS pattern |
| "Subarray sum" | Kadane's or Prefix Sum |
| "Target sum with items" | Knapsack |
| "Can partition/subset" | 0/1 Knapsack |
| "Coin change" | Unbounded Knapsack |
| "Palindrome" | Palindrome DP |
| "Grid paths" | Grid DP |
| "Buy/Sell stocks" | State Machine |

## Steps to Solve DP Problems

1. **Identify** if it's a DP problem (optimal substructure + overlapping subproblems)
2. **Define** the state (what does dp[i] represent?)
3. **Find** the recurrence relation
4. **Determine** base cases
5. **Decide** top-down vs bottom-up
6. **Optimize** space if possible

## Common Optimizations

### Space Optimization
```kotlin
// From 2D to 1D
// Before: dp[i][j] = dp[i-1][j] + dp[i][j-1]
// After:  dp[j] = dp[j] + dp[j-1]

// From 1D to variables
// When only last 2 states needed (Fibonacci pattern)
```

### Time Optimization
- Eliminate redundant calculations
- Use binary search where applicable (LIS)
- Monotonic deque/stack patterns

## Interview Tips

1. **Start with brute force** - understand the problem first
2. **Identify overlapping subproblems** - spot repeated calculations
3. **Draw the recursion tree** - visualize the problem
4. **Start with memoization** - easier to code
5. **Optimize to tabulation** - better performance
6. **Optimize space** - impress the interviewer
7. **Explain your approach** - very important!

## Time Complexity Patterns

- **1D DP**: O(n) or O(n²)
- **2D DP**: O(n²) or O(mn)
- **With binary search**: O(n log n)
- **MCM pattern**: O(n³)

Remember: **If you can solve it recursively, you can solve it with DP!**
