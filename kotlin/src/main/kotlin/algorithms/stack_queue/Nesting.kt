package algorithms.stack_queue

/**
 * PROBLEM: Nesting
 *
 * A string S consisting of N characters is called properly nested if:
 * - S is empty
 * - S has the form "(U)" where U is a properly nested string
 * - S has the form "VW" where V and W are properly nested strings
 *
 * INPUT: S = "(()(())())"
 * OUTPUT: 1 (properly nested)
 *
 * INPUT: S = "())"
 * OUTPUT: 0 (not properly nested)
 *
 * APPROACH:
 * Use a stack. Count opening and closing parentheses.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case - properly nested
    println("=== Test 1: Normal case - properly nested ===")
    var result1 = checkNesting("(()(())())")
    println("Result: $result1")
    println("Expected: 1 (properly nested)")
    println()

    // Test 2: Normal case - improperly nested
    println("=== Test 2: Normal case - improperly nested ===")
    var result2 = checkNesting("())")
    println("Result: $result2")
    println("Expected: 0 (extra closing bracket)")
    println()

    // Test 3: Edge case - empty string
    println("=== Test 3: Edge case - empty string ===")
    val result3 = checkNesting("")
    println("Result: $result3")
    println("Expected: 1 (empty is properly nested)")
    println()

    // Test 4: Edge case - single pair
    println("=== Test 4: Edge case - single pair ===")
    val result4 = checkNesting("()")
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: Edge case - only opening brackets
    println("=== Test 5: Edge case - only opening brackets ===")
    val result5 = checkNesting("(((")
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Edge case - only closing brackets
    println("=== Test 6: Edge case - only closing brackets ===")
    val result6 = checkNesting(")))")
    println("Result: $result6")
    println("Expected: 0")
}

// TIME COMPLEXITY: O(n) - single pass through string with stack operations
// SPACE COMPLEXITY: O(n) - stack can hold up to n/2 opening parentheses

private fun checkNesting(S: String): Int {
    val stack = mutableListOf<Char>()

    for (bracket in S) {
        if (bracket == '(') {
            stack.add(bracket)
        } else if (bracket == ')') {
            if (stack.isEmpty()) {
                return 0
            }
            stack.removeAt(stack.size - 1)
        }
    }

    return if (stack.isEmpty()) 1 else 0
}
