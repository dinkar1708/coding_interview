package algorithms.recursion

/**
 * PROBLEM: Sum Array Elements Using Recursion
 *
 * Calculate the sum of all elements in an array using recursive approach.
 *
 * INPUT:
 *   A = [1, 5, 7, -2]
 *
 * OUTPUT:
 *   11
 *
 * EXPLANATION:
 *   Recursive approach divides the problem:
 *   - Base case: if index == array length, return 0
 *   - Recursive case: current element + sum of rest
 *
 *   Execution trace:
 *   sum([1,5,7,-2], 0) = 1 + sum([1,5,7,-2], 1)
 *   sum([1,5,7,-2], 1) = 5 + sum([1,5,7,-2], 2)
 *   sum([1,5,7,-2], 2) = 7 + sum([1,5,7,-2], 3)
 *   sum([1,5,7,-2], 3) = -2 + sum([1,5,7,-2], 4)
 *   sum([1,5,7,-2], 4) = 0 (base case)
 *   Result: 1 + 5 + 7 + (-2) = 11
 *
 * Time Complexity: O(n) - visits each element once
 * Space Complexity: O(n) - recursion call stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val arr = intArrayOf(1, 5, 7, -2)
    val result = sumArray(arr, arr.size, 0)
    println("Input: ${arr.contentToString()}")
    println("Result: $result")
    println("Expected: 11")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val arr2 = intArrayOf()
    val result2 = sumArray(arr2, arr2.size, 0)
    println("Input: ${arr2.contentToString()}")
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val arr3 = intArrayOf(42)
    val result3 = sumArray(arr3, arr3.size, 0)
    println("Input: ${arr3.contentToString()}")
    println("Result: $result3")
    println("Expected: 42")
    println()

    // Test 4: All zeros
    println("=== Test 4: All zeros ===")
    val arr4 = intArrayOf(0, 0, 0, 0)
    val result4 = sumArray(arr4, arr4.size, 0)
    println("Input: ${arr4.contentToString()}")
    println("Result: $result4")
    println("Expected: 0")
    println()

    // Test 5: All negative numbers
    println("=== Test 5: All negative numbers ===")
    val arr5 = intArrayOf(-1, -2, -3, -4)
    val result5 = sumArray(arr5, arr5.size, 0)
    println("Input: ${arr5.contentToString()}")
    println("Result: $result5")
    println("Expected: -10")
    println()

    // Test 6: All positive numbers
    println("=== Test 6: All positive numbers ===")
    val arr6 = intArrayOf(1, 2, 3, 4, 5)
    val result6 = sumArray(arr6, arr6.size, 0)
    println("Input: ${arr6.contentToString()}")
    println("Result: $result6")
    println("Expected: 15")
}

// TIME COMPLEXITY: O(n) - visit each element once recursively
// SPACE COMPLEXITY: O(n) - recursion call stack of depth n
private fun sumArray(A: IntArray, lenA: Int, index: Int): Int {
    // Edge case: empty array
    if (lenA == 0) {
        return 0
    }

    if (index == lenA) {
        // Base case: reached end of array
        return 0
    }
    // Recursive case: current element + sum of rest
    val sum = A[index] + sumArray(A, lenA, index + 1)
    return sum
}
