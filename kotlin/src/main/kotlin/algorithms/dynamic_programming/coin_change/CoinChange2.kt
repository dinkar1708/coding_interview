package algorithms.dynamic_programming.coin_change

/**
 * PROBLEM: Coin Change - Bottom Up DP (Tabulation)
 *
 * Coins can be used UNLIMITED times.
 * Count ways to make sum using given coins.
 *
 * INPUT: coins = [1, 2], sum = 3
 * OUTPUT: 2 ways
 *
 * VISUALIZATION: DP Table builds bottom-up
 *
 * table[sum][coin] = ways to make 'sum' using coins[0..coin]
 *
 * Example: coins = [1, 2], sum = 3
 *
 * Start (base: sum=0 = 1 way):
 *        [1]  [2]
 * sum=0   1    1   ← Base
 * sum=1   ?    ?
 * sum=2   ?    ?
 * sum=3   ?    ?
 *
 * Final table:
 *        [1]  [2]
 * sum=0   1    1
 * sum=1   1    1   ← 1 way: [1]
 * sum=2   1    2   ← 2 ways: [1,1] or [2]
 * sum=3   1    2   ← 2 ways: [1,1,1] or [1,2]
 *             ↑
 *           Answer = 2
 *
 * How we fill each cell:
 * table[sum][coin] = exclude + include
 *   exclude = table[sum][coin-1]        (don't use this coin)
 *   include = table[sum-coin_val][coin] (use this coin)
 *
 * Time: O(sum × n), Space: O(sum × n)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = count(intArrayOf(1, 2, 5), 3, 5)
    println("Result: $result1")
    println("Expected: 4")
    println()

    // Test 2: Edge case - sum is 0
    println("=== Test 2: Edge case - sum is 0 ===")
    val result2 = count(intArrayOf(1, 2, 5), 3, 0)
    println("Result: $result2")
    println("Expected: 1")
    println()

    // Test 3: Edge case - empty coins
    println("=== Test 3: Edge case - empty coins ===")
    val result3 = count(intArrayOf(), 0, 5)
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Single coin type
    println("=== Test 4: Single coin type ===")
    val result4 = count(intArrayOf(2), 1, 6)
    println("Result: $result4")
    println("Expected: 1 ([2,2,2])")
    println()

    // Test 5: Impossible sum
    println("=== Test 5: Impossible sum ===")
    val result5 = count(intArrayOf(3, 5), 2, 7)
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Multiple coins
    println("=== Test 6: Multiple coins ===")
    val result6 = count(intArrayOf(1, 2, 3), 3, 4)
    println("Result: $result6")
    println("Expected: 4")
}

// BOTTOM-UP DP (Tabulation)
// TIME COMPLEXITY: O(sum × n) - fill table of size sum × n
// SPACE COMPLEXITY: O(sum × n) - 2D table
private fun count(S: IntArray, n: Int, sum: Int): Int {
    // Edge case: empty coins or sum < 0
    if (S.isEmpty() || sum < 0) {
        return 0
    }

    // We need sum+1 rows as the table is constructed
    // in bottom up manner using the base case 0 value case (sum = 0)
    val table = Array(sum + 1) { IntArray(n) }

    // Fill the entries for 0 value case (sum = 0)
    // There is one way to make sum 0: use no coins
    for (i in 0 until n) {
        table[0][i] = 1
    }

    // Fill rest of the table entries in bottom up manner
    for (i in 1..sum) {
        for (j in 0 until n) {
            print("[$i]")

            // Count of solutions including S[j]
            val x = if (i - S[j] >= 0) table[i - S[j]][j] else 0

            // Count of solutions excluding S[j]
            val y = if (j >= 1) table[i][j - 1] else 0

            // Total count
            table[i][j] = x + y
        }
        println()
        println(table.map { it.contentToString() })
    }

    return table[sum][n - 1]
}
