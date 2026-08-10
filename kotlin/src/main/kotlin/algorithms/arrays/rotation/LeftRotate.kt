package algorithms.arrays.rotation

/**
 * PROBLEM: Array Cyclic Left Rotation by Shifting Elements One by One
 *
 * Given an array A of N integers and an integer K, rotate the array to the LEFT by K positions.
 * This method shifts elements one position at a time, K times.
 *
 * INPUT:
 *   A = [3, 8, 9, 7, 6]
 *   K = 3
 *
 * OUTPUT:
 *   [7, 6, 3, 8, 9]
 *
 * EXPLANATION:
 *   Rotating left by 3 positions (one at a time):
 *   Step 1: [3, 8, 9, 7, 6] -> [8, 9, 7, 6, 3]
 *   Step 2: [8, 9, 7, 6, 3] -> [9, 7, 6, 3, 8]
 *   Step 3: [9, 7, 6, 3, 8] -> [7, 6, 3, 8, 9]
 *
 * Time Complexity: O(n*k) where k is number of rotations
 * Space Complexity: O(1)
 */

fun main() {
    // Test case 1: Normal case
    println("=== Test 1: Normal rotation ===")
    val ar1 = intArrayOf(3, 8, 9, 7, 6)
    println("Original: ${ar1.contentToString()}")
    println("Rotating left by 3 positions")
    leftRotate(ar1, 3)
    println("Result: ${ar1.contentToString()}")
    println("Expected: [7, 6, 3, 8, 9]")
    println()

    // Test case 2: Empty array
    println("=== Test 2: Empty array ===")
    val ar2 = intArrayOf()
    println("Original: ${ar2.contentToString()}")
    leftRotate(ar2, 3)
    println("Result: ${ar2.contentToString()}")
    println("Expected: []")
    println()

    // Test case 3: K = 0
    println("=== Test 3: K = 0 (no rotation) ===")
    val ar3 = intArrayOf(1, 2, 3)
    println("Original: ${ar3.contentToString()}")
    leftRotate(ar3, 0)
    println("Result: ${ar3.contentToString()}")
    println("Expected: [1, 2, 3]")
    println()

    // Test case 4: K > array size
    println("=== Test 4: K > array size ===")
    val ar4 = intArrayOf(1, 2, 3, 4, 5)
    println("Original: ${ar4.contentToString()}")
    println("Rotating left by 7 positions (7 % 5 = 2)")
    leftRotate(ar4, 7)
    println("Result: ${ar4.contentToString()}")
    println("Expected: [3, 4, 5, 1, 2]")
}

// TIME COMPLEXITY: O(n*k) - shift n elements k times, where k is reduced by modulus
// SPACE COMPLEXITY: O(1) - only use temp variable for first element
private fun leftRotate(ar: IntArray, k: Int) {
    // Edge cases
    if (ar.isEmpty()) return
    if (k == 0) return

    val rotations = k % ar.size  // Handle K > N
    if (rotations == 0) return

    var i = 0
    while (i < rotations) {
        // Take first element and move it to the end (left rotation)
        val temp = ar[0]

        // Shift all elements to the left
        for (j in 0 until ar.size - 1) {
            ar[j] = ar[j + 1]
        }

        // Place the first element at the end
        ar[ar.size - 1] = temp

        println("After rotation ${i + 1}: ${ar.contentToString()}")
        i++
    }
}
