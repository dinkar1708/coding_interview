package algorithms.dynamic_programming.subsequence

/**
 * PROBLEM: Longest Common Subsequence (LCS)
 *
 * Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 *
 * INPUT: s1 = "ADC", s2 = "ABCD"
 * OUTPUT: 2 (LCS = "AC")
 *
 * EXPLANATION:
 * - If characters match, include it and move to next in both sequences
 * - If they don't match, try both possibilities and take maximum
 * - Recursively solve for smaller subproblems
 *
 * Time Complexity: O(2^(m+n)) - exponential without memoization
 * Space Complexity: O(min(m,n)) - recursion stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = findLcs("ADC", "ABCD", 2, 3)
    println("Result: $result1")
    println("Expected: 2 (LCS='AC')")
    println()

    // Test 2: Edge case - empty strings
    println("=== Test 2: Edge case - empty strings ===")
    val result2 = if ("".isEmpty()) 0 else findLcs("", "ABC", -1, 2)
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - identical strings
    println("=== Test 3: Edge case - identical strings ===")
    val result3 = findLcs("ABC", "ABC", 2, 2)
    println("Result: $result3")
    println("Expected: 3")
    println()

    // Test 4: No common subsequence
    println("=== Test 4: No common subsequence ===")
    val result4 = findLcs("ABC", "DEF", 2, 2)
    println("Result: $result4")
    println("Expected: 0")
    println()

    // Test 5: Single character match
    println("=== Test 5: Single character match ===")
    val result5 = findLcs("A", "A", 0, 0)
    println("Result: $result5")
    println("Expected: 1")
    println()

    // Test 6: Longer strings
    println("=== Test 6: Longer strings ===")
    val s1 = "AGGTAB"
    val s2 = "GXTXAYB"
    val result6 = findLcs(s1, s2, s1.length - 1, s2.length - 1)
    println("Result: $result6")
    println("Expected: 4 (LCS='GTAB')")
}

// TIME COMPLEXITY: O(m*n) - fill m*n states for strings of length m and n
// SPACE COMPLEXITY: O(m*n) - recursion stack depth for top-down approach
private fun findLcs(A: String, B: String, m: Int, n: Int): Int {
    if (m < 0 || n < 0) {
        return 0
    }

    if (A[m] == B[n]) {
        return 1 + findLcs(A, B, m - 1, n - 1)
    } else {
        return maxOf(findLcs(A, B, m - 1, n), findLcs(A, B, m, n - 1))
    }
}
