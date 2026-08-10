package algorithms.basics

/**
 * NUMBER ITERATION - Complete Guide
 *
 * This file demonstrates all number iteration patterns.
 * Covers the patterns from Number-Iteration-in-Kotlin.md
 *
 * IMPORTANT: `until` is EXCLUSIVE, `..` is INCLUSIVE
 */

// TIME COMPLEXITY: O(n) - iterate from 1 to n
// SPACE COMPLEXITY: O(1) - only loop variable

fun main() {
    println("NUMBER ITERATION PATTERNS")
    val n = 5
    println("n = $n")
    println()

    // KEY QUESTION: Does the loop include n or exclude n?

    // Pattern 1: 0 until n (EXCLUDES n)
    println("Pattern 1: for (i in 0 until n) - EXCLUDES n")
    for (i in 0 until n) {
        print("$i ")  // 0 1 2 3 4
    }
    println()
    println("Range: 0 to ${n-1} (does NOT include $n)")
    println()

    // Pattern 2: 0..n-1 (EXCLUDES n, same as until)
    println("Pattern 2: for (i in 0..n-1) - EXCLUDES n (same as until)")
    for (i in 0..n-1) {
        print("$i ")  // 0 1 2 3 4
    }
    println()
    println("Range: 0 to ${n-1} (does NOT include $n)")
    println()

    // Pattern 3: 0..n (INCLUDES n)
    println("Pattern 3: for (i in 0..n) - INCLUDES n")
    for (i in 0..n) {
        print("$i ")  // 0 1 2 3 4 5
    }
    println()
    println("Range: 0 to $n (INCLUDES $n)")
    println()

    // Pattern 4: 1..n (INCLUDES n, starts from 1)
    println("Pattern 4: for (i in 1..n) - INCLUDES n, starts from 1")
    for (i in 1..n) {
        print("$i ")  // 1 2 3 4 5
    }
    println()
    println("Range: 1 to $n (INCLUDES $n)")
    println()

    // Pattern 5: 1 until n (EXCLUDES n, starts from 1)
    println("Pattern 5: for (i in 1 until n) - EXCLUDES n, starts from 1")
    for (i in 1 until n) {
        print("$i ")  // 1 2 3 4
    }
    println()
    println("Range: 1 to ${n-1} (does NOT include $n)")
    println()

    // Pattern 6: repeat(n) - No index needed
    println("Pattern 6: repeat(n) - When you don't need index")
    repeat(n) {
        print("* ")  // * * * * *
    }
    println()
    println("Repeats $n times")
    println()

    // Pattern 7: repeat(n) with index
    println("Pattern 7: repeat(n) with index - Index 0 to n-1")
    repeat(n) { i ->
        print("$i ")  // 0 1 2 3 4
    }
    println()
    println("Index goes from 0 to ${n-1}")
    println()

    // RELATIONSHIP TO ARRAYS
    println("RELATIONSHIP TO ARRAYS:")
    val arr = intArrayOf(10, 20, 30, 40, 50)
    println("Array: ${arr.contentToString()}")  // [10, 20, 30, 40, 50]
    println("Array size: ${arr.size}")  // 5
    println("Valid indices: 0, 1, 2, 3, 4")
    println()

    println("All these are EQUIVALENT for arrays:")
    println("for (i in 0 until arr.size)  // 0, 1, 2, 3, 4")
    println("for (i in 0 until 5)         // 0, 1, 2, 3, 4")
    println("for (i in arr.indices)       // 0, 1, 2, 3, 4")
    println()

    println("Why 'until' is SAFE for arrays:")
    println("✓ for (i in 0 until arr.size) accesses arr[0] to arr[4]")
    println("✗ for (i in 0..arr.size) tries arr[5] - CRASH!")
    println()

    // COMMON USE CASES
    println("COMMON USE CASES:")
    println()

    // Use case 1: Print numbers 1 to n
    println("Use case 1: Print numbers 1 to n")
    for (i in 1..n) {
        print("$i ")  // 1 2 3 4 5
    }
    println()
    println()

    // Use case 2: Calculate factorial
    println("Use case 2: Factorial of $n")
    var factorial = 1
    for (i in 1..n) {
        factorial *= i
    }
    println("$n! = $factorial")  // 5! = 120
    println()

    // Use case 3: Sum of 1 to n
    println("Use case 3: Sum of 1 to $n")
    var sum = 0
    for (i in 1..n) {
        sum += i
    }
    println("Sum = $sum")  // Sum = 15 (1+2+3+4+5)
    println()

    // Use case 4: Fill array with values
    println("Use case 4: Fill array with multiples of 2")
    val arr2 = IntArray(n)
    for (i in 0 until n) {
        arr2[i] = i * 2
    }
    println("Result: ${arr2.contentToString()}")  // [0, 2, 4, 6, 8]
    println()

    // REVERSE ITERATION
    println("REVERSE ITERATION:")
    println()

    // Countdown from n to 1
    println("Countdown from $n to 1:")
    for (i in n downTo 1) {
        print("$i ")  // 5 4 3 2 1
    }
    println()
    println()

    // Countdown from n-1 to 0
    println("Countdown from ${n-1} to 0:")
    for (i in n - 1 downTo 0) {
        print("$i ")  // 4 3 2 1 0
    }
    println()
    println()

    // STEP ITERATION
    println("STEP ITERATION:")
    println()

    // Even numbers (step by 2)
    println("Even numbers from 0 to 10 (step 2):")
    for (i in 0 until 10 step 2) {
        print("$i ")  // 0 2 4 6 8
    }
    println()
    println()

    // Multiples of 3
    println("Multiples of 3 from 0 to 12 (step 3):")
    for (i in 0 until 12 step 3) {
        print("$i ")  // 0 3 6 9
    }
    println()
    println()

    // WRONG PATTERN - DO NOT USE
    println("WRONG PATTERN - Using .. with array size:")
    println("val arr = intArrayOf(10, 20, 30, 40, 50)")
    println("for (i in 0..arr.size) { arr[i] }  // CRASHES!")
    println("Reason: 0..5 gives 0,1,2,3,4,5 but arr[5] doesn't exist")
    println()
    println("CORRECT:")
    println("for (i in 0 until arr.size) { arr[i] }  // Safe!")
    println("Reason: 0 until 5 gives 0,1,2,3,4 (all valid indices)")
    println()

    // SUMMARY TABLE
    println("------------------------------------------------------------")
    println("SUMMARY:")
    println("n = 5")
    println()
    println("for (i in 0 until n)    // 0, 1, 2, 3, 4    (EXCLUDES n)")
    println("for (i in 0..n-1)       // 0, 1, 2, 3, 4    (EXCLUDES n)")
    println("for (i in 0..n)         // 0, 1, 2, 3, 4, 5 (INCLUDES n)")
    println("for (i in 1..n)         // 1, 2, 3, 4, 5    (INCLUDES n)")
    println("for (i in 1 until n)    // 1, 2, 3, 4       (EXCLUDES n)")
    println()
    println("✓ 'until' is EXCLUSIVE (does NOT include end)")
    println("✓ '..' is INCLUSIVE (includes both start and end)")
    println("✓ Use '0 until n' for array-like iteration (safe)")
    println("✓ Use '1..n' for counting problems")
    println("✓ Use 'repeat(n)' when you don't need index")
    println("------------------------------------------------------------")
}
