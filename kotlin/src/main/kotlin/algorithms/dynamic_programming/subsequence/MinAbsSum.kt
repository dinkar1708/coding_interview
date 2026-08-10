package algorithms.dynamic_programming.subsequence


/**
 * PROBLEM: Min Abs Sum
 *
 * Given array A of N integers, find the minimal absolute value of a sum of subsequences.
 * For each element A[i], you can choose its sign (+ or -).
 *
 * INPUT: A = [1, 5, 2, -2]
 * OUTPUT: 0
 *
 * EXPLANATION:
 * Generate all possible sign combinations and find minimum absolute sum.
 * Possible sums by choosing signs:
 * - All positive: 1+5+2+(-2) = 6
 * - With some negatives: 1+5+2-(-2) = 10
 * - Best: -1+5-2+(-2) = 0 (or other combinations)
 *
 * Time Complexity: O(2^n) - exponential (brute force)
 * Space Complexity: O(n)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = minAbsSum(intArrayOf(1, 5, 2, -2))
    println("Result: $result1")
    println("Expected: 0")
    println()

    // Test 2: Edge case - single element
    println("=== Test 2: Edge case - single element ===")
    val result2 = minAbsSum(intArrayOf(5))
    println("Result: $result2")
    println("Expected: 5 (abs(-5) or abs(5))")
    println()

    // Test 3: Edge case - all positive
    println("=== Test 3: Edge case - all positive ===")
    val result3 = minAbsSum(intArrayOf(1, 2, 3))
    println("Result: $result3")
    println("Expected: 0 (1+2-3)")
    println()

    // Test 4: Two elements
    println("=== Test 4: Two elements ===")
    val result4 = minAbsSum(intArrayOf(3, 3))
    println("Result: $result4")
    println("Expected: 0 (3-3)")
    println()

    // Test 5: Larger array
    println("=== Test 5: Larger array ===")
    val result5 = minAbsSum(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result5")
    println("Expected: 1 or 0")
    println()

    // Test 6: Mix of positive and negative
    println("=== Test 6: Mix of positive and negative ===")
    val result6 = minAbsSum(intArrayOf(-5, 3, 2))
    println("Result: $result6")
    println("Expected: 0 (-5+3+2)")
    /*
    Note: This generates all sign combinations.
    Expected output pattern (will show all combinations):
    [1, 1, 1, 1]
    6
    [-1, 1, 1, 1]
    4
    ... (more combinations)
    */
}

// TIME COMPLEXITY: O(n * sum) - DP table of size n * max_sum
// SPACE COMPLEXITY: O(sum) - DP array of size max possible sum

private fun minAbsSum(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return 0
    }

    var minValue = A.sum()

    // Generate all possible sign combinations using bit manipulation
    val n = A.size
    val totalCombinations = 1 shl n // 2^n combinations

    for (mask in 0 until totalCombinations) {
        var sum = 0
        val sequence = mutableListOf<Int>()

        for (i in A.indices) {
            // Check if i-th bit is set
            val sign = if ((mask and (1 shl i)) != 0) 1 else -1
            sequence.add(sign)
            sum += A[i] * sign
        }

        println(sequence)
        println(sum)
        minValue = minOf(minValue, kotlin.math.abs(sum))
        println(minValue)
    }

    return minValue
}
