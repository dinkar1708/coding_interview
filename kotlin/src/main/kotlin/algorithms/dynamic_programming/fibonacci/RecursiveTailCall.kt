package algorithms.dynamic_programming.fibonacci

/**
 * PROBLEM: Fibonacci using Recursion (Alternative approach)
 *
 * Calculate Fibonacci sequence using current and previous values.
 *
 * INPUT: n = 5
 * OUTPUT: 7 (sum of all fibonacci numbers up to index 5)
 *
 * EXPLANATION:
 * This approach maintains current and previous values through recursion,
 * showing how the Fibonacci sequence progresses at each step.
 * Uses current and prev value: fib(n) = fib(n-1) + fib(n-2)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) - recursion stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    println("Result: ${fibSimple(5, 0, 1, 1)}")
    println("Expected: 7 (sum)")
    println()

    // Test 2: Edge case - one
    println("=== Test 2: Edge case (n=1) ===")
    println("Result: ${fibSimple(1, 0, 1, 1)}")
    println("Expected: 0")
    println()

    // Test 3: Small number
    println("=== Test 3: Small number (n=3) ===")
    println("Result: ${fibSimple(3, 0, 1, 1)}")
    println("Expected: 2")
    println()

    // Test 4: Larger
    println("=== Test 4: Larger (n=6) ===")
    println("Result: ${fibSimple(6, 0, 1, 1)}")
    println("Expected: 12")
    println()

    // Test 5: Even larger
    println("=== Test 5: Even larger (n=7) ===")
    println("Result: ${fibSimple(7, 0, 1, 1)}")
    println("Expected: 20")
    println()

    // Test 6: Different start
    println("=== Test 6: Different start (n=4) ===")
    println("Result: ${fibSimple(4, 0, 1, 1)}")
    println("Expected: 4")
}

// TIME COMPLEXITY: O(n) - tail recursion equivalent to iteration
// SPACE COMPLEXITY: O(n) - recursion stack (optimized to O(1) by compiler with tail call optimization)
private fun fibSimple(n: Int, current: Int, prev: Int, index: Int): Int {
    // Edge case: negative n
    if (n < 0) {
        return 0
    }

    // Using current and prev value
    // fib(n) = fib(n-1) + fib(n-2)
    // Logs show how current and prev progress in each step

    println("Each recursion.... $index")
    println("Current recursion....current prev $current $prev")

    if (index == n) {
        // Print the final number
        println(current)
        return 0
    }

    // Print current number
    println(current)
    return current + prev + fibSimple(n, current + prev, current, index + 1)
}
