package algorithms.sorting


/**
 * PROBLEM: Max Product of Three
 *
 * A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R)
 * equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
 *
 * The goal is to find the maximal product of any triplet.
 *
 * INPUT: A = [-3, 1, 2, -2, 5, 6]
 * OUTPUT: 60
 * EXPLANATION: After sorting [-3, -2, 1, 2, 5, 6], max product is 2 * 5 * 6 = 60
 *
 * INPUT: A = [-1, -5, 1, -2, 2, 3]
 * OUTPUT: 30
 * EXPLANATION: After sorting [-5, -2, -1, 1, 2, 3], max product is -5 * -2 * 3 = 30
 *
 * APPROACH:
 * Sort the array. Maximum product is either:
 * 1. Three largest positive numbers: A[n-1] * A[n-2] * A[n-3]
 * 2. Two smallest negative numbers and largest positive: A[0] * A[1] * A[n-1]
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case - mixed positive and negative
    println("=== Test 1: Normal case - mixed values ===")
    var result1 = maxProductOfThree(intArrayOf(-3, 1, 2, -2, 5, 6))
    println("Result: $result1")
    println("Expected: 60 (2 * 5 * 6)")
    println()

    // Test 2: Two large negatives with positive
    println("=== Test 2: Two large negatives with positive ===")
    var result2 = maxProductOfThree(intArrayOf(-1, -5, 1, -2, 2, 3))
    println("Result: $result2")
    println("Expected: 30 (-5 * -2 * 3)")
    println()

    // Test 3: Edge case - exactly 3 elements
    println("=== Test 3: Edge case - exactly 3 elements ===")
    val result3 = maxProductOfThree(intArrayOf(5, 10, 15))
    println("Result: $result3")
    println("Expected: 750 (5 * 10 * 15)")
    println()

    // Test 4: Edge case - all negative
    println("=== Test 4: Edge case - all negative ===")
    val result4 = maxProductOfThree(intArrayOf(-5, -3, -2, -1))
    println("Result: $result4")
    println("Expected: -6 (-3 * -2 * -1)")
    println()

    // Test 5: Edge case - all positive
    println("=== Test 5: Edge case - all positive ===")
    val result5 = maxProductOfThree(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result5")
    println("Expected: 60 (3 * 4 * 5)")
    println()

    // Test 6: Edge case - zeros present
    println("=== Test 6: Edge case - zeros present ===")
    val result6 = maxProductOfThree(intArrayOf(-5, -3, 0, 1, 2))
    println("Result: $result6")
    println("Expected: 0 (-5 * -3 * 0 = 0 or 0 * 1 * 2 = 0)")
}

// TIME COMPLEXITY: O(n log n) - sorting array dominates
// SPACE COMPLEXITY: O(1) - sort in place, constant extra space

private fun maxProductOfThree(A: IntArray): Int {
    // Edge case: array length < 3
    if (A.size < 3) {
        return 0
    }

    // Sort the array
    A.sort()
    println("Input      ${A.contentToString()}")
    val lenAr = A.size

    // Max negative case: two negative numbers produce positive result
    println("First two && last multiplication - ")
    val maxNegative = A[0] * A[1] * A[lenAr - 1]
    println(maxNegative)

    // Max positive case: three biggest positive numbers
    println("Last 3 multiplication - ")
    val maxPositive = A[lenAr - 1] * A[lenAr - 2] * A[lenAr - 3]
    println(maxPositive)

    return maxOf(maxNegative, maxPositive)
}
