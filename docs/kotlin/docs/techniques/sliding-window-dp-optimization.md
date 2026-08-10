# Sliding Window DP Optimization

## What is Sliding Window in DP?

A **space optimization technique** that reduces DP space complexity from **O(n)** to **O(1)** by keeping only the necessary previous states instead of the entire DP array.

---

## Why Use It?

### Problem:
```kotlin
// Traditional Bottom-Up DP
val dp = IntArray(n)
dp[0] = base_case_1
dp[1] = base_case_2

for (i in 2 until n) {
    dp[i] = f(dp[i-1], dp[i-2])  // Only uses last 2 values!
}

return dp[n-1]
```

**Space**: O(n) - entire array
**Observation**: At each step, we only need `dp[i-1]` and `dp[i-2]`, not the entire array!

### Solution:
```kotlin
// Sliding Window Optimization
var prev2 = base_case_1  // dp[i-2]
var prev1 = base_case_2  // dp[i-1]

for (i in 2 until n) {
    val current = f(prev1, prev2)  // Calculate dp[i]
    prev2 = prev1  // Slide window forward
    prev1 = current
}

return prev1
```

**Space**: O(1) - only 2-3 variables

---

## When to Use It?

### ✅ Use Sliding Window When:

1. **DP recurrence only depends on fixed number of previous states**
   - Example: `dp[i] = dp[i-1] + dp[i-2]` (Fibonacci)
   - Example: `dp[i] = max(dp[i-1], dp[i-2] + A[i])` (House Robber)

2. **You don't need to reconstruct the solution path**
   - Only need final answer, not which choices were made

3. **States are processed in linear order**
   - Can't skip around or access arbitrary indices

4. **Space optimization is important**
   - Large input sizes where O(n) space is problematic

### ❌ Don't Use When:

1. **Need to access arbitrary previous states**
   - Example: `dp[i] = min(dp[j] + cost[j][i])` for all `j < i`

2. **Need to reconstruct the solution**
   - Must track which choices were made (need to backtrack through DP array)

3. **2D/Multi-dimensional DP with complex dependencies**
   - May only optimize one dimension, not worth complexity

---

## How Many Variables Needed?

| Dependency Pattern | Variables Needed | Example |
|-------------------|------------------|---------|
| `dp[i] = f(dp[i-1])` | **1 variable** | Climbing stairs (1 step) |
| `dp[i] = f(dp[i-1], dp[i-2])` | **2 variables** | Fibonacci, House Robber |
| `dp[i] = f(dp[i-1], dp[i-2], dp[i-3])` | **3 variables** | Tribonacci, Climbing stairs (1,2,3 steps) |
| `dp[i] = f(dp[i-k])` for fixed k | **k variables** | Depends on max lookback |

**Rule**: Number of variables = **Number of previous states needed in recurrence**

---

## Common Problem Types

### 1. Fibonacci-like Sequences

**Pattern**: `dp[i] = dp[i-1] + dp[i-2]`

**Examples**:
- Fibonacci Numbers
- Climbing Stairs (1 or 2 steps)
- Decode Ways

**Template**:
```kotlin
fun fibonacci(n: Int): Int {
    if (n <= 1) return n

    var prev2 = 0  // dp[0]
    var prev1 = 1  // dp[1]

    for (i in 2..n) {
        val current = prev1 + prev2
        prev2 = prev1
        prev1 = current
    }

    return prev1
}
```

---

### 2. House Robber Pattern

**Pattern**: `dp[i] = max(dp[i-1], dp[i-2] + value[i])`

**Examples**:
- House Robber (max sum, no adjacent)
- Delete and Earn
- Maximum Sum of Non-Adjacent Elements

**Template**:
```kotlin
fun houseRobber(nums: IntArray): Int {
    val n = nums.size
    if (n == 0) return 0
    if (n == 1) return nums[0]

    var prev2 = nums[0]           // dp[0]
    var prev1 = max(nums[0], nums[1])  // dp[1]

    for (i in 2 until n) {
        val current = max(prev1, prev2 + nums[i])
        prev2 = prev1
        prev1 = current
    }

    return prev1
}
```

---

### 3. Buy/Sell Stock Pattern (with state)

**Pattern**: Multiple states at each position

**Examples**:
- Best Time to Buy and Sell Stock (with cooldown)
- Best Time to Buy and Sell Stock (k transactions)

**Template**:
```kotlin
fun maxProfit(prices: IntArray): Int {
    var sold = 0       // State: just sold
    var held = Int.MIN_VALUE  // State: holding stock
    var rest = 0       // State: resting

    for (price in prices) {
        val prevSold = sold
        sold = held + price
        held = max(held, rest - price)
        rest = max(rest, prevSold)
    }

    return max(sold, rest)
}
```

---

### 4. Coin Change / Unbounded Knapsack

**Pattern**: `dp[i] = min/max(dp[i], dp[i-coin] + 1)`

**Note**: Usually can't optimize much since we need all `dp[0..amount]`

**Examples**:
- Coin Change
- Perfect Squares
- Unbounded Knapsack

---

### 5. Longest Increasing Subsequence (LIS)

**Pattern**: `dp[i] = max(dp[j] + 1)` for all `j < i` where `A[j] < A[i]`

**Note**: Hard to optimize with sliding window (need all previous values)

**Alternative**: Use Binary Search approach instead

---

### 6. Kadane's Algorithm (Maximum Subarray)

**Pattern**: `dp[i] = max(nums[i], dp[i-1] + nums[i])` - Space optimized to O(1)

**Use Case**: Find contiguous subarray with maximum sum

**Examples**:
- Maximum Subarray
- Maximum Sum Circular Subarray
- Best Time to Buy and Sell Stock

**Why it's Sliding Window DP**:
- **DP Recurrence**: `dp[i] = max(nums[i], dp[i-1] + nums[i])`
- **Space Optimization**: Only need `dp[i-1]`, not entire array
- **Sliding**: Keep one variable that "slides" forward

**Template**:
```kotlin
fun maxSubArray(nums: IntArray): Int {
    var maxSoFar = nums[0]       // Global maximum
    var maxEndingHere = nums[0]  // Maximum ending at current position (dp[i])

    for (i in 1 until nums.size) {
        // DP: max(take current alone, extend previous subarray)
        maxEndingHere = max(nums[i], maxEndingHere + nums[i])

        // Update global max
        maxSoFar = max(maxSoFar, maxEndingHere)
    }

    return maxSoFar
}
```

**Space Complexity Analysis**:
```
Traditional DP Array:
  val dp = IntArray(n)
  dp[i] = max(nums[i], dp[i-1] + nums[i])
  Space: O(n)

Sliding Window (Kadane's):
  var maxEndingHere = nums[0]  // Only keep dp[i-1]
  maxEndingHere = max(nums[i], maxEndingHere + nums[i])
  Space: O(1) ✓
```

**Step-by-Step Example**:
```
Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Traditional DP array:
dp[0] = -2
dp[1] = max(1, -2 + 1) = 1
dp[2] = max(-3, 1 + -3) = -2
dp[3] = max(4, -2 + 4) = 4
dp[4] = max(-1, 4 + -1) = 3
dp[5] = max(2, 3 + 2) = 5
dp[6] = max(1, 5 + 1) = 6
dp[7] = max(-5, 6 + -5) = 1
dp[8] = max(4, 1 + 4) = 5

Full DP: [-2, 1, -2, 4, 3, 5, 6, 1, 5]
Space: O(n)

Kadane's (Sliding Window):
i=0: maxEndingHere = -2, maxSoFar = -2
i=1: maxEndingHere = max(1, -2+1) = 1, maxSoFar = 1
i=2: maxEndingHere = max(-3, 1-3) = -2, maxSoFar = 1
i=3: maxEndingHere = max(4, -2+4) = 4, maxSoFar = 4
i=4: maxEndingHere = max(-1, 4-1) = 3, maxSoFar = 4
i=5: maxEndingHere = max(2, 3+2) = 5, maxSoFar = 5
i=6: maxEndingHere = max(1, 5+1) = 6, maxSoFar = 6 ← Answer
i=7: maxEndingHere = max(-5, 6-5) = 1, maxSoFar = 6
i=8: maxEndingHere = max(4, 1+4) = 5, maxSoFar = 6

Only keeping 2 variables (maxEndingHere, maxSoFar)!
Space: O(1) ✓
```

**Visual: Sliding Window**:
```
Array: [-2,  1, -3,  4, -1,  2,  1, -5,  4]
        ↓
     maxEndingHere = -2

        [-2,  1, -3,  4, -1,  2,  1, -5,  4]
              ↓
     maxEndingHere = 1 (start new)

        [-2,  1, -3,  4, -1,  2,  1, -5,  4]
                  ↓
     maxEndingHere = -2 (extend: 1-3)

        [-2,  1, -3,  4, -1,  2,  1, -5,  4]
                      ↓
     maxEndingHere = 4 (start new - better!)

        [-2,  1, -3,  4, -1,  2,  1, -5,  4]
                          └───────────┘
     maxEndingHere keeps extending: 4-1+2+1 = 6 (best!)
```

**Key Insight**:
- **maxEndingHere** = sliding variable representing `dp[i]`
- At each position: **start fresh** vs **extend previous subarray**
- Only need current state, not entire history

**Relation to Prefix Sum**:
```
Kadane's can also be viewed using prefix sum:

For subarray sum from i to j:
  sum(i, j) = prefixSum[j] - prefixSum[i-1]

To maximize:
  maxSum = max(prefixSum[j] - min(prefixSum[0..j-1]))

Both approaches work, but Kadane's is more elegant!
```

**Problems**:
- Maximum Subarray
- Maximum Sum Circular Subarray
- Maximum Product Subarray (similar idea)
- Best Time to Buy and Sell Stock
- Maximum Sum of Two Non-Overlapping Subarrays

---

## 7. String Pattern Matching (Different Context)

**Note**: Sliding window is also used in **string searching algorithms**, though in a different context than DP optimization.

### KMP (Knuth-Morris-Pratt) Algorithm

**Use Case**: Find pattern in text efficiently

**Pattern**: Uses a sliding window approach with "failure function" to avoid re-checking characters

**Examples**:
- Find Needle in Haystack
- Repeated Substring Pattern
- Shortest Palindrome

**Template**:
```kotlin
fun kmpSearch(text: String, pattern: String): Int {
    if (pattern.isEmpty()) return 0

    // Build failure function (LPS array)
    val lps = buildLPS(pattern)

    var i = 0  // Index for text
    var j = 0  // Index for pattern

    while (i < text.length) {
        if (text[i] == pattern[j]) {
            i++
            j++
        }

        if (j == pattern.length) {
            return i - j  // Pattern found at index (i - j)
        } else if (i < text.length && text[i] != pattern[j]) {
            if (j != 0) {
                j = lps[j - 1]  // Slide pattern using LPS
            } else {
                i++
            }
        }
    }

    return -1  // Pattern not found
}

// Build Longest Proper Prefix which is also Suffix array
fun buildLPS(pattern: String): IntArray {
    val lps = IntArray(pattern.length)
    var len = 0
    var i = 1

    while (i < pattern.length) {
        if (pattern[i] == pattern[len]) {
            len++
            lps[i] = len
            i++
        } else {
            if (len != 0) {
                len = lps[len - 1]
            } else {
                lps[i] = 0
                i++
            }
        }
    }

    return lps
}
```

**How KMP uses sliding window**:
```
Text:    "ABABDABACDABABCABAB"
Pattern: "ABABCABAB"

Traditional approach:
- Mismatch at position → restart from next position
- Time: O(m × n)

KMP approach:
- Mismatch at position → use LPS to skip unnecessary comparisons
- Slide pattern intelligently based on prefix-suffix info
- Time: O(m + n)

Example:
Text:    A B A B D A B A C D A B A B C A B A B
Pattern: A B A B C A B A B
         ↑ ↑ ↑ ↑ ✗
         Match until 'D' != 'C'

Instead of restarting from position 1:
Use LPS to know "AB" at start matches "AB" before mismatch
Slide pattern to continue from where partial match exists

         A B A B D A B A C D A B A B C A B A B
                 A B A B C A B A B
                 ↑ (continue from here)
```

**LPS (Longest Proper Prefix-Suffix) Array**:
```
Pattern: "ABABCABAB"
LPS:     [0, 0, 1, 2, 0, 1, 2, 3, 4]

Explanation:
Index 0: "A"        → LPS = 0 (no proper prefix/suffix)
Index 1: "AB"       → LPS = 0 (no match)
Index 2: "ABA"      → LPS = 1 ("A" matches)
Index 3: "ABAB"     → LPS = 2 ("AB" matches)
Index 4: "ABABC"    → LPS = 0 (no match)
Index 5: "ABABCA"   → LPS = 1 ("A" matches)
Index 6: "ABABCAB"  → LPS = 2 ("AB" matches)
Index 7: "ABABCABA" → LPS = 3 ("ABA" matches)
Index 8: "ABABCABAB"→ LPS = 4 ("ABAB" matches)
```

---

### Rabin-Karp Algorithm

**Use Case**: Find pattern using rolling hash (sliding window on hash value)

**Pattern**: Slide window of pattern length, compute hash in O(1)

**Template**:
```kotlin
fun rabinKarp(text: String, pattern: String): Int {
    val m = pattern.length
    val n = text.length

    if (m > n) return -1

    val base = 256
    val mod = 101

    var patternHash = 0
    var textHash = 0
    var h = 1

    // h = base^(m-1) % mod
    for (i in 0 until m - 1) {
        h = (h * base) % mod
    }

    // Calculate initial hash
    for (i in 0 until m) {
        patternHash = (base * patternHash + pattern[i].code) % mod
        textHash = (base * textHash + text[i].code) % mod
    }

    // Slide pattern over text
    for (i in 0..n - m) {
        // Check if hash matches
        if (patternHash == textHash) {
            // Verify character by character (avoid hash collision)
            if (text.substring(i, i + m) == pattern) {
                return i
            }
        }

        // Calculate hash for next window (slide)
        if (i < n - m) {
            textHash = (base * (textHash - text[i].code * h) +
                       text[i + m].code) % mod

            if (textHash < 0) {
                textHash += mod
            }
        }
    }

    return -1
}
```

**Rolling hash calculation**:
```
Text: "ABCDE", Pattern: "CDE"

Window 1: "ABC"
  hash = (A * 256² + B * 256 + C) % mod

Window 2: "BCD" (slide by 1)
  Remove 'A': hash - (A * 256²)
  Shift left: hash * 256
  Add 'D': hash + D

  New hash = ((hash - A * 256²) * 256 + D) % mod

  Time: O(1) for sliding!
```

---

### Z-Algorithm

**Use Case**: Find all occurrences of pattern in linear time

**Pattern**: Compute Z-array (length of longest substring starting from i matching prefix)

**Template**:
```kotlin
fun zAlgorithm(text: String, pattern: String): List<Int> {
    val combined = "$pattern$$$text"  // $ is separator
    val z = computeZArray(combined)

    val result = mutableListOf<Int>()
    for (i in pattern.length + 1 until combined.length) {
        if (z[i] == pattern.length) {
            result.add(i - pattern.length - 1)
        }
    }

    return result
}

fun computeZArray(s: String): IntArray {
    val n = s.length
    val z = IntArray(n)
    var left = 0
    var right = 0

    for (i in 1 until n) {
        if (i > right) {
            left = i
            right = i
            while (right < n && s[right] == s[right - left]) {
                right++
            }
            z[i] = right - left
            right--
        } else {
            val k = i - left
            if (z[k] < right - i + 1) {
                z[i] = z[k]
            } else {
                left = i
                while (right < n && s[right] == s[right - left]) {
                    right++
                }
                z[i] = right - left
                right--
            }
        }
    }

    return z
}
```

---

### Comparison: String Pattern Matching Algorithms

| Algorithm | Time Complexity | Space | Best Use Case |
|-----------|----------------|-------|---------------|
| **Brute Force** | O(m × n) | O(1) | Short patterns |
| **KMP** | O(m + n) | O(m) | Single pattern, avoid backtracking |
| **Rabin-Karp** | O(m + n) avg | O(1) | Multiple patterns (hash reuse) |
| **Z-Algorithm** | O(m + n) | O(m + n) | All occurrences, simple to code |
| **Boyer-Moore** | O(m + n) best | O(m) | Large alphabets, practical speed |
| **Aho-Corasick** | O(m + n + k) | O(mk) | Multiple patterns simultaneously |

Where:
- m = pattern length
- n = text length
- k = number of patterns

---

### When to Use Which String Algorithm?

**KMP**:
- ✅ Need guaranteed O(m + n) time
- ✅ Pattern has repeating prefixes
- ✅ Cannot afford backtracking in text

**Rabin-Karp**:
- ✅ Multiple pattern search
- ✅ 2D pattern matching (image search)
- ✅ Plagiarism detection

**Z-Algorithm**:
- ✅ Need all occurrences
- ✅ Simpler to implement than KMP
- ✅ Pattern preprocessing useful for other tasks

**Boyer-Moore**:
- ✅ Large alphabet (English text)
- ✅ Long patterns
- ✅ Practical performance matters

---

### String Matching Problems

**Beginner**:
1. Find the Index of First Occurrence (Needle in Haystack)
2. Repeated Substring Pattern
3. Valid Anagram

**Intermediate**:
4. Implement strStr() (KMP/Rabin-Karp)
5. Shortest Palindrome (KMP)
6. Longest Happy Prefix (KMP/Z-Algorithm)

**Advanced**:
7. Find All Anagrams in String (Rabin-Karp)
8. Minimum Window Substring
9. String Matching with Wildcards

---

## Sliding Window Implementation Patterns

### Pattern 1: Two Variables (most common)

```kotlin
// For: dp[i] = f(dp[i-1], dp[i-2])

var prev2 = initialize_dp_0
var prev1 = initialize_dp_1

for (i in 2 until n) {
    val current = f(prev1, prev2)
    prev2 = prev1  // Slide
    prev1 = current
}

return prev1
```

### Pattern 2: Using Temp Variable

```kotlin
// Alternative approach with temp

var dpLastToLast = initialize_dp_0
var dpLast = initialize_dp_1

for (i in 2 until n) {
    val temp = dpLast  // Save old value
    dpLast = f(dpLast, dpLastToLast)
    dpLastToLast = temp  // Update with old value
}

return dpLast
```

### Pattern 3: Three Variables

```kotlin
// For: dp[i] = f(dp[i-1], dp[i-2], dp[i-3])

var prev3 = initialize_dp_0
var prev2 = initialize_dp_1
var prev1 = initialize_dp_2

for (i in 3 until n) {
    val current = f(prev1, prev2, prev3)
    prev3 = prev2  // Slide
    prev2 = prev1
    prev1 = current
}

return prev1
```

---

## Step-by-Step Example: House Robber

### Traditional DP Array Approach:

```kotlin
Input: [5, 1, 2, 10]

val dp = IntArray(4)
dp[0] = 5
dp[1] = max(5, 1) = 5
dp[2] = max(dp[1], dp[0] + 2) = max(5, 7) = 7
dp[3] = max(dp[2], dp[1] + 10) = max(7, 15) = 15

Full DP array: [5, 5, 7, 15]
Space: O(n)
```

### Sliding Window Approach:

```kotlin
Input: [5, 1, 2, 10]

Initialize:
  prev2 = 5      (dp[0])
  prev1 = 5      (dp[1])

i=2: value=2
  current = max(prev1, prev2 + 2) = max(5, 7) = 7
  prev2 = 5  →  prev2 = 5
  prev1 = 5  →  prev1 = 7

  Window: [5, 7]

i=3: value=10
  current = max(prev1, prev2 + 10) = max(7, 15) = 15
  prev2 = 7  →  prev2 = 7
  prev1 = 7  →  prev1 = 15

  Window: [7, 15]

Only keeping 2 values instead of 4!
Space: O(1)
```

---

## Visual: Sliding Window Movement

```
Array: [5, 1, 2, 10]
Index:  0  1  2   3

Full DP:
  dp = [5, 5, 7, 15]
       ↑  ↑  ↑   ↑
       All values stored in memory

Sliding Window:

  Start:        [5, 5, -, -]
                 ↑  ↑
              prev2 prev1

  After i=2:    [5, 5, 7, -]
                    ↑  ↑
                 prev2 prev1

  After i=3:    [5, 5, 7, 15]
                       ↑   ↑
                    prev2 prev1

Only 2 values in memory at any time!
```

---

## Problems to Practice

### Beginner (2 variables):
1. ✅ Fibonacci Number
2. ✅ Climbing Stairs
3. ✅ House Robber
4. ✅ Min Cost Climbing Stairs

### Intermediate (2-3 variables):
5. ✅ House Robber II (circular)
6. ✅ Decode Ways
7. ✅ Delete and Earn
8. ✅ Tribonacci Number (3 variables)

### Advanced (state management):
9. ✅ Best Time to Buy/Sell Stock with Cooldown
10. ✅ Best Time to Buy/Sell Stock with Transaction Fee

---

## Comparison: Space Complexity

| Approach | Space | When to Use |
|----------|-------|-------------|
| **Top-Down (Recursion + Memo)** | O(n) memo + O(n) stack | Easy to code, need flexibility |
| **Bottom-Up (DP Array)** | O(n) | Need to reconstruct solution |
| **Sliding Window (Variables)** | O(1) | Only need final answer, optimize space |

---

## Common Mistakes

### ❌ Mistake 1: Wrong initialization order
```kotlin
// WRONG
var prev2 = nums[1]  // Should be nums[0]
var prev1 = nums[0]  // Should be nums[1]
```

### ❌ Mistake 2: Forgetting to slide window
```kotlin
// WRONG - prev2 never updates!
for (i in 2 until n) {
    val current = max(prev1, prev2 + nums[i])
    prev1 = current  // Missing: prev2 = prev1
}
```

### ❌ Mistake 3: Using wrong variable
```kotlin
// WRONG - should use prev2, not prev1
val current = max(prev1, prev1 + nums[i])  // BUG!
```

---

## Summary

### Key Takeaways:

1. **Sliding Window** = Keep only necessary previous DP states
2. **Works when**: DP depends on fixed number of recent states
3. **Saves space**: O(n) → O(1)
4. **Number of variables** = Number of dependencies in recurrence
5. **Most common**: 2 variables for `dp[i-1]` and `dp[i-2]`

### When to Apply:

✅ Linear DP with local dependencies
✅ Only need final answer
✅ Space is a concern

❌ Need solution reconstruction
❌ Random access to DP states
❌ Complex multi-dimensional DP

---

## Related Files in This Codebase

### Fibonacci Pattern:
- (Add your Fibonacci files here)

### House Robber Pattern:
- `IntMaximumPossibleStolenValueFromHousesMaxSubArSumBottomUp2.kt`

### Other Space-Optimized DP:
- (Add other optimized DP files here)

---

## Further Reading

- **DP Optimization Techniques**: Sliding Window, Space Optimization, Monotonic Queue
- **Related Patterns**: Kadane's Algorithm (also uses constant space for max subarray)
- **Advanced**: Matrix Exponentiation for Fibonacci (O(log n) time!)
