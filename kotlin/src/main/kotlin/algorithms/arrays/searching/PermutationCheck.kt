package algorithms.arrays.searching


/**
 * PROBLEM: Check if Array is a Permutation
 *
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 * Determine if an array is a valid permutation.
 *
 * INPUT:
 *   ar = [1, 2, 3, 4]
 *
 * OUTPUT:
 *   1 (true - it's a valid permutation)
 *
 * EXPLANATION:
 *   Use XOR operation to check if array contains exactly elements 1 to N.
 *   - XOR all elements in the array
 *   - XOR all numbers from 1 to N
 *   - If result is 0, array is a permutation (all elements cancel out)
 *
 *   For [1, 2]:
 *   Array XOR: 1 XOR 2 = 3
 *   Range XOR: 1 XOR 2 = 3
 *   Combined: 3 XOR 3 = 0 ✓
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only one variable used
 */

fun main() {
    // Test 1: Normal case - valid permutation
    println("=== Test 1: Normal case - valid permutation ===")
    val arr1 = intArrayOf(1, 2, 3, 4)
    val result1 = permCheck(arr1)
    println("Input: ${arr1.contentToString()}")
    println("Result: $result1")
    println("Expected: 1")
    println()

    // Test 2: Two elements - valid permutation
    println("=== Test 2: Two elements - valid permutation ===")
    val arr2 = intArrayOf(1, 2)
    val result2 = permCheck(arr2)
    println("Input: ${arr2.contentToString()}")
    println("Result: $result2")
    println("Expected: 1")
    println()

    // Test 3: Empty array
    println("=== Test 3: Empty array ===")
    val arr3 = intArrayOf()
    val result3 = permCheck(arr3)
    println("Input: ${arr3.contentToString()}")
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Single element - valid (must be 1)
    println("=== Test 4: Single element - valid (must be 1) ===")
    val arr4 = intArrayOf(1)
    val result4 = permCheck(arr4)
    println("Input: ${arr4.contentToString()}")
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: Single element - invalid (not 1)
    println("=== Test 5: Single element - invalid (not 1) ===")
    val arr5 = intArrayOf(5)
    val result5 = permCheck(arr5)
    println("Input: ${arr5.contentToString()}")
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Missing element
    println("=== Test 6: Missing element ===")
    val arr6 = intArrayOf(1, 3, 4)
    val result6 = permCheck(arr6)
    println("Input: ${arr6.contentToString()}")
    println("Result: $result6")
    println("Expected: 0 (missing 2)")
    println()

    // Test 7: Duplicate element
    println("=== Test 7: Duplicate element ===")
    val arr7 = intArrayOf(1, 2, 2, 3)
    val result7 = permCheck(arr7)
    println("Input: ${arr7.contentToString()}")
    println("Result: $result7")
    println("Expected: 0 (duplicate 2)")
}

// TIME COMPLEXITY: O(n) - single pass XORing array elements with range 1..n
// SPACE COMPLEXITY: O(1) - only one variable for XOR accumulation
private fun permCheck(ar: IntArray): Int {
    // Edge case: empty array
    if (ar.isEmpty()) {
        return 0
    }

    // Edge case: single element (must be [1] to be valid)
    if (ar.size == 1) {
        return if (ar[0] == 1) 1 else 0
    }

    var xorSum = 0

    // XOR all elements from 1 to len(ar) and array elements
    for (index in 1..ar.size) {
        xorSum = xorSum xor index xor ar[index - 1]
    }

    // If xor sum is 0, it's a valid permutation
    return if (xorSum == 0) 1 else 0
}
