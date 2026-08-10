package algorithms.bit_manipulation

/**
 * PROBLEM: Find Longest Binary Gap
 *
 * A binary gap is the maximum sequence of consecutive zeros surrounded by ones
 * in the binary representation of a number.
 *
 * INPUT:
 *   N = 1041
 *
 * OUTPUT:
 *   5
 *
 * EXPLANATION:
 *   1041 in binary is 10000010001
 *   Binary gaps:
 *   - Between 1st and 2nd '1': 5 zeros
 *   - Between 2nd and 3rd '1': 3 zeros
 *   Longest binary gap = 5
 *
 *   Algorithm:
 *   Convert number to binary string and find gaps between '1's.
 *   Track the index of last '1' seen and calculate gap length.
 *
 * Time Complexity: O(log n) - number of bits in N
 * Space Complexity: O(log n) - binary string representation
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result = findLongestBinaryGap(1041)
    println("Input: 1041")
    println("Binary: ${1041.toString(2)}")
    println("Result: $result")
    println("Expected: 5")
    println()

    // Test 2: N = 0
    println("=== Test 2: N = 0 ===")
    val result2 = findLongestBinaryGap(0)
    println("Input: 0")
    println("Binary: ${0.toString(2)}")
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: N = 1 (single bit, no gap)
    println("=== Test 3: N = 1 (single bit, no gap) ===")
    val result3 = findLongestBinaryGap(1)
    println("Input: 1")
    println("Binary: ${1.toString(2)}")
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Power of 2 (no gap, like 8 = 1000)
    println("=== Test 4: Power of 2 (no gap) ===")
    val result4 = findLongestBinaryGap(8)
    println("Input: 8")
    println("Binary: ${8.toString(2)}")
    println("Result: $result4")
    println("Expected: 0 (trailing zeros don't count)")
    println()

    // Test 5: Consecutive ones (like 7 = 111)
    println("=== Test 5: Consecutive ones (no gap) ===")
    val result5 = findLongestBinaryGap(7)
    println("Input: 7")
    println("Binary: ${7.toString(2)}")
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Number with gap of 1 (like 5 = 101)
    println("=== Test 6: Gap of 1 ===")
    val result6 = findLongestBinaryGap(5)
    println("Input: 5")
    println("Binary: ${5.toString(2)}")
    println("Result: $result6")
    println("Expected: 1")
    println()

    // Test 7: Large number with multiple gaps
    println("=== Test 7: Multiple gaps (9 = 1001) ===")
    val result7 = findLongestBinaryGap(9)
    println("Input: 9")
    println("Binary: ${9.toString(2)}")
    println("Result: $result7")
    println("Expected: 2")
}

// TIME COMPLEXITY: O(log n) - iterate through log(n) bits in binary representation
// SPACE COMPLEXITY: O(log n) - binary string of length log(n)
private fun findLongestBinaryGap(N: Int): Int {
    // Edge case: N <= 0
    if (N <= 0) {
        return 0
    }

    var longest = -1
    var lastLongestIndex = 0
    val ar = N.toString(2) // Convert to binary string

    for (i in ar.indices) {
        if (ar[i] == '1') {
            // Calculate gap length from last '1'
            longest = maxOf(longest, i - lastLongestIndex - 1)
            lastLongestIndex = i
        }
    }

    return if (longest == -1) 0 else longest
}
