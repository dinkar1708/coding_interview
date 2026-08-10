package algorithms.arrays.subarrays

/**
 * PROBLEM: Array Range Increment with Multiple Queries
 *
 * Given an array and multiple range update queries, efficiently increment
 * values in specified ranges by a factor.
 *
 * INPUT:
 *   A = [1, 2, 3, 4]
 *   queries = [[0, 2], [1, 3], [2, 3]]
 *   factor = 2
 *
 * OUTPUT:
 *   [3, 6, 9, 8]
 *
 * EXPLANATION:
 *   Naive approach: O(m*n) - for each query, update all elements in range
 *   Optimized approach: O(n+m) - use difference array technique
 *
 *   Algorithm (Difference Array):
 *   1. Create sum array of size n
 *   2. For each query [start, end]:
 *      - sum[start] += factor
 *      - if (end+1 < n): sum[end+1] -= factor
 *   3. Compute prefix sum of sum array
 *   4. Add sum array to original array
 *
 *   Example: A=[1,2,3,4], queries=[[0,2],[1,3],[2,3]], factor=2
 *   After queries: sum = [2, 2, 2, -2]
 *   Prefix sum:    sum = [2, 4, 6, 4]
 *   Final:         A   = [3, 6, 9, 8]
 *
 * Time Complexity: O(n + m) where n=array size, m=number of queries
 * Space Complexity: O(n) - sum array
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val array = intArrayOf(1, 2, 3, 4)
    val qArr = arrayOf(
        intArrayOf(0, 2),
        intArrayOf(1, 3),
        intArrayOf(2, 3)
    )
    println("Input A: ${array.contentToString()}")
    println("Input ranges: ${qArr.contentDeepToString()}")
    incrementRange(array, qArr, array.size, qArr.size, 2)
    println("Result: ${array.contentToString()}")
    println("Expected: [3, 6, 9, 8]")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val array2 = intArrayOf()
    val qArr2 = arrayOf(intArrayOf(0, 0))
    println("Input A: ${array2.contentToString()}")
    incrementRange(array2, qArr2, array2.size, qArr2.size, 2)
    println("Result: ${array2.contentToString()}")
    println("Expected: []")
    println()

    // Test 3: No queries
    println("=== Test 3: No queries ===")
    val array3 = intArrayOf(1, 2, 3)
    val qArr3 = arrayOf<IntArray>()
    println("Input A: ${array3.contentToString()}")
    incrementRange(array3, qArr3, array3.size, qArr3.size, 2)
    println("Result: ${array3.contentToString()}")
    println("Expected: [1, 2, 3]")
    println()

    // Test 4: Single element array
    println("=== Test 4: Single element array ===")
    val array4 = intArrayOf(5)
    val qArr4 = arrayOf(intArrayOf(0, 0))
    println("Input A: ${array4.contentToString()}")
    incrementRange(array4, qArr4, array4.size, qArr4.size, 3)
    println("Result: ${array4.contentToString()}")
    println("Expected: [8]")
    println()

    // Test 5: Single query covering entire array
    println("=== Test 5: Single query covering entire array ===")
    val array5 = intArrayOf(1, 2, 3, 4)
    val qArr5 = arrayOf(intArrayOf(0, 3))
    println("Input A: ${array5.contentToString()}")
    incrementRange(array5, qArr5, array5.size, qArr5.size, 5)
    println("Result: ${array5.contentToString()}")
    println("Expected: [6, 7, 8, 9]")
    println()

    // Test 6: Non-overlapping queries
    println("=== Test 6: Non-overlapping queries ===")
    val array6 = intArrayOf(1, 1, 1, 1, 1, 1)
    val qArr6 = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(4, 5)
    )
    println("Input A: ${array6.contentToString()}")
    incrementRange(array6, qArr6, array6.size, qArr6.size, 10)
    println("Result: ${array6.contentToString()}")
    println("Expected: [11, 11, 1, 1, 11, 11]")
}

// TIME COMPLEXITY: O(n + m) - m queries to mark boundaries + n elements for prefix sum
// SPACE COMPLEXITY: O(n) - difference array of size n
private fun incrementRange(A: IntArray, queryArray: Array<IntArray>, lenA: Int, m: Int, factor: Int) {
    // Edge case: empty array or no queries
    if (lenA == 0 || m == 0) {
        return
    }

    val sum = IntArray(lenA)

    // Step 1 & 2: Mark range boundaries
    for (i in queryArray.indices) {
        val start = queryArray[i][0]
        val end = queryArray[i][1]

        // Increment at start of range
        sum[start] += factor

        // Decrement at position after end of range
        if (end + 1 < lenA) {
            sum[end + 1] -= factor
        }
    }

    // Step 3: Compute prefix sum
    A[0] += sum[0]
    for (i in 1 until lenA) {
        sum[i] += sum[i - 1]
    }

    // Step 4: Add cumulative sum to original array
    for (i in 1 until lenA) {
        A[i] += sum[i]
    }
}
