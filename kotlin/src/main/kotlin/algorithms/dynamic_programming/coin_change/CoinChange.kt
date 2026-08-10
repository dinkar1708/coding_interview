package algorithms.dynamic_programming.coin_change

/**
 * PROBLEM: Coin Change - Count Ways
 *
 * Given an array of coin denominations and a sum, count how many ways
 * we can make change for that sum using the given coins.
 *
 * INPUT: coins = [1, 2, 5], sum = 5
 * OUTPUT: 4 ways
 *
 * EXPLANATION:
 * For each coin, we have two choices:
 * 1. Include the coin (subtract its value from sum)
 * 2. Exclude the coin (move to next coin)
 * Possible combinations: [1,1,1,1,1], [1,1,1,2], [1,2,2], [5]
 *
 * APPROACHES:
 * 1. Recursive: O(2^n) time, O(n) space
 * 2. Memoization (Top-Down DP): O(n * sum) time, O(n * sum) space
 * 3. Tabulation (Bottom-Up DP): O(n * sum) time, O(n * sum) space
 */

fun main() {
    println("APPROACH 1: RECURSIVE (Slow - Exponential Time)")
    println()

    // Test 1: Normal case - Recursive
    println("=== Test 1: Normal case (Recursive) ===")
    val result1 = coinChangeRecursive(intArrayOf(1, 2, 5), 3, 5)
    println("Result: $result1")  // Result: 4
    println("Expected: 4")
    println()

    // Test 2: Edge case - sum is 0
    println("=== Test 2: Edge case - sum is 0 (Recursive) ===")
    val result2 = coinChangeRecursive(intArrayOf(1, 2, 5), 3, 0)
    println("Result: $result2")  // Result: 1
    println("Expected: 1 (empty set)")
    println()

    println("------------------------------------------------------------")
    println("APPROACH 2: MEMOIZATION (Fast - O(n * sum) Time)")
    println()

    // Test 1: Normal case - Memoization
    println("=== Test 1: Normal case (Memoization) ===")
    val result3 = coinChangeMemoization(intArrayOf(1, 2, 5), 5)
    println("Result: $result3")  // Result: 4
    println("Expected: 4")
    println()

    // Test 2: Edge case - sum is 0
    println("=== Test 2: Edge case - sum is 0 (Memoization) ===")
    val result4 = coinChangeMemoization(intArrayOf(1, 2, 5), 0)
    println("Result: $result4")  // Result: 1
    println("Expected: 1 (empty set)")
    println()

    // Test 3: Edge case - no coins
    println("=== Test 3: Edge case - no coins (Memoization) ===")
    val result5 = coinChangeMemoization(intArrayOf(), 5)
    println("Result: $result5")  // Result: 0
    println("Expected: 0")
    println()

    // Test 4: Single coin
    println("=== Test 4: Single coin (Memoization) ===")
    val result6 = coinChangeMemoization(intArrayOf(2), 4)
    println("Result: $result6")  // Result: 1
    println("Expected: 1 ([2,2])")
    println()

    // Test 5: Impossible sum
    println("=== Test 5: Impossible sum (Memoization) ===")
    val result7 = coinChangeMemoization(intArrayOf(2, 5), 3)
    println("Result: $result7")  // Result: 0
    println("Expected: 0 (cannot make 3)")
    println()

    // Test 6: Larger sum
    println("=== Test 6: Larger sum (Memoization) ===")
    val result8 = coinChangeMemoization(intArrayOf(1, 2, 3), 4)
    println("Result: $result8")  // Result: 4
    println("Expected: 4 ([1,1,1,1], [1,1,2], [2,2], [1,3])")
    println()

    // Test 7: Performance comparison
    println("=== Test 7: Performance comparison ===")
    val coins = intArrayOf(1, 2, 5)
    val sum = 10

    println("Coins: ${coins.contentToString()}, Sum: $sum")

    val startRecursive = System.currentTimeMillis()
    val recursiveResult = coinChangeRecursive(coins, coins.size, sum)
    val recursiveTime = System.currentTimeMillis() - startRecursive

    val startMemo = System.currentTimeMillis()
    val memoResult = coinChangeMemoization(coins, sum)
    val memoTime = System.currentTimeMillis() - startMemo

    println("Recursive result: $recursiveResult, Time: ${recursiveTime}ms")  // Slower
    println("Memoization result: $memoResult, Time: ${memoTime}ms")  // Faster
    println("Both should give same answer: ${recursiveResult == memoResult}")
}

// APPROACH 1: RECURSIVE SOLUTION (Exponential Time - Slow)
// TIME COMPLEXITY: O(2^(n+sum)) - exponential (very slow for large inputs)
// SPACE COMPLEXITY: O(n + sum) - recursion stack depth
//
// Example: coins = [1, 2], sum = 3
// How many ways to make 3?
//
// Recursion Tree (each node shows duplicate work):
//
//                     (n=2, sum=3)
//                     "Make 3 using [1,2]"
//                            |
//              +-------------+-------------+
//              |                           |
//        EXCLUDE [1]=2                INCLUDE [1]=2
//        (move next)                  (use 2, reduce sum)
//              |                           |
//              v                           v
//         (n=1, sum=3)                (n=2, sum=1)
//         "Make 3 using [1]"          "Make 1 using [1,2]"
//              |                           |
//        +-----+-----+               +-----+-----+
//        |           |               |           |
//    EXCLUDE     INCLUDE         EXCLUDE     INCLUDE
//        |           |               |           |
//        v           v               v           v
//    (n=0, sum=3) (n=1, sum=2)  (n=1, sum=1) (n=2, sum=-1)
//    return 0     "Make 2        "Make 1     return 0
//                 with [1]"      with [1]"
//                      |              |
//                  +---+---+      +---+---+
//                  |       |      |       |
//               (n=0,  (n=1,   (n=0,  (n=1,
//               sum=2) sum=1)  sum=1) sum=0)
//               ret 0  "1 way" ret 0  ret 1
//                         |
//                     +---+---+
//                     |       |
//                  (n=0,  (n=1,
//                  sum=1) sum=0)
//                  ret 0  ret 1
//
// Notice: (n=1, sum=1) computed TWICE! This is the problem!
// Without memoization: O(2^n) - exponential time
//
// Final answer: 2 ways ([1,1,1] and [1,2])
//
private fun coinChangeRecursive(ar: IntArray, n: Int, sum: Int): Int {
    // Base case: if sum is 0, we found one solution
    if (sum == 0) {
        return 1
    }

    // Base case: if sum is negative, no solution
    if (sum < 0) {
        return 0
    }

    // Base case: no coins but sum is greater than 0, no solution
    if (n <= 0 && sum >= 1) {
        return 0
    }

    // Two options:
    // 1) excluding ar[n-1] - move to next coin
    // 2) including ar[n-1] - keep same coin, reduce sum
    return coinChangeRecursive(ar, n - 1, sum) + coinChangeRecursive(ar, n, sum - ar[n - 1])
}

// APPROACH 2: MEMOIZATION (Top-Down DP - Fast)
// TIME COMPLEXITY: O(n × sum) - each state computed once
// SPACE COMPLEXITY: O(n × sum) - memo table + O(n) recursion stack
//
// Same problem: coins = [1, 2], sum = 3
//
// With Memoization - Avoid duplicate work:
//
//                     (n=2, sum=3)
//                            |
//              +-------------+-------------+
//              |                           |
//         (n=1, sum=3)                (n=2, sum=1)
//         COMPUTE                     COMPUTE
//         memo[1][3] = 1              memo[2][1] = 1
//              |                           |
//        +-----+-----+               +-----+-----+
//        |           |               |           |
//    (n=0, sum=3) (n=1, sum=2)  (n=1, sum=1) (n=2, sum=-1)
//    return 0     COMPUTE        COMPUTE     return 0
//                 memo[1][2]=1   memo[1][1]=1
//                      |              |
//                  +---+---+      +---+---+
//                  |       |      |       |
//               (n=0,  (n=1,   (n=0,  (n=1,
//               sum=2) sum=1)  sum=1) sum=0)
//               ret 0  🔥CACHE  ret 0  ret 1
//                      HIT!
//                      memo[1][1]
//                      NO RECURSION!
//
// Memo Table Evolution:
//
// Initial:        After (n=1,sum=1):   After all:
// [sum] 0 1 2 3   [sum] 0 1 2 3        [sum] 0 1 2 3
// [n=0] -1 -1 -1 -1  [0] -1 -1 -1 -1       [0] -1 -1 -1 -1
// [n=1] -1 -1 -1 -1  [1]  1  1 -1 -1       [1]  1  1  1  1
// [n=2] -1 -1 -1 -1  [2] -1 -1 -1 -1       [2] -1  1 -1  2
//
// Key: Each (n, sum) computed ONCE, reused forever!
// Time: O(n × sum) - polynomial, much faster!
//
private fun coinChangeMemoization(coins: IntArray, sum: Int): Int {
    // Edge case: no coins
    if (coins.isEmpty()) {
        return if (sum == 0) 1 else 0
    }

    // Create memo table: memo[coinIndex][remainingSum]
    // -1 means not computed yet
    val memo = Array(coins.size + 1) { IntArray(sum + 1) { -1 } }

    return coinChangeMemo(coins, coins.size, sum, memo)
}

private fun coinChangeMemo(coins: IntArray, n: Int, sum: Int, memo: Array<IntArray>): Int {
    // Base case: if sum is 0, we found one solution
    if (sum == 0) {
        return 1
    }

    // Base case: if sum is negative, no solution
    if (sum < 0) {
        return 0
    }

    // Base case: no coins but sum is greater than 0, no solution
    if (n <= 0 && sum >= 1) {
        return 0
    }

    // Check if already computed
    if (memo[n][sum] != -1) {
        return memo[n][sum]
    }

    // Two options:
    // 1) excluding coins[n-1] - move to next coin
    val exclude = coinChangeMemo(coins, n - 1, sum, memo)

    // 2) including coins[n-1] - keep same coin, reduce sum
    val include = coinChangeMemo(coins, n, sum - coins[n - 1], memo)

    // Store result in memo and return
    memo[n][sum] = exclude + include
    return memo[n][sum]
}
