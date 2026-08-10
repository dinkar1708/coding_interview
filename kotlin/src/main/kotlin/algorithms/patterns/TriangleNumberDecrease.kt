package algorithms.patterns

/**
 * PROBLEM: Print Decreasing Number Triangle
 *
 * Print a triangle pattern where each row starts from current row number
 * and decreases to 1.
 *
 * INPUT: n = 5
 * OUTPUT:
 * 54321
 * 4321
 * 321
 * 21
 * 1
 *
 * APPROACH:
 * - Two approaches: iterative and recursive
 * - For each row, print numbers from row value down to 1
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(n) for recursion stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    println("Iterative approach:")
    triangle(5)
    println("Recursive approach:")
    triangleRecursion(5, 5)
    println("Expected: 5 rows of decreasing numbers")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    println("Iterative:")
    triangle(0)
    println("Recursive:")
    triangleRecursion(0, 0)
    println("Expected: No output")
    println()

    // Test 3: Edge case - single row
    println("=== Test 3: Edge case - single row (n=1) ===")
    println("Iterative:")
    triangle(1)
    println("Recursive:")
    triangleRecursion(1, 1)
    println("Expected: Just '1'")
    println()

    // Test 4: Edge case - negative
    println("=== Test 4: Edge case - negative (n=-2) ===")
    println("Iterative:")
    triangle(-2)
    println("Recursive:")
    triangleRecursion(-2, -2)
    println("Expected: No output")
    println()

    // Test 5: Small triangle
    println("=== Test 5: Small triangle (n=3) ===")
    println("Iterative:")
    triangle(3)
    println("Recursive:")
    triangleRecursion(3, 3)
    println("Expected: 3 rows of decreasing numbers")
    println()

    // Test 6: Larger triangle
    println("=== Test 6: Larger triangle (n=8) ===")
    println("Iterative:")
    triangle(8)
    println("Expected: 8 rows of decreasing numbers")
}

// TIME COMPLEXITY: O(n^2) - n rows with decreasing numbers, total n*(n+1)/2 operations
// SPACE COMPLEXITY: O(1) - iterative approach with only loop variables
private fun triangle(n: Int) {
    // Edge case: n <= 0
    if (n <= 0) {
        return
    }

    // Iterative approach
    for (col in n downTo 1) {
        for (row in col downTo 1) {
            print(row)
        }
        println()
    }
}

// TIME COMPLEXITY: O(n^2) - n rows with decreasing numbers, total n*(n+1)/2 operations
// SPACE COMPLEXITY: O(n) - recursion stack depth up to n levels
private fun triangleRecursion(row: Int, col: Int) {
    // Edge case: row <= 0 or col <= 0
    if (row <= 0 || col <= 0) {
        return
    }

    // Recursive approach
    print(row)

    if (col == 1 && row == 1) {
        // Base case: reached the end
        println("Break recursion...")
        return
    } else if (row == 1) {
        // End of current row
        println()
        // Move to next row with decreased column
        triangleRecursion(col - 1, col - 1)
    } else {
        // Continue printing current row
        triangleRecursion(row - 1, col)
    }
}
