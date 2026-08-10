package algorithms.dynamic_programming.coin_change

/**
 * PROBLEM: Coin Change - Minimum Coins
 *
 * Given coins, find MINIMUM number of coins needed to make sum.
 * Coins can be used UNLIMITED times (with repetition).
 *
 * INPUT: coins = [1, 2, 5], sum = 11
 * OUTPUT: 3 coins
 *
 * EXPLANATION:
 * You have: UNLIMITED coins of each type
 *
 * All possible ways to make 11:
 * - [5, 5, 1] = 3 coins ✓ MINIMUM!
 * - [5, 1, 1, 1, 1, 1, 1] = 7 coins
 * - [2, 2, 2, 2, 2, 1] = 6 coins
 * - [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1] = 11 coins
 *
 * Answer: 3 coins (minimum)
 *
 * DIFFERENCE from Count Ways problem:
 *
 * Count Ways:
 *   coins = [1, 2, 5], sum = 5
 *   Question: How many combinations?
 *   Answer: 4 ways ([1,1,1,1,1], [1,1,1,2], [1,2,2], [5])
 *
 * Minimum Coins:
 *   coins = [1, 2, 5], sum = 5
 *   Question: Minimum coins needed?
 *   Answer: 1 coin ([5])
 *
 * APPROACHES:
 * 1. Recursive: O(amount^n) time (exponential)
 * 2. Memoization: O(coins × amount) time, O(amount) space
 * 3. DP (bottom-up): O(coins × amount) time, O(amount) space
 */

fun main() {
    println("COIN CHANGE - MINIMUM COINS")
    println()

    println("APPROACH 1: RECURSIVE")
    println()

    // Test 1: Normal case
    println("=== Test 1: coins=[1,2,5], sum=11 ===")
    val result1 = coinChangeMinRecursive(intArrayOf(1, 2, 5), 11)
    println("Result: $result1")  // Result: 3
    println("Expected: 3 ([5,5,1])")
    println()

    // Test 2: Sum is 0
    println("=== Test 2: coins=[1,2,5], sum=0 ===")
    val result2 = coinChangeMinRecursive(intArrayOf(1, 2, 5), 0)
    println("Result: $result2")  // Result: 0
    println("Expected: 0 (no coins needed)")
    println()

    // Test 3: Impossible
    println("=== Test 3: coins=[2], sum=3 ===")
    val result3 = coinChangeMinRecursive(intArrayOf(2), 3)
    println("Result: $result3")  // Result: -1
    println("Expected: -1 (impossible)")
    println()

    // Test 4: Single coin exact match
    println("=== Test 4: coins=[5], sum=5 ===")
    val result4 = coinChangeMinRecursive(intArrayOf(5), 5)
    println("Result: $result4")  // Result: 1
    println("Expected: 1 ([5])")
    println()

    println("------------------------------------------------------------")
    println("APPROACH 2: MEMOIZATION")
    println()

    // Test 1: Normal case - Memoization
    println("=== Test 1: coins=[1,2,5], sum=11 (Memoization) ===")
    val result5 = coinChangeMinMemoization(intArrayOf(1, 2, 5), 11)
    println("Result: $result5")  // Result: 3
    println("Expected: 3 ([5,5,1])")
    println()

    // Test 2: Larger example
    println("=== Test 2: coins=[1,3,4,5], sum=7 (Memoization) ===")
    val result6 = coinChangeMinMemoization(intArrayOf(1, 3, 4, 5), 7)
    println("Result: $result6")  // Result: 2
    println("Expected: 2 ([3,4] or [5,2] - wait, no 2)")
    println("Actual: [3,4] = 2 coins")
    println()

    // Test 3: Performance comparison
    println("=== Test 3: Performance comparison ===")
    val coins = intArrayOf(1, 2, 5)
    val amount = 11

    println("Coins: ${coins.contentToString()}, Amount: $amount")

    val startRecursive = System.currentTimeMillis()
    val recursiveResult = coinChangeMinRecursive(coins, amount)
    val recursiveTime = System.currentTimeMillis() - startRecursive

    val startMemo = System.currentTimeMillis()
    val memoResult = coinChangeMinMemoization(coins, amount)
    val memoTime = System.currentTimeMillis() - startMemo

    println("Recursive result: $recursiveResult, Time: ${recursiveTime}ms")
    println("Memoization result: $memoResult, Time: ${memoTime}ms")
    println("Both should give same answer: ${recursiveResult == memoResult}")
}

// APPROACH 1: RECURSIVE
// TIME COMPLEXITY: O(amount^n) - exponential (tries all combinations)
// SPACE COMPLEXITY: O(amount) - recursion stack depth
private fun coinChangeMinRecursive(coins: IntArray, amount: Int): Int {
    // Base case: amount is 0, need 0 coins
    if (amount == 0) {
        return 0
    }

    // Base case: amount is negative, impossible
    if (amount < 0) {
        return -1
    }

    var minCoins = Int.MAX_VALUE

    // Try using each coin
    for (coin in coins) {
        val result = coinChangeMinRecursive(coins, amount - coin)

        // If valid result, update minimum
        if (result >= 0) {
            minCoins = minOf(minCoins, result + 1)
        }
    }

    // If no valid solution found, return -1
    return if (minCoins == Int.MAX_VALUE) -1 else minCoins
}

// APPROACH 2: MEMOIZATION
// TIME COMPLEXITY: O(n × amount) - each amount computed once for all coins
// SPACE COMPLEXITY: O(amount) - memo array + O(amount) recursion stack
private fun coinChangeMinMemoization(coins: IntArray, amount: Int): Int {
    // memo[i] = minimum coins needed to make amount i
    // -2 means not computed yet
    val memo = IntArray(amount + 1) { -2 }
    return coinChangeMinMemo(coins, amount, memo)
}

private fun coinChangeMinMemo(coins: IntArray, amount: Int, memo: IntArray): Int {
    // Base case: amount is 0, need 0 coins
    if (amount == 0) {
        return 0
    }

    // Base case: amount is negative, impossible
    if (amount < 0) {
        return -1
    }

    // Check if already computed
    if (memo[amount] != -2) {
        return memo[amount]
    }

    var minCoins = Int.MAX_VALUE

    // Try using each coin
    for (coin in coins) {
        val result = coinChangeMinMemo(coins, amount - coin, memo)

        // If valid result, update minimum
        if (result >= 0) {
            minCoins = minOf(minCoins, result + 1)
        }
    }

    // Store result in memo
    memo[amount] = if (minCoins == Int.MAX_VALUE) -1 else minCoins
    return memo[amount]
}

// APPROACH 3: BOTTOM-UP DP
// TIME COMPLEXITY: O(n × amount) - iterate through all amounts and coins
// SPACE COMPLEXITY: O(amount) - dp array only
private fun coinChangeMinDP(coins: IntArray, amount: Int): Int {
    // dp[i] = minimum coins to make amount i
    val dp = IntArray(amount + 1) { Int.MAX_VALUE }
    dp[0] = 0  // Base case: 0 coins for amount 0

    // For each amount from 1 to target
    for (i in 1..amount) {
        // Try using each coin
        for (coin in coins) {
            if (i >= coin && dp[i - coin] != Int.MAX_VALUE) {
                dp[i] = minOf(dp[i], dp[i - coin] + 1)
            }
        }
    }

    return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
}

/*
================================================================================
RECURSION TREE VISUALIZATION
================================================================================

INPUT: coins = [1, 2, 5], amount = 11
QUESTION: What is the MINIMUM number of coins?

STEP 1: Tree Goes DOWN (trying all possibilities)

                            (amount=11)
                            "Make 11"
                                |
                +---------------+---------------+---------------+
                |               |               |               |
            Use coin 1      Use coin 2      Use coin 5      (try all coins)
                |               |               |
                v               v               v
           (amount=10)     (amount=9)      (amount=6)
           "Make 10"       "Make 9"        "Make 6"
                |               |               |
        +-------+-------+   +---+---+     +-----+-----+
        |       |       |   |   |   |     |     |     |
      use 1   use 2   use 5  ...  ...   use 1 use 2 use 5
        |       |       |                 |     |     |
        v       v       v                 v     v     v
    (amt=9) (amt=8) (amt=5)          (amt=5) (amt=4) (amt=1)
                        |                 |
                    +---+---+         +---+---+
                    |   |   |         |   |   |
                  use 1|use 5       use 1|use 5
                    |   |   |         |   |   |
                    v   v   v         v   v   v
                (amt=4)(amt=3)(amt=0) (amt=4)(amt=3)(amt=0)
                                ↑                     ↑
                            return 0 ✓            return 0 ✓
                            (base case)           (base case)

Tree continues with many branches...

STEP 2: Values RETURN UP (finding minimum)

At each node, we take MINIMUM of all children + 1:

(amount=0) → return 0 ✓ (base case - found solution!)

(amount=1):
  Use coin 1: (amount=0) = 0 → 0 + 1 = 1 ✓
  Use coin 2: (amount=-1) = -1 (invalid)
  Use coin 5: (amount=-4) = -1 (invalid)
  return min(1) = 1

(amount=2):
  Use coin 1: (amount=1) = 1 → 1 + 1 = 2
  Use coin 2: (amount=0) = 0 → 0 + 1 = 1 ✓
  Use coin 5: (amount=-3) = -1 (invalid)
  return min(2, 1) = 1

(amount=5):
  Use coin 1: (amount=4) = 2 → 2 + 1 = 3
  Use coin 2: (amount=3) = 2 → 2 + 1 = 3
  Use coin 5: (amount=0) = 0 → 0 + 1 = 1 ✓
  return min(3, 3, 1) = 1

(amount=6):
  Use coin 1: (amount=5) = 1 → 1 + 1 = 2 ✓
  Use coin 2: (amount=4) = 2 → 2 + 1 = 3
  Use coin 5: (amount=1) = 1 → 1 + 1 = 2 ✓
  return min(2, 3, 2) = 2

(amount=11):
  Use coin 1: (amount=10) = 3 → 3 + 1 = 4
  Use coin 2: (amount=9) = 3 → 3 + 1 = 4
  Use coin 5: (amount=6) = 2 → 2 + 1 = 3 ✓
  return min(4, 4, 3) = 3 ✓ ANSWER!

STEP 3: VISUAL - Values Flow Up (arrows show return values)

                            (amount=11)
                            min(4,4,3)+0 = 3 ✓ FINAL!
                                    ↑
                    +---------------+---------------+
                    |               |               |
                    4               4               3 ✓ (best path!)
                    ↑               ↑               ↑
               (amount=10)     (amount=9)      (amount=6)
               min(3,...)+1    min(3,...)+1    min(2,3,2)+1=2
                                                    ↑
                                            +-------+-------+
                                            |       |       |
                                            2 ✓     3       2
                                            ↑       ↑       ↑
                                        (amt=5) (amt=4) (amt=1)
                                        min+1=1 min+1=2 min+1=1
                                            ↑               ↑
                                    +-------+-------+   +---+---+
                                    |       |       |   |       |
                                    3       3       1 ✓ -1      0 ✓
                                    ↑       ↑       ↑   ↑       ↑
                                (amt=4) (amt=3) (amt=0) (amt=-1) base
                                                ret 0           ret 0

Level-by-level return flow:

Bottom (base cases):
  (amount=0) → 0 ✓
  (amount=-1) → -1 (invalid)

Level 1:
  (amount=1) = min(0+1, -1, -1) = 1 ✓

Level 2:
  (amount=2) = min(1+1, 0+1, -1) = 1 ✓

Level 3:
  (amount=5) = min(2+1, 2+1, 0+1) = 1 ✓

Level 4:
  (amount=6) = min(1+1, 2+1, 1+1) = 2 ✓

...continues...

Level Final:
  (amount=11) = min(3+1, 3+1, 2+1) = 3 ✓ ANSWER!

The optimal path (minimum coins):
  amount=11 → use coin 5 → amount=6
  amount=6  → use coin 5 → amount=1
  amount=1  → use coin 1 → amount=0 ✓

  Coins used: [5, 5, 1] = 3 coins ✓

KEY DIFFERENCE from Count Ways:
  - Count Ways: ADD all paths (return sum)
  - Min Coins: Take MINIMUM of all paths (return min)

Formula:
  Count Ways: return sum of all children
  Min Coins:  return min of all children + 1

================================================================================
*/
