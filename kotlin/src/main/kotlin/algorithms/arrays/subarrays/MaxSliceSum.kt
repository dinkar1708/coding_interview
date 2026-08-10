package algorithms.arrays.subarrays


/**
 * PROBLEM: Maximum Slice Sum (Kadane's Algorithm)
 *
 * Find the maximum sum of any slice (contiguous subarray) of an array.
 *
 * INPUT:
 *   A = [3, 2, -6, 4, 0]
 *
 * OUTPUT:
 *   5 (slice [3, 2] from index 0 to 1)
 *
 * EXPLANATION:
 *   Similar to classic maximum subarray problem (Kadane's algorithm).
 *   At each position i, calculate max sum ending at that position:
 *   max_ending_here[i] = max(A[i], max_ending_here[i-1] + A[i])
 *
 *   Trace for [3, 2, -6, 4, 0]:
 *   i=0: max_ending_here=3,  max_so_far=3
 *   i=1: max_ending_here=5,  max_so_far=5
 *   i=2: max_ending_here=-1, max_so_far=5
 *   i=3: max_ending_here=4,  max_so_far=5
 *   i=4: max_ending_here=4,  max_so_far=5
 *
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(1) - constant space
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result = maxSliceSum(intArrayOf(3, 2, -6, 4, 0))
    println("Input: [3, 2, -6, 4, 0]")
    println("Result: $result")
    println("Expected: 5")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val result2 = maxSliceSum(intArrayOf())
    println("Input: []")
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val result3 = maxSliceSum(intArrayOf(-5))
    println("Input: [-5]")
    println("Result: $result3")
    println("Expected: -5")
    println()

    // Test 4: All negative numbers
    println("=== Test 4: All negative numbers ===")
    val result4 = maxSliceSum(intArrayOf(-5, -2, -8, -1))
    println("Input: [-5, -2, -8, -1]")
    println("Result: $result4")
    println("Expected: -1")
    println()

    // Test 5: All positive numbers
    println("=== Test 5: All positive numbers ===")
    val result5 = maxSliceSum(intArrayOf(1, 2, 3, 4, 5))
    println("Input: [1, 2, 3, 4, 5]")
    println("Result: $result5")
    println("Expected: 15")
    println()

    // Test 6: Two elements
    println("=== Test 6: Two elements ===")
    val result6 = maxSliceSum(intArrayOf(-3, 5))
    println("Input: [-3, 5]")
    println("Result: $result6")
    println("Expected: 5")
}

// TIME COMPLEXITY: O(n) - single pass through array using Kadane's algorithm
// SPACE COMPLEXITY: O(1) - only two variables for tracking max sums
private fun maxSliceSum(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return 0
    }

    var maxEndingHere = A[0]
    var maxSoFar = A[0]

    for (i in 1 until A.size) {
        // Max of: current element alone OR current + previous sum
        maxEndingHere = maxOf(A[i], maxEndingHere + A[i])
        // Update global maximum
        maxSoFar = maxOf(maxEndingHere, maxSoFar)
    }

    return maxSoFar
}
