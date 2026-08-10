package algorithms.dynamic_programming.subsequence

/**
 * PROBLEM: House Robber - Maximum Stolen Value
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
 * Bottom-Up DP with O(1) space optimization.
 * Keep only last two values instead of entire DP array.
 * dp_last = max(A[i] + dp_last_to_last, dp_last)
 * Rob houses at indices 0, 2, 4, 6 -> 6+1+8+4=19
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
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
    println("Expected: 10")
    println()

    // Test 5: All same values
    println("=== Test 5: All same values ===")
    val result5 = maxProfit(intArrayOf(5, 5, 5, 5))
    println("Result: $result5")
    println("Expected: 10 (rob 0 and 2)")
    println()

    // Test 6: Alternating values
    println("=== Test 6: Alternating values ===")
    val result6 = maxProfit(intArrayOf(2, 7, 9, 3, 1))
    println("Result: $result6")
    println("Expected: 12 (2+9+1)")

    /*
    Expected detailed output for test 1:
    Array: [6, 7, 1, 3, 8, 2, 4]
    Index:  0  1  2  3  4  5  6

    INITIALIZATION (before loop):
    dpLastToLast = A[0] = 6           (max money from house 0)
    dpLast = max(A[0], A[1]) = 7      (max money from houses 0-1)

    WHY dpLastToLast = 6?
    → It represents dp[0] = max money we can rob up to index 0
    → Only one house (index 0), so best we can do is rob it = 6

    WHY dpLast = 7?
    → It represents dp[1] = max money we can rob up to index 1
    → Two choices: rob house 0 (=6) OR rob house 1 (=7)
    → Best choice: rob house 1 = 7

    ------------------------------------------------------------------------

    LOOP starts from i=2 (we already handled i=0 and i=1 in initialization)

    For each i: 2
    A[i]: 1
    DP dp_last_to_last: 6   (represents dp[0] = max up to house 0)
    DP dp_last: 7           (represents dp[1] = max up to house 1)
    Calculation: max(1+6, 7) = max(7, 7) = 7

    For each i: 3
    A[i]: 3
    DP dp_last_to_last: 7   (now represents dp[1])
    DP dp_last: 7           (now represents dp[2])
    Calculation: max(3+7, 7) = max(10, 7) = 10

    For each i: 4
    A[i]: 8
    DP dp_last_to_last: 7   (now represents dp[2])
    DP dp_last: 10          (now represents dp[3])
    Calculation: max(8+7, 10) = max(15, 10) = 15

    For each i: 5
    A[i]: 2
    DP dp_last_to_last: 10  (now represents dp[3])
    DP dp_last: 15          (now represents dp[4])
    Calculation: max(2+10, 15) = max(12, 15) = 15

    For each i: 6
    A[i]: 4
    DP dp_last_to_last: 15  (now represents dp[4])
    DP dp_last: 15          (now represents dp[5])
    Calculation: max(4+15, 15) = max(19, 15) = 19

    Max Profit: 19
    */
}

// TIME COMPLEXITY: O(n) - iterate through houses once building DP array
// SPACE COMPLEXITY: O(n) - DP array storing maximum value at each house
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

    // dp_last_to_last = dp[0]
    var dpLastToLast = A[0]
    // dp_last = dp[1]
    var dpLast = maxOf(A[1], dpLastToLast)

    // Find other dp values starting from index 2
    for (i in 2 until n) {
        println("For each i: $i")
        println("A[i]: ${A[i]}")

        // Store the last value temporarily
        val temp = dpLast
        println("DP dp_last_to_last: $dpLastToLast")
        println("DP dp_last: $dpLast")

        // DP condition: max of (current + last_to_last, last)
        dpLast = maxOf(A[i] + dpLastToLast, dpLast)

        // Update last_to_last with the old last value
        dpLastToLast = temp
    }

    // Last dp value should be the max profit
    return dpLast
}

/*
================================================================================
SPACE-OPTIMIZED APPROACH - SIMPLE EXAMPLE [5, 1, 2, 10]
================================================================================

Full DP array: [5, 5, 7, 15]
Space-optimized: Keep only 2 values at a time

INITIALIZATION:
dpLastToLast = 5 (dp[0])    dpLast = 5 (dp[1])
Why? dp[0]=A[0]=5, dp[1]=max(5,1)=5

i=2, A[2]=2:
  BEFORE: dpLastToLast=5(dp[0]), dpLast=5(dp[1])
  CALCULATE: max(2+5, 5) = 7
  AFTER: dpLastToLast=5(dp[1]), dpLast=7(dp[2])

  Sliding: [5, 5, ?, ?]  →  [5, 5, 7, ?]
                ↑  ↑              ↑  ↑

i=3, A[3]=10:
  BEFORE: dpLastToLast=5(dp[1]), dpLast=7(dp[2])
  CALCULATE: max(10+5, 7) = 15
  AFTER: dpLastToLast=7(dp[2]), dpLast=15(dp[3])

  Sliding: [5, 5, 7, ?]  →  [5, 5, 7, 15]
                   ↑  ↑                 ↑  ↑

FINAL: dpLast = 15

KEY INSIGHT:
dpLastToLast = dp[i-2] (use if ROB current house)
dpLast = dp[i-1] (use if SKIP current house)

Formula: dp[i] = max(A[i] + dp[i-2], dp[i-1])

Each iteration, slide window forward, discard old values!

================================================================================
*/
