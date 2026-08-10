package algorithms.patterns

/**
 * PROBLEM: Print Right Triangle Pattern with Stars
 *
 * Print a right-aligned triangle pattern using stars (*).
 * Each row has spaces followed by stars.
 *
 * INPUT: n = 6
 * OUTPUT:
 *      *
 *     **
 *    ***
 *   ****
 *  *****
 * ******
 *
 * APPROACH:
 * - Use recursion to print pattern
 * - For each row: print (n-1) spaces, then (num-n+1) stars
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(n) for recursion stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=6) ===")
    pattern(6, 6)
    println("Expected: Right-aligned triangle with 6 rows")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    pattern(0, 0)
    println("Expected: No output")
    println()

    // Test 3: Edge case - single row
    println("=== Test 3: Edge case - single row (n=1) ===")
    pattern(1, 1)
    println("Expected: Single star")
    println()

    // Test 4: Edge case - negative
    println("=== Test 4: Edge case - negative (n=-3) ===")
    pattern(-3, -3)
    println("Expected: No output")
    println()

    // Test 5: Small triangle
    println("=== Test 5: Small triangle (n=4) ===")
    pattern(4, 4)
    println("Expected: Right-aligned triangle with 4 rows")
    println()

    // Test 6: Larger triangle
    println("=== Test 6: Larger triangle (n=9) ===")
    pattern(9, 9)
    println("Expected: Right-aligned triangle with 9 rows")
}

// TIME COMPLEXITY: O(n^2) - n rows with spaces and stars, total proportional to n^2
// SPACE COMPLEXITY: O(n) - recursion stack depth up to n levels
private fun pattern(n: Int, num: Int) {
    // Edge case: n <= 0
    if (n <= 0) {
        return
    }

    // Print spaces (right align)
    printRow(n - 1, " ")

    // Print stars
    printRow(num - n + 1, "*")

    // Print newline and recurse
    println()
    pattern(n - 1, num)
}

private fun printRow(row: Int, symbol: String) {
    if (row == 0) {
        return
    }
    print(symbol)
    printRow(row - 1, symbol)
}
