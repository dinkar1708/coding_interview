package algorithms.dynamic_programming.fibonacci

/**
 * PROBLEM: Fibonacci using Simple Recursion (Top-Down)
 *
 * Calculate the nth Fibonacci number using pure recursion.
 *
 * INPUT: n = 5
 * OUTPUT: 5
 *
 * EXPLANATION:
 * This is the simplest but least efficient approach.
 * Pure recursion without memoization.
 * F(n) = F(n-1) + F(n-2) with base cases F(0)=0, F(1)=1
 *
 * Time Complexity: O(2^n) - exponential (very slow)
 * Space Complexity: O(n) - recursion stack depth
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    println("Result: ${fibMemoization(5)}")
    println("Expected: 5")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    println("Result: ${fibMemoization(0)}")
    println("Expected: 0")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (n=1) ===")
    println("Result: ${fibMemoization(1)}")
    println("Expected: 1")
    println()

    // Test 4: Small number
    println("=== Test 4: Small number (n=3) ===")
    println("Result: ${fibMemoization(3)}")
    println("Expected: 2")
    println()

    // Test 5: Note - This is very slow for large n
    println("=== Test 5: Medium (n=8) - slow due to no memoization ===")
    println("Result: ${fibMemoization(8)}")
    println("Expected: 21")
    println()

    // Test 6: Another test
    println("=== Test 6: Another test (n=6) ===")
    println("Result: ${fibMemoization(6)}")
    println("Expected: 8")
}

// TIME COMPLEXITY: O(2^n) - exponential without memoization (separate from other memoized version)
// SPACE COMPLEXITY: O(n) - recursion stack depth
private fun fibMemoization(n: Int): Int {
    // Using recursion, top down approach (without memoization in this simple version)
    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return 1
    }
    return fibMemoization(n - 1) + fibMemoization(n - 2)
}
