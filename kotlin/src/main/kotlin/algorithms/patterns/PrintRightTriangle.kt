package algorithms.patterns

/**
 * PROBLEM: Print Right Triangle Pattern
 *
 * Print a right-angled triangle pattern using asterisks.
 *
 * INPUT:
 *   n = 6
 *
 * OUTPUT:
 *   *
 *   * *
 *   * * *
 *   * * * *
 *   * * * * *
 *   * * * * * *
 *
 * EXPLANATION:
 *   Use recursion to print n rows where row i has i asterisks.
 *   - printRow(num): recursively prints 'num' asterisks
 *   - printPattern(n, i): recursively prints n rows starting from row i
 *
 * Time Complexity: O(n²) - n rows with up to n stars each
 * Space Complexity: O(n) - recursion depth
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=6) ===")
    val n1 = 6
    printPattern(n1, 1)
    println("Expected: 6 rows with increasing stars")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    printPattern(0, 1)
    println("Expected: No output (empty pattern)")
    println()

    // Test 3: Edge case - single row
    println("=== Test 3: Edge case - single row (n=1) ===")
    printPattern(1, 1)
    println("Expected: Single star")
    println()

    // Test 4: Edge case - negative number
    println("=== Test 4: Edge case - negative (n=-3) ===")
    printPattern(-3, 1)
    println("Expected: No output")
    println()

    // Test 5: Small triangle
    println("=== Test 5: Small triangle (n=3) ===")
    printPattern(3, 1)
    println("Expected: 3 rows with increasing stars")
    println()

    // Test 6: Large triangle
    println("=== Test 6: Larger triangle (n=10) ===")
    printPattern(10, 1)
    println("Expected: 10 rows with increasing stars")
}

private fun printRow(num: Int) {
    if (num == 0) {
        return
    }
    print("* ")
    printRow(num - 1)
}

// TIME COMPLEXITY: O(n^2) - n rows with i stars each, total n*(n+1)/2 stars printed
// SPACE COMPLEXITY: O(n) - recursion stack depth up to n levels
private fun printPattern(n: Int, i: Int) {
    // Edge case: n <= 0
    if (n <= 0) {
        return
    }
    // Print single line/row
    printRow(i)
    println() // New line
    // Print next line/row
    printPattern(n - 1, i + 1)
}
