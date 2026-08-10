package algorithms.dynamic_programming.coin_change

/**
 * PROBLEM: Coin Change - 1D DP Array (Space Optimized)
 *
 * Coins can be used UNLIMITED times.
 * Count ways to make amount using given coins.
 *
 * INPUT: coins = [1, 2], amount = 3
 * OUTPUT: 2 ways
 *
 * VISUALIZATION: 1D DP array updates step-by-step
 *
 * dp[amount] = ways to make that amount
 *
 * Example: coins = [1, 2], amount = 3
 *
 * Initial:
 * dp = [1, 0, 0, 0]
 *       ↑
 *     amount=0 has 1 way (no coins)
 *
 * After processing coin = 1:
 * dp = [1, 1, 1, 1]
 *          ↑  ↑  ↑
 *        Each amount has 1 way using only coin 1
 *
 * After processing coin = 2:
 * dp = [1, 1, 2, 2]
 *             ↑  ↑
 *       amount=2: [1,1] or [2] = 2 ways
 *       amount=3: [1,1,1] or [1,2] = 2 ways
 *
 * Answer = dp[3] = 2
 *
 * How it works:
 * For each coin:
 *   For each amount >= coin:
 *     dp[amount] += dp[amount - coin]
 *
 * Time: O(coins × amount), Space: O(amount)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = coinChangeTopDown(intArrayOf(1, 2, 5), 5)
    println("Result: $result1")
    println("Expected: 4")
    println()

    // Test 2: Edge case - amount is 0
    println("=== Test 2: Edge case - amount is 0 ===")
    val result2 = coinChangeTopDown(intArrayOf(1, 2, 5), 0)
    println("Result: $result2")
    println("Expected: 1")
    println()

    // Test 3: Edge case - empty coins
    println("=== Test 3: Edge case - empty coins ===")
    val result3 = coinChangeTopDown(intArrayOf(), 5)
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Single coin
    println("=== Test 4: Single coin ===")
    val result4 = coinChangeTopDown(intArrayOf(3), 9)
    println("Result: $result4")
    println("Expected: 1 ([3,3,3])")
    println()

    // Test 5: No solution
    println("=== Test 5: No solution ===")
    val result5 = coinChangeTopDown(intArrayOf(2), 3)
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Multiple combinations
    println("=== Test 6: Multiple combinations ===")
    val result6 = coinChangeTopDown(intArrayOf(1, 2, 3), 4)
    println("Result: $result6")
    println("Expected: 4")
}

// 1D DP ARRAY (Space Optimized)
// TIME COMPLEXITY: O(n × amount) - iterate coins × amounts
// SPACE COMPLEXITY: O(amount) - 1D array only (optimized from 2D)
private fun coinChangeTopDown(amountsParam: IntArray, amount: Int): Int {
    // Edge case: empty coins or amount < 0
    if (amountsParam.isEmpty() || amount < 0) {
        return 0
    }

    // dp[i] = number of ways to make amount i
    val dp = IntArray(amount + 1)
    dp[0] = 1 // One way to make 0: use no coins

    for (coin in amountsParam) {
        for (x in coin..amount) {
            dp[x] += dp[x - coin]
        }
        println("for coin $coin: ${dp.contentToString()}")
    }

    return dp[amount]
}
