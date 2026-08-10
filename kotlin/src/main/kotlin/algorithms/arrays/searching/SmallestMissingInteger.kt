package algorithms.arrays.searching


/**
 * PROBLEM: Find Smallest Missing Positive Integer
 *
 * Given an array of integers, find the smallest positive integer (greater than 0)
 * that does not occur in the array.
 *
 * INPUT:
 *   A = [2, 3, 6, 4, 1, 2]
 *
 * OUTPUT:
 *   5
 *
 * EXPLANATION:
 *   The array contains [1, 2, 3, 4, 6]. The smallest missing positive is 5.
 *
 *   Algorithm:
 *   1. Find the maximum value in the array
 *   2. If max < 1, return 1 (no positive numbers)
 *   3. If array has one element:
 *      - Return 2 if element is 1
 *      - Return 1 otherwise
 *   4. Create a boolean array of size max to mark present numbers
 *   5. Mark all positive integers as present (using value as index)
 *   6. Find first unmarked position (that's the missing integer)
 *   7. If all positions marked, return max + 1
 *
 * Time Complexity: O(n) - linear passes through array
 * Space Complexity: O(max) - temporary array of size max value
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val arr1 = intArrayOf(2, 3, 6, 4, 1, 2)
    val result1 = findSmallestMissingInteger(arr1)
    println("Input: ${arr1.contentToString()}")
    println("Result: $result1")
    println("Expected: 5")
    println()

    // Test 2: Only negative numbers
    println("=== Test 2: Only negative numbers ===")
    val arr2 = intArrayOf(-1, -3)
    val result2 = findSmallestMissingInteger(arr2)
    println("Input: ${arr2.contentToString()}")
    println("Result: $result2")
    println("Expected: 1")
    println()

    // Test 3: Empty array
    println("=== Test 3: Empty array ===")
    val arr3 = intArrayOf()
    val result3 = findSmallestMissingInteger(arr3)
    println("Input: ${arr3.contentToString()}")
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: Single element - value 1
    println("=== Test 4: Single element - value 1 ===")
    val arr4 = intArrayOf(1)
    val result4 = findSmallestMissingInteger(arr4)
    println("Input: ${arr4.contentToString()}")
    println("Result: $result4")
    println("Expected: 2")
    println()

    // Test 5: Single element - value not 1
    println("=== Test 5: Single element - value not 1 ===")
    val arr5 = intArrayOf(5)
    val result5 = findSmallestMissingInteger(arr5)
    println("Input: ${arr5.contentToString()}")
    println("Result: $result5")
    println("Expected: 1")
    println()

    // Test 6: Consecutive sequence 1 to N
    println("=== Test 6: Consecutive sequence 1 to N ===")
    val arr6 = intArrayOf(1, 2, 3, 4, 5)
    val result6 = findSmallestMissingInteger(arr6)
    println("Input: ${arr6.contentToString()}")
    println("Result: $result6")
    println("Expected: 6")
    println()

    // Test 7: Missing first element (1)
    println("=== Test 7: Missing first element (1) ===")
    val arr7 = intArrayOf(2, 3, 4, 5)
    val result7 = findSmallestMissingInteger(arr7)
    println("Input: ${arr7.contentToString()}")
    println("Result: $result7")
    println("Expected: 1")
}

// TIME COMPLEXITY: O(n) - multiple passes through array to find max, mark present values, and find missing
// SPACE COMPLEXITY: O(max) - boolean array of size equal to maximum value in input array
private fun findSmallestMissingInteger(A: IntArray): Int {
    val maxValue = A.maxOrNull() ?: 0

    if (maxValue < 1) {
        // No positive numbers, smallest positive is 1
        return 1
    }

    if (A.size == 1) {
        // One element
        return if (A[0] == 1) 2 else 1
    }

    // Create array to mark which positive integers are present
    val tempMaxLenArray = IntArray(maxValue)

    for (i in A.indices) {
        // Only process positive items
        if (A[i] > 0) {
            // Mark this value as present (using value as index)
            if (tempMaxLenArray[A[i] - 1] != 1) {
                tempMaxLenArray[A[i] - 1] = 1
            }
        }
    }

    // Find first missing positive integer
    for (i in tempMaxLenArray.indices) {
        if (tempMaxLenArray[i] == 0) {
            return i + 1
        }
    }

    // All values from 1 to max are present, return max + 1
    return maxValue + 1
}
