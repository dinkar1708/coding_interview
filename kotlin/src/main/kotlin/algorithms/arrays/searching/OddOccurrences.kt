package algorithms.arrays.searching

/**
 * PROBLEM: Find the Odd Occurrence in Array
 *
 * Given an array with an odd number of elements, where every element except one appears
 * an even number of times, find the element that appears an odd number of times.
 *
 * INPUT:
 *   ar = [9, 3, 9, 3, 5]
 *
 * OUTPUT:
 *   5
 *
 * EXPLANATION:
 *   Using XOR operation: XOR of two same numbers cancels out (2 XOR 2 = 0).
 *   By XORing all elements, pairs cancel out and only the unique element remains.
 *   9 XOR 3 XOR 9 XOR 3 XOR 5 = (9 XOR 9) XOR (3 XOR 3) XOR 5 = 0 XOR 0 XOR 5 = 5
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only one variable used
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val arr = intArrayOf(9, 3, 9, 3, 5)
    val result = findOddOccurrence(arr)
    println("Input: ${arr.contentToString()}")
    println("Result: $result")
    println("Expected: 5")
    println()

    // Test 2: Single element
    println("=== Test 2: Single element ===")
    val arr2 = intArrayOf(42)
    val result2 = findOddOccurrence(arr2)
    println("Input: ${arr2.contentToString()}")
    println("Result: $result2")
    println("Expected: 42")
    println()

    // Test 3: Three elements (one pair)
    println("=== Test 3: Three elements (one pair) ===")
    val arr3 = intArrayOf(7, 7, 1)
    val result3 = findOddOccurrence(arr3)
    println("Input: ${arr3.contentToString()}")
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: Larger array with multiple pairs
    println("=== Test 4: Larger array with multiple pairs ===")
    val arr4 = intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5)
    val result4 = findOddOccurrence(arr4)
    println("Input: ${arr4.contentToString()}")
    println("Result: $result4")
    println("Expected: 5")
    println()

    // Test 5: Unordered array
    println("=== Test 5: Unordered array ===")
    val arr5 = intArrayOf(4, 2, 4, 1, 2)
    val result5 = findOddOccurrence(arr5)
    println("Input: ${arr5.contentToString()}")
    println("Result: $result5")
    println("Expected: 1")
}

// TIME COMPLEXITY: O(n) - single pass through array using XOR operation
// SPACE COMPLEXITY: O(1) - only one variable for XOR accumulation
private fun findOddOccurrence(ar: IntArray): Int {
    // Edge case: empty array
    if (ar.isEmpty()) {
        return 0
    }

    // Edge case: single element
    if (ar.size == 1) {
        return ar[0]
    }

    var xorSum = 0
    for (item in ar) {
        xorSum = xorSum xor item
    }
    return xorSum
}
