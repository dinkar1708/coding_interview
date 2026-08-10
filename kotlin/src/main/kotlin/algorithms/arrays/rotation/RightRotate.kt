package algorithms.arrays.rotation

/**
 * PROBLEM: Array Cyclic Right Rotation by Shifting Elements One by One
 *
 * Given an array A of N integers and an integer K, rotate the array to the RIGHT by K positions.
 * This method shifts elements one position at a time, K times.
 *
 * INPUT:
 *   A = [3, 8, 9, 7, 6]
 *   K = 3
 *
 * OUTPUT:
 *   [9, 7, 6, 3, 8]
 *
 * EXPLANATION:
 *   Rotating right by 3 positions (one at a time):
 *   Step 1: [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 *   Step 2: [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 *   Step 3: [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 *
 * Time Complexity: O(n*k) where k is number of rotations
 * Space Complexity: O(1)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val ar = intArrayOf(3, 8, 9, 7, 6)
    val k = 3
    println("Original array: ${ar.contentToString()}")
    println("Rotating right by $k positions")
    rightRotate(ar, k)
    println("Result: ${ar.contentToString()}")
    println("Expected: [9, 7, 6, 3, 8]")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val ar2 = intArrayOf()
    val k2 = 3
    println("Original array: ${ar2.contentToString()}")
    rightRotate(ar2, k2)
    println("Result: ${ar2.contentToString()}")
    println("Expected: []")
    println()

    // Test 3: K = 0 (no rotation)
    println("=== Test 3: K = 0 (no rotation) ===")
    val ar3 = intArrayOf(1, 2, 3, 4, 5)
    val k3 = 0
    println("Original array: ${ar3.contentToString()}")
    rightRotate(ar3, k3)
    println("Result: ${ar3.contentToString()}")
    println("Expected: [1, 2, 3, 4, 5]")
    println()

    // Test 4: K > N (K greater than array size)
    println("=== Test 4: K > N (K greater than array size) ===")
    val ar4 = intArrayOf(1, 2, 3, 4, 5)
    val k4 = 8  // 8 % 5 = 3, same as rotating by 3
    println("Original array: ${ar4.contentToString()}")
    println("Rotating right by $k4 positions (effective rotation: ${k4 % ar4.size})")
    rightRotate(ar4, k4)
    println("Result: ${ar4.contentToString()}")
    println("Expected: [3, 4, 5, 1, 2]")
    println()

    // Test 5: Single element array
    println("=== Test 5: Single element array ===")
    val ar5 = intArrayOf(42)
    val k5 = 5
    println("Original array: ${ar5.contentToString()}")
    rightRotate(ar5, k5)
    println("Result: ${ar5.contentToString()}")
    println("Expected: [42]")
    println()

    // Test 6: Two element array
    println("=== Test 6: Two element array ===")
    val ar6 = intArrayOf(1, 2)
    val k6 = 1
    println("Original array: ${ar6.contentToString()}")
    rightRotate(ar6, k6)
    println("Result: ${ar6.contentToString()}")
    println("Expected: [2, 1]")
}

// TIME COMPLEXITY: O(n*k) - shift n elements k times, where k is reduced by modulus
// SPACE COMPLEXITY: O(1) - only use temp variable for last element
private fun rightRotate(ar: IntArray, k: Int) {
    // Edge cases
    if (ar.isEmpty()) return
    if (k == 0) return

    val rotations = k % ar.size  // Handle K > N
    if (rotations == 0) return

    var i = 0
    while (i < rotations) {
        // Take last element and move it to the beginning (right rotation)
        val temp = ar[ar.size - 1]

        // Shift all elements to the right
        for (j in ar.size - 1 downTo 1) {
            ar[j] = ar[j - 1]
        }

        // Place the last element at the beginning
        ar[0] = temp

        println("After rotation ${i + 1}: ${ar.contentToString()}")
        i++
    }
}
