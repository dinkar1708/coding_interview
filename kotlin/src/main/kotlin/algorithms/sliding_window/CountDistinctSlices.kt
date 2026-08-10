package algorithms.sliding_window

/**
 * PROBLEM: Count Distinct Slices
 *
 * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
 * The slice consists of the elements A[P], A[P + 1], ..., A[Q].
 * A slice is called distinct if no two elements in the slice are equal.
 *
 * INPUT: M = 6, A = [3, 4, 5, 5, 2]
 * OUTPUT: 9
 *
 * EXPLANATION:
 * Method uses caterpillar/two-pointer technique:
 * - tail pointer and head pointer both start at 0
 * - For each tail position, move head forward as far as possible while maintaining distinct elements
 * - Count all valid slices ending at each head position
 * There are exactly nine distinct slices:
 * (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(M)
 */

fun main() {
    // Test 1: Normal case (with debug output)
    println("=== Test 1: Normal case ===")
    println("There are exactly nine distinct slices:")
    println("(0,0), (0,1), (0,2), (1,1), (1,2), (2,2), (3,3), (3,4), (4,4)")
    val result = countDistinctSlices(6, intArrayOf(3, 4, 5, 5, 2))
    println("Result: $result")
    println("Expected: 9")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val result2 = solutionQuiet(6, intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val result3 = solutionQuiet(6, intArrayOf(3))
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: All same elements (M >= value)
    println("=== Test 4: All same elements ===")
    val result4 = solutionQuiet(5, intArrayOf(2, 2, 2, 2))
    println("Result: $result4")
    println("Expected: 4 (only individual slices)")
    println()

    // Test 5: All distinct elements
    println("=== Test 5: All distinct elements ===")
    val result5 = solutionQuiet(10, intArrayOf(1, 2, 3, 4))
    println("Result: $result5")
    println("Expected: 10 (n*(n+1)/2 = 4*5/2)")
    println()

    // Test 6: M = 0
    println("=== Test 6: M = 0 ===")
    val result6 = solutionQuiet(0, intArrayOf(0))
    println("Result: $result6")
    println("Expected: 1")
}

// Quiet version without debug output for testing
private fun solutionQuiet(M: Int, A: IntArray): Int {
    if (A.isEmpty() || M < 0) return 0
    val inCurrentSlice = BooleanArray(M + 1) { false }
    var totalSlices = 0
    var head = 0
    for (tail in A.indices) {
        while (head < A.size && !inCurrentSlice[A[head]]) {
            inCurrentSlice[A[head]] = true
            totalSlices += (head - tail) + 1
            head += 1
            if (totalSlices > 1000000000) totalSlices = 1000000000
        }
        inCurrentSlice[A[tail]] = false
    }
    return totalSlices
}

// TIME COMPLEXITY: O(n) - each element visited at most twice by head and tail pointers
// SPACE COMPLEXITY: O(M) - boolean array of size M+1 to track elements in current slice
private fun countDistinctSlices(M: Int, A: IntArray): Int {
    // Edge case: empty array or M < 0
    if (A.isEmpty() || M < 0) {
        return 0
    }

    val inCurrentSlice = BooleanArray(M + 1) { false }
    var totalSlices = 0
    var head = 0

    // For each tail position
    for (tail in A.indices) {
        println("For this tail.. $tail")

        // Move head forward while no duplicates
        while (head < A.size && !inCurrentSlice[A[head]]) {
            println("For this head.. $head")
            // Mark item at head as visited
            inCurrentSlice[A[head]] = true
            // Count all slices from tail to head
            totalSlices += (head - tail) + 1
            head += 1

            // Cap at 1 billion as per problem constraint
            if (totalSlices > 1000000000) {
                totalSlices = 1000000000
            }
        }
        println("total up to here $totalSlices")

        // Mark tail position as not visited for next iteration
        println(inCurrentSlice.contentToString())
        inCurrentSlice[A[tail]] = false
    }

    return totalSlices
}
