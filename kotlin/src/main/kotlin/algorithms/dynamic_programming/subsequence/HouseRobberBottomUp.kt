package algorithms.dynamic_programming.subsequence

/**
 * PROBLEM: House Robber - Maximum Stolen Value (Bottom-Up DP)
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
 *
 * You are a thief planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * Adjacent houses have security systems connected, so you can't rob two adjacent houses.
 *
 * INPUT: [6, 7, 1, 3, 8, 2, 4]
 * OUTPUT: 19
 *
 * EXPLANATION:
 * Bottom-Up DP approach.
 * dp[i] represents the maximum value stolen so far after reaching house i.
 * dp[i] = max(A[i] + dp[i-2], dp[i-1])
 * Steal 6, 1, 8, and 4 from the houses -> 6+1+8+4=19
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = maxProfit(intArrayOf(6, 7, 1, 3, 8, 2, 4))
    println("Result: $result1")
    println("Expected: 19 (6+1+8+4)")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = maxProfit(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single house
    println("=== Test 3: Edge case - single house ===")
    val result3 = maxProfit(intArrayOf(10))
    println("Result: $result3")
    println("Expected: 10")
    println()

    // Test 4: Two houses
    println("=== Test 4: Two houses ===")
    val result4 = maxProfit(intArrayOf(5, 10))
    println("Result: $result4")
    println("Expected: 10 (rob second)")
    println()

    // Test 5: Increasing values
    println("=== Test 5: Increasing values ===")
    val result5 = maxProfit(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result5")
    println("Expected: 9 (1+3+5)")
    println()

    // Test 6: All same values
    println("=== Test 6: All same values ===")
    val result6 = maxProfit(intArrayOf(5, 5, 5, 5))
    println("Result: $result6")
    println("Expected: 10 (rob houses 0 and 2)")

    /*
    Expected detailed output for test 1:
    [6, 7, 1, 3, 8, 2, 4]
    For each i: 0
    [6, 0, 0, 0, 0, 0, 0]
    For each i: 1
    [6, 7, 0, 0, 0, 0, 0]
    For each i: 2
    A[i]: 1
    dp[i - 2]: 6
    dp[i - 1]: 7
    [6, 7, 7, 0, 0, 0, 0]
    DP value: 7
    For each i: 3
    A[i]: 3
    dp[i - 2]: 7
    dp[i - 1]: 7
    [6, 7, 7, 10, 0, 0, 0]
    DP value: 10
    For each i: 4
    A[i]: 8
    dp[i - 2]: 7
    dp[i - 1]: 10
    [6, 7, 7, 10, 15, 0, 0]
    DP value: 15
    For each i: 5
    A[i]: 2
    dp[i - 2]: 10
    dp[i - 1]: 15
    [6, 7, 7, 10, 15, 15, 0]
    DP value: 15
    For each i: 6
    A[i]: 4
    dp[i - 2]: 15
    dp[i - 1]: 15
    [6, 7, 7, 10, 15, 15, 19]
    DP value: 19
    Max Profit: 19
    */
}

// TIME COMPLEXITY: O(n) - iterate through houses once building DP solution
// SPACE COMPLEXITY: O(1) - only two variables for previous states
private fun maxProfit(A: IntArray): Int {
    val n = A.size

    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return A[0]
    }
    if (n == 2) {
        return maxOf(A[0], A[1])
    }

    // dp[i] represents the maximum value stolen after reaching house i
    val dp = IntArray(n)

    // Base case: dp[0]
    dp[0] = A[0]
    println("For each i: 0")
    println(dp.contentToString())

    // Base case: dp[1]
    dp[1] = maxOf(A[0], A[1])
    println("For each i: 1")
    println(dp.contentToString())

    // Fill the rest of dp array
    for (i in 2 until n) {
        println("For each i: $i")
        println("A[i]: ${A[i]}")
        println("dp[i - 2]: ${dp[i - 2]}")
        println("dp[i - 1]: ${dp[i - 1]}")

        // DP condition: max of (current + dp[i-2], dp[i-1])
        dp[i] = maxOf(A[i] + dp[i - 2], dp[i - 1])
        println(dp.contentToString())
        println("DP value: ${dp[i]}")
    }

    // Return the last value of array
    return dp[n - 1]
}

/*
================================================================================
DP TABLE VISUALIZATION - HOUSE ROBBER (Bottom-Up DP)
================================================================================

INPUT: houses = [6, 7, 1, 3, 8, 2, 4]
        Index:    0  1  2  3  4  5  6

QUESTION: What is the maximum money you can rob without robbing adjacent houses?

ANSWER: 19 (rob houses at indices 0, 2, 4, 6 → 6 + 1 + 8 + 4 = 19)

RECURRENCE RELATION:
dp[i] = max(A[i] + dp[i-2], dp[i-1])
        ↑                  ↑
    ROB house i        SKIP house i

--------------------------------------------------------------------------------

APPROACH: Bottom-Up (NO memoization, NO recursion)
- Build DP array from left to right
- Each dp[i] = maximum money robbed up to house i
- Time: O(n), Space: O(n)

--------------------------------------------------------------------------------

STEP 1: Initialize Base Cases

houses = [6, 7, 1, 3, 8, 2, 4]
Index:    0  1  2  3  4  5  6

dp[0] = A[0] = 6 (only one house, rob it)
dp[1] = max(A[0], A[1]) = max(6, 7) = 7 (rob the better one)

Initial state:
dp = [6, 7, ?, ?, ?, ?, ?]
      ↑  ↑
    base cases

--------------------------------------------------------------------------------

STEP 2: Build DP Array (Bottom-Up)

For i = 2:
  houses[2] = 1
  dp[2] = max(A[2] + dp[0], dp[1])
  dp[2] = max(1 + 6, 7)
  dp[2] = max(7, 7) = 7 ✓

  Decision: SKIP house 2 (or ROB, both give same result)

dp = [6, 7, 7, ?, ?, ?, ?]
            ↑

For i = 3:
  houses[3] = 3
  dp[3] = max(A[3] + dp[1], dp[2])
  dp[3] = max(3 + 7, 7)
  dp[3] = max(10, 7) = 10 ✓

  Decision: ROB house 3 (value 3) + use dp[1] (value 7)

dp = [6, 7, 7, 10, ?, ?, ?]
               ↑

For i = 4:
  houses[4] = 8
  dp[4] = max(A[4] + dp[2], dp[3])
  dp[4] = max(8 + 7, 10)
  dp[4] = max(15, 10) = 15 ✓

  Decision: ROB house 4 (value 8) + use dp[2] (value 7)

dp = [6, 7, 7, 10, 15, ?, ?]
                   ↑

For i = 5:
  houses[5] = 2
  dp[5] = max(A[5] + dp[3], dp[4])
  dp[5] = max(2 + 10, 15)
  dp[5] = max(12, 15) = 15 ✓

  Decision: SKIP house 5, keep dp[4] value

dp = [6, 7, 7, 10, 15, 15, ?]
                       ↑

For i = 6:
  houses[6] = 4
  dp[6] = max(A[6] + dp[4], dp[5])
  dp[6] = max(4 + 15, 15)
  dp[6] = max(19, 15) = 19 ✓

  Decision: ROB house 6 (value 4) + use dp[4] (value 15)

Final DP array:
dp = [6, 7, 7, 10, 15, 15, 19]
                           ↑
                      ANSWER = 19 ✓

--------------------------------------------------------------------------------

STEP 3: VISUAL - DP Array Evolution

houses = [6, 7, 1, 3, 8, 2, 4]
Index:    0  1  2  3  4  5  6

Step 0 (base):
dp = [6, ?, ?, ?, ?, ?, ?]

Step 1 (base):
dp = [6, 7, ?, ?, ?, ?, ?]

Step 2:
dp = [6, 7, 7, ?, ?, ?, ?]
     ↑      ↑
   dp[0]  used here (but dp[1]=7 was better)

Step 3:
dp = [6, 7, 7, 10, ?, ?, ?]
        ↑      ↑
      dp[1]  ROB house 3: 3+7=10 ✓

Step 4:
dp = [6, 7, 7, 10, 15, ?, ?]
            ↑      ↑
          dp[2]  ROB house 4: 8+7=15 ✓

Step 5:
dp = [6, 7, 7, 10, 15, 15, ?]
                  ↑    ↑
                dp[3] SKIP house 5, keep dp[4]

Step 6:
dp = [6, 7, 7, 10, 15, 15, 19]
                  ↑        ↑
                dp[4]    ROB house 6: 4+15=19 ✓

--------------------------------------------------------------------------------

STEP 4: Which Houses Were Robbed?

Backtrack through dp array to find which houses were robbed:

Start at i = 6 (last house):
  dp[6] = 19
  dp[6] = 19 > dp[5] = 15
  → ROB house 6 (value 4) ✓
  → Move to i = 4 (skip i-1 because we robbed i)

At i = 4:
  dp[4] = 15
  dp[4] = 15 > dp[3] = 10
  → ROB house 4 (value 8) ✓
  → Move to i = 2

At i = 2:
  dp[2] = 7
  dp[2] = 7 == dp[1] = 7
  → SKIP house 2 (or ROB, same result)
  → Move to i = 0

At i = 0:
  dp[0] = 6
  → ROB house 0 (value 6) ✓

Houses Robbed: [0, 2, 4, 6] → 6 + 1 + 8 + 4 = 19 ✓

(Note: [0, 4, 6] → 6 + 8 + 4 = 18, but we actually got [0, 2, 4, 6])

Actually, let me trace more carefully:
If dp[6] = 19 came from 4 + dp[4] = 4 + 15
And dp[4] = 15 came from 8 + dp[2] = 8 + 7
And dp[2] = 7 came from max(1+6, 7) = 7 (could be either)

Best path: Houses [1, 4, 6] → 7 + 8 + 4 = 19 ✓
OR: Houses [0, 2, 4, 6] → 6 + 1 + 8 + 4 = 19 ✓

--------------------------------------------------------------------------------

KEY DIFFERENCES from Top-Down:

1. NO Recursion:
   - Top-Down: Recursive calls with memoization
   - Bottom-Up: Simple for loop, no function calls

2. NO Memoization Table:
   - Top-Down: Needs memoTable to check if computed
   - Bottom-Up: Naturally computes each value once

3. Direction:
   - Top-Down: Start from end, work backwards
   - Bottom-Up: Start from beginning, work forward

4. Space Usage:
   - Top-Down: dp array + memoTable + recursion stack
   - Bottom-Up: Only dp array (can optimize to O(1))

5. Ease of Understanding:
   - Top-Down: More intuitive (matches problem description)
   - Bottom-Up: More efficient (no recursion overhead)

Both approaches have SAME time complexity: O(n)
But Bottom-Up is slightly faster in practice (no recursion overhead)

================================================================================
*/
