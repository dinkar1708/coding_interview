package algorithms.stack_queue

/**
 * PROBLEM: Balanced Brackets (Multiple Types)
 *
 * Check if a string containing brackets '()', '{}', '[]' is properly balanced.
 *
 * A string is balanced if:
 * 1. Every opening bracket has a corresponding closing bracket of the same type
 * 2. Brackets are properly nested (no crossing)
 * 3. Closing brackets appear in the correct order
 *
 * INPUT: "({[]})"
 * OUTPUT: Balanced ✓
 *
 * INPUT: "([)]"
 * OUTPUT: Not balanced (mismatch)
 *
 * APPROACH:
 * - Use a stack to track opening brackets
 * - When seeing opening bracket: push to stack
 * - When seeing closing bracket: pop and verify it matches
 * - At end: stack must be empty (all brackets closed)
 *
 * Time Complexity: O(n) where n is string length
 * Space Complexity: O(n) for stack in worst case
 */

import java.util.Stack

fun main() {
    // Key test cases to verify mismatch detection logic

    println("=== Test 1: Mismatch - crossing brackets ===")
    println("This shows WHY we need to check after pop!")
    checkBalancedParentheses("([)]")
    println()

    println("=== Test 2: Mismatch - wrong closing ===")
    println("Another example showing mismatch detection")
    checkBalancedParentheses("({)}")
    println()

    println("=== Test 3: Balanced - proper nesting ===")
    println("Compare with correct version")
    checkBalancedParentheses("({[]})")
    println()

    // Uncomment below to test other cases:

    // println("=== Test 4: Simple balanced ===")
    // checkBalancedParentheses("()")
    // println()

    // println("=== Test 5: Alternating types ===")
    // checkBalancedParentheses("(){}[]")
    // println()

    // println("=== Test 6: Unclosed bracket ===")
    // checkBalancedParentheses("(()")
    // println()

    // println("=== Test 7: Extra closing bracket ===")
    // checkBalancedParentheses("())")
    // println()

    // println("=== Test 8: Empty string ===")
    // checkBalancedParentheses("")
    // println()

    // println("=== Test 9: Only opening brackets ===")
    // checkBalancedParentheses("({[")
    // println()

    // println("=== Test 10: Only closing brackets ===")
    // checkBalancedParentheses("]})")
    // println()

    // println("=== Test 11: Complex nested ===")
    // checkBalancedParentheses("((())){[[]]}()")
    // println()

    // println("=== Test 12: Deep nesting ===")
    // checkBalancedParentheses("(((({}))))")
    // println()
}

// TIME COMPLEXITY: O(n) - single pass through string
// SPACE COMPLEXITY: O(n) - stack can hold up to n/2 opening brackets
private fun checkBalancedParentheses(str: String) {
    println("Input: \"$str\"")

    val stack = Stack<Char>()

    for (ch in str) {
        when (ch) {
            '(', '{', '[' -> {
                // Opening bracket - push to stack
                stack.push(ch)
                println("  → Pushed: '$ch', Stack: $stack")
            }
            ')', '}', ']' -> {
                // Closing bracket - check if matches top
                if (stack.isEmpty()) {
                    println("  → Stack is EMPTY! Cannot match '$ch'")
                    println("Result: Not balanced (no matching opening)")
                    return  // ← RETURN exits the function immediately (NOT BALANCED)
                }

                val top = stack.pop()
                println("  → Stack: $stack, Popped: '$top', Match with: '$ch'")

                val isMatching = (ch == ')' && top == '(') ||
                                (ch == '}' && top == '{') ||
                                (ch == ']' && top == '[')

                if (!isMatching) {
                    // MISMATCH DETECTED - This is the KEY check!
                    println("  → ❌ MISMATCH! '$top' does NOT match '$ch'")
                    println("      (Expected: '$top' should be opening for '$ch')")
                    println("Result: Not balanced (mismatch)")
                    return  // ← RETURN exits the function from loop (NOT BALANCED)
                }

                println("  → ✓ Match!")
            }
        }
    }

    // If we reach here, no mismatch was found in the loop
    // Now check if all opening brackets were closed
    println("\nFinal check:")
    println("  → Stack: $stack")
    if (stack.isEmpty()) {
        println("  → All brackets matched correctly!")
        println("Result: Balanced ✓")
        return  // ← RETURN from function (BALANCED)
    } else {
        println("  → ${stack.size} unclosed bracket(s) remaining!")
        println("Result: Not balanced (unclosed brackets)")
        return  // ← RETURN from function (NOT BALANCED - unclosed)
    }
}

/*
================================================================================
WHY WE NEED TO CHECK AFTER POP
================================================================================

KEY QUESTION: Why not just pop without checking?

ANSWER: Because the stack can contain DIFFERENT bracket types!

Example that FAILS without checking:

Input: "([)]"

Step-by-step:
1. '(' → Push '('     Stack: [ '(' ]
2. '[' → Push '['     Stack: [ '(', '[' ]
3. ')' → Pop...       Stack top is '['

   WITHOUT CHECK:
   - Just pop '[' → Stack: [ '(' ]
   - Continue processing ✗ WRONG!
   - Would say "balanced" ✗ INCORRECT!

   WITH CHECK:
   - Pop '[', check if '[' matches ')' ?
   - '[' should match ']', NOT ')' !
   - Return "not balanced" ✓ CORRECT!

The Problem:
- ')' should ONLY match '('
- '}' should ONLY match '{'
- ']' should ONLY match '['

But stack can have any opening bracket on top!
So we MUST verify the types match!

================================================================================
VISUAL EXAMPLES
================================================================================

Example 1: "({[]})" - BALANCED ✓

Stack evolution:
'(' → [ '(' ]
'{' → [ '(', '{' ]
'[' → [ '(', '{', '[' ]
']' → pop '[', check '[' matches ']' ? YES ✓ → [ '(', '{' ]
'}' → pop '{', check '{' matches '}' ? YES ✓ → [ '(' ]
')' → pop '(', check '(' matches ')' ? YES ✓ → [ EMPTY ]

Result: Balanced ✓

---

Example 2: "([)]" - NOT BALANCED ✗

Stack evolution:
'(' → [ '(' ]
'[' → [ '(', '[' ]
')' → pop '[', check '[' matches ')' ? NO ✗

Stack top is '[' but we need '(' to match ')'!
MISMATCH detected → Return "not balanced"

---

Example 3: "(()" - NOT BALANCED ✗

Stack evolution:
'(' → [ '(' ]
'(' → [ '(', '(' ]
')' → pop '(', check '(' matches ')' ? YES ✓ → [ '(' ]

Final stack: [ '(' ] - NOT EMPTY!
Unclosed bracket → Return "not balanced"

---

Example 4: "())" - NOT BALANCED ✗

Stack evolution:
'(' → [ '(' ]
')' → pop '(', check '(' matches ')' ? YES ✓ → [ EMPTY ]
')' → stack is EMPTY! Cannot pop!

Extra closing bracket → Return "not balanced"

================================================================================
COMPARISON WITH SINGLE BRACKET TYPE (StackQueueNesting.kt)
================================================================================

Single type (only '(' and ')'):

private fun checkBalancedBrackets(S: String): Int {
    val stack = mutableListOf<Char>()

    for (bracket in S) {
        if (bracket == '(') {
            stack.add(bracket)
        } else if (bracket == ')') {
            if (stack.isEmpty()) return 0
            stack.removeAt(stack.size - 1)  // ← NO CHECK NEEDED!
        }
    }

    return if (stack.isEmpty()) 1 else 0
}

Why no check needed?
- Stack ONLY contains '(' characters
- When we pop for ')', it's ALWAYS going to be '('
- No other bracket type to worry about!

Multiple types (this problem):

private fun checkBalancedParentheses(str: String) {
    val stack = Stack<Char>()

    for (ch in str) {
        when (ch) {
            '(', '{', '[' -> stack.push(ch)
            ')', '}', ']' -> {
                if (stack.isEmpty()) return

                val top = stack.pop()
                val isMatching = (ch == ')' && top == '(') ||
                                (ch == '}' && top == '{') ||
                                (ch == ']' && top == '[')

                if (!isMatching) return  // ← CHECK IS REQUIRED!
            }
        }
    }
}

Why check needed?
- Stack can have '(', '{', OR '[' on top
- Must verify the types match!
- Example: Top is '[' but we see ')' → NO MATCH!

================================================================================
SUMMARY
================================================================================

The check after pop() ensures:

1. TYPE MATCHING:
   ')' ONLY pairs with '('
   '}' ONLY pairs with '{'
   ']' ONLY pairs with '['

2. PROPER NESTING:
   Inner brackets must close before outer
   Example: "({[]})" ✓ vs "([)]" ✗

3. CORRECT PAIRING:
   Every opening needs matching closing
   Every closing needs matching opening

Without check: "([)]" would pass ✗ WRONG
With check: "([)]" fails correctly ✓ CORRECT

The pop() gives us what's on top.
The check tells us if it's the RIGHT match!

================================================================================
*/
