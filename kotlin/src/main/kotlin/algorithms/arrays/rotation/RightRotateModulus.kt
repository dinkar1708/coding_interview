package algorithms.arrays.rotation

/**
 * PROBLEM: Array Cyclic Right Rotation using Modulus Operator
 *
 * Given an array A of N integers and an integer K, rotate the array to the RIGHT by K positions.
 * A cyclic rotation means shifting each element to a new position using modulus operator.
 *
 * INPUT:
 *   A = [3, 8, 9, 7, 6]
 *   K = 3
 *
 * OUTPUT:
 *   [9, 7, 6, 3, 8]
 *
 * EXPLANATION:
 *   Rotating right by 3 positions:
 *   - Element at index 0 (3) goes to index (0+3)%5 = 3
 *   - Element at index 1 (8) goes to index (1+3)%5 = 4
 *   - Element at index 2 (9) goes to index (2+3)%5 = 0
 *   - Element at index 3 (7) goes to index (3+3)%5 = 1
 *   - Element at index 4 (6) goes to index (4+3)%5 = 2
 *   Result: [9, 7, 6, 3, 8]
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val A = intArrayOf(3, 8, 9, 7, 6)
    val K = 3
    println("Original array: ${A.contentToString()}")
    println("Rotating right by $K positions using modulus operator")
    val result = rightRotateModulus(A, K)
    println("Result: ${result.contentToString()}")
    println("Expected: [9, 7, 6, 3, 8]")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val A2 = intArrayOf()
    val K2 = 3
    println("Original array: ${A2.contentToString()}")
    val result2 = rightRotateModulus(A2, K2)
    println("Result: ${result2.contentToString()}")
    println("Expected: []")
    println()

    // Test 3: K = 0 (no rotation)
    println("=== Test 3: K = 0 (no rotation) ===")
    val A3 = intArrayOf(1, 2, 3, 4, 5)
    val K3 = 0
    println("Original array: ${A3.contentToString()}")
    val result3 = rightRotateModulus(A3, K3)
    println("Result: ${result3.contentToString()}")
    println("Expected: [1, 2, 3, 4, 5]")
    println()

    // Test 4: K > N (K greater than array size)
    println("=== Test 4: K > N (K greater than array size) ===")
    val A4 = intArrayOf(1, 2, 3, 4, 5)
    val K4 = 8  // 8 % 5 = 3, same as rotating by 3
    println("Original array: ${A4.contentToString()}")
    println("Rotating right by $K4 positions (effective rotation: ${K4 % A4.size})")
    val result4 = rightRotateModulus(A4, K4)
    println("Result: ${result4.contentToString()}")
    println("Expected: [3, 4, 5, 1, 2]")
    println()

    // Test 5: Single element array
    println("=== Test 5: Single element array ===")
    val A5 = intArrayOf(42)
    val K5 = 5
    println("Original array: ${A5.contentToString()}")
    val result5 = rightRotateModulus(A5, K5)
    println("Result: ${result5.contentToString()}")
    println("Expected: [42]")
    println()

    // Test 6: K = N (full rotation, returns to original)
    println("=== Test 6: K = N (full rotation) ===")
    val A6 = intArrayOf(1, 2, 3, 4, 5)
    val K6 = 5
    println("Original array: ${A6.contentToString()}")
    val result6 = rightRotateModulus(A6, K6)
    println("Result: ${result6.contentToString()}")
    println("Expected: [1, 2, 3, 4, 5]")
}

// TIME COMPLEXITY: O(n) - iterate through array once to place elements in new positions
// SPACE COMPLEXITY: O(n) - create new array of size n to store rotated elements
private fun rightRotateModulus(A: IntArray, K: Int): IntArray {
    // Edge cases
    if (A.isEmpty()) return intArrayOf()
    if (K == 0) return A.copyOf()

    val lenN = A.size
    val rotations = K % lenN  // Handle K > N
    if (rotations == 0) return A.copyOf()

    val temp = IntArray(lenN)

    for (i in A.indices) {
        // find new index by modulus operator for right rotation
        val newIndex = (i + rotations) % lenN
        temp[newIndex] = A[i]
    }
    return temp
}
