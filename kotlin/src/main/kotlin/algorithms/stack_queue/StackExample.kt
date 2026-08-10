package algorithms.stacks

/**
 * STACK EXAMPLES - Using Native Stack Library
 *
 * Stack: Last-In-First-Out (LIFO) data structure
 * Think of it like a stack of plates - you add/remove from the top only
 *
 * Common Stack Methods:
 * - push(item): Add item to top
 * - pop(): Remove and return top item
 * - peek(): Look at top item without removing
 * - isEmpty(): Check if stack is empty
 * - size: Get number of items
 *
 * Time Complexity: All operations O(1)
 * Space Complexity: O(n) where n is number of elements
 */

import java.util.Stack

fun main() {
    println("=== Example 1: Basic Stack Operations ===")
    basicStackOperations()
    println()

    println("=== Example 2: Check for Empty Stack ===")
    checkEmptyStack()
    println()

    println("=== Example 3: Reverse a String Using Stack ===")
    reverseString("hello")
    println()

    println("=== Example 4: Check Balanced Parentheses ===")
    checkBalancedParentheses("({[]})")
    checkBalancedParentheses("({[})")
    println()

    println("=== Example 5: Stack with Integers ===")
    stackWithIntegers()
    println()

    println("=== Example 6: Peek vs Pop ===")
    peekVsPop()
    println()

    println("=== Example 7: Clear Stack ===")
    clearStack()
}

// EXAMPLE 1: Basic Stack Operations
private fun basicStackOperations() {
    val stack = Stack<String>()

    // push() - Add items to top
    stack.push("First")
    stack.push("Second")
    stack.push("Third")

    println("Stack after push: $stack")
    println("Size: ${stack.size}")

    // pop() - Remove and return top item
    val removed = stack.pop()
    println("Popped: $removed")
    println("Stack after pop: $stack")
}

// EXAMPLE 2: Check for Empty Stack
private fun checkEmptyStack() {
    val stack = Stack<Int>()

    println("Is stack empty? ${stack.isEmpty()}")
    println("Is stack empty (using empty())? ${stack.empty()}")

    stack.push(1)
    println("After push(1), is empty? ${stack.isEmpty()}")

    stack.pop()
    println("After pop(), is empty? ${stack.isEmpty()}")
}

// EXAMPLE 3: Reverse a String Using Stack
// Time: O(n), Space: O(n)
private fun reverseString(str: String) {
    println("Original string: $str")

    val stack = Stack<Char>()

    // Push all characters to stack
    for (ch in str) {
        stack.push(ch)
    }

    // Pop all characters (LIFO order reverses the string)
    var reversed = ""
    while (!stack.isEmpty()) {
        reversed += stack.pop()
    }

    println("Reversed string: $reversed")
}

// EXAMPLE 4: Check Balanced Parentheses
// Time: O(n), Space: O(n)
private fun checkBalancedParentheses(str: String) {
    println("Input: $str")

    val stack = Stack<Char>()

    for (ch in str) {
        when (ch) {
            '(', '{', '[' -> {
                // Opening bracket - push to stack
                stack.push(ch)
            }
            ')', '}', ']' -> {
                // Closing bracket - check if matches top
                if (stack.isEmpty()) {
                    println("Result: Not balanced (no matching opening)")
                    return
                }

                val top = stack.pop()
                val isMatching = (ch == ')' && top == '(') ||
                                (ch == '}' && top == '{') ||
                                (ch == ']' && top == '[')

                if (!isMatching) {
                    println("Result: Not balanced (mismatch)")
                    return
                }
            }
        }
    }

    if (stack.isEmpty()) {
        println("Result: Balanced ✓")
    } else {
        println("Result: Not balanced (unclosed brackets)")
    }
}

// EXAMPLE 5: Stack with Integers
private fun stackWithIntegers() {
    val stack = Stack<Int>()

    // Push multiple items
    for (i in 1..5) {
        stack.push(i * 10)
        println("Pushed: ${i * 10}, Stack: $stack")
    }

    println("Final stack: $stack")

    // Pop all items
    while (!stack.isEmpty()) {
        val item = stack.pop()
        println("Popped: $item, Remaining: $stack")
    }
}

// EXAMPLE 6: Peek vs Pop
private fun peekVsPop() {
    val stack = Stack<String>()
    stack.push("A")
    stack.push("B")
    stack.push("C")

    println("Stack: $stack")

    // peek() - Look at top WITHOUT removing
    val peeked = stack.peek()
    println("Peeked: $peeked")
    println("Stack after peek: $stack (unchanged)")

    // pop() - Remove and return top
    val popped = stack.pop()
    println("Popped: $popped")
    println("Stack after pop: $stack (changed)")
}

// EXAMPLE 7: Clear Stack
private fun clearStack() {
    val stack = Stack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)

    println("Stack before clear: $stack")

    // Method 1: clear()
    stack.clear()
    println("Stack after clear(): $stack")
    println("Is empty? ${stack.isEmpty()}")

    // Method 2: Pop until empty
    stack.push(10)
    stack.push(20)
    stack.push(30)
    println("Stack before manual clear: $stack")

    while (!stack.isEmpty()) {
        stack.pop()
    }
    println("Stack after manual clear: $stack")
}

/*
================================================================================
NATIVE STACK METHODS (java.util.Stack)
================================================================================

METHOD                  DESCRIPTION                         TIME
----------------------------------------------------------------------
push(item)             Add item to top of stack            O(1)
pop()                  Remove and return top item          O(1)
peek()                 Return top item without removing    O(1)
isEmpty()              Check if stack is empty             O(1)
empty()                Same as isEmpty() (legacy)          O(1)
size                   Get number of elements              O(1)
clear()                Remove all elements                 O(1)
search(item)           Find position from top (1-based)    O(n)

================================================================================
COMMON STACK OPERATIONS
================================================================================

Operation               Code Example
----------------------------------------------------------------------
Create stack           val stack = Stack<Int>()
Add item               stack.push(10)
Remove item            val item = stack.pop()
Look at top            val top = stack.peek()
Check empty            if (stack.isEmpty()) { ... }
Get size               val size = stack.size
Clear all              stack.clear()

================================================================================
STACK vs ARRAYDEQUE
================================================================================

Stack (java.util.Stack):
✓ Simple, intuitive methods (push, pop, peek)
✓ Legacy class, well-known
✗ Synchronized (slower in single-threaded apps)
✗ Extends Vector (not ideal design)

ArrayDeque (recommended for new code):
✓ Faster (not synchronized)
✓ More memory efficient
✗ Different method names (addFirst, removeFirst)

For interviews: Stack is fine and clearer!
For production: Consider ArrayDeque for better performance

================================================================================
COMMON USE CASES
================================================================================

1. Reverse a string/array
2. Check balanced parentheses/brackets
3. Undo/Redo functionality
4. Backtracking algorithms (DFS)
5. Expression evaluation (infix to postfix)
6. Function call stack (recursion)
7. Browser back button
8. Text editor undo

================================================================================
EXAMPLE: Stack Trace
================================================================================

When you see error messages like:
  at functionA()
  at functionB()
  at functionC()

This is a "stack trace" - shows the call stack (which functions called which)!

================================================================================
*/
