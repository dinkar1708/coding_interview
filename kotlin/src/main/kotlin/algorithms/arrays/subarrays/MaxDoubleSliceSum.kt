package algorithms.arrays.subarrays


/**
 * PROBLEM: Maximum Double Slice Sum
 *
 * A double slice is a pair of slices (X, Y, Z) where 0 ≤ X < Y < Z < N.
 * The sum is: A[X+1] + A[X+2] + ... + A[Y-1] + A[Y+1] + A[Y+2] + ... + A[Z-1]
 * (Note: Elements at X, Y, and Z are NOT included)
 *
 * INPUT:
 *   A = [3, 2, 6, -1, 4, 5, -1, 2]
 *
 * OUTPUT:
 *   17 (double slice (0, 3, 6): 2 + 6 + 4 + 5 = 17)
 *
 * EXPLANATION:
 *   Use two auxiliary arrays:
 *   - ending_here_sum[i]: max sum of subarray ending at index i
 *   - starting_here_sum[i]: max sum of subarray starting at index i
 *
 *   For each position Y, the double slice sum is:
 *   ending_here_sum[Y-1] + starting_here_sum[Y+1]
 *
 *   ending_here_sum:   [0, 2, 8, 7, 11, 16, 0, 0]
 *   starting_here_sum: [0, 0, 14, 8, 9, 5, 0, 0]
 *
 *   At Y=3: ending[2] + starting[4] = 8 + 9 = 17
 *
 * Time Complexity: O(n) - three passes through array
 * Space Complexity: O(n) - two auxiliary arrays
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result = maxDoubleSliceSum(intArrayOf(3, 2, 6, -1, 4, 5, -1, 2))
    println("Input: [3, 2, 6, -1, 4, 5, -1, 2]")
    println("Result: $result")
    println("Expected: 17")
    println()

    // Test 2: Minimum size array (3 elements)
    println("=== Test 2: Minimum size array (3 elements) ===")
    val result2 = maxDoubleSliceSum(intArrayOf(5, 10, 5))
    println("Input: [5, 10, 5]")
    println("Result: $result2")
    println("Expected: 0 (no valid double slice)")
    println()

    // Test 3: Array too small (2 elements)
    println("=== Test 3: Array too small (2 elements) ===")
    val result3 = maxDoubleSliceSum(intArrayOf(5, 10))
    println("Input: [5, 10]")
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: All negative numbers except boundaries
    println("=== Test 4: All negative numbers except boundaries ===")
    val result4 = maxDoubleSliceSum(intArrayOf(5, -1, -2, -3, 10))
    println("Input: [5, -1, -2, -3, 10]")
    println("Result: $result4")
    println("Expected: 0 (skip all negative)")
    println()

    // Test 5: All positive numbers
    println("=== Test 5: All positive numbers ===")
    val result5 = maxDoubleSliceSum(intArrayOf(1, 2, 3, 4, 5))
    println("Input: [1, 2, 3, 4, 5]")
    println("Result: $result5")
    println("Expected: 9 (2+3+4, Y=0 or Y=4)")
    println()

    // Test 6: Four elements
    println("=== Test 6: Four elements ===")
    val result6 = maxDoubleSliceSum(intArrayOf(10, 20, 30, 40))
    println("Input: [10, 20, 30, 40]")
    println("Result: $result6")
    println("Expected: 50")
}

// TIME COMPLEXITY: O(n) - three linear passes to build arrays and find max
// SPACE COMPLEXITY: O(n) - two auxiliary arrays for ending and starting sums
private fun maxDoubleSliceSum(A: IntArray): Int {
    // Edge case: array length < 3 (need at least 3 elements for double slice)
    if (A.size < 3) {
        return 0
    }

    val arLen = A.size
    val endingHereSum = IntArray(arLen)
    val startingHereSum = IntArray(arLen)

    // Build ending_here_sum: max sum contiguous subarray ending at index i
    for (index in 1 until arLen - 1) {
        endingHereSum[index] = maxOf(endingHereSum[index - 1] + A[index], 0)
    }

    // Build starting_here_sum: max sum contiguous subarray starting at index i
    for (index in arLen - 2 downTo 1) {
        startingHereSum[index] = maxOf(startingHereSum[index + 1] + A[index], 0)
    }

    // Find maximum double slice sum
    var maxSliceSum = 0
    for (index in 1 until arLen - 1) {
        // Double slice centered at Y=index
        val doubleSlice = endingHereSum[index - 1] + startingHereSum[index + 1]
        maxSliceSum = maxOf(maxSliceSum, doubleSlice)
    }

    return maxSliceSum
}
