package algorithms.basics

/**
 * 2D ARRAY ITERATION - Complete Guide
 *
 * This file demonstrates all 2D array iteration patterns.
 * Covers the patterns from 2D-Arrays-in-Kotlin.md
 */

// TIME COMPLEXITY: O(n*m) - iterate through n rows and m columns
// SPACE COMPLEXITY: O(1) - only loop variables

fun main() {
    println("2D ARRAY ITERATION PATTERNS")

    // Create a 2D array (matrix)
    val matrix = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12)
    )

    println("Matrix (3 rows x 4 columns):")
    for (row in matrix) {
        println(row.contentToString())  // [1, 2, 3, 4]
                                        // [5, 6, 7, 8]
                                        // [9, 10, 11, 12]
    }
    println()

    // Dimensions
    println("Dimensions:")
    println("Rows: ${matrix.size}")           // Rows: 3
    println("Columns: ${matrix[0].size}")     // Columns: 4
    println()

    // Pattern 1: Iterate with indices (BEST for full matrix with positions)
    println("Pattern 1: for (row in matrix.indices) - BEST for index access")
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            print("${matrix[row][col]} ")  // 1 2 3 4 5 6 7 8 9 10 11 12
        }
    }
    println()
    println()

    // Pattern 2: Iterate values directly (NO INDEX)
    println("Pattern 2: for (row in matrix) - BEST when no index needed")
    for (row in matrix) {
        for (value in row) {
            print("$value ")  // 1 2 3 4 5 6 7 8 9 10 11 12
        }
    }
    println()
    println()

    // Pattern 3: Iterate with both index and value
    println("Pattern 3: withIndex() - When you need BOTH position and value")
    for ((rowIndex, row) in matrix.withIndex()) {
        for ((colIndex, value) in row.withIndex()) {
            println("matrix[$rowIndex][$colIndex] = $value")  // matrix[0][0] = 1
                                                               // matrix[0][1] = 2
                                                               // matrix[0][2] = 3
                                                               // matrix[0][3] = 4
                                                               // matrix[1][0] = 5
                                                               // ... (all elements)
        }
    }
    println()

    // Pattern 4: Using size (alternative to indices)
    println("Pattern 4: 0 until size - Alternative to indices")
    val rows = matrix.size
    val cols = matrix[0].size
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            print("${matrix[i][j]} ")  // 1 2 3 4 5 6 7 8 9 10 11 12
        }
    }
    println()
    println()

    // PRINTING PATTERNS
    println("PRINTING PATTERNS:")
    println()

    // Print row by row
    println("Row by row (each row on new line):")
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            print("${matrix[row][col]} ")
        }
        println()  // 1 2 3 4
                   // 5 6 7 8
                   // 9 10 11 12
    }
    println()

    // Print column by column
    println("Column by column:")
    for (col in matrix[0].indices) {
        for (row in matrix.indices) {
            print("${matrix[row][col]} ")
        }
        println()  // 1 5 9
                   // 2 6 10
                   // 3 7 11
                   // 4 8 12
    }
    println()

    // Print diagonal (for visualization - partial diagonal)
    println("Main diagonal (partial):")
    val n = minOf(matrix.size, matrix[0].size)
    for (i in 0 until n) {
        print("${matrix[i][i]} ")  // 1 6 11
    }
    println()
    println()

    // COMMON OPERATIONS
    println("COMMON OPERATIONS:")
    println()

    // Sum all elements
    println("Sum of all elements:")
    var sum = 0
    for (row in matrix) {
        for (value in row) {
            sum += value
        }
    }
    println("Sum = $sum")  // Sum = 78
    println()

    // Find maximum
    println("Find maximum element:")
    var max = matrix[0][0]
    for (row in matrix) {
        for (value in row) {
            if (value > max) {
                max = value
            }
        }
    }
    println("Max = $max")  // Max = 12
    println()

    // Count even numbers
    println("Count even numbers:")
    var evenCount = 0
    for (row in matrix) {
        for (value in row) {
            if (value % 2 == 0) {
                evenCount++
            }
        }
    }
    println("Even count = $evenCount")  // Even count = 6
    println()

    // Search for element
    println("Search for element 7:")
    var found = false
    var foundRow = -1
    var foundCol = -1
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            if (matrix[row][col] == 7) {
                found = true
                foundRow = row
                foundCol = col
            }
        }
    }
    if (found) {
        println("Found at position [$foundRow][$foundCol]")  // Found at position [1][2]
    } else {
        println("Not found")
    }
    println()

    // CREATING 2D ARRAYS
    println("CREATING 2D ARRAYS:")
    println()

    // Method 1: Using arrayOf
    println("Method 1: Using arrayOf with intArrayOf")
    val arr1 = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6)
    )
    println(arr1.size)           // 2
    println(arr1[0].size)        // 3
    println()

    // Method 2: Using Array constructor (all zeros)
    println("Method 2: Array constructor (all zeros)")
    val arr2 = Array(2) { IntArray(3) }
    for (row in arr2) {
        println(row.contentToString())  // [0, 0, 0]
                                        // [0, 0, 0]
    }
    println()

    // Method 3: With initialization
    println("Method 3: With initialization")
    val arr3 = Array(2) { row ->
        IntArray(3) { col ->
            row * 3 + col + 1
        }
    }
    for (row in arr3) {
        println(row.contentToString())  // [1, 2, 3]
                                        // [4, 5, 6]
    }
    println()

    // SUMMARY
    println("------------------------------------------------------------")
    println("SUMMARY:")
    println("✓ 2D array type: Array<IntArray>")
    println("✓ Access: matrix[row][col]")
    println("✓ Rows: matrix.size")
    println("✓ Columns: matrix[0].size")
    println("✓ for (row in matrix.indices) - BEST for index access")
    println("✓ for (row in matrix) - BEST when no index needed")
    println("✓ for ((i, row) in matrix.withIndex()) - Both index and value")
    println("✓ Print row: println(matrix[0].contentToString())")
    println("------------------------------------------------------------")
}
