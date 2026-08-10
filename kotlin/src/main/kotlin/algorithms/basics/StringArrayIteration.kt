package algorithms.basics

/**
 * STRING ARRAY ITERATION - Complete Guide
 *
 * This file demonstrates Array<String> iteration patterns.
 * Covers the iteration table from Strings-in-Kotlin.md
 */

// TIME COMPLEXITY: O(n*m) - iterate through n strings of average length m
// SPACE COMPLEXITY: O(1) - only loop variables

fun main() {
    println("STRING ARRAY (Array<String>) ITERATION PATTERNS")
    val words = arrayOf("apple", "banana", "cherry")
    println("Array: ${words.contentToString()}")  // Array: [apple, banana, cherry]
    println("Size: ${words.size}")  // Size: 3
    println()

    // Pattern 1: Iterate strings directly (NO INDEX)
    println("Pattern 1: for (word in words) - BEST when no index needed")
    for (word in words) {
        println(word)  // apple
                       // banana
                       // cherry
    }
    println()

    // Pattern 2: Iterate with index (FULL ARRAY)
    println("Pattern 2: for (i in words.indices) - BEST for full array with index")
    for (i in words.indices) {
        println("words[$i] = \"${words[i]}\"")  // words[0] = "apple"
                                                 // words[1] = "banana"
                                                 // words[2] = "cherry"
    }
    println()

    // Pattern 3: Offset iteration - Word pairs
    println("Pattern 3: for (i in 0 until words.size - 1) - BEST for pairs")
    for (i in 0 until words.size - 1) {
        println("Pair: ${words[i]} and ${words[i + 1]}")  // Pair: apple and banana
                                                           // Pair: banana and cherry
    }
    println()

    // Pattern 4: Both index and word together
    println("Pattern 4: for ((index, word) in words.withIndex()) - When you need BOTH")
    for ((index, word) in words.withIndex()) {
        println("Index $index: $word")  // Index 0: apple
                                         // Index 1: banana
                                         // Index 2: cherry
    }
    println()

    // Pattern 5: Alternative (same as indices)
    println("Pattern 5: for (i in 0 until words.size) - Same as indices (prefer words.indices)")
    for (i in 0 until words.size) {
        println("words[$i] = \"${words[i]}\"")  // words[0] = "apple"
                                                 // words[1] = "banana"
                                                 // words[2] = "cherry"
    }
    println()

    // WRONG PATTERN - DO NOT USE
    println("WRONG: for (i in 0..words.size) - CRASHES!")
    try {
        for (i in 0..words.size) {
            println("words[$i] = \"${words[i]}\"")  // words[0] = "apple"
                                                     // words[1] = "banana"
                                                     // words[2] = "cherry"
                                                     // Then CRASHES!
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("ERROR: ArrayIndexOutOfBoundsException!")  // ERROR: ArrayIndexOutOfBoundsException!
        println("Tried to access words[3] but size is 3 (valid indices: 0-2)")
    }
    println()

    // SUMMARY
    println("------------------------------------------------------------")
    println("SUMMARY:")
    println("✓ for (word in words)                - No index needed")
    println("✓ for (i in words.indices)           - Full array with index")
    println("✓ for (i in 0 until words.size - 1)  - Pairs (words[i], words[i+1])")
    println("✓ for ((i, w) in words.withIndex())  - Both index and word")
    println("✗ for (i in 0..words.size)           - CRASHES!")
    println("------------------------------------------------------------")
}
