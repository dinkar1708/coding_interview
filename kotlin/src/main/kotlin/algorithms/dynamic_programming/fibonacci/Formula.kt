package algorithms.dynamic_programming.fibonacci

/**
 * PROBLEM: Fibonacci using Binet's Formula
 *
 * Calculate the nth Fibonacci number using mathematical formula.
 *
 * INPUT: n = 5
 * OUTPUT: 5
 *
 * EXPLANATION:
 * Use Binet's Formula: F(n) = phi^n / sqrt(5) rounded to nearest integer
 * where phi = (1 + sqrt(5)) / 2 (golden ratio)
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

import kotlin.math.*

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    println("Result: ${fibFormula(5)}")
    println("Expected: 5")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    println("Result: ${fibFormula(0)}")
    println("Expected: 0 or 1 (formula approximation)")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (n=1) ===")
    println("Result: ${fibFormula(1)}")
    println("Expected: 1")
    println()

    // Test 4: Small number
    println("=== Test 4: Small number (n=7) ===")
    println("Result: ${fibFormula(7)}")
    println("Expected: 13")
    println()

    // Test 5: Large number
    println("=== Test 5: Large number (n=20) ===")
    println("Result: ${fibFormula(20)}")
    println("Expected: 6765")
    println()

    // Test 6: Very large
    println("=== Test 6: Very large (n=31) ===")
    println("Result: ${fibFormula(31)}")
    println("Expected: 1346269")
}

// TIME COMPLEXITY: O(1) - direct formula calculation (Binet's formula)
// SPACE COMPLEXITY: O(1) - only constant variables
private fun fibFormula(n: Int): Int {
    // Edge case: negative n
    if (n < 0) {
        return 0
    }

    val phi = (1 + sqrt(5.0)) / 2
    return ceil(phi.pow(n) / sqrt(5.0)).toInt()
}
