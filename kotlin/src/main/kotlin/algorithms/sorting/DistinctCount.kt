package algorithms.sorting


/**
 * PROBLEM: Distinct Array Count
 *
 * Write a function that, given an array A consisting of N integers, returns the number
 * of distinct values in array A.
 *
 * INPUT: A = [2, 1, 1, 2, 3, 1]
 * OUTPUT: 3
 * EXPLANATION: Array has 3 distinct values: 1, 2, 3
 *
 * APPROACH:
 * Use a set (hash map) to track unique values. Each value is added to the set,
 * which automatically handles duplicates.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = countDistinct(intArrayOf(2, 1, 1, 2, 3, 1))
    println("Result: $result1")
    println("Expected: 3 (distinct: 1, 2, 3)")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = countDistinct(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single element
    println("=== Test 3: Edge case - single element ===")
    val result3 = countDistinct(intArrayOf(42))
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: Edge case - all same values
    println("=== Test 4: Edge case - all same values ===")
    val result4 = countDistinct(intArrayOf(5, 5, 5, 5, 5))
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: Edge case - all distinct
    println("=== Test 5: Edge case - all distinct ===")
    val result5 = countDistinct(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result5")
    println("Expected: 5")
    println()

    // Test 6: Negative and positive numbers
    println("=== Test 6: Negative and positive numbers ===")
    val result6 = countDistinct(intArrayOf(-3, -1, 0, 1, 3, -3, 0))
    println("Result: $result6")
    println("Expected: 5 (distinct: -3, -1, 0, 1, 3)")
    println()

    println("==========================================================")
    println("ALTERNATIVE APPROACH: Using Fixed-Size Array (NO HashMap)")
    println("==========================================================")
    println()

    // Test 7: Small range (0-9)
    println("=== Test 7: Small range 0-9 (using array) ===")
    val result7 = solutionUsingArray(intArrayOf(2, 1, 1, 2, 3, 1, 9, 9))
    println("Result: $result7")
    println("Expected: 4 (distinct: 1, 2, 3, 9)")
    println()

    // Test 8: All same
    println("=== Test 8: All same (using array) ===")
    val result8 = solutionUsingArray(intArrayOf(5, 5, 5, 5))
    println("Result: $result8")
    println("Expected: 1")
    println()

    // Test 9: All distinct
    println("=== Test 9: All distinct (using array) ===")
    val result9 = solutionUsingArray(intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    println("Result: $result9")
    println("Expected: 10")
}

// APPROACH 1: Using HashSet (Set)
// TIME COMPLEXITY: O(n) - iterate array once, add to set is O(1) average
// SPACE COMPLEXITY: O(k) - where k is number of distinct values
private fun countDistinct(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return 0
    }

    val temp = mutableSetOf<Int>()

    for (i in A.indices) {
        temp.add(A[i])
    }

    println(temp)
    return temp.size
}

// ALTERNATIVE APPROACH: Using Fixed-Size Array (NO HashMap)
// TIME COMPLEXITY: O(n) - single pass through array
// SPACE COMPLEXITY: O(k) - where k is the range of values (e.g., 10 for 0-9)
//
// WHEN TO USE:
// - Numbers are in a SMALL, KNOWN range (e.g., 0-9, 0-100)
// - Need O(1) space complexity (fixed array size)
// - Faster than HashMap for small ranges
//
// LIMITATIONS:
// - Only works for non-negative integers in known range
// - For large ranges (e.g., 0-1,000,000), wastes memory
// - For negative numbers, need offset (see solutionUsingArrayWithNegatives)
//
private fun solutionUsingArray(A: IntArray): Int {
    // Assume: numbers are in range 0-9
    // Similar to character counting (like 'a'-'z' uses IntArray(26))

    if (A.isEmpty()) return 0

    // Fixed-size array for range 0-9
    val seen = IntArray(10)  // Index 0 for value 0, index 1 for value 1, etc.

    // Mark each number as seen
    for (num in A) {
        if (num >= 0 && num < 10) {  // Ensure in valid range
            seen[num] = 1  // Mark as seen (like marking 'a' was seen)
        }
    }

    println("Seen array: ${seen.contentToString()}")

    // Count how many positions have value 1 (were seen)
    var distinctCount = 0
    for (i in seen.indices) {
        if (seen[i] == 1) {
            distinctCount++
        }
    }

    return distinctCount
}

// BONUS: Handle negative numbers with offset
// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(k) where k is range size
//
// Example: If range is -100 to 100, need array size 201
// Offset: value = -100 maps to index 0, value = 0 maps to index 100, value = 100 maps to index 200
//
private fun solutionUsingArrayWithNegatives(A: IntArray, minValue: Int, maxValue: Int): Int {
    if (A.isEmpty()) return 0

    val range = maxValue - minValue + 1
    val seen = IntArray(range)

    for (num in A) {
        if (num >= minValue && num <= maxValue) {
            val index = num - minValue  // Offset: -100 becomes 0, 0 becomes 100, 100 becomes 200
            seen[index] = 1
        }
    }

    return seen.count { it == 1 }
}

/*
================================================================================
COMPARISON: HashMap vs Fixed-Size Array
================================================================================

Use HashMap (Set):
✓ Numbers can be any range (even negative)
✓ Unknown range
✓ Very large range (e.g., -1,000,000 to 1,000,000)
✓ Sparse data (few distinct values in large range)

Examples:
- A = [1, 1000000, -500000, 1]  → HashMap better (range too large)
- A = [random integers]          → HashMap better (unknown range)

---

Use Fixed-Size Array:
✓ Numbers in SMALL, KNOWN range (e.g., 0-9, 0-100)
✓ Dense data (many values in small range)
✓ Need maximum performance
✓ Similar to character counting problems

Examples:
- A = [2, 1, 1, 2, 3, 1, 9, 9]  → Array better (range 0-9)
- A = [ages of people]           → Array better (range 0-120)
- A = [dice rolls]               → Array better (range 1-6)
- A = [test scores 0-100]        → Array better (range 0-100)

================================================================================
PATTERN: Similar to Character Counting
================================================================================

Character counting (lowercase):
    val count = IntArray(26)
    for (ch in string) {
        count[ch - 'a']++  // Map 'a'->0, 'b'->1, etc.
    }

Number counting (0-9):
    val count = IntArray(10)
    for (num in array) {
        count[num]++  // Map 0->0, 1->1, etc.
    }

Number counting with offset (-5 to 5):
    val count = IntArray(11)  // Range size: 5-(-5)+1 = 11
    for (num in array) {
        count[num + 5]++  // Map -5->0, 0->5, 5->10
    }

SAME CONCEPT: Use value as direct index (with offset if needed)!

================================================================================
COMPLEXITY COMPARISON
================================================================================

Input: Array of n elements, k distinct values

Approach 1: HashMap/Set
- Time: O(n)
- Space: O(k) - stores k distinct values
- Overhead: Hash computation, collision handling

Approach 2: Fixed Array (range 0-9)
- Time: O(n)
- Space: O(10) = O(1) - fixed size!
- Overhead: None

Approach 3: Fixed Array (range minVal to maxVal)
- Time: O(n)
- Space: O(maxVal - minVal + 1)
- Overhead: None

For small ranges: Array is FASTER and uses LESS space than HashMap! ✓

================================================================================
*/
