package algorithms.prefix_sum

/**
 * PROBLEM: Count Divisible Numbers in Range
 *
 * Write a function that, given three integers A, B and K, returns the number of integers
 * within the range [A..B] that are divisible by K.
 *
 * INPUT: A = 6, B = 11, K = 2
 * OUTPUT: 3
 * EXPLANATION: Numbers divisible by 2 in range [6..11]: 6, 8, 10 (3 numbers)
 *
 * APPROACH:
 * - Count of multiples of K up to B: floor(B / K)
 * - Count of multiples of K up to A-1: ceil(A / K) - 1
 * - Answer: floor(B / K) - floor((A - 1) / K)
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = countDivisible(6, 11, 2)
    println("Result: $result1")
    println("Expected: 3 (numbers 6, 8, 10)")
    println()

    // Test 2: Edge case - K is 0
    println("=== Test 2: Edge case - K is 0 ===")
    val result2 = countDivisible(1, 10, 0)
    println("Result: $result2")
    println("Expected: 0 (cannot divide by zero)")
    println()

    // Test 3: Edge case - single number range
    println("=== Test 3: Edge case - single number range ===")
    val result3 = countDivisible(5, 5, 5)
    println("Result: $result3")
    println("Expected: 1 (5 is divisible by 5)")
    println()

    // Test 4: Edge case - A > B
    println("=== Test 4: Edge case - A > B ===")
    val result4 = countDivisible(10, 5, 2)
    println("Result: $result4")
    println("Expected: 0 (invalid range)")
    println()

    // Test 5: Edge case - no divisible numbers
    println("=== Test 5: Edge case - no divisible numbers ===")
    val result5 = countDivisible(1, 2, 3)
    println("Result: $result5")
    println("Expected: 0 (no multiples of 3 in range [1..2])")
    println()

    // Test 6: Large range
    println("=== Test 6: Large range ===")
    val result6 = countDivisible(0, 2000000000, 1)
    println("Result: $result6")
    println("Expected: 2000000001 (all numbers divisible by 1)")
}

// TIME COMPLEXITY: O(1) - mathematical formula using integer division
// SPACE COMPLEXITY: O(1) - only constant variables for calculation
private fun countDivisible(A: Int, B: Int, K: Int): Int {
    // Edge case: K = 0 or A > B
    if (K == 0 || A > B) {
        return 0
    }

    // Count divisible numbers in range [A..B]
    // floor(B/K) gives count of multiples up to B
    // floor((A-1)/K) gives count of multiples up to A-1
    return B / K - (A - 1) / K
}
