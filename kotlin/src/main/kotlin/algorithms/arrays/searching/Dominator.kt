package algorithms.arrays.searching


/**
 * PROBLEM: Find the Dominator (Leader) Index
 *
 * Find the index of a dominator in an array. A dominator is a value that occurs
 * in more than half of the elements of the array.
 *
 * INPUT:
 *   A = [3, 4, 3, 2, 3, -1, 3, 3]
 *
 * OUTPUT:
 *   Index of dominator (e.g., 0, 2, 4, 6, or 7 - any index where 3 appears)
 *   Returns -1 if no dominator exists
 *
 * EXPLANATION:
 *   The value 3 appears 5 times in an array of length 8.
 *   Since 5 > 8/2, value 3 is the dominator.
 *   We use Boyer-Moore Voting Algorithm to find the candidate in O(n) time.
 *   First pass: Find candidate using majority voting
 *   Second pass: Verify candidate appears more than n/2 times
 *
 * Time Complexity: O(n) - two passes through array
 * Space Complexity: O(1) - constant space
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val testArray = intArrayOf(3, 4, 3, 2, 3, -1, 3, 3)
    println("Boyer-Moore: ${findDominator(testArray)}")
    println("Map version: ${solutionWithMap(testArray)}")
    println("Input: ${testArray.contentToString()}")
    println("Expected: Any index where 3 appears (0, 2, 4, 6, or 7)")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val arr2 = intArrayOf()
    println("Boyer-Moore: ${findDominator(arr2)}")
    println("Map version: ${solutionWithMap(arr2)}")
    println("Input: ${arr2.contentToString()}")
    println("Expected: -1")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val arr3 = intArrayOf(5)
    println("Boyer-Moore: ${findDominator(arr3)}")
    println("Map version: ${solutionWithMap(arr3)}")
    println("Input: ${arr3.contentToString()}")
    println("Expected: 0")
    println()

    // Test 4: Two elements - same value
    println("=== Test 4: Two elements - same value ===")
    val arr4 = intArrayOf(7, 7)
    println("Boyer-Moore: ${findDominator(arr4)}")
    println("Map version: ${solutionWithMap(arr4)}")
    println("Input: ${arr4.contentToString()}")
    println("Expected: 0 or 1")
    println()

    // Test 5: No dominator (all different)
    println("=== Test 5: No dominator (all different) ===")
    val arr5 = intArrayOf(1, 2, 3, 4, 5)
    println("Boyer-Moore: ${findDominator(arr5)}")
    println("Map version: ${solutionWithMap(arr5)}")
    println("Input: ${arr5.contentToString()}")
    println("Expected: -1")
    println()

    // Test 6: No dominator (equal frequency)
    println("=== Test 6: No dominator (equal frequency) ===")
    val arr6 = intArrayOf(1, 1, 2, 2)
    println("Boyer-Moore: ${findDominator(arr6)}")
    println("Map version: ${solutionWithMap(arr6)}")
    println("Input: ${arr6.contentToString()}")
    println("Expected: -1")
    println()

    // Test 7: All same elements
    println("=== Test 7: All same elements ===")
    val arr7 = intArrayOf(9, 9, 9, 9)
    println("Boyer-Moore: ${findDominator(arr7)}")
    println("Map version: ${solutionWithMap(arr7)}")
    println("Input: ${arr7.contentToString()}")
    println("Expected: 0")
}

// TIME COMPLEXITY: O(n) - two passes: Boyer-Moore voting + verification with count and indexOf
// SPACE COMPLEXITY: O(1) - only constant variables for candidate tracking
private fun findDominator(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return -1
    }

    // Edge case: single element (it's always the dominator)
    if (A.size == 1) {
        return 0
    }

    var continuousBlockSize = 0
    var candidate = 0

    // Find candidate using Boyer-Moore Voting Algorithm
    for (element in A) {
        if (continuousBlockSize == 0) {
            // New candidate starts
            candidate = element
            continuousBlockSize += 1
        } else if (candidate == element) {
            // Same element, increase counter
            continuousBlockSize += 1
        } else {
            // Different element, decrease counter
            continuousBlockSize -= 1
        }
    }

    // Verify if candidate is actually a dominator
    val count = A.count { it == candidate }
    return if (count > A.size / 2) {
        A.indexOf(candidate)
    } else {
        -1
    }
}

/**
 * Alternative Solution: Using MutableMap for Frequency Counting
 *
 * APPROACH:
 *   - Store each number as key and its frequency as value
 *   - Track the value with maximum frequency
 *   - If max frequency > n/2, find and return its index
 *   - Otherwise return -1
 *
 * Time Complexity: O(n) - single pass + index lookup
 * Space Complexity: O(n) - map stores unique elements
 */
// TIME COMPLEXITY: O(n) - single pass for frequency counting + map iteration + indexOf lookup
// SPACE COMPLEXITY: O(k) - map stores k unique elements, worst case k=n
private fun solutionWithMap(A: IntArray): Int {
    // Edge cases
    if (A.isEmpty()) return -1
    if (A.size == 1) return 0

    // Count frequency of each element
    val freqMap = mutableMapOf<Int, Int>()
    for (num in A) {
        freqMap[num] = freqMap.getOrDefault(num, 0) + 1
    }

    // Find element with maximum frequency
    var maxCount = 0
    var dominator: Int? = null

    for ((num, count) in freqMap) {
        if (count > maxCount) {
            maxCount = count
            dominator = num
        }
    }

    // Check if dominator appears more than n/2 times
    return if (dominator != null && maxCount > A.size / 2) {
        A.indexOf(dominator)
    } else {
        -1
    }
}
