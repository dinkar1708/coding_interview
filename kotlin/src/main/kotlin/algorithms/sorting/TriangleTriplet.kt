package algorithms.sorting


/**
 * PROBLEM: Triangle Triplet
 *
 * An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if:
 * - 0 ≤ P < Q < R < N
 * - A[P] + A[Q] > A[R]
 * - A[Q] + A[R] > A[P]
 * - A[R] + A[P] > A[Q]
 *
 * INPUT: A = [10, 2, 5, 1, 8, 20]
 * OUTPUT: 1
 * EXPLANATION: After sorting [1, 2, 5, 8, 10, 20], triplet (2, 8, 10) forms a triangle
 *
 * APPROACH:
 * Sort the array. For sorted array A[P] ≤ A[Q] ≤ A[R]:
 * - A[Q] + A[R] > A[P] is always true
 * - A[R] + A[P] > A[Q] is always true
 * - Only need to check: A[P] + A[Q] > A[R]
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (with sorting) ===")
    val result1 = checkTriangle(intArrayOf(10, 2, 5, 1, 8, 20))
    println("Result: $result1")
    println("Expected: 1 (triangle exists)")
    println()

    // Test 1b: Same test without sorting
    println("=== Test 1b: Normal case (WITHOUT sorting - brute force) ===")
    val result1b = solutionWithoutSort(intArrayOf(10, 2, 5, 1, 8, 20))
    println("Result: $result1b")
    println("Expected: 1 (triangle exists)")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = checkTriangle(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - less than 3 elements
    println("=== Test 3: Edge case - only 2 elements ===")
    val result3 = checkTriangle(intArrayOf(5, 10))
    println("Result: $result3")
    println("Expected: 0 (need at least 3 elements)")
    println()

    // Test 4: Edge case - no triangle possible
    println("=== Test 4: Edge case - no triangle possible ===")
    val result4 = checkTriangle(intArrayOf(1, 2, 10, 20))
    println("Result: $result4")
    println("Expected: 0 (no valid triangle)")
    println()

    // Test 5: Edge case - exact triangle (3, 4, 5)
    println("=== Test 5: Edge case - exact right triangle ===")
    val result5 = checkTriangle(intArrayOf(3, 4, 5))
    println("Result: $result5")
    println("Expected: 1 (3-4-5 triangle)")
    println()

    // Test 6: All same values
    println("=== Test 6: All same values ===")
    val result6 = checkTriangle(intArrayOf(5, 5, 5, 5))
    println("Result: $result6")
    println("Expected: 1 (equilateral triangle)")
}

// TIME COMPLEXITY: O(n log n) - sorting array then O(n) scan
// SPACE COMPLEXITY: O(1) - sort in place, constant extra space

private fun checkTriangle(A: IntArray): Int {
    // Edge case: array length < 3
    if (A.size < 3) {
        return 0
    }

    // Sort the array
    A.sort()
    println(A.contentToString())

    // Check for all consecutive triplets
    // WHY ONLY ONE CHECK WORKS (because array is sorted):
    // For sorted array where A[P] ≤ A[Q] ≤ A[R]:
    //   1. A[Q] + A[R] > A[P] ✓ always true (two larger > one smaller)
    //   2. A[R] + A[P] > A[Q] ✓ always true (largest + any > middle)
    //   3. A[P] + A[Q] > A[R] ? only this needs checking!
    // Example: [2, 5, 8] (sorted)
    //   - 5+8 > 2? YES (13 > 2) ✓
    //   - 8+2 > 5? YES (10 > 5) ✓
    //   - 2+5 > 8? NO (7 < 8) ✗ Not a triangle!
    for (index in 0 until A.size - 2) {
        if (A[index] + A[index + 1] > A[index + 2]) {
            return 1
        }
    }

    return 0
}

// ALTERNATIVE APPROACH: WITHOUT SORTING (Brute Force)
// TIME COMPLEXITY: O(n³) - three nested loops to check all triplets
// SPACE COMPLEXITY: O(1) - no extra space needed
//
// This approach checks all possible triplets (P, Q, R) where P < Q < R
// and validates all 3 triangle conditions:
// 1. A[P] + A[Q] > A[R]
// 2. A[Q] + A[R] > A[P]
// 3. A[R] + A[P] > A[Q]
//
// Note: Much slower than sorting approach (O(n³) vs O(n log n))
// but works without modifying the original array
//
private fun solutionWithoutSort(A: IntArray): Int {
    // Edge case: array length < 3
    if (A.size < 3) {
        return 0
    }

    // Check all possible triplets (P, Q, R) where P < Q < R
    for (p in 0 until A.size - 2) {
        for (q in p + 1 until A.size - 1) {
            for (r in q + 1 until A.size) {
                // Check all 3 triangle conditions
                val condition1 = A[p] + A[q] > A[r]
                val condition2 = A[q] + A[r] > A[p]
                val condition3 = A[r] + A[p] > A[q]

                if (condition1 && condition2 && condition3) {
                    println("Found triangle: A[$p]=${A[p]}, A[$q]=${A[q]}, A[$r]=${A[r]}")
                    return 1
                }
            }
        }
    }

    return 0
}

/*
================================================================================
COMPARISON: Sorting vs Brute Force
================================================================================

APPROACH 1: With Sorting (solution)
- Sort the array first: O(n log n)
- Check consecutive triplets: O(n)
- Total Time: O(n log n)
- Space: O(1) - in-place sort
- Optimized: Only need to check A[P] + A[Q] > A[R] for sorted array
- Best for: Most cases (faster!)

APPROACH 2: Without Sorting (solutionWithoutSort)
- Three nested loops: O(n³)
- Check all 3 triangle conditions for each triplet
- Total Time: O(n³)
- Space: O(1)
- Brute force: Checks ALL possible triplets
- Best for: When cannot modify array OR very small arrays

Example: A = [10, 2, 5, 1, 8, 20]

With Sorting:
- Sort: [1, 2, 5, 8, 10, 20]
- Check: (1,2,5)? No. (2,5,8)? Yes! → Return 1
- Checks: ~6 triplets

Without Sorting:
- Check ALL combinations: (10,2,5), (10,2,1), (10,2,8), (10,2,20), ...
- Finds: (10,5,8) forms triangle → Return 1
- Checks: C(6,3) = 20 triplets

Performance for n=100:
- With sorting: ~100 checks
- Without sorting: ~161,700 checks (C(100,3))

Recommendation: Use sorting approach unless you cannot modify the array! ✓
================================================================================
*/
