package algorithms.arrays.subarrays

/**
 * PROBLEM: Maximum Sum Contiguous Subarray (Kadane's Algorithm)
 *
 * Find the contiguous subarray within an array which has the largest sum.
 *
 * INPUT:
 *   arr = [-2, -3, 4, -1, -2, 1, 5, -3]
 *
 * OUTPUT:
 *   7 (subarray [4, -1, -2, 1, 5])
 *
 * EXPLANATION:
 *   Kadane's Algorithm maintains two variables:
 *   - max_ending_here: Maximum sum ending at current position
 *   - max_so_far: Maximum sum found so far
 *
 *   At each position, decide whether to:
 *   - Continue the current subarray (max_ending_here + item)
 *   - Start a new subarray (item)
 *
 *   Example trace:
 *   [-2, -3, 4, -1, -2, 1, 5, -3]
 *   max_ending_here: -2, -3, 4, 3, 1, 2, 7, 4
 *   max_so_far:      -2, -2, 4, 4, 4, 4, 7, 7
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - constant space
 */

fun main() {
    // Test 1: Normal case with mixed positive and negative numbers
    println("=== Test 1: Normal case ===")
    val arr = intArrayOf(-2, -3, 4, -1, -2, 1, 5, -3)
    val result = largestSum(arr)
    println("Input: ${arr.contentToString()}")
    println("Result: $result")
    println("Expected: 7 (subarray [4, -1, -2, 1, 5])")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val arr2 = intArrayOf()
    val result2 = largestSum(arr2)
    println("Input: ${arr2.contentToString()}")
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val arr3 = intArrayOf(5)
    val result3 = largestSum(arr3)
    println("Input: ${arr3.contentToString()}")
    println("Result: $result3")
    println("Expected: 5")
    println()

    // Test 4: All negative numbers
    println("=== Test 4: All negative numbers ===")
    val arr4 = intArrayOf(-5, -2, -8, -1, -4)
    val result4 = largestSum(arr4)
    println("Input: ${arr4.contentToString()}")
    println("Result: $result4")
    println("Expected: -1 (least negative)")
    println()

    // Test 5: All positive numbers
    println("=== Test 5: All positive numbers ===")
    val arr5 = intArrayOf(1, 2, 3, 4, 5)
    val result5 = largestSum(arr5)
    println("Input: ${arr5.contentToString()}")
    println("Result: $result5")
    println("Expected: 15 (entire array)")
    println()

    // Test 6: Two elements
    println("=== Test 6: Two elements ===")
    val arr6 = intArrayOf(-1, 2)
    val result6 = largestSum(arr6)
    println("Input: ${arr6.contentToString()}")
    println("Result: $result6")
    println("Expected: 2")
}

// TIME COMPLEXITY: O(n) - single pass through array using Kadane's algorithm
// SPACE COMPLEXITY: O(1) - only two variables for tracking max sums
private fun largestSum(arr: IntArray): Int {
    // Edge case: empty array
    if (arr.isEmpty()) {
        return 0
    }

    var maxEndingHere = arr[0]
    var maxSoFar = arr[0]

    for (i in 1 until arr.size) {
        val item = arr[i]
        // Max of: starting fresh from current item OR continuing previous subarray
        maxEndingHere = maxOf(item, maxEndingHere + item)
        // Update global maximum
        maxSoFar = maxOf(maxEndingHere, maxSoFar)
    }

    return maxSoFar
}
