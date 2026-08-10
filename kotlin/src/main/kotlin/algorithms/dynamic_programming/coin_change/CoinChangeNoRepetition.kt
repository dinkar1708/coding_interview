package algorithms.dynamic_programming.coin_change

/**
 * PROBLEM: Coin Change - NO REPETITION (Each coin used at most ONCE)
 *
 * Given coins, count ways to make sum using each coin AT MOST ONCE.
 * This is 0/1 Knapsack variant.
 *
 * INPUT: coins = [1, 2, 3], sum = 4
 * OUTPUT: 1 way
 *
 * EXPLANATION:
 * You have: ONE coin of value 1, ONE coin of value 2, ONE coin of value 3
 *
 * Try all combinations for sum=4:
 * - [1, 3] = 1 + 3 = 4  ✓ VALID (only way!)
 * - [2, 2] = 2 + 2 = 4  ✗ INVALID (only have ONE coin of value 2)
 * - [1, 1, 2] = 4  ✗ INVALID (only have ONE coin of value 1)
 * - [1, 1, 1, 1] = 4  ✗ INVALID (only have ONE coin of value 1)
 * - [4] = 4  ✗ INVALID (no coin of value 4)
 *
 * Answer: 1 way [1,3]
 *
 * DIFFERENCE from regular Coin Change WITH repetition:
 *
 * Regular Coin Change (UNLIMITED coins):
 *   coins = [1, 2, 5], sum = 5
 *   Answer: 4 ways
 *   Valid: [1,1,1,1,1], [1,1,1,2], [1,2,2], [5]
 *   (can reuse coin 1 multiple times)
 *
 * No Repetition (ONE coin each):
 *   coins = [1, 2, 5], sum = 5
 *   Answer: 1 way
 *   Valid: [5] only
 *   Invalid: [1,1,1,1,1] (only have ONE coin of value 1!)
 *
 * KEY: After including a coin, move to next coin (cannot reuse)
 *
 * APPROACHES:
 * 1. Recursive: O(2^n) time, O(n) space
 * 2. Memoization: O(n × sum) time, O(n × sum) space
 */

fun main() {
    println("COIN CHANGE - NO REPETITION (Each coin used at most ONCE)")
    println()

    println("APPROACH 1: RECURSIVE")
    println()

    // Test 1: Normal case
    println("=== Test 1: coins=[1,2,3], sum=4 ===")
    val result1 = coinChangeNoRepRecursive(intArrayOf(1, 2, 3), 3, 4)
    println("Result: $result1")  // Result: 1
    println("Expected: 1 ([1,3])")
    println()

    // Test 2: Sum is 0
    println("=== Test 2: coins=[1,2,3], sum=0 ===")
    val result2 = coinChangeNoRepRecursive(intArrayOf(1, 2, 3), 3, 0)
    println("Result: $result2")  // Result: 1
    println("Expected: 1 (empty set)")
    println()

    // Test 3: Single coin exact match
    println("=== Test 3: coins=[5], sum=5 ===")
    val result3 = coinChangeNoRepRecursive(intArrayOf(5), 1, 5)
    println("Result: $result3")  // Result: 1
    println("Expected: 1 ([5])")
    println()

    // Test 4: Cannot make sum
    println("=== Test 4: coins=[2,4], sum=3 ===")
    val result4 = coinChangeNoRepRecursive(intArrayOf(2, 4), 2, 3)
    println("Result: $result4")  // Result: 0
    println("Expected: 0 (cannot make 3)")
    println()

    // Test 5: Multiple ways
    println("=== Test 5: coins=[1,2,3,4], sum=5 ===")
    val result5 = coinChangeNoRepRecursive(intArrayOf(1, 2, 3, 4), 4, 5)
    println("Result: $result5")  // Result: 3
    println("Expected: 3 ([1,4], [2,3], [5]) - wait, no 5 coin")
    println("Actual valid: [1,4], [2,3] = 2 ways")
    println()

    println("------------------------------------------------------------")
    println("APPROACH 2: MEMOIZATION")
    println()

    // Test 1: Normal case - Memoization
    println("=== Test 1: coins=[1,2,3], sum=4 (Memoization) ===")
    val result6 = coinChangeNoRepMemoization(intArrayOf(1, 2, 3), 4)
    println("Result: $result6")  // Result: 1
    println("Expected: 1 ([1,3])")
    println()

    // Test 2: Sum is 0
    println("=== Test 2: coins=[1,2,3], sum=0 (Memoization) ===")
    val result7 = coinChangeNoRepMemoization(intArrayOf(1, 2, 3), 0)
    println("Result: $result7")  // Result: 1
    println("Expected: 1 (empty set)")
    println()

    // Test 3: Multiple ways
    println("=== Test 3: coins=[1,2,3,4], sum=5 (Memoization) ===")
    val result8 = coinChangeNoRepMemoization(intArrayOf(1, 2, 3, 4), 5)
    println("Result: $result8")  // Result: 2
    println("Expected: 2 ([1,4], [2,3])")
    println()

    // Test 4: Larger example
    println("=== Test 4: coins=[1,2,3,4,5], sum=10 (Memoization) ===")
    val result9 = coinChangeNoRepMemoization(intArrayOf(1, 2, 3, 4, 5), 10)
    println("Result: $result9")
    println("Valid combinations: [1,2,3,4], [1,4,5], [2,3,5], [1,3,6]")
    println()

    println("------------------------------------------------------------")
    println("COMPARISON: With Repetition vs No Repetition")
    println()

    val coins = intArrayOf(1, 2, 5)
    val sum = 5

    // With repetition (from CoinChange.kt)
    val withRep = coinChangeWithRepetition(coins, coins.size, sum)

    // Without repetition
    val noRep = coinChangeNoRepRecursive(coins, coins.size, sum)

    println("Coins: ${coins.contentToString()}, Sum: $sum")
    println()
    println("With Repetition (unlimited use):")
    println("  Ways: $withRep")
    println("  Examples: [1,1,1,1,1], [1,1,1,2], [1,2,2], [5]")
    println()
    println("No Repetition (each coin once):")
    println("  Ways: $noRep")
    println("  Examples: [5] only")
    println("  Cannot use [1,1,1,1,1] - only have ONE coin of value 1")
}

// APPROACH 1: RECURSIVE (No Repetition)
// TIME COMPLEXITY: O(2^n) - each coin has 2 choices (include/exclude)
// SPACE COMPLEXITY: O(n) - recursion stack depth
//
// Recursion Tree: coins=[1,2,3], sum=4
//
// STEP 1: Tree goes DOWN (recursive calls)
//
//                     (n=3, sum=4)
//                     "Make 4 using [1,2,3] once"
//                            |
//              +-------------+-------------+
//              |                           |
//        EXCLUDE [2]=3                INCLUDE [2]=3
//        (skip coin 3)                (use 3 ONCE, move next)
//              |                           |
//              v                           v
//         (n=2, sum=4)                (n=2, sum=1)
//         "Make 4 using [1,2]"        "Make 1 using [1,2]"
//              |                           |
//        +-----+-----+               +-----+-----+
//        |           |               |           |
//    EXCLUDE [1]=2 INCLUDE [1]=2  EXCLUDE [1]=2 INCLUDE [1]=2
//        |           |               |           |
//        v           v               v           v
//    (n=1, sum=4) (n=1, sum=2)  (n=1, sum=1) (n=1, sum=-1)
//    "Make 4      "Make 2       "Make 1      return 0
//    using [1]"   using [1]"    using [1]"
//        |           |               |
//    +---+---+   +---+---+       +---+---+
//    |       |   |       |       |       |
// EXCL  INCL EXCL  INCL       EXCL  INCL
//    |       |   |       |       |       |
//    v       v   v       v       v       v
// (n=0,   (n=0, (n=0,  (n=0,   (n=0,  (n=0,
// sum=4)  sum=3) sum=2) sum=1)  sum=1) sum=0)
// ret 0   ret 0  ret 0  ret 0   ret 0  ret 1 ✓ BASE CASE!
//
// STEP 2: Values RETURN UP (propagate back to root)
//
// Level 3 (bottom - base cases):
//   (n=0, sum=4) → 0
//   (n=0, sum=3) → 0
//   (n=0, sum=2) → 0
//   (n=0, sum=1) → 0
//   (n=0, sum=1) → 0
//   (n=0, sum=0) → 1 ✓ (found solution!)
//   (n=1, sum=-1) → 0
//
// Level 2 (add EXCLUDE + INCLUDE):
//   (n=1, sum=4) = 0 + 0 = 0
//   (n=1, sum=2) = 0 + 0 = 0
//   (n=1, sum=1) = 0 + 1 = 1 ✓
//
// Level 1 (add EXCLUDE + INCLUDE):
//   (n=2, sum=4) = 0 + 0 = 0
//   (n=2, sum=1) = 1 + 0 = 1 ✓
//
// Level 0 (root - final answer):
//   (n=3, sum=4) = 0 + 1 = 1 ✓ ANSWER!
//
// Answer: 1 way [1,3]
//
// Key: After INCLUDING a coin, move to n-1 (cannot reuse)
//
private fun coinChangeNoRepRecursive(coins: IntArray, n: Int, sum: Int): Int {
    // Base case: sum is 0, found one solution
    if (sum == 0) {
        return 1
    }

    // Base case: sum is negative, no solution
    if (sum < 0) {
        return 0
    }

    // Base case: no coins left but sum > 0, no solution
    if (n <= 0) {
        return 0
    }

    // Two choices for each coin:
    // 1) EXCLUDE coins[n-1] - skip this coin, move to next
    val exclude = coinChangeNoRepRecursive(coins, n - 1, sum)

    // 2) INCLUDE coins[n-1] - use this coin ONCE, move to next
    //    KEY: After including, we go to n-1 (not n like in repetition allowed)
    val include = coinChangeNoRepRecursive(coins, n - 1, sum - coins[n - 1])

    return exclude + include
}

// APPROACH 2: MEMOIZATION (No Repetition)
// TIME COMPLEXITY: O(n × sum) - each state computed once
// SPACE COMPLEXITY: O(n × sum) - memo table + O(n) recursion stack
private fun coinChangeNoRepMemoization(coins: IntArray, sum: Int): Int {
    if (coins.isEmpty()) {
        return if (sum == 0) 1 else 0
    }

    // memo[coinIndex][remainingSum]
    val memo = Array(coins.size + 1) { IntArray(sum + 1) { -1 } }

    return coinChangeNoRepMemo(coins, coins.size, sum, memo)
}

private fun coinChangeNoRepMemo(coins: IntArray, n: Int, sum: Int, memo: Array<IntArray>): Int {
    // Base case: sum is 0, found one solution
    if (sum == 0) {
        return 1
    }

    // Base case: sum is negative, no solution
    if (sum < 0) {
        return 0
    }

    // Base case: no coins left but sum > 0, no solution
    if (n <= 0) {
        return 0
    }

    // Check memo
    if (memo[n][sum] != -1) {
        return memo[n][sum]
    }

    // Two choices:
    // 1) EXCLUDE coins[n-1] - skip this coin
    val exclude = coinChangeNoRepMemo(coins, n - 1, sum, memo)

    // 2) INCLUDE coins[n-1] - use this coin ONCE
    //    KEY: After including, we move to n-1 (cannot reuse)
    val include = coinChangeNoRepMemo(coins, n - 1, sum - coins[n - 1], memo)

    // Store and return
    memo[n][sum] = exclude + include
    return memo[n][sum]
}

// Helper: Regular coin change WITH repetition (for comparison)
private fun coinChangeWithRepetition(coins: IntArray, n: Int, sum: Int): Int {
    if (sum == 0) return 1
    if (sum < 0) return 0
    if (n <= 0 && sum >= 1) return 0

    // KEY: After including, stay at n (can reuse same coin)
    return coinChangeWithRepetition(coins, n - 1, sum) +
           coinChangeWithRepetition(coins, n, sum - coins[n - 1])
}

/*
================================================================================
SECOND EXAMPLE - 3 WAYS (Multiple Paths)
================================================================================

INPUT: coins = [1, 2, 3, 4, 5], sum = 5
QUESTION: How many ways to make sum 5 (each coin used at most once)?

Valid combinations:
  1. [5] = 5 ✓
  2. [1, 4] = 5 ✓
  3. [2, 3] = 5 ✓

ANSWER: 3 ways

STEP 1: Tree Goes DOWN (recursive calls)

                                    (n=5, sum=5)
                                    "Make 5 using [1,2,3,4,5]"
                                            |
                        +-------------------+-------------------+
                        |                                       |
                  EXCLUDE [4]=5                           INCLUDE [4]=5
                  (skip coin 5)                           (use 5, move to n-1)
                        |                                       |
                        v                                       v
                   (n=4, sum=5)                            (n=4, sum=0)
               "Make 5 using [1,2,3,4]"                    return 1 ✓ Path 1: [5]
                        |
            +-----------+-----------+
            |                       |
      EXCLUDE [3]=4            INCLUDE [3]=4
      (skip coin 4)            (use 4, move to n-1)
            |                       |
            v                       v
       (n=3, sum=5)            (n=3, sum=1)
   "Make 5 using [1,2,3]"  "Make 1 using [1,2,3]"
            |                       |
      +-----+-----+            +----+----+
      |           |            |         |
  EXCLUDE     INCLUDE      EXCLUDE   INCLUDE
  [2]=3       [2]=3        [2]=3     [2]=3
      |           |            |         |
      v           v            v         v
  (n=2, sum=5)(n=2, sum=2) (n=2,    (n=2,
              "Make 2      sum=1)   sum=-2)
              using[1,2]"           ret 0
      |           |            |
  +---+---+   +---+---+    +---+---+
  |       |   |       |    |       |
EXCL  INCL EXCL  INCL  EXCL  INCL
[1]=2 [1]=2 [1]=2 [1]=2 [1]=2 [1]=2
  |       |   |       |    |       |
  v       v   v       v    v       v
(n=1,  (n=1, (n=1, (n=1, (n=1,  (n=1,
sum=5) sum=3) sum=2) sum=0) sum=1) sum=-1)
cont   cont   cont  ret 1  cont   ret 0
                      ✓           Path 3
                   Path 3       branch
                   branch

... tree continues ...

STEP 2: Values RETURN UP (counting all paths)

Level 5 (bottom - base cases):
  (n=0, sum=0) → 1 ✓ (Path 2: found [1,4])
  (n=1, sum=0) → 1 ✓ (Path 3: found [2,3])
  (n=4, sum=0) → 1 ✓ (Path 1: found [5])
  ... many other leaves return 0

Level 4:
  (n=1, sum=1) = EXCLUDE(0) + INCLUDE(1) = 0 + 1 = 1 ✓
  (n=2, sum=2) = EXCLUDE(0) + INCLUDE(1) = 0 + 1 = 1 ✓

Level 3:
  (n=2, sum=1) = EXCLUDE(1) + INCLUDE(0) = 1 + 0 = 1 ✓
  (n=2, sum=5) = EXCLUDE(1) + INCLUDE(0) = 1 + 0 = 1 ✓

Level 2:
  (n=3, sum=1) = EXCLUDE(1) + INCLUDE(0) = 1 + 0 = 1 ✓
  (n=3, sum=5) = EXCLUDE(1) + INCLUDE(0) = 1 + 0 = 1 ✓

Level 1:
  (n=4, sum=5) = EXCLUDE(1) + INCLUDE(1) = 1 + 1 = 2 ✓

Level 0 (root):
  (n=5, sum=5) = EXCLUDE(2) + INCLUDE(1) = 2 + 1 = 3 ✓ FINAL!

STEP 3: VISUAL - Values Flow Up (arrows show path contributions)

                            (n=5, sum=5)
                            2 + 1 = 3 ✓ FINAL!
                                    ↑
                    +---------------+---------------+
                    |                               |
                    2 ✓                             1 ✓
                    ↑                               ↑
               (n=4, sum=5)                    (n=4, sum=0)
               1 + 1 = 2 ✓                     Path 1: [5]
                    ↑
        +-----------+-----------+
        |                       |
        1 ✓                     1 ✓
        ↑                       ↑
   (n=3, sum=5)            (n=3, sum=1)
   Path 3 branch           Path 2 branch
   finds [2,3]             finds [1,4]
        ↑                       ↑
        |                       |
   (n=2, sum=2)            (n=2, sum=1)
   1 + 0 = 1 ✓             1 + 0 = 1 ✓
        ↑                       ↑
    +---+---+               +---+---+
    |       |               |       |
    0       1 ✓             1 ✓     0
    ↑       ↑               ↑       ↑
(n=1,   (n=1,           (n=1,   (n=1,
sum=2)  sum=0)          sum=1)  sum=-1)
ret 0   ret 1 ✓         cont    ret 0
        [2,3] found!         |
                         +---+---+
                         |       |
                         0       1 ✓
                         ↑       ↑
                     (n=0,   (n=0,
                     sum=1)  sum=0)
                     ret 0   ret 1 ✓
                             [1,4] found!

THREE paths find sum=0:
  Path 1: [5]     → contributes 1
  Path 2: [1,4]   → contributes 1
  Path 3: [2,3]   → contributes 1
                    ---------------
  Total:             3 ways ✓

Simplified path tracking:

Path 1 (using coin 5):
  (n=5, sum=5) → INCLUDE coin 5 → (n=4, sum=0) → return 1 ✓

Path 2 (using coins 1 and 4):
  (n=5, sum=5) → EXCLUDE coin 5 → (n=4, sum=5)
                → INCLUDE coin 4 → (n=3, sum=1)
                → EXCLUDE coin 3 → (n=2, sum=1)
                → EXCLUDE coin 2 → (n=1, sum=1)
                → INCLUDE coin 1 → (n=0, sum=0) → return 1 ✓

Path 3 (using coins 2 and 3):
  (n=5, sum=5) → EXCLUDE coin 5 → (n=4, sum=5)
                → EXCLUDE coin 4 → (n=3, sum=5)
                → INCLUDE coin 3 → (n=2, sum=2)
                → INCLUDE coin 2 → (n=1, sum=0) → return 1 ✓

KEY INSIGHT:
  Each independent path that reaches sum=0 contributes 1
  All contributions ADD UP at the root: 1 + 1 + 1 = 3 ✓

This is how recursion counts ALL possible ways!

================================================================================
*/
