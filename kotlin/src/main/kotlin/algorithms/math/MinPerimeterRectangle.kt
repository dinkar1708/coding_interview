package algorithms.math

/**
 * PROBLEM: Minimal Perimeter Rectangle
 *
 * An integer N is given, representing the area of some rectangle.
 * The area of a rectangle whose sides are of length A and B is A * B,
 * and the perimeter is 2 * (A + B).
 *
 * The goal is to find the minimal perimeter of any rectangle whose area equals N.
 * The sides of this rectangle should be only integers.
 *
 * INPUT: N = 30
 * OUTPUT: 22
 * EXPLANATION: Rectangle (5, 6) has the minimal perimeter
 * - (1, 30) perimeter = 62
 * - (2, 15) perimeter = 34
 * - (3, 10) perimeter = 26
 * - (5, 6) perimeter = 22
 *
 * APPROACH:
 * Start from sqrt(N) and find the first divisor.
 * This gives the largest width (closest to square), which minimizes perimeter.
 *
 * Time Complexity: O(√N)
 * Space Complexity: O(1)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (N=30) ===")
    var result1 = minPerimeterRectangle(30)
    println("Result: $result1")
    println("Expected: 22 (5 x 6 rectangle)")
    println()

    // Test 2: Perfect square
    println("=== Test 2: Perfect square (N=16) ===")
    var result2 = minPerimeterRectangle(16)
    println("Result: $result2")
    println("Expected: 16 (4 x 4 square)")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (N=1) ===")
    var result3 = minPerimeterRectangle(1)
    println("Result: $result3")
    println("Expected: 4 (1 x 1)")
    println()

    // Test 4: Prime number
    println("=== Test 4: Prime number (N=7) ===")
    var result4 = minPerimeterRectangle(7)
    println("Result: $result4")
    println("Expected: 16 (1 x 7)")
    println()

    // Test 5: Large number
    println("=== Test 5: Large number (N=101) ===")
    var result5 = minPerimeterRectangle(101)
    println("Result: $result5")
    println("Expected: 204 (1 x 101, prime)")
    println()

    // Test 6: Large composite
    println("=== Test 6: Large composite (N=100) ===")
    var result6 = minPerimeterRectangle(100)
    println("Result: $result6")
    println("Expected: 40 (10 x 10)")
}

// TIME COMPLEXITY: O(sqrt(N)) - iterate through divisors up to square root
// SPACE COMPLEXITY: O(1) - only constant variables

private fun minPerimeterRectangle(N: Int): Int {
    if (N <= 0) {
        return 0
    }

    // Start from square root and go down to find largest divisor
    var a = Math.sqrt(N.toDouble()).toInt()

    while (a > 0) {
        if (N % a == 0) {
            // Found largest divisor a
            val b = N / a
            println("A = $a, B = $b")
            return 2 * (a + b)
        }
        a -= 1
    }

    return 0
}
