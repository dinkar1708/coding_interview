package algorithms.arrays.searching

/**
 * PROBLEM: Max Counter Operations
 *
 * You are given N counters, initially set to 0, with two possible operations:
 * - increase(X): counter X is increased by 1
 * - max counter: all counters are set to the maximum value of any counter
 *
 * Given a non-empty array A of M integers representing consecutive operations:
 * - if A[K] = X, where 1 ≤ X ≤ N, then operation K is increase(X)
 * - if A[K] = N + 1, then operation K is max counter
 *
 * INPUT:
 *   N = 5, A = [3, 4, 4, 6, 1, 4, 4]
 *
 * OUTPUT:
 *   [3, 2, 2, 4, 2]
 *
 * EXPLANATION:
 *   Initial:     [0, 0, 0, 0, 0]
 *   A[0]=3:      [0, 0, 1, 0, 0]  (increase counter 3)
 *   A[1]=4:      [0, 0, 1, 1, 0]  (increase counter 4)
 *   A[2]=4:      [0, 0, 1, 2, 0]  (increase counter 4)
 *   A[3]=6(N+1): [2, 2, 2, 2, 2]  (max counter - set all to 2)
 *   A[4]=1:      [3, 2, 2, 2, 2]  (increase counter 1)
 *   A[5]=4:      [3, 2, 2, 3, 2]  (increase counter 4)
 *   A[6]=4:      [3, 2, 2, 4, 2]  (increase counter 4)
 *
 *   Optimization: Track local_max and global_max to avoid O(n²) complexity.
 *   Only apply max operation lazily at the end.
 *
 * Time Complexity: O(N + M) - linear in counter size and operations
 * Space Complexity: O(N) - counter array
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result = maxCounterOperations(5, intArrayOf(3, 4, 4, 6, 1, 4, 4))
    println("Input: N = 5, A = [3, 4, 4, 6, 1, 4, 4]")
    println("Result: ${result.contentToString()}")
    println("Expected: [3, 2, 2, 4, 2]")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val result2 = maxCounterOperations(5, intArrayOf())
    println("Input: N = 5, A = []")
    println("Result: ${result2.contentToString()}")
    println("Expected: [0, 0, 0, 0, 0]")
    println()

    // Test 3: N = 1 (single counter)
    println("=== Test 3: N = 1 (single counter) ===")
    val result3 = maxCounterOperations(1, intArrayOf(1, 1, 1))
    println("Input: N = 1, A = [1, 1, 1]")
    println("Result: ${result3.contentToString()}")
    println("Expected: [3]")
    println()

    // Test 4: Only max counter operations
    println("=== Test 4: Only max counter operations ===")
    val result4 = maxCounterOperations(3, intArrayOf(4, 4, 4))
    println("Input: N = 3, A = [4, 4, 4] (all max counter operations)")
    println("Result: ${result4.contentToString()}")
    println("Expected: [0, 0, 0]")
    println()

    // Test 5: No max counter operations
    println("=== Test 5: No max counter operations ===")
    val result5 = maxCounterOperations(3, intArrayOf(1, 2, 3, 1, 2, 3))
    println("Input: N = 3, A = [1, 2, 3, 1, 2, 3]")
    println("Result: ${result5.contentToString()}")
    println("Expected: [2, 2, 2]")
    println()

    // Test 6: Max counter at the end
    println("=== Test 6: Max counter at the end ===")
    val result6 = maxCounterOperations(3, intArrayOf(1, 1, 1, 4))
    println("Input: N = 3, A = [1, 1, 1, 4]")
    println("Result: ${result6.contentToString()}")
    println("Expected: [3, 3, 3]")
    println()

    // Test 7: N = 0 (invalid)
    println("=== Test 7: N = 0 (invalid) ===")
    val result7 = maxCounterOperations(0, intArrayOf(1, 2, 3))
    println("Input: N = 0, A = [1, 2, 3]")
    println("Result: ${result7.contentToString()}")
    println("Expected: []")
}

// TIME COMPLEXITY: O(N + M) - M operations on array + final N-size pass for lazy update
// SPACE COMPLEXITY: O(N) - counter array of size N
private fun maxCounterOperations(N: Int, A: IntArray): IntArray {
    // Edge case: N <= 0
    if (N <= 0) {
        return intArrayOf()
    }

    // Edge case: empty array
    if (A.isEmpty()) {
        return IntArray(N)
    }

    var globalMax = 0
    var localMax = 0
    val counter = IntArray(N)

    for ((i, item) in A.withIndex()) {
        val itemAsCounterIndex = item - 1

        // Check if current element is within valid range [1, N]
        if (item in 1..N) {
            // Increase operation: max of local_max or current counter value, then increment
            counter[itemAsCounterIndex] = maxOf(localMax, counter[itemAsCounterIndex]) + 1
            // Track the global maximum counter value so far
            globalMax = maxOf(globalMax, counter[itemAsCounterIndex])
        } else if (item == N + 1) {
            // Max counter operation: set local_max to global_max
            // This avoids updating all counters immediately (lazy update)
            localMax = globalMax
        }
    }

    // Final pass: replace all elements less than local_max with local_max
    for (i in counter.indices) {
        counter[i] = maxOf(counter[i], localMax)
    }

    return counter
}
