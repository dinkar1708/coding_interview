package algorithms.arrays.subarrays


/**
 * PROBLEM: Maximum Profit from Stock Prices
 *
 * Given an array of stock prices where A[i] is the price on day i,
 * find the maximum profit from buying and selling once.
 *
 * INPUT:
 *   A = [3, 2, 4, 1, 5]
 *
 * OUTPUT:
 *   4 (buy at 1, sell at 5)
 *
 * EXPLANATION:
 *   Transform the problem into maximum subarray sum of price deltas.
 *   For each day, calculate delta = price[i] - price[i-1]
 *   Then find maximum sum of consecutive deltas using Kadane's algorithm.
 *
 *   Prices:       [3, 2, 4, 1, 5]
 *   Price deltas: [-1, 2, -3, 4]
 *
 *   Using Kadane's algorithm:
 *   i=1: delta=-1, localMax=max(-1, 0+-1)=-1, globalMax=max(-1, 0)=0
 *   i=2: delta=2,  localMax=max(2, -1+2)=2,   globalMax=max(2, 0)=2
 *   i=3: delta=-3, localMax=max(-3, 2-3)=-1,  globalMax=max(-1, 2)=2
 *   i=4: delta=4,  localMax=max(4, -1+4)=4,   globalMax=max(4, 2)=4
 *
 *   Result: 4 (buy at 1, sell at 5, profit = 5 - 1 = 4)
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - constant space
 */

fun main() {
    // Test 1: Normal case - small values for visibility
    println("=== Test 1: Normal case ===")
    val result = maxProfit(intArrayOf(3, 2, 4, 1, 5))
    // Day 1: price=2, delta=-1, localMax=-1, globalMax=0
    // Day 2: price=4, delta=2, localMax=2, globalMax=2
    // Day 3: price=1, delta=-3, localMax=-1, globalMax=2
    // Day 4: price=5, delta=4, localMax=4, globalMax=4
    println("Prices: [3, 2, 4, 1, 5]")
    println("Deltas: [-1, 2, -3, 4]")
    println("Result: $result")  // Result: 4
    println("Expected: 4 (buy at 1, sell at 5)")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val result2 = maxProfit(intArrayOf())
    println("Input: []")
    println("Result: $result2")  // Result: 0
    println("Expected: 0")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val result3 = maxProfit(intArrayOf(100))
    println("Input: [100]")
    println("Result: $result3")  // Result: 0
    println("Expected: 0 (no transaction possible)")
    println()

    // Test 4: Decreasing prices (no profit)
    println("=== Test 4: Decreasing prices (no profit) ===")
    val result4 = maxProfit(intArrayOf(5, 4, 3, 2, 1))
    // All deltas negative: -1, -1, -1, -1
    // localMax stays negative, globalMax stays 0
    println("Input: [5, 4, 3, 2, 1]")
    println("Result: $result4")  // Result: 0
    println("Expected: 0 (only losses, so don't buy)")
    println()

    // Test 5: Increasing prices (max profit)
    println("=== Test 5: Increasing prices (max profit) ===")
    val result5 = maxProfit(intArrayOf(1, 2, 3, 4, 5))
    // All deltas positive: 1, 1, 1, 1
    // localMax accumulates: 1, 2, 3, 4
    println("Input: [1, 2, 3, 4, 5]")
    println("Result: $result5")  // Result: 4
    println("Expected: 4 (buy at 1, sell at 5)")
    println()

    // Test 6: Two same prices
    println("=== Test 6: Two same prices ===")
    val result6 = maxProfit(intArrayOf(10, 10))
    // Delta = 0, no profit
    println("Input: [10, 10]")
    println("Result: $result6")  // Result: 0
    println("Expected: 0 (no price change)")
}

// TIME COMPLEXITY: O(n) - single pass computing deltas and applying Kadane's algorithm
// SPACE COMPLEXITY: O(1) - only two variables for local and global max
private fun maxProfit(A: IntArray): Int {
    // Edge case: empty array or single element
    if (A.isEmpty() || A.size == 1) {
        return 0
    }

    var localMax = 0   // Maximum profit ending at current day
    var globalMax = 0  // Maximum profit seen so far

    for (i in 1 until A.size) {
        // Calculate price change (delta) from previous day
        val delta = A[i] - A[i - 1]  // Price change today

        // Kadane's algorithm: either start fresh or continue
        localMax = maxOf(delta, localMax + delta)

        // Update global maximum profit
        globalMax = maxOf(localMax, globalMax)

        // Debug output (comment out for production)
        println("Day $i: price=${A[i]}, delta=$delta, localMax=$localMax, globalMax=$globalMax")
    }

    return globalMax
}
