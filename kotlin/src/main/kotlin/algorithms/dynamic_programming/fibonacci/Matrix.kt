package algorithms.dynamic_programming.fibonacci

/**
 * PROBLEM: Fibonacci using Matrix Exponentiation
 *
 * Calculate the nth Fibonacci number using matrix exponentiation.
 *
 * INPUT: n = 31
 * OUTPUT: 1346269
 *
 * EXPLANATION:
 * This is one of the most efficient approaches for large n.
 * The Fibonacci recurrence can be represented as:
 * [F(n+1)]   [1 1]^n   [1]
 * [F(n)  ] = [1 0]   * [0]
 * Use binary exponentiation for fast matrix power calculation.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(log n) - recursion stack
 */

import kotlin.math.*

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    println("Result (Matrix): ${fibMatrix(5)}")
    println("Result (Formula): ${fibFormula(5)}")
    println("Expected: 5")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    println("Result (Matrix): ${fibMatrix(0)}")
    println("Expected: 0")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (n=1) ===")
    println("Result (Matrix): ${fibMatrix(1)}")
    println("Expected: 1")
    println()

    // Test 4: Larger number
    println("=== Test 4: Larger number (n=10) ===")
    println("Result (Matrix): ${fibMatrix(10)}")
    println("Result (Formula): ${fibFormula(10)}")
    println("Expected: 55")
    println()

    // Test 5: Very large
    println("=== Test 5: Very large (n=31) ===")
    println("Result (Matrix): ${fibMatrix(31)}")
    println("Result (Formula): ${fibFormula(31)}")
    println("Expected: 1346269")
    println()

    // Test 6: Another large
    println("=== Test 6: Another large (n=20) ===")
    println("Result (Matrix): ${fibMatrix(20)}")
    println("Result (Formula): ${fibFormula(20)}")
    println("Expected: 6765")
}

// TIME COMPLEXITY: O(log n) - matrix exponentiation using fast power
// SPACE COMPLEXITY: O(log n) - recursion depth for power function
private fun fibMatrix(n: Int): Int {
    val F = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 0)
    )

    if (n == 0) {
        return 0
    }

    power(F, n - 1)
    println(F.map { it.contentToString() })
    return F[0][0]
}

private fun multiply(F: Array<IntArray>, M: Array<IntArray>) {
    // Multiply two 2x2 matrices
    val x = F[0][0] * M[0][0] + F[0][1] * M[1][0]
    val y = F[0][0] * M[0][1] + F[0][1] * M[1][1]
    val z = F[1][0] * M[0][0] + F[1][1] * M[1][0]
    val w = F[1][0] * M[0][1] + F[1][1] * M[1][1]

    F[0][0] = x
    F[0][1] = y
    F[1][0] = z
    F[1][1] = w
}

private fun power(F: Array<IntArray>, n: Int) {
    if (n == 0 || n == 1) {
        return
    }

    val M = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 0)
    )

    power(F, n / 2)
    multiply(F, F)

    if (n % 2 != 0) {
        multiply(F, M)
    }
}

private fun fibFormula(n: Int): Int {
    // Using formula - O(1) time
    val phi = (1 + sqrt(5.0)) / 2
    return ceil(phi.pow(n) / sqrt(5.0)).toInt()
}
