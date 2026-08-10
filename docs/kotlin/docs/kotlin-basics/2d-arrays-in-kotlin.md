# 2D Arrays in Kotlin

## Overview
2D arrays (matrices) are commonly used in dynamic programming, graph problems, and image processing questions in coding interviews.

## Creating 2D Arrays

### IntArray 2D (Primitive)
```kotlin
// Create with fixed size
val matrix = Array(3) { IntArray(4) }  // 3x4 matrix of zeros

// Create with initial values
val matrix = Array(3) { IntArray(4) { 0 } }

// Create with row and column indices
val matrix = Array(3) { row ->
    IntArray(4) { col ->
        row * 4 + col
    }
}

// From existing arrays
val matrix = arrayOf(
    intArrayOf(1, 2, 3),
    intArrayOf(4, 5, 6),
    intArrayOf(7, 8, 9)
)
```

### Generic 2D Array
```kotlin
val matrix = Array(3) { Array(4) { 0 } }
val matrix = Array(3) { arrayOfNulls<Int>(4) }
```

## Accessing Elements

```kotlin
// Get element
val element = matrix[row][col]

// Set element
matrix[row][col] = value

// Safe access
val element = matrix.getOrNull(row)?.getOrNull(col)

// Dimensions
val rows = matrix.size
val cols = matrix[0].size  // Assuming non-empty and rectangular
```

## Common Traversal Patterns

### Row-wise Traversal
```kotlin
for (row in matrix.indices) {
    for (col in matrix[row].indices) {
        print("${matrix[row][col]} ")
    }
    println()
}

// Alternative
for (row in matrix) {
    for (element in row) {
        print("$element ")
    }
    println()
}

// Using forEach
matrix.forEach { row ->
    row.forEach { element ->
        print("$element ")
    }
    println()
}
```

### Column-wise Traversal
```kotlin
val rows = matrix.size
val cols = matrix[0].size

for (col in 0 until cols) {
    for (row in 0 until rows) {
        print("${matrix[row][col]} ")
    }
    println()
}
```

### Diagonal Traversal (Main Diagonal)
```kotlin
// Top-left to bottom-right
for (i in 0 until minOf(rows, cols)) {
    print("${matrix[i][i]} ")
}

// Top-right to bottom-left
for (i in 0 until minOf(rows, cols)) {
    print("${matrix[i][cols - 1 - i]} ")
}
```

### Spiral Traversal
```kotlin
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val result = mutableListOf<Int>()
    if (matrix.isEmpty()) return result

    var top = 0
    var bottom = matrix.size - 1
    var left = 0
    var right = matrix[0].size - 1

    while (top <= bottom && left <= right) {
        // Traverse right
        for (col in left..right) {
            result.add(matrix[top][col])
        }
        top++

        // Traverse down
        for (row in top..bottom) {
            result.add(matrix[row][right])
        }
        right--

        // Traverse left
        if (top <= bottom) {
            for (col in right downTo left) {
                result.add(matrix[bottom][col])
            }
            bottom--
        }

        // Traverse up
        if (left <= right) {
            for (row in bottom downTo top) {
                result.add(matrix[row][left])
            }
            left++
        }
    }

    return result
}
```

## Common Interview Problems

### Matrix Rotation (90° Clockwise)
```kotlin
fun rotate(matrix: Array<IntArray>) {
    val n = matrix.size

    // Transpose
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }

    // Reverse each row
    for (i in 0 until n) {
        matrix[i].reverse()
    }
}
```

### Set Matrix Zeros
```kotlin
fun setZeroes(matrix: Array<IntArray>) {
    val rows = matrix.size
    val cols = matrix[0].size
    var firstRowZero = false
    var firstColZero = false

    // Check if first row/col has zeros
    for (j in 0 until cols) {
        if (matrix[0][j] == 0) firstRowZero = true
    }
    for (i in 0 until rows) {
        if (matrix[i][0] == 0) firstColZero = true
    }

    // Use first row/col as markers
    for (i in 1 until rows) {
        for (j in 1 until cols) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0
                matrix[0][j] = 0
            }
        }
    }

    // Set zeros based on markers
    for (i in 1 until rows) {
        for (j in 1 until cols) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0
            }
        }
    }

    // Handle first row and column
    if (firstRowZero) {
        for (j in 0 until cols) matrix[0][j] = 0
    }
    if (firstColZero) {
        for (i in 0 until rows) matrix[i][0] = 0
    }
}
```

### Search in 2D Matrix
```kotlin
// Sorted matrix (each row sorted, first element of next row > last element of prev row)
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    if (matrix.isEmpty() || matrix[0].isEmpty()) return false

    val rows = matrix.size
    val cols = matrix[0].size

    // Binary search treating 2D array as 1D
    var left = 0
    var right = rows * cols - 1

    while (left <= right) {
        val mid = left + (right - left) / 2
        val midValue = matrix[mid / cols][mid % cols]

        when {
            midValue == target -> return true
            midValue < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return false
}
```

### Valid Sudoku
```kotlin
fun isValidSudoku(board: Array<CharArray>): Boolean {
    val rows = Array(9) { mutableSetOf<Char>() }
    val cols = Array(9) { mutableSetOf<Char>() }
    val boxes = Array(9) { mutableSetOf<Char>() }

    for (i in 0..8) {
        for (j in 0..8) {
            val num = board[i][j]
            if (num == '.') continue

            val boxIndex = (i / 3) * 3 + j / 3

            if (num in rows[i] || num in cols[j] || num in boxes[boxIndex]) {
                return false
            }

            rows[i].add(num)
            cols[j].add(num)
            boxes[boxIndex].add(num)
        }
    }

    return true
}
```

### Flood Fill
```kotlin
fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
    val originalColor = image[sr][sc]
    if (originalColor == newColor) return image

    fun dfs(row: Int, col: Int) {
        if (row < 0 || row >= image.size ||
            col < 0 || col >= image[0].size ||
            image[row][col] != originalColor) {
            return
        }

        image[row][col] = newColor

        dfs(row + 1, col)  // down
        dfs(row - 1, col)  // up
        dfs(row, col + 1)  // right
        dfs(row, col - 1)  // left
    }

    dfs(sr, sc)
    return image
}
```

### Number of Islands
```kotlin
fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty()) return 0

    val rows = grid.size
    val cols = grid[0].size
    var count = 0

    fun dfs(row: Int, col: Int) {
        if (row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == '0') {
            return
        }

        grid[row][col] = '0'  // Mark as visited

        dfs(row + 1, col)
        dfs(row - 1, col)
        dfs(row, col + 1)
        dfs(row, col - 1)
    }

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            if (grid[i][j] == '1') {
                count++
                dfs(i, j)
            }
        }
    }

    return count
}
```

## Direction Arrays

```kotlin
// 4 directions (up, down, left, right)
val dirs = arrayOf(
    intArrayOf(-1, 0),  // up
    intArrayOf(1, 0),   // down
    intArrayOf(0, -1),  // left
    intArrayOf(0, 1)    // right
)

for ((dr, dc) in dirs) {
    val newRow = row + dr
    val newCol = col + dc
    // Process neighbor
}

// 8 directions (including diagonals)
val dirs8 = arrayOf(
    intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(-1, 1),
    intArrayOf(0, -1),                      intArrayOf(0, 1),
    intArrayOf(1, -1),  intArrayOf(1, 0),  intArrayOf(1, 1)
)
```

## Matrix Operations

### Transpose
```kotlin
fun transpose(matrix: Array<IntArray>): Array<IntArray> {
    val rows = matrix.size
    val cols = matrix[0].size
    val result = Array(cols) { IntArray(rows) }

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            result[j][i] = matrix[i][j]
        }
    }

    return result
}
```

### Matrix Multiplication
```kotlin
fun multiply(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    val m = A.size
    val n = A[0].size
    val p = B[0].size
    val C = Array(m) { IntArray(p) }

    for (i in 0 until m) {
        for (j in 0 until p) {
            for (k in 0 until n) {
                C[i][j] += A[i][k] * B[k][j]
            }
        }
    }

    return C
}
```

## Performance Tips

1. **Access patterns matter** - row-wise access is faster (cache-friendly)
2. **Use IntArray for primitives** - avoids boxing overhead
3. **Consider using 1D array** for large matrices: `val matrix = IntArray(rows * cols)`
4. **In-place modifications** save space (O(1) vs O(n²))

## Interview Tips

1. **Always check bounds** before accessing elements
2. **Clarify if matrix is square** or rectangular
3. **Ask about matrix size** - might affect algorithm choice
4. **Consider edge cases**: empty matrix, single element, single row/column
5. **Direction arrays** simplify neighbor access
6. **DFS/BFS** are common for matrix traversal problems

## Common Pitfalls

```kotlin
// ❌ Wrong - all rows reference same array
val matrix = Array(3) { IntArray(4) }
matrix[0][0] = 1
// This is actually correct, but watch out for:

// ❌ Wrong - shared reference
val row = intArrayOf(1, 2, 3)
val matrix = Array(3) { row }  // All rows are the same object!
matrix[0][0] = 99
// matrix[1][0] is also 99!

// ✅ Correct - each row is independent
val matrix = Array(3) { intArrayOf(1, 2, 3) }
```
