package algorithms.basics

/**
 * STRING ITERATION - Complete Guide
 *
 * This file demonstrates all string iteration patterns.
 * Covers the iteration table from Strings-in-Kotlin.md
 */

// TIME COMPLEXITY: O(n) - iterate through n characters in string
// SPACE COMPLEXITY: O(1) - only loop variables

fun main() {
    println("STRING ITERATION PATTERNS")
    val text = "hello"
    println("String: \"$text\"")  // String: "hello"
    println("Length: ${text.length}")  // Length: 5
    println()

    // Pattern 1: Iterate characters directly (NO INDEX)
    println("Pattern 1: for (char in text) - BEST when no index needed")
    for (char in text) {
        print("$char ")  // h e l l o
    }
    println()
    println()

    // Pattern 2: Iterate with index (FULL STRING)
    println("Pattern 2: for (i in text.indices) - BEST for full string with index")
    for (i in text.indices) {
        println("text[$i] = '${text[i]}'")  // text[0] = 'h'
                                             // text[1] = 'e'
                                             // text[2] = 'l'
                                             // text[3] = 'l'
                                             // text[4] = 'o'
    }
    println()

    // Pattern 3: Offset iteration - Character pairs
    println("Pattern 3: for (i in 0 until text.length - 1) - BEST for pairs")
    for (i in 0 until text.length - 1) {
        println("Pair: '${text[i]}${text[i + 1]}'")  // Pair: 'he'
                                                      // Pair: 'el'
                                                      // Pair: 'll'
                                                      // Pair: 'lo'
    }
    println()

    // Pattern 4: Both index and character together
    println("Pattern 4: for ((index, char) in text.withIndex()) - When you need BOTH")
    for ((index, char) in text.withIndex()) {
        println("Index $index: '$char'")  // Index 0: 'h'
                                           // Index 1: 'e'
                                           // Index 2: 'l'
                                           // Index 3: 'l'
                                           // Index 4: 'o'
    }
    println()

    // Pattern 5: Alternative (same as indices)
    println("Pattern 5: for (i in 0 until text.length) - Same as indices (prefer text.indices)")
    for (i in 0 until text.length) {
        println("text[$i] = '${text[i]}'")  // text[0] = 'h'
                                             // text[1] = 'e'
                                             // text[2] = 'l'
                                             // text[3] = 'l'
                                             // text[4] = 'o'
    }
    println()

    // WRONG PATTERN - DO NOT USE
    println("WRONG: for (i in 0..text.length) - CRASHES!")
    try {
        for (i in 0..text.length) {
            println("text[$i] = '${text[i]}'")  // text[0] = 'h'
                                                 // text[1] = 'e'
                                                 // text[2] = 'l'
                                                 // text[3] = 'l'
                                                 // text[4] = 'o'
                                                 // Then CRASHES!
        }
    } catch (e: StringIndexOutOfBoundsException) {
        println("ERROR: StringIndexOutOfBoundsException!")  // ERROR: StringIndexOutOfBoundsException!
        println("Tried to access text[5] but length is 5 (valid indices: 0-4)")
    }
    println()

    // BONUS: Reverse iteration with downTo
    println("BONUS: Reverse iteration - for (i in text.length - 1 downTo 0)")
    for (i in text.length - 1 downTo 0) {
        print("${text[i]}")  // olleh
    }
    println()
    println()

    // Alternative: reversed indices
    println("Alternative: for (i in text.indices.reversed())")
    for (i in text.indices.reversed()) {
        print("${text[i]}")  // olleh
    }
    println()
    println()

    // SUMMARY
    println("------------------------------------------------------------")
    println("SUMMARY:")
    println("✓ for (char in text)                 - No index needed")
    println("✓ for (i in text.indices)            - Full string with index")
    println("✓ for (i in 0 until text.length - 1) - Pairs (text[i], text[i+1])")
    println("✓ for ((i, c) in text.withIndex())   - Both index and char")
    println("✓ for (i in text.length-1 downTo 0)  - Reverse iteration")
    println("✗ for (i in 0..text.length)          - CRASHES!")
    println("------------------------------------------------------------")
}
