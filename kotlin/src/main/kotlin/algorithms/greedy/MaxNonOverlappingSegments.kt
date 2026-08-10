package algorithms.greedy

/*
Problem: Max Non-overlapping Segments

Given two arrays A and B consisting of N integers, representing N segments.
- A[i] is the start point of segment i
- B[i] is the end point of segment i (B is sorted)

Find the maximum number of non-overlapping segments.

Two segments are non-overlapping if:
- The start of one segment is greater than the end of the other segment

Example visualization:
Segments do not overlap if one ends before the other starts:
(1,5)  ->  1...5
(7,8)  ->        7.8

Segments overlap if they share any point:
(1,5)  ->  1...5
(3,6)  ->    3...6

Greedy Approach:
- Keep track of the end point of the last non-overlapping segment
- For each segment, check if its start is after the previous segment's end
- If yes, it's non-overlapping, count it and update the end point
*/

fun main() {
    // Test 1: Normal case (with debug output)
    println("=== Test 1: Normal case ===")
    val result = maxNonOverlappingSegments(intArrayOf(1, 3, 7, 9, 9), intArrayOf(5, 6, 8, 9, 10))
    println("Result: $result")
    println("Expected: 3")
    println()

    // Test 2: Empty arrays
    println("=== Test 2: Empty arrays ===")
    val result2 = solutionQuiet(intArrayOf(), intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Single segment
    println("=== Test 3: Single segment ===")
    val result3 = solutionQuiet(intArrayOf(1), intArrayOf(5))
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: All overlapping segments
    println("=== Test 4: All overlapping segments ===")
    val result4 = solutionQuiet(intArrayOf(1, 2, 3), intArrayOf(5, 6, 7))
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: All non-overlapping segments
    println("=== Test 5: All non-overlapping segments ===")
    val result5 = solutionQuiet(intArrayOf(1, 5, 9), intArrayOf(2, 6, 10))
    println("Result: $result5")
    println("Expected: 3")
    println()

    // Test 6: Adjacent segments (touching)
    println("=== Test 6: Adjacent segments (touching) ===")
    val result6 = solutionQuiet(intArrayOf(1, 5, 10), intArrayOf(5, 10, 15))
    println("Result: $result6")
    println("Expected: 3")
}

// Quiet version for testing
private fun solutionQuiet(A: IntArray, B: IntArray): Int {
    if (A.isEmpty() || B.isEmpty() || A.size != B.size) return 0
    var prevSegEndPoint = -1
    var count = 0
    for (i in B.indices) {
        if (A[i] > prevSegEndPoint) {
            prevSegEndPoint = B[i]
            count += 1
        }
    }
    return count
}

// TIME COMPLEXITY: O(n) - single pass through sorted segments array
// SPACE COMPLEXITY: O(1) - only constant variables for tracking end point and count
private fun maxNonOverlappingSegments(A: IntArray, B: IntArray): Int {
    // Edge case: empty arrays or length mismatch
    if (A.isEmpty() || B.isEmpty() || A.size != B.size) {
        return 0
    }

    var prevSegEndPoint = -1
    var count = 0

    for (i in B.indices) {
        println("Compare for prev_seg_end_point $prevSegEndPoint")

        // Check if current segment does not overlap with previous
        // Start of current segment > end of previous segment
        if (A[i] > prevSegEndPoint) {
            println("Non-overlapping - A(start, end) => ${A[i]} , ${B[i]}")
            println("prev_seg_end_point $prevSegEndPoint")
            prevSegEndPoint = B[i]
            count += 1
        }
    }

    return count
}
