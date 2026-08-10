package algorithms.stack_queue

/**
 * BALANCED BRACKETS - Visual Step-by-Step Explanation
 *
 * This file demonstrates WHY we need to check if brackets match after popping
 * Shows visual stack states for both PASSED and FAILED cases
 */

import java.util.Stack

fun main() {
    println("================================================================================")
    println("VISUAL EXPLANATION: Balanced Brackets with Multiple Types () {} []")
    println("================================================================================")
    println()

    // PASSED CASES
    println("=" .repeat(80))
    println("PASSED CASES - Valid Bracket Sequences")
    println("=" .repeat(80))
    println()

    checkBalancedParenthesesVisual("()")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("{[]}")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("({[]})")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("(){}[]")
    println("\n" + "-".repeat(80) + "\n")

    // FAILED CASES
    println("=" .repeat(80))
    println("FAILED CASES - Invalid Bracket Sequences")
    println("=" .repeat(80))
    println()

    checkBalancedParenthesesVisual("([)]")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("({)}")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("(()")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("())")
    println("\n" + "-".repeat(80) + "\n")

    checkBalancedParenthesesVisual("}(")
    println("\n" + "-".repeat(80) + "\n")
}

private fun checkBalancedParenthesesVisual(str: String) {
    println("INPUT: \"$str\"")
    println()

    val stack = Stack<Char>()
    var step = 0

    for ((index, ch) in str.withIndex()) {
        step++
        println("STEP $step: Processing character '$ch' at position $index")

        when (ch) {
            '(', '{', '[' -> {
                // Opening bracket - push to stack
                stack.push(ch)
                println("  → Action: PUSH '$ch' (opening bracket)")
                printStack(stack)
            }
            ')', '}', ']' -> {
                // Closing bracket - check if matches top
                println("  → Action: Need to MATCH closing bracket '$ch'")

                if (stack.isEmpty()) {
                    printStack(stack)
                    println()
                    println("  ❌ ERROR: Stack is EMPTY! No opening bracket to match '$ch'")
                    println()
                    println("RESULT: ❌ NOT BALANCED (no matching opening)")
                    return
                }

                val top = stack.peek()
                println("  → Stack top: '$top'")

                val isMatching = (ch == ')' && top == '(') ||
                                (ch == '}' && top == '{') ||
                                (ch == ']' && top == '[')

                if (!isMatching) {
                    println("  → Checking: Does '$top' match '$ch'?")
                    println("      • ')' matches '(' ? ${if (ch == ')' && top == '(') "YES" else "NO"}")
                    println("      • '}' matches '{' ? ${if (ch == '}' && top == '{') "YES" else "NO"}")
                    println("      • ']' matches '[' ? ${if (ch == ']' && top == '[') "YES" else "NO"}")
                    println()
                    println("  ❌ ERROR: '$top' does NOT match '$ch'!")
                    printStack(stack)
                    println()
                    println("RESULT: ❌ NOT BALANCED (mismatch)")
                    return
                }

                // Matches! Pop it
                stack.pop()
                println("  → Match found! '$top' matches '$ch' ✓")
                println("  → Action: POP '$top' from stack")
                printStack(stack)
            }
        }
        println()
    }

    // Check if stack is empty at the end
    println("=" .repeat(60))
    println("FINAL CHECK:")
    printStack(stack)

    if (stack.isEmpty()) {
        println()
        println("RESULT: ✅ BALANCED - All brackets matched correctly!")
    } else {
        println()
        println("  ❌ ERROR: Stack still has ${stack.size} unclosed bracket(s)!")
        println("RESULT: ❌ NOT BALANCED (unclosed brackets)")
    }
}

private fun printStack(stack: Stack<Char>) {
    if (stack.isEmpty()) {
        println("  → Stack: [ EMPTY ]")
    } else {
        print("  → Stack: [ ")
        for (i in 0 until stack.size) {
            print("'${stack[i]}'")
            if (i < stack.size - 1) print(", ")
        }
        print(" ]  (top → '${stack.peek()}')")
        println()
    }
}

/*
================================================================================
KEY INSIGHTS - Why Checking After Pop is CRITICAL
================================================================================

SCENARIO 1: Input "({[]})" - BALANCED ✓

Visual Flow:
Position: 0   1   2   3   4   5
Input:    (   {   [   ]   }   )

Step 1: '(' → Push    Stack: [ '(' ]
Step 2: '{' → Push    Stack: [ '(', '{' ]
Step 3: '[' → Push    Stack: [ '(', '{', '[' ]
Step 4: ']' → Pop '[', Check: '[' matches ']' ? YES ✓
              Stack: [ '(', '{' ]
Step 5: '}' → Pop '{', Check: '{' matches '}' ? YES ✓
              Stack: [ '(' ]
Step 6: ')' → Pop '(', Check: '(' matches ')' ? YES ✓
              Stack: [ EMPTY ]

Result: ✅ BALANCED

---

SCENARIO 2: Input "([)]" - NOT BALANCED ✗

Visual Flow:
Position: 0   1   2   3
Input:    (   [   )   ]

Step 1: '(' → Push    Stack: [ '(' ]
Step 2: '[' → Push    Stack: [ '(', '[' ]
Step 3: ')' → Pop '[', Check: '[' matches ')' ? NO ✗

              WHY IT FAILS:
              - Stack top is '['
              - We see closing ')'
              - '[' and ')' are DIFFERENT types!
              - ')' should only match '('
              - But top is '[', not '('

              ❌ STOP! Not balanced!

WITHOUT the check, we would:
  - Pop '[' (wrong!)
  - Continue and pop '(' for ']' (wrong!)
  - Return "balanced" ✗ INCORRECT!

WITH the check:
  - Pop '[', see it doesn't match ')'
  - Immediately return "not balanced" ✓ CORRECT!

---

SCENARIO 3: Input "({)}" - NOT BALANCED ✗

Visual Flow:
Position: 0   1   2   3
Input:    (   {   )   }

Step 1: '(' → Push    Stack: [ '(' ]
Step 2: '{' → Push    Stack: [ '(', '{' ]
Step 3: ')' → Pop '{', Check: '{' matches ')' ? NO ✗

              Stack has: [ '(', '{' ]
                                 ↑ top
              We need to match: ')'

              But '{' ≠ '(' so NO MATCH!

              ❌ STOP! Not balanced!

---

SCENARIO 4: Input "(){}[]" - BALANCED ✓

Visual Flow:
Position: 0   1   2   3   4   5
Input:    (   )   {   }   [   ]

Step 1: '(' → Push    Stack: [ '(' ]
Step 2: ')' → Pop '(', Check: '(' matches ')' ? YES ✓
              Stack: [ EMPTY ]
Step 3: '{' → Push    Stack: [ '{' ]
Step 4: '}' → Pop '{', Check: '{' matches '}' ? YES ✓
              Stack: [ EMPTY ]
Step 5: '[' → Push    Stack: [ '[' ]
Step 6: ']' → Pop '[', Check: '[' matches ']' ? YES ✓
              Stack: [ EMPTY ]

Result: ✅ BALANCED

---

SCENARIO 5: Input "(()" - NOT BALANCED ✗

Visual Flow:
Position: 0   1   2
Input:    (   (   )

Step 1: '(' → Push    Stack: [ '(' ]
Step 2: '(' → Push    Stack: [ '(', '(' ]
Step 3: ')' → Pop '(', Check: '(' matches ')' ? YES ✓
              Stack: [ '(' ]

Final: Stack NOT empty!
       Still has 1 unclosed '('

       ❌ STOP! Not balanced!

---

SCENARIO 6: Input "())" - NOT BALANCED ✗

Visual Flow:
Position: 0   1   2
Input:    (   )   )

Step 1: '(' → Push    Stack: [ '(' ]
Step 2: ')' → Pop '(', Check: '(' matches ')' ? YES ✓
              Stack: [ EMPTY ]
Step 3: ')' → Stack is EMPTY!
              Cannot pop! No matching opening!

              ❌ STOP! Not balanced!

================================================================================
SUMMARY: Why Check is Needed
================================================================================

The check after popping ensures:

1. TYPE MATCHING:
   - ')' ONLY pairs with '('
   - '}' ONLY pairs with '{'
   - ']' ONLY pairs with '['

   Without check: "([)]" would pass ✗ WRONG
   With check: "([)]" fails correctly ✓

2. PROPER NESTING:
   - Inner brackets must close before outer brackets
   - Example: "({[]})" ✓ vs "({)]}" ✗

3. COMPLETE PAIRING:
   - Every opening needs a matching closing
   - Every closing needs a matching opening

The pop() gives us WHAT is on top.
The check tells us IF it's the RIGHT match!

Without checking = accepting ANY bracket pair
With checking = accepting ONLY correct pairs

================================================================================
*/
