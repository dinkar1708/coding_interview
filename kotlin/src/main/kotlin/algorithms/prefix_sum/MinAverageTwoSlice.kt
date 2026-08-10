package algorithms.prefix_sum


/**
 * PROBLEM: Min Average Two Slice
 *
 * A non-empty array A consisting of N integers is given. A pair of integers (P, Q),
 * such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice
 * contains at least two elements).
 *
 * The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by
 * the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
 *
 * The goal is to find the starting position of a slice whose average is minimal.
 *
 * INPUT: A = [4, 2, 2, 5, 1, 5, 8]
 * OUTPUT: 1
 * EXPLANATION: Slice (1, 2) has average (2 + 2) / 2 = 2.0 which is minimal
 *
 * APPROACH:
 * Mathematical insight: The minimal average slice will always have length 2 or 3.
 * Proof: Any slice of length 4+ can be split into smaller slices of 2 or 3,
 * and at least one of those must have average <= original.
 *
 * Therefore, we only need to check slices of length 2 and 3.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) for prefix sum
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = minAverageTwoSlice(intArrayOf(4, 2, 2, 5, 1, 5, 8))
    println("Result: $result1")
    println("Expected: 1 (slice [2, 2] has average 2.0)")
    println()

    // Test 2: Edge case - minimum length (2 elements)
    println("=== Test 2: Edge case - minimum length (2 elements) ===")
    val result2 = minAverageTwoSlice(intArrayOf(3, 5))
    println("Result: $result2")
    println("Expected: 0 (only one slice possible)")
    println()

    // Test 3: Edge case - all same values
    println("=== Test 3: Edge case - all same values ===")
    val result3 = minAverageTwoSlice(intArrayOf(5, 5, 5, 5, 5))
    println("Result: $result3")
    println("Expected: 0 (all slices have same average)")
    println()

    // Test 4: Edge case - negative numbers
    println("=== Test 4: Edge case - negative numbers ===")
    val result4 = minAverageTwoSlice(intArrayOf(10, -5, -3, 8))
    println("Result: $result4")
    println("Expected: 1 (slice [-5, -3] has average -4.0)")
    println()

    // Test 5: Minimum at end
    println("=== Test 5: Minimum at end ===")
    val result5 = minAverageTwoSlice(intArrayOf(10, 10, 1, 2))
    println("Result: $result5")
    println("Expected: 2 (slice [1, 2] has average 1.5)")
    println()

    // Test 6: Descending values
    println("=== Test 6: Descending values ===")
    val result6 = minAverageTwoSlice(intArrayOf(5, 4, 3, 2, 1))
    println("Result: $result6")
    println("Expected: 3 (slice [2, 1] has average 1.5)")
}

// TIME COMPLEXITY: O(n) - single pass checking 2-element and 3-element slices
// SPACE COMPLEXITY: O(1) - only constant variables for tracking minimum
private fun minAverageTwoSlice(A: IntArray): Int {
    // Edge case: array length < 2 (need at least 2 elements for a slice)
    if (A.size < 2) {
        return 0
    }

    val lenAr = A.size

    // Build prefix sum for easy average calculation
    val prefixSum = IntArray(lenAr + 1)
    for (i in 1..lenAr) {
        prefixSum[i] = prefixSum[i - 1] + A[i - 1]
    }
    println(prefixSum.contentToString())

    // Max possible average (max element / 3)
    var maxAverage = 100000.0 / 3.0
    var minIdxStart = 0

    // Check slices of length 2 and 3 only
    for (i in A.indices) {
        // Check slices starting at i with length 2 or 3
        for (j in i + 1 until minOf(i + 3, A.size)) {
            println("For i $i j $j")
            val average = (prefixSum[j + 1] - prefixSum[i]).toDouble() / (j - i + 1)
            println("Avg $average")

            if (average < maxAverage) {
                minIdxStart = i
                maxAverage = average
                println("Min from here $minIdxStart")
            }
        }
    }

    return minIdxStart
}
