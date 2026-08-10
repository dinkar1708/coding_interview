package algorithms.arrays.subarrays

/**
 * PROBLEM: Partition Array into Two Equal Sum Subsets
 *
 * Given an array of integers, determine if it can be partitioned into two subsets
 * such that the sum of elements in both subsets is equal.
 *
 * INPUT:
 *   arr = [1, 5, 11, 5]
 *
 * OUTPUT:
 *   true (can be divided into {1, 5, 5} and {11})
 *
 * EXPLANATION:
 *   Total sum = 22, which is even.
 *   We need to find if there's a subset with sum = 11.
 *   Using recursion/dynamic programming to check all possible subsets.
 *
 *   Base cases:
 *   - If sum = 0, return true (empty subset)
 *   - If n = 0 and sum ≠ 0, return false (no elements left)
 *   - If last element > sum, ignore it and recurse
 *   - Otherwise, check both including and excluding last element
 *
 * Time Complexity: O(2^n) - recursive solution (can be optimized with DP)
 * Space Complexity: O(n) - recursion stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val arr = intArrayOf(1, 5, 11, 5)
    val n = arr.size
    println("Input: ${arr.contentToString()}")
    println("Result: ${if (findPartition(arr, n)) "Can be divided" else "Cannot be divided"}")
    println("Expected: true")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val arr2 = intArrayOf()
    println("Input: ${arr2.contentToString()}")
    println("Result: ${if (findPartition(arr2, arr2.size)) "Can be divided" else "Cannot be divided"}")
    println("Expected: false")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val arr3 = intArrayOf(10)
    println("Input: ${arr3.contentToString()}")
    println("Result: ${if (findPartition(arr3, arr3.size)) "Can be divided" else "Cannot be divided"}")
    println("Expected: false")
    println()

    // Test 4: Odd sum (cannot partition)
    println("=== Test 4: Odd sum (cannot partition) ===")
    val arr4 = intArrayOf(1, 2, 3)
    println("Input: ${arr4.contentToString()}")
    println("Result: ${if (findPartition(arr4, arr4.size)) "Can be divided" else "Cannot be divided"}")
    println("Expected: false (sum = 6, but no valid partition)")
    println()

    // Test 5: Two equal elements
    println("=== Test 5: Two equal elements ===")
    val arr5 = intArrayOf(5, 5)
    println("Input: ${arr5.contentToString()}")
    println("Result: ${if (findPartition(arr5, arr5.size)) "Can be divided" else "Cannot be divided"}")
    println("Expected: true")
    println()

    // Test 6: All same elements (even count)
    println("=== Test 6: All same elements (even count) ===")
    val arr6 = intArrayOf(2, 2, 2, 2)
    println("Input: ${arr6.contentToString()}")
    println("Result: ${if (findPartition(arr6, arr6.size)) "Can be divided" else "Cannot be divided"}")
    println("Expected: true")
}

private fun isSubsetSum(arr: IntArray, n: Int, sum: Int): Boolean {
    // Base case: sum is 0
    if (sum == 0) {
        return true
    }

    // Base case: no elements left and sum is not 0
    if (n == 0 && sum != 0) {
        return false
    }

    // If last element is greater than sum, ignore it
    if (arr[n - 1] > sum) {
        return isSubsetSum(arr, n - 1, sum)
    }

    // Check if sum can be obtained by:
    // (a) including the last element
    // (b) excluding the last element
    return isSubsetSum(arr, n - 1, sum) || isSubsetSum(arr, n - 1, sum - arr[n - 1])
}

// TIME COMPLEXITY: O(2^n) - recursive exploration of all subsets (can be optimized to O(n*sum) with DP)
// SPACE COMPLEXITY: O(n) - recursion call stack depth
private fun findPartition(arr: IntArray, n: Int): Boolean {
    // Edge case: empty array or single element
    if (n == 0 || n == 1) {
        return false
    }

    // Calculate sum of all elements
    val sum = arr.sum()

    // If sum is odd, cannot partition into equal subsets
    if (sum % 2 != 0) {
        return false
    }

    // Find if there is a subset with sum equal to half of total sum
    return isSubsetSum(arr, n, sum / 2)
}
