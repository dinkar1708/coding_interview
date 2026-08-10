package algorithms.dynamic_programming.subsequence

/**
 * PROBLEM: House Robber - Maximum Stolen Value (Top-Down DP with Memoization)
 * https://medium.com/outco/how-to-solve-the-house-robber-problem-f3535ebaef1b
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent-set-2/
 *
 * You are a thief planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * Adjacent houses have security systems connected, so you can't rob two adjacent houses.
 *
 * INPUT: [6, 7, 1, 3, 8, 2, 4]
 * OUTPUT: 19
 *
 * EXPLANATION:
 * Top-Down DP (Recursion with Memoization).
 * Calculate from the end backwards.
 * For each house i, we have two choices:
 * 1. Skip this house: max_profit(i+1)
 * 2. Rob this house: A[i] + max_profit(i+2)
 * dp[i] = max(max_profit(i+1), A[i] + max_profit(i+2))
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val A1 = intArrayOf(6, 7, 1, 3, 8, 2, 4)
    val result1 = maxProfitTopDown(A1, 0, A1.size, IntArray(A1.size), IntArray(A1.size))
    println("Result: $result1")
    println("Expected: 19 (6+1+8+4)")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val A2 = intArrayOf()
    val result2 = if (A2.isEmpty()) 0 else maxProfitTopDown(A2, 0, A2.size, IntArray(A2.size), IntArray(A2.size))
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single house
    println("=== Test 3: Edge case - single house ===")
    val A3 = intArrayOf(10)
    val result3 = maxProfitTopDown(A3, 0, A3.size, IntArray(A3.size), IntArray(A3.size))
    println("Result: $result3")
    println("Expected: 10")
    println()

    // Test 4: Two houses
    println("=== Test 4: Two houses ===")
    val A4 = intArrayOf(5, 10)
    val result4 = maxProfitTopDown(A4, 0, A4.size, IntArray(A4.size), IntArray(A4.size))
    println("Result: $result4")
    println("Expected: 10")
    println()

    // Test 5: Increasing values
    println("=== Test 5: Increasing values ===")
    val A5 = intArrayOf(1, 2, 3, 4, 5)
    val result5 = maxProfitTopDown(A5, 0, A5.size, IntArray(A5.size), IntArray(A5.size))
    println("Result: $result5")
    println("Expected: 9 (1+3+5)")
    println()

    // Test 6: All same values
    println("=== Test 6: All same values ===")
    val A6 = intArrayOf(5, 5, 5, 5)
    val result6 = maxProfitTopDown(A6, 0, A6.size, IntArray(A6.size), IntArray(A6.size))
    println("Result: $result6")
    println("Expected: 10")

    /*
    Expected output:
    [6, 7, 1, 3, 8, 2, 4]
    Memoization table i: 0
    [1, 0, 0, 0, 0, 0, 0]
    Memoization table i: 1
    [1, 1, 0, 0, 0, 0, 0]
    Memoization table i: 2
    [1, 1, 1, 0, 0, 0, 0]
    Memoization table i: 3
    [1, 1, 1, 1, 0, 0, 0]
    Memoization table i: 4
    [1, 1, 1, 1, 1, 0, 0]
    Memoization table i: 5
    [1, 1, 1, 1, 1, 1, 0]
    Memoization table i: 6
    [1, 1, 1, 1, 1, 1, 1]
    Base case...i: 7
    Base case...i: 8
    For each i: 6
    [0, 0, 0, 0, 0, 0, 4]
    DP value: 4
    Base case...i: 7
    For each i: 5
    [0, 0, 0, 0, 0, 4, 4]
    DP value: 4
    For each i: 4
    [0, 0, 0, 0, 12, 4, 4]
    DP value: 12
    For each i: 3
    [0, 0, 0, 12, 12, 4, 4]
    DP value: 12
    For each i: 2
    [0, 0, 13, 12, 12, 4, 4]
    DP value: 13
    For each i: 1
    [0, 19, 13, 12, 12, 4, 4]
    DP value: 19
    For each i: 0
    [19, 19, 13, 12, 12, 4, 4]
    DP value: 19
    Max Profit: 19
    */
}

// TIME COMPLEXITY: O(n) - each house computed once with memoization
// SPACE COMPLEXITY: O(n) - memoization table + DP array + recursion stack
private fun maxProfitTopDown(A: IntArray, i: Int, n: Int, dp: IntArray, memoTable: IntArray): Int {
    // Base case: beyond array bounds
    if (i >= n) {
        println("Base case...i: $i")
        return 0
    }

    // Check if already calculated (memoization)
    if (memoTable[i] == 1) {
        return dp[i]
    }

    // Mark as calculated
    memoTable[i] = 1
    println("Memoization table i: $i")
    println(memoTable.contentToString())

    // Recurrence relation:
    // Skip current house -> max_profit(i+1)
    // Rob current house -> A[i] + max_profit(i+2)
    dp[i] = maxOf(
        maxProfitTopDown(A, i + 1, n, dp, memoTable),
        A[i] + maxProfitTopDown(A, i + 2, n, dp, memoTable)
    )

    println("For each i: $i")
    println(dp.contentToString())
    println("DP value: ${dp[i]}")

    return dp[i]
}

/*
================================================================================
RECURSION TREE VISUALIZATION - HOUSE ROBBER (Top-Down DP)
================================================================================

INPUT: houses = [6, 7, 1, 3, 8, 2, 4]
        Index:    0  1  2  3  4  5  6

QUESTION: What is the maximum money you can rob without robbing adjacent houses?

ANSWER: 19 (rob houses at indices 0, 2, 4, 6 → 6 + 1 + 8 + 4 = 19)

RECURRENCE RELATION:
For each house i, we have TWO choices:
1. SKIP this house: maxProfit(i+1)
2. ROB this house: A[i] + maxProfit(i+2) (can't rob i+1, it's adjacent)

dp[i] = max(maxProfit(i+1), A[i] + maxProfit(i+2))

--------------------------------------------------------------------------------

STEP 1: Recursion Tree Goes DOWN (calls from root to leaves)

                                maxProfit(0)
                                "Start at house 0"
                                "Value: 6"
                                        |
                        +---------------+---------------+
                        |                               |
                    SKIP house 0                    ROB house 0
                    maxProfit(1)                    6 + maxProfit(2)
                        |                               |
            +-----------+-----------+       +-----------+-----------+
            |                       |       |                       |
        SKIP house 1            ROB house 1 SKIP house 2        ROB house 2
        maxProfit(2)            7 + maxProfit(3)  maxProfit(3)  1 + maxProfit(4)
            |                       |                   |               |
        +---+---+               +---+---+           +---+---+       +---+---+
        |       |               |       |           |       |       |       |
      SKIP    ROB             SKIP    ROB         SKIP    ROB     SKIP    ROB
      mp(3)   1+mp(4)         mp(4)   3+mp(5)     mp(4)   3+mp(5) mp(5)   8+mp(6)
        |       |               |       |           |       |       |       |
       ...     ...             ...     ...         ...     ...     ...    mp(6)
                                                                            |
                                                                        +---+---+
                                                                        |       |
                                                                      SKIP    ROB
                                                                      mp(7)   4+mp(8)
                                                                        |       |
                                                                      ret 0   ret 0
                                                                      base    base

Without memoization: MANY duplicate calls (exponential time)
With memoization: Each house computed ONCE (linear time)

--------------------------------------------------------------------------------

STEP 2: Memoization Table Evolution (Top-Down)

Initial state:
memoTable = [0, 0, 0, 0, 0, 0, 0]  (0 = not computed, 1 = computed)
dp        = [0, 0, 0, 0, 0, 0, 0]

Call maxProfit(0):
  Needs maxProfit(1) and maxProfit(2)

Call maxProfit(1):
  Needs maxProfit(2) and maxProfit(3)

Call maxProfit(2):
  Needs maxProfit(3) and maxProfit(4)

... (recursion continues to base case)

Call maxProfit(7): → return 0 (base case: i >= n)
Call maxProfit(8): → return 0 (base case: i >= n)

Now values RETURN UP:

At index 6:
  houses[6] = 4
  dp[6] = max(maxProfit(7), 4 + maxProfit(8))
  dp[6] = max(0, 4 + 0) = 4 ✓
  memoTable[6] = 1 (mark as computed)

State:
memoTable = [0, 0, 0, 0, 0, 0, 1]
dp        = [0, 0, 0, 0, 0, 0, 4]

At index 5:
  houses[5] = 2
  dp[5] = max(maxProfit(6), 2 + maxProfit(7))
  dp[5] = max(4, 2 + 0) = 4 ✓
  memoTable[5] = 1

State:
memoTable = [0, 0, 0, 0, 0, 1, 1]
dp        = [0, 0, 0, 0, 0, 4, 4]

At index 4:
  houses[4] = 8
  dp[4] = max(maxProfit(5), 8 + maxProfit(6))
  dp[4] = max(4, 8 + 4) = 12 ✓ (ROB house 4!)
  memoTable[4] = 1

State:
memoTable = [0, 0, 0, 0, 1, 1, 1]
dp        = [0, 0, 0, 0, 12, 4, 4]

At index 3:
  houses[3] = 3
  dp[3] = max(maxProfit(4), 3 + maxProfit(5))
  dp[3] = max(12, 3 + 4) = 12 ✓ (SKIP house 3, keep 12)
  memoTable[3] = 1

State:
memoTable = [0, 0, 0, 1, 1, 1, 1]
dp        = [0, 0, 0, 12, 12, 4, 4]

At index 2:
  houses[2] = 1
  dp[2] = max(maxProfit(3), 1 + maxProfit(4))
  dp[2] = max(12, 1 + 12) = 13 ✓ (ROB house 2!)
  memoTable[2] = 1

State:
memoTable = [0, 0, 1, 1, 1, 1, 1]
dp        = [0, 0, 13, 12, 12, 4, 4]

At index 1:
  houses[1] = 7
  dp[1] = max(maxProfit(2), 7 + maxProfit(3))
  dp[1] = max(13, 7 + 12) = 19 ✓ (ROB house 1!)
  memoTable[1] = 1

State:
memoTable = [0, 1, 1, 1, 1, 1, 1]
dp        = [0, 19, 13, 12, 12, 4, 4]

At index 0:
  houses[0] = 6
  dp[0] = max(maxProfit(1), 6 + maxProfit(2))
  dp[0] = max(19, 6 + 13) = 19 ✓ (either choice gives 19)
  memoTable[0] = 1

Final State:
memoTable = [1, 1, 1, 1, 1, 1, 1]
dp        = [19, 19, 13, 12, 12, 4, 4]
                ↑
            ANSWER = 19 ✓

--------------------------------------------------------------------------------

STEP 3: VISUAL - Values Flow Up (showing return values)

                                maxProfit(0)
                                max(19, 6+13) = 19 ✓ ANSWER!
                                        ↑
                        +---------------+---------------+
                        |                               |
                        19 ✓                         6+13=19 ✓
                        ↑                               ↑
                    maxProfit(1)                    maxProfit(2)
                    max(13, 7+12)=19                max(12, 1+12)=13
                        ↑                               ↑
            +-----------+-----------+       +-----------+-----------+
            |                       |       |                       |
            13 ✓                 7+12=19    12 ✓                 1+12=13
            ↑                       ↑       ↑                       ↑
        maxProfit(2)            maxProfit(3) maxProfit(3)      maxProfit(4)
        max(12,1+12)=13         max(12,3+4)=12  12          max(4,8+4)=12
            ↑                       ↑                               ↑
        +---+---+               +---+---+                       +---+---+
        |       |               |       |                       |       |
        12      1+12=13         12      3+4=7                   4    8+4=12 ✓
        ↑       ↑               ↑       ↑                       ↑       ↑
      mp(3)   mp(4)           mp(4)   mp(5)                   mp(5)   mp(6)
        12      12              12      4                       4       4
                                                                        ↑
                                                                    +---+---+
                                                                    |       |
                                                                    0    4+0=4 ✓
                                                                    ↑       ↑
                                                                  mp(7)   mp(8)
                                                                  ret 0   ret 0

--------------------------------------------------------------------------------

STEP 4: Which Houses to Rob?

To find which houses were robbed, trace back through decisions:

dp[0] = 19
  → max(maxProfit(1), 6 + maxProfit(2))
  → max(19, 19) - TIE!

Path 1 (SKIP house 0): Use maxProfit(1) = 19
  dp[1] = 19
    → max(13, 7 + 12)
    → ROB house 1 (7) ✓
    → Continue from maxProfit(3)
  dp[3] = 12
    → max(12, 3 + 4)
    → SKIP house 3
    → Continue from maxProfit(4)
  dp[4] = 12
    → max(4, 8 + 4)
    → ROB house 4 (8) ✓
    → Continue from maxProfit(6)
  dp[6] = 4
    → max(0, 4 + 0)
    → ROB house 6 (4) ✓

Path 1: Houses [1, 4, 6] → 7 + 8 + 4 = 19 ✓

Path 2 (ROB house 0): Use 6 + maxProfit(2) = 19
  dp[2] = 13
    → max(12, 1 + 12)
    → ROB house 2 (1) ✓
    → Continue from maxProfit(4)
  dp[4] = 12
    → ROB house 4 (8) ✓
  dp[6] = 4
    → ROB house 6 (4) ✓

Path 2: Houses [0, 2, 4, 6] → 6 + 1 + 8 + 4 = 19 ✓

Both paths give maximum value of 19!

--------------------------------------------------------------------------------

KEY INSIGHTS:

1. Top-Down Approach:
   - Start from index 0 (first house)
   - Recursively solve smaller subproblems
   - Cache results in memoization table
   - Build solution from leaves to root

2. Decision at Each House:
   - SKIP: Get best from next house (i+1)
   - ROB: Get house value + best from i+2 (skip adjacent)
   - Choose maximum of both options

3. Time Complexity: O(n)
   - Each house computed exactly once
   - Memoization prevents duplicate work
   - Without memo: O(2^n) exponential!

4. Space Complexity: O(n)
   - dp array: O(n)
   - memoTable: O(n)
   - Recursion stack: O(n)

5. Pattern Recognition:
   - This is "Maximum Non-Adjacent Sum" problem
   - Similar to Fibonacci with memoization
   - Classic example of optimal substructure

================================================================================
*/
