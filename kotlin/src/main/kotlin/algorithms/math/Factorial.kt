package algorithms.math

/**
 * PROBLEM: Calculate Factorial
 *
 * Calculate n! (factorial of n) using different approaches:
 * 1. Simple recursion
 * 2. Memoization (top-down DP)
 * 3. Tabulation (bottom-up DP)
 *
 * INPUT:
 *   n = 5
 *
 * OUTPUT:
 *   120
 *
 * EXPLANATION:
 *   5! = 5 × 4 × 3 × 2 × 1 = 120
 *
 *   Simple Recursion: fact(n) = n * fact(n-1), base case: fact(0) = 1
 *   Memoization: Store computed values to avoid recomputation
 *   Tabulation: Build solution bottom-up from 0 to n
 *
 * Time Complexity: O(n) for all approaches
 * Space Complexity: O(n) for recursion stack / memoization array
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    val n1 = 5
    println("Recursion: ${fact(n1)}")
    println("Memoization: ${factMemoization(IntArray(n1 + 1), n1)}")
    println("Tabulation: ${factTabulation(n1)}")
    println("Expected: 120")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    println("Recursion: ${fact(0)}")
    println("Memoization: ${factMemoization(IntArray(1), 0)}")
    println("Tabulation: ${factTabulation(0)}")
    println("Expected: 1 (0! = 1)")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (n=1) ===")
    println("Recursion: ${fact(1)}")
    println("Memoization: ${factMemoization(IntArray(2), 1)}")
    println("Tabulation: ${factTabulation(1)}")
    println("Expected: 1")
    println()

    // Test 4: Small number
    println("=== Test 4: Small number (n=3) ===")
    val n4 = 3
    println("Recursion: ${fact(n4)}")
    println("Memoization: ${factMemoization(IntArray(n4 + 1), n4)}")
    println("Tabulation: ${factTabulation(n4)}")
    println("Expected: 6")
    println()

    // Test 5: Larger number
    println("=== Test 5: Larger number (n=7) ===")
    val n5 = 7
    println("Recursion: ${fact(n5)}")
    println("Memoization: ${factMemoization(IntArray(n5 + 1), n5)}")
    println("Tabulation: ${factTabulation(n5)}")
    println("Expected: 5040")
    println()

    // Test 6: n=10
    println("=== Test 6: n=10 ===")
    val n6 = 10
    println("Recursion: ${fact(n6)}")
    println("Memoization: ${factMemoization(IntArray(n6 + 1), n6)}")
    println("Tabulation: ${factTabulation(n6)}")
    println("Expected: 3628800")
}

// TIME COMPLEXITY: O(n) - n recursive calls
// SPACE COMPLEXITY: O(n) - recursion stack depth
private fun fact(n: Int): Int {
    // Edge case: negative n or n = 0
    if (n <= 0) {
        return 1
    }
    return n * fact(n - 1)
}

// TIME COMPLEXITY: O(n) - compute each value once with memoization
// SPACE COMPLEXITY: O(n) - memoization array + recursion stack
private fun factMemoization(ar: IntArray, n: Int): Int {
    // Top-down approach with memoization
    // Edge case: negative n or n = 0
    if (n <= 0) {
        return 1
    }
    if (ar[n] != 0) {
        return ar[n]
    }
    ar[n] = n * factMemoization(ar, n - 1)
    return ar[n]
}

// TIME COMPLEXITY: O(n) - iterate from 1 to n
// SPACE COMPLEXITY: O(n) - array storing factorials from 0 to n
private fun factTabulation(n: Int): Int {
    // Bottom-up approach
    // Edge case: negative n or n = 0
    if (n <= 0) {
        return 1
    }
    val ar = IntArray(n + 1) { 1 }
    for (i in 1..n) {
        ar[i] = ar[i - 1] * i
    }
    return ar[n]
}
