package algorithms.greedy

/*
Problem: Tie Ropes

There are N ropes numbered from 0 to N − 1, whose lengths are given in an array A.
You can tie adjacent ropes together, summing their lengths.

The goal is to tie the ropes in such a way that the number of ropes whose length
is greater than or equal to K is maximal.

Greedy Approach:
- Keep summing rope lengths until we reach or exceed K
- Once we reach K, we have a valid tied rope, count it and reset
- Continue with the next ropes
*/

fun main() {
    // Test 1: Normal case (with debug output)
    println("=== Test 1: Normal case ===")
    val result = tieRopes(4, intArrayOf(1, 2, 3, 4, 1, 1, 3))
    println("Result: $result")
    println("Expected: 3")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val result2 = solutionQuiet(4, intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: K = 0
    println("=== Test 3: K = 0 ===")
    val result3 = solutionQuiet(0, intArrayOf(1, 2, 3))
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: All ropes too short
    println("=== Test 4: All ropes too short ===")
    val result4 = solutionQuiet(10, intArrayOf(1, 1, 1, 1))
    println("Result: $result4")
    println("Expected: 0")
    println()

    // Test 5: Single rope >= K
    println("=== Test 5: Single rope >= K ===")
    val result5 = solutionQuiet(3, intArrayOf(5))
    println("Result: $result5")
    println("Expected: 1")
    println()

    // Test 6: All ropes exactly K
    println("=== Test 6: All ropes exactly K ===")
    val result6 = solutionQuiet(2, intArrayOf(2, 2, 2, 2))
    println("Result: $result6")
    println("Expected: 4")
}

// Quiet version for testing
private fun solutionQuiet(K: Int, A: IntArray): Int {
    if (A.isEmpty() || K <= 0) return 0
    var windowSum = 0
    var count = 0
    for (i in A.indices) {
        if (windowSum + A[i] >= K) {
            count += 1
            windowSum = 0
        } else {
            windowSum += A[i]
        }
    }
    return count
}

// TIME COMPLEXITY: O(n) - single pass through array summing rope lengths
// SPACE COMPLEXITY: O(1) - only constant variables for counting and sum
private fun tieRopes(K: Int, A: IntArray): Int {
    // Edge case: empty array or K <= 0
    if (A.isEmpty() || K <= 0) {
        return 0
    }

    var windowSum = 0
    var count = 0

    for (i in A.indices) {
        println("process ${A[i]}")
        println("after window_sum with current ${windowSum + A[i]}")

        if (windowSum + A[i] >= K) {
            count += 1
            windowSum = 0
            println("found...count $count")
        } else {
            windowSum += A[i]
        }
    }

    return count
}
