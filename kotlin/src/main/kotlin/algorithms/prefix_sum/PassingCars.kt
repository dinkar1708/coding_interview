package algorithms.prefix_sum


/**
 * PROBLEM: Passing Cars
 *
 * A non-empty array A consisting of N integers is given.
 * The consecutive elements of array A represent consecutive cars on a road.
 *
 * Array A contains only 0s and/or 1s:
 * - 0 represents a car traveling east
 * - 1 represents a car traveling west
 *
 * The goal is to count passing cars. We say that a pair of cars (P, Q),
 * where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.
 *
 * INPUT: A = [0, 1, 0, 1, 1]
 * OUTPUT: 5
 * EXPLANATION: Pairs passing: (0,1), (0,3), (0,4), (2,3), (2,4)
 *
 * APPROACH:
 * Use suffix sum from right to left. For each 0 (east-bound car),
 * count how many 1s (west-bound cars) are ahead of it.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = countPassingCars(intArrayOf(0, 1, 0, 1, 1))
    println("Result: $result1")
    println("Expected: 5 (pairs: (0,1), (0,3), (0,4), (2,3), (2,4))")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = countPassingCars(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single element
    println("=== Test 3: Edge case - single element ===")
    val result3 = countPassingCars(intArrayOf(0))
    println("Result: $result3")
    println("Expected: 0 (no pairs possible)")
    println()

    // Test 4: Edge case - all east-bound (all 0s)
    println("=== Test 4: Edge case - all east-bound (all 0s) ===")
    val result4 = countPassingCars(intArrayOf(0, 0, 0, 0))
    println("Result: $result4")
    println("Expected: 0 (no west-bound cars to pass)")
    println()

    // Test 5: Edge case - all west-bound (all 1s)
    println("=== Test 5: Edge case - all west-bound (all 1s) ===")
    val result5 = countPassingCars(intArrayOf(1, 1, 1, 1))
    println("Result: $result5")
    println("Expected: 0 (no east-bound cars)")
    println()

    // Test 6: Maximum pairs
    println("=== Test 6: One east-bound at start ===")
    val result6 = countPassingCars(intArrayOf(0, 1, 1, 1, 1))
    println("Result: $result6")
    println("Expected: 4 (all west-bound cars pass the first)")
}

// TIME COMPLEXITY: O(n) - single pass to build suffix sum array
// SPACE COMPLEXITY: O(n) - suffix sum array of size n+1
private fun countPassingCars(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return 0
    }

    println(A.contentToString())
    val lenAr = A.size
    val suffixSum = IntArray(lenAr + 1)
    var total = 0

    // Build suffix sum from right to left
    for (i in lenAr - 1 downTo 0) {
        suffixSum[i] = A[i] + suffixSum[i + 1]
        println("suffix sum here...")
        println(suffixSum.contentToString())

        if (A[i] == 0) {
            // This car is traveling east, count west-bound cars ahead
            total += suffixSum[i]
            println(total)

            if (total > 1000000000) {
                return -1
            }
        }
    }

    return total
}
