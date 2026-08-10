package algorithms.arrays.rotation

/**
 * PROBLEM: Array Cyclic Left Rotation using Juggling Algorithm
 *
 * Given an array A of N integers and an integer K, rotate the array to the LEFT by K positions.
 * The Juggling Algorithm uses GCD (Greatest Common Divisor) to divide the array into sets
 * and rotate elements within each set efficiently.
 *
 * INPUT:
 *   A = [3, 8, 9, 7, 6]
 *   K = 3
 *
 * OUTPUT:
 *   [7, 6, 3, 8, 9]
 *
 * EXPLANATION:
 *   WITHOUT GCD (Naive approach):
 *   - Rotate one position at a time, repeat K times
 *   - Problem: Takes O(N*K) time - very slow for large K
 *   - Example: Rotate [3,8,9,7,6] left by 3
 *     Step 1: [8,9,7,6,3]  (1 rotation)
 *     Step 2: [9,7,6,3,8]  (2 rotations)
 *     Step 3: [7,6,3,8,9]  (3 rotations)
 *   - Need to shift all N elements K times = O(N*K)
 *
 *   WITH GCD (Juggling Algorithm) - The Smart Solution:
 *   - Key insight: Elements form independent cycles when jumping by K
 *   - GCD tells us how many cycles exist
 *   - Each element moves exactly ONCE = O(N) time
 *
 *   Why GCD works:
 *   - Number of cycles = GCD(N, K)
 *   - Each cycle length = N / GCD(N, K)
 *   - Total elements = cycles * cycle_length = GCD(N,K) * N/GCD(N,K) = N ✓
 *
 *   Example: N=5, K=3 -> GCD(5,3)=1
 *   - Only 1 cycle needed (all elements connected)
 *   - Cycle: 0->3->1->4->2->0 (length 5)
 *   - Save arr[0]=3, move 7->0, 8->3, 6->1, 9->4, 3->2
 *   - Result: [7,6,3,8,9] in ONE pass!
 *
 *   Example: N=6, K=2 -> GCD(6,2)=2
 *   - Need 2 separate cycles
 *   - Cycle 1: 0->2->4->0 (even indices)
 *   - Cycle 2: 1->3->5->1 (odd indices)
 *   - Each cycle processed independently
 *
 * Time Complexity: O(n) - each element moved exactly once
 * Space Complexity: O(1) - only temp variable used
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val arr = intArrayOf(3, 8, 9, 7, 6)
    val k = 3
    println("Original array: ${arr.contentToString()}")
    println("Rotating left by $k positions")
    leftRotateUsingJugglingAlgorithm(arr, k, arr.size)
    println("Result: ${arr.contentToString()}")
    println("Expected: [7, 6, 3, 8, 9]")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val arr2 = intArrayOf()
    val k2 = 3
    println("Original array: ${arr2.contentToString()}")
    leftRotateUsingJugglingAlgorithm(arr2, k2, arr2.size)
    println("Result: ${arr2.contentToString()}")
    println("Expected: []")
    println()

    // Test 3: K = 0 (no rotation)
    println("=== Test 3: K = 0 (no rotation) ===")
    val arr3 = intArrayOf(1, 2, 3, 4, 5)
    val k3 = 0
    println("Original array: ${arr3.contentToString()}")
    leftRotateUsingJugglingAlgorithm(arr3, k3, arr3.size)
    println("Result: ${arr3.contentToString()}")
    println("Expected: [1, 2, 3, 4, 5]")
    println()

    // Test 4: K > N (K greater than array size)
    println("=== Test 4: K > N (K greater than array size) ===")
    val arr4 = intArrayOf(1, 2, 3, 4, 5)
    val k4 = 8  // 8 % 5 = 3
    println("Original array: ${arr4.contentToString()}")
    println("Rotating left by $k4 positions (effective rotation: ${k4 % arr4.size})")
    leftRotateUsingJugglingAlgorithm(arr4, k4, arr4.size)
    println("Result: ${arr4.contentToString()}")
    println("Expected: [4, 5, 1, 2, 3]")
    println()

    // Test 5: Single element array
    println("=== Test 5: Single element array ===")
    val arr5 = intArrayOf(42)
    val k5 = 5
    println("Original array: ${arr5.contentToString()}")
    leftRotateUsingJugglingAlgorithm(arr5, k5, arr5.size)
    println("Result: ${arr5.contentToString()}")
    println("Expected: [42]")
    println()

    // Test 6: GCD > 1 case (multiple cycles)
    println("=== Test 6: GCD > 1 case (multiple cycles) ===")
    val arr6 = intArrayOf(1, 2, 3, 4, 5, 6)
    val k6 = 2  // GCD(6, 2) = 2
    println("Original array: ${arr6.contentToString()}")
    println("Rotating left by $k6 positions (GCD = 2, multiple cycles)")
    leftRotateUsingJugglingAlgorithm(arr6, k6, arr6.size)
    println("Result: ${arr6.contentToString()}")
    println("Expected: [3, 4, 5, 6, 1, 2]")
}

private fun gcdForJuggling(a: Int, b: Int): Int {
    // Greatest Common Divisor using Euclidean algorithm
    // Example: gcd(5, 3) = gcd(3, 2) = gcd(2, 1) = gcd(1, 0) = 1
    return if (b == 0) {
        a
    } else {
        gcdForJuggling(b, a % b)
    }
}

// TIME COMPLEXITY: O(n) - each element moved exactly once using GCD-based cycles
// SPACE COMPLEXITY: O(1) - only temp variable used to store cycle start element
private fun leftRotateUsingJugglingAlgorithm(arr: IntArray, d: Int, n: Int) {
    // Edge cases
    if (arr.isEmpty()) return
    if (d == 0) return
    if (n <= 0) return

    // Handle rotations greater than array size
    val rotations = d % n
    if (rotations == 0) return

    // GCD gives us number of independent cycles
    // Formula: Number of cycles = GCD(N, K)
    //          Cycle length = N / GCD(N, K)
    val gcdValue = gcdForJuggling(rotations, n)
    println("gcd: $gcdValue")

    // Process each cycle separately
    for (i in 0 until gcdValue) {
        println("steps...")

        // Save starting element of this cycle
        val temp = arr[i]
        var j = i

        // Move elements within this cycle
        while (true) {
            println(arr.contentToString())

            // Calculate next position in cycle (jump by rotations)
            var k = j + rotations
            if (k >= n) {
                k = k - n  // Wrap around using modulo
            }

            // If we're back to start, cycle is complete
            if (k == i) {
                break
            }

            println("  $j <-> $k")
            arr[j] = arr[k]  // Move element from k to j
            j = k            // Move to next position in cycle
        }

        // Place saved element in final position
        arr[j] = temp
    }
}

