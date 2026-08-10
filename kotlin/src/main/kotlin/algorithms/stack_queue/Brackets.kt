package algorithms.stack_queue

/**
 * PROBLEM: Brackets
 *
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 * - S is empty
 * - S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string
 * - S has the form "VW" where V and W are properly nested strings
 *
 * INPUT: S = "{[()()]}"
 * OUTPUT: 1 (properly nested)
 *
 * INPUT: S = "([)()]"
 * OUTPUT: 0 (not properly nested)
 *
 * APPROACH:
 * Use a stack. Push opening brackets, pop and match with closing brackets.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case - properly nested
    println("=== Test 1: Normal case - properly nested ===")
    var result1 = checkBrackets("{[()()]}")
    println("Result: $result1")
    println("Expected: 1 (properly nested)")
    println()

    // Test 2: Normal case - improperly nested
    println("=== Test 2: Normal case - improperly nested ===")
    var result2 = checkBrackets("([)()]")
    println("Result: $result2")
    println("Expected: 0 (not properly nested)")
    println()

    // Test 3: Edge case - empty string
    println("=== Test 3: Edge case - empty string ===")
    val result3 = checkBrackets("")
    println("Result: $result3")
    println("Expected: 1 (empty is properly nested)")
    println()

    // Test 4: Edge case - single pair
    println("=== Test 4: Edge case - single pair ===")
    val result4 = checkBrackets("()")
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: Edge case - unmatched opening bracket
    println("=== Test 5: Edge case - unmatched opening bracket ===")
    val result5 = checkBrackets("{[(")
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Edge case - unmatched closing bracket
    println("=== Test 6: Edge case - unmatched closing bracket ===")
    val result6 = checkBrackets(")}]")
    println("Result: $result6")
    println("Expected: 0")
}

// TIME COMPLEXITY: O(n) - single pass through string with stack operations
// SPACE COMPLEXITY: O(n) - stack can hold up to n/2 opening brackets

private fun checkBrackets(S: String): Int {
    val stack = mutableListOf<Char>()

    for (bracket in S) {
        when (bracket) {
            '{', '[', '(' -> stack.add(bracket)
            '}' -> {
                if (stack.isEmpty() || stack.removeAt(stack.size - 1) != '{') return 0
            }
            ']' -> {
                if (stack.isEmpty() || stack.removeAt(stack.size - 1) != '[') return 0
            }
            ')' -> {
                if (stack.isEmpty() || stack.removeAt(stack.size - 1) != '(') return 0
            }
        }
    }

    return if (stack.isEmpty()) 1 else 0
}
