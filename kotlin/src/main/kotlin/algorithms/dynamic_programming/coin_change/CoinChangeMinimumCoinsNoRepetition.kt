package algorithms.dynamic_programming.coin_change

/**
 * PROBLEM: Coin Change - Minimum Coins (NO REPETITION)
 *
 * Given coins, find MINIMUM number of coins needed to make sum.
 * Each coin can be used AT MOST ONCE (no repetition).
 *
 * INPUT: coins = [1, 2, 3, 4, 5], sum = 11
 * OUTPUT: 3 coins
 *
 * EXPLANATION:
 * You have: ONE coin of each value
 *
 * Possible ways to make 11:
 * - [5, 4, 2] = 3 coins ✓ MINIMUM!
 * - [5, 3, 2, 1] = 4 coins
 * - [4, 3, 2, 1, 1] = NOT VALID (only have ONE coin of value 1)
 *
 * Answer: 3 coins (minimum)
 *
 * DIFFERENCE from WITH repetition:
 *
 * With Repetition (unlimited coins):
 *   coins = [1, 2, 5], sum = 11
 *   Answer: 3 coins ([5, 5, 1])
 *   Can reuse coin 5 twice
 *
 * No Repetition (each coin once):
 *   coins = [1, 2, 5], sum = 11
 *   Answer: -1 (impossible!)
 *   Cannot make 11 using [1, 2, 5] only once each
 *   Max possible: 1+2+5 = 8 < 11
 *
 * This is like 0/1 Knapsack with minimization!
 *
 * APPROACHES:
 * 1. Recursive: O(2^n) time (exponential)
 * 2. Memoization: O(n × sum) time, O(n × sum) space
 */

fun main() {
    println("COIN CHANGE - MINIMUM COINS (NO REPETITION)")
    println()

    println("APPROACH 1: RECURSIVE")
    println()

    // Test 1: Normal case
    println("=== Test 1: coins=[1,2,3,4,5], sum=11 ===")
    val result1 = coinChangeMinNoRepRecursive(intArrayOf(1, 2, 3, 4, 5), 5, 11)
    println("Result: $result1")  // Result: 3
    println("Expected: 3 ([5,4,2] or [5,3,2,1])")
    println()

    // Test 2: Sum is 0
    println("=== Test 2: coins=[1,2,5], sum=0 ===")
    val result2 = coinChangeMinNoRepRecursive(intArrayOf(1, 2, 5), 3, 0)
    println("Result: $result2")  // Result: 0
    println("Expected: 0 (no coins needed)")
    println()

    // Test 3: Impossible (with repetition it's possible, but not without)
    println("=== Test 3: coins=[1,2,5], sum=11 ===")
    val result3 = coinChangeMinNoRepRecursive(intArrayOf(1, 2, 5), 3, 11)
    println("Result: $result3")  // Result: -1
    println("Expected: -1 (impossible - max is 1+2+5=8)")
    println()

    // Test 4: Exact match
    println("=== Test 4: coins=[5], sum=5 ===")
    val result4 = coinChangeMinNoRepRecursive(intArrayOf(5), 1, 5)
    println("Result: $result4")  // Result: 1
    println("Expected: 1 ([5])")
    println()

    // Test 5: Multiple coins needed
    println("=== Test 5: coins=[1,3,4,5], sum=7 ===")
    val result5 = coinChangeMinNoRepRecursive(intArrayOf(1, 3, 4, 5), 4, 7)
    println("Result: $result5")  // Result: 2
    println("Expected: 2 ([3,4])")
    println()

    println("------------------------------------------------------------")
    println("APPROACH 2: MEMOIZATION")
    println()

    // Test 1: Normal case - Memoization
    println("=== Test 1: coins=[1,2,3,4,5], sum=11 (Memoization) ===")
    val result6 = coinChangeMinNoRepMemoization(intArrayOf(1, 2, 3, 4, 5), 11)
    println("Result: $result6")  // Result: 3
    println("Expected: 3 ([5,4,2])")
    println()

    // Test 2: Impossible case
    println("=== Test 2: coins=[1,2,5], sum=11 (Memoization) ===")
    val result7 = coinChangeMinNoRepMemoization(intArrayOf(1, 2, 5), 11)
    println("Result: $result7")  // Result: -1
    println("Expected: -1 (impossible)")
    println()

    // Test 3: Comparison
    println("=== Test 3: Comparison ===")
    val coins = intArrayOf(1, 2, 3, 4, 5)
    val sum = 11

    println("Coins: ${coins.contentToString()}, Sum: $sum")
    println()

    val noRep = coinChangeMinNoRepMemoization(coins, sum)
    println("No Repetition (each coin once): $noRep coins")
    println("  Best: [5,4,2] = 3 coins")
    println()

    // For comparison, if we had unlimited coins:
    println("With Repetition (unlimited coins): 3 coins")
    println("  Best: [5,5,1] = 3 coins")
}

// APPROACH 1: RECURSIVE
// TIME COMPLEXITY: O(2^n) - each coin has 2 choices (include/exclude)
// SPACE COMPLEXITY: O(n) - recursion stack depth
private fun coinChangeMinNoRepRecursive(coins: IntArray, n: Int, sum: Int): Int {
    // Base case: sum is 0, need 0 coins
    if (sum == 0) {
        return 0
    }

    // Base case: sum is negative, impossible
    if (sum < 0) {
        return -1
    }

    // Base case: no coins left but sum > 0, impossible
    if (n <= 0) {
        return -1
    }

    // Two choices:
    // 1) EXCLUDE coins[n-1] - skip this coin
    val exclude = coinChangeMinNoRepRecursive(coins, n - 1, sum)

    // 2) INCLUDE coins[n-1] - use this coin ONCE, move to n-1
    val include = coinChangeMinNoRepRecursive(coins, n - 1, sum - coins[n - 1])

    // Take minimum of both choices
    // If exclude is valid, consider it
    // If include is valid, consider it + 1 (for using this coin)
    // Return minimum of valid options

    if (exclude == -1 && include == -1) {
        return -1  // Both impossible
    } else if (exclude == -1) {
        return include + 1  // Only include is valid
    } else if (include == -1) {
        return exclude  // Only exclude is valid
    } else {
        return minOf(exclude, include + 1)  // Both valid, take minimum
    }
}

// APPROACH 2: MEMOIZATION
// TIME COMPLEXITY: O(n × sum) - each state computed once
// SPACE COMPLEXITY: O(n × sum) - memo table + O(n) recursion stack
private fun coinChangeMinNoRepMemoization(coins: IntArray, sum: Int): Int {
    // memo[coinIndex][remainingSum]
    // -2 means not computed yet
    val memo = Array(coins.size + 1) { IntArray(sum + 1) { -2 } }
    return coinChangeMinNoRepMemo(coins, coins.size, sum, memo)
}

private fun coinChangeMinNoRepMemo(coins: IntArray, n: Int, sum: Int, memo: Array<IntArray>): Int {
    // Base case: sum is 0, need 0 coins
    if (sum == 0) {
        return 0
    }

    // Base case: sum is negative, impossible
    if (sum < 0) {
        return -1
    }

    // Base case: no coins left but sum > 0, impossible
    if (n <= 0) {
        return -1
    }

    // Check memo
    if (memo[n][sum] != -2) {
        return memo[n][sum]
    }

    // Two choices:
    // 1) EXCLUDE coins[n-1] - skip this coin
    val exclude = coinChangeMinNoRepMemo(coins, n - 1, sum, memo)

    // 2) INCLUDE coins[n-1] - use this coin ONCE
    val include = coinChangeMinNoRepMemo(coins, n - 1, sum - coins[n - 1], memo)

    // Calculate result
    val result = if (exclude == -1 && include == -1) {
        -1  // Both impossible
    } else if (exclude == -1) {
        include + 1  // Only include is valid
    } else if (include == -1) {
        exclude  // Only exclude is valid
    } else {
        minOf(exclude, include + 1)  // Both valid, take minimum
    }

    // Store and return
    memo[n][sum] = result
    return result
}

/*
================================================================================
RECURSION TREE VISUALIZATION
================================================================================

INPUT: coins = [1, 2, 3, 4, 5], sum = 11
QUESTION: What is the MINIMUM number of coins? (each coin used at most once)

Valid combinations:
  - [5, 4, 2] = 3 coins ✓ MINIMUM!
  - [5, 3, 2, 1] = 4 coins
  - [4, 3, 2, 1, 1] = NOT VALID (only ONE coin of value 1)

ANSWER: 3 coins

STEP 1: Tree Goes DOWN (trying both choices for each coin)

                                (n=5, sum=11)
                                "Make 11 using coins once"
                                        |
                        +---------------+---------------+
                        |                               |
                  EXCLUDE [4]=5                   INCLUDE [4]=5
                  (skip coin 5)                   (use 5, move to n-1)
                        |                               |
                        v                               v
                   (n=4, sum=11)                   (n=4, sum=6)
               "Make 11 using [1,2,3,4]"       "Make 6 using [1,2,3,4]"
                        |                               |
            +-----------+-----------+       +-----------+-----------+
            |                       |       |                       |
      EXCLUDE [3]=4           INCLUDE [3]=4  EXCLUDE [3]=4    INCLUDE [3]=4
            |                       |       |                       |
            v                       v       v                       v
       (n=3, sum=11)           (n=3, sum=7) (n=3, sum=6)      (n=3, sum=2)
       impossible              continue     continue           continue
       (max=1+2+3=6)               |           |                   |
                              +----+----+  +---+---+          +----+----+
                              |         |  |       |          |         |
                          EXCL     INCL EXCL   INCL       EXCL      INCL
                              |         |  |       |          |         |
                              v         v  v       v          v         v
                          (n=2,    (n=2, (n=2,  (n=2,     (n=2,    (n=2,
                          sum=7)   sum=4) sum=6) sum=3)   sum=2)   sum=-1)
                          cont     cont   cont   cont     cont     ret -1
                                      |                      |
                                  +---+---+              +---+---+
                                  |       |              |       |
                              EXCL    INCL           EXCL    INCL
                                  |       |              |       |
                                  v       v              v       v
                              (n=1,   (n=1,          (n=1,   (n=1,
                              sum=4)  sum=2)         sum=2)  sum=0)
                              cont    cont           cont    ret 0 ✓
                                          |                      Found!
                                      +---+---+
                                      |       |
                                  EXCL    INCL
                                      |       |
                                      v       v
                                  (n=0,   (n=0,
                                  sum=2)  sum=1)
                                  ret -1  ret -1

... tree continues with all branches ...

STEP 2: Values RETURN UP (finding minimum)

Bottom (base cases):
  (n=0, sum=0) → 0 ✓ (found a solution!)
  (n=0, sum>0) → -1 (no coins left, impossible)
  (n=?, sum<0) → -1 (negative sum, impossible)

Level 4:
  (n=1, sum=0) = min(EXCL:-1, INCL:0+1) = 0 ✓

Level 3:
  (n=2, sum=2) = min(EXCL:?, INCL:0+1) = 1 ✓
  (Using coin 2 reaches sum=0)

Level 2:
  (n=3, sum=2) = min(EXCL:1, INCL:-1+1) = 1 ✓

Level 1:
  (n=4, sum=6) = min(EXCL:?, INCL:1+1) = 2 ✓
  (Using coin 4, then coin 2)

Level 0 (root):
  (n=5, sum=11) = min(EXCL:?, INCL:2+1) = 3 ✓ ANSWER!
  (Using coin 5, then coin 4, then coin 2)

STEP 3: VISUAL - Values Flow Up

                                (n=5, sum=11)
                                min(∞, 2+1) = 3 ✓ FINAL!
                                        ↑
                        +---------------+---------------+
                        |                               |
                        ∞ (impossible)                  2 ✓ (best path!)
                        ↑                               ↑
                   (n=4, sum=11)                   (n=4, sum=6)
                   max=1+2+3+4=10<11               min(∞, 1+1) = 2 ✓
                                                        ↑
                                            +-----------+-----------+
                                            |                       |
                                            ∞                       1 ✓
                                            ↑                       ↑
                                       (n=3, sum=6)            (n=3, sum=2)
                                                                min(1, ∞) = 1 ✓
                                                                    ↑
                                                            +-------+-------+
                                                            |               |
                                                            1 ✓             ∞
                                                            ↑               ↑
                                                        (n=2, sum=2)    (n=2, sum=-1)
                                                        min(∞, 0+1)=1   ret -1
                                                            ↑
                                                        +---+---+
                                                        |       |
                                                        ∞       0 ✓
                                                        ↑       ↑
                                                    (n=1,   (n=1,
                                                    sum=2)  sum=0)
                                                            ret 0

The optimal path (minimum coins):
  sum=11 → use coin 5 → sum=6
  sum=6  → use coin 4 → sum=2
  sum=2  → use coin 2 → sum=0 ✓

  Coins used: [5, 4, 2] = 3 coins ✓

KEY DIFFERENCE from WITH repetition:
  - With Repetition: Can reuse coins, might use [5,5,1]
  - No Repetition: Each coin once, must use [5,4,2]

Formula at each node:
  return min(exclude, include + 1)
         where include moves to n-1 (cannot reuse coin)

================================================================================
*/
