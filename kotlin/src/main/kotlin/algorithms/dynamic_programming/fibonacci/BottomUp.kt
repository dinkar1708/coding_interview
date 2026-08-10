package algorithms.dynamic_programming.fibonacci

/**
 * PROBLEM: Fibonacci using Bottom-Up (Tabulation)
 *
 * Calculate the nth Fibonacci number using iterative bottom-up approach.
 *
 * INPUT: n = 5
 * OUTPUT: 5
 *
 * EXPLANATION:
 * This is an efficient approach that builds up the solution iteratively.
 * Use two variables to track previous two fibonacci numbers.
 * Update them iteratively: curr = prev + curr
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    println("Result: ${fibTabulation(5)}")
    println("Expected: 5")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    println("Result: ${fibTabulation(0)}")
    println("Expected: 0")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (n=1) ===")
    println("Result: ${fibTabulation(1)}")
    println("Expected: 1")
    println()

    // Test 4: Small number
    println("=== Test 4: Small number (n=3) ===")
    println("Result: ${fibTabulation(3)}")
    println("Expected: 2")
    println()

    // Test 5: Larger number
    println("=== Test 5: Larger number (n=10) ===")
    println("Result: ${fibTabulation(10)}")
    println("Expected: 55")
    println()

    // Test 6: Very large
    println("=== Test 6: Very large (n=15) ===")
    println("Result: ${fibTabulation(15)}")
    println("Expected: 610")
}

// TIME COMPLEXITY: O(n) - iterate from 2 to n building up fibonacci numbers
// SPACE COMPLEXITY: O(1) - only store last two fibonacci numbers
private fun fibTabulation(n: Int): Int {
    // Bottom up approach
    if (n == 0) return 0
    if (n == 1) return 1

    var prev = 0
    var curr = 1

    for (i in 2..n) {
        val sum = prev + curr
        prev = curr
        curr = sum
    }

    return curr
}
