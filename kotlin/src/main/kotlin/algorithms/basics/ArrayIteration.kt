package algorithms.basics

/**
 * ARRAY ITERATION - Complete Guide
 *
 * This file demonstrates all array iteration patterns.
 * Covers the iteration table from Arrays-in-Kotlin.md
 */

// TIME COMPLEXITY: O(n) - iterate through n array elements
// SPACE COMPLEXITY: O(1) - only loop variables

fun main() {
    println("ARRAY ITERATION PATTERNS")
    val arr = intArrayOf(10, 20, 30, 40, 50)
    println("Array: ${arr.contentToString()}")  // Array: [10, 20, 30, 40, 50]
    println("Size: ${arr.size}")  // Size: 5
    println()

    // Pattern 1: Iterate values directly (NO INDEX)
    println("Pattern 1: for (value in arr) - BEST when no index needed")
    for (num in arr) {
        print("$num ")  // 10 20 30 40 50
    }
    println()
    println()

    // Pattern 2: Iterate with index (FULL ARRAY)
    println("Pattern 2: for (i in arr.indices) - BEST for full array with index")
    for (i in arr.indices) {
        println("arr[$i] = ${arr[i]}")  // arr[0] = 10
                                        // arr[1] = 20
                                        // arr[2] = 30
                                        // arr[3] = 40
                                        // arr[4] = 50
    }
    println()

    // Pattern 3: Offset iteration - Pairs
    println("Pattern 3: for (i in 0 until arr.size - 1) - BEST for pairs (arr[i] and arr[i+1])")
    for (i in 0 until arr.size - 1) {
        println("Pair: ${arr[i]} and ${arr[i + 1]}")  // Pair: 10 and 20
                                                       // Pair: 20 and 30
                                                       // Pair: 30 and 40
                                                       // Pair: 40 and 50
    }
    println()

    // Pattern 4: Offset iteration - Triplets
    println("Pattern 4: for (i in 0 until arr.size - 2) - BEST for triplets")
    for (i in 0 until arr.size - 2) {
        println("Triplet: ${arr[i]}, ${arr[i + 1]}, ${arr[i + 2]}")  // Triplet: 10, 20, 30
                                                                      // Triplet: 20, 30, 40
                                                                      // Triplet: 30, 40, 50
    }
    println()

    // Pattern 5: Both index and value together
    println("Pattern 5: for ((index, value) in arr.withIndex()) - When you need BOTH")
    for ((index, value) in arr.withIndex()) {
        println("Index $index: $value")  // Index 0: 10
                                          // Index 1: 20
                                          // Index 2: 30
                                          // Index 3: 40
                                          // Index 4: 50
    }
    println()

    // Pattern 6: Alternative (same as indices)
    println("Pattern 6: for (i in 0 until arr.size) - Same as indices (prefer arr.indices)")
    for (i in 0 until arr.size) {
        println("arr[$i] = ${arr[i]}")  // arr[0] = 10
                                        // arr[1] = 20
                                        // arr[2] = 30
                                        // arr[3] = 40
                                        // arr[4] = 50
    }
    println()

    // WRONG PATTERN - DO NOT USE
    println("WRONG: for (i in 0..arr.size) - CRASHES!")
    try {
        for (i in 0..arr.size) {
            println("arr[$i] = ${arr[i]}")  // arr[0] = 10
                                            // arr[1] = 20
                                            // arr[2] = 30
                                            // arr[3] = 40
                                            // arr[4] = 50
                                            // Then CRASHES!
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("ERROR: ArrayIndexOutOfBoundsException!")  // ERROR: ArrayIndexOutOfBoundsException!
        println("Tried to access arr[5] but size is 5 (valid indices: 0-4)")
    }
    println()

    // BONUS: Reverse iteration with downTo
    println("BONUS: Reverse iteration - for (i in arr.size - 1 downTo 0)")
    for (i in arr.size - 1 downTo 0) {
        print("arr[$i]=${arr[i]} ")  // arr[4]=50 arr[3]=40 arr[2]=30 arr[1]=20 arr[0]=10
    }
    println()
    println()

    // Alternative: reversed indices
    println("Alternative: for (i in arr.indices.reversed())")
    for (i in arr.indices.reversed()) {
        print("arr[$i]=${arr[i]} ")  // arr[4]=50 arr[3]=40 arr[2]=30 arr[1]=20 arr[0]=10
    }
    println()
    println()

    // SUMMARY
    println("------------------------------------------------------------")
    println("SUMMARY:")
    println("✓ for (value in arr)                - No index needed")
    println("✓ for (i in arr.indices)            - Full array with index")
    println("✓ for (i in 0 until arr.size - 1)   - Pairs (arr[i], arr[i+1])")
    println("✓ for (i in 0 until arr.size - 2)   - Triplets")
    println("✓ for ((i, v) in arr.withIndex())   - Both index and value")
    println("✓ for (i in arr.size-1 downTo 0)    - Reverse iteration")
    println("✗ for (i in 0..arr.size)            - CRASHES!")
    println("------------------------------------------------------------")
}
