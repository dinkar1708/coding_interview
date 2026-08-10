package algorithms.dynamic_programming.subsequence

/**
 * PROBLEM: Best Time to Buy and Sell Stock II
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times).
 *
 * INPUT: prices = [7, 1, 5, 3, 6, 4]
 * OUTPUT: 7
 *
 * EXPLANATION:
 * Add up all positive differences between consecutive days.
 * Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit = 4 + 3 = 7.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
    println("Result: $result1")
    println("Expected: 7 ((5-1)+(6-3))")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = maxProfit(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single element
    println("=== Test 3: Edge case - single element ===")
    val result3 = maxProfit(intArrayOf(5))
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Decreasing prices
    println("=== Test 4: Decreasing prices ===")
    val result4 = maxProfit(intArrayOf(7, 6, 4, 3, 1))
    println("Result: $result4")
    println("Expected: 0 (no profit)")
    println()

    // Test 5: Increasing prices
    println("=== Test 5: Increasing prices ===")
    val result5 = maxProfit(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result5")
    println("Expected: 4 (buy at 1, sell at 5)")
    println()

    // Test 6: Multiple peaks and valleys
    println("=== Test 6: Multiple peaks and valleys ===")
    val result6 = maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4))
    println("Result: $result6")
    println("Expected: 8 ((5-3)+(3-0)+(4-1))")
}

// TIME COMPLEXITY: O(n) - single pass tracking min price and max profit
// SPACE COMPLEXITY: O(1) - only two variables

private fun maxProfit(prices: IntArray): Int {
    // Edge case: empty array or single element
    if (prices.isEmpty() || prices.size == 1) {
        return 0
    }

    var maxProfit = 0

    for (i in 1 until prices.size) {
        if (prices[i] > prices[i - 1]) {
            println("Price at next day is greater than previous day...Next day & prev day: ${prices[i]} ${prices[i - 1]}")
            maxProfit += prices[i] - prices[i - 1]
            println("Current profit: $maxProfit")
        }
    }

    return maxProfit
}
