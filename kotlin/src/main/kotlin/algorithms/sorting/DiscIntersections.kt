package algorithms.sorting


/**
 * PROBLEM: Number of Disc Intersections
 *
 * We draw N discs on a plane. The discs are numbered from 0 to N − 1.
 * An array A of N non-negative integers is given, where A[K] is the radius of the disc
 * with center at (K, 0).
 *
 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs
 * have at least one common point (assuming that the discs contain their borders).
 *
 * The goal is to count the number of (unordered) pairs of discs that intersect.
 *
 * INPUT: A = [1, 5, 2, 1, 4, 0]
 * OUTPUT: 11
 *
 * APPROACH:
 * - Each disc has a start point (index - radius) and end point (index + radius)
 * - Sort both arrays
 * - For each end point, count how many start points are <= to it
 * - This gives us all intersections
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case (original)
    println("=== Test 1: Normal case (original) ===")
    val result1 = numberOfDiscIntersections(intArrayOf(1, 5, 2, 1, 4, 0))
    println("Result: $result1")
    println("Expected: 11")
    println()

    // Test 1b: With helper function
    println("=== Test 1b: With helper function ===")
    val result1b = solutionWithHelper(intArrayOf(1, 5, 2, 1, 4, 0))
    println("Result: $result1b")
    println("Expected: 11")
    println()

    // Test 1c: Completely manual (no library functions)
    println("=== Test 1c: Completely manual (no library) ===")
    val result1c = solutionCompletelyManual(intArrayOf(1, 5, 2, 1, 4, 0))
    println("Result: $result1c")
    println("Expected: 11")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = numberOfDiscIntersections(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single disc
    println("=== Test 3: Edge case - single disc ===")
    val result3 = numberOfDiscIntersections(intArrayOf(5))
    println("Result: $result3")
    println("Expected: 0 (no pairs possible)")
    println()

    // Test 4: Edge case - no intersections (all radius 0)
    println("=== Test 4: Edge case - no intersections ===")
    val result4 = numberOfDiscIntersections(intArrayOf(0, 0, 0, 0))
    println("Result: $result4")
    println("Expected: 0")
    println()

    // Test 5: All discs intersect
    println("=== Test 5: All discs intersect ===")
    val result5 = numberOfDiscIntersections(intArrayOf(10, 10, 10))
    println("Result: $result5")
    println("Expected: 3 (all pairs intersect)")
    println()

    // Test 6: Two discs only
    println("=== Test 6: Two discs ===")
    val result6 = numberOfDiscIntersections(intArrayOf(1, 1))
    println("Result: $result6")
    println("Expected: 1 (single pair)")
}

// TIME COMPLEXITY: O(n log n) - sorting start and end points
// SPACE COMPLEXITY: O(n) - arrays for disc start and end points

private fun numberOfDiscIntersections(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return 0
    }

    // Calculate end points and start points
    val endPoints = A.mapIndexed { k, v -> k + v }.sorted()
    val startPoints = A.mapIndexed { k, v -> k - v }.sorted()

    var startPoint = 0
    println("Start pos ${startPoints}")
    println("End pos ${endPoints}")

    var counter = 0

    for ((index, endPoint) in endPoints.withIndex()) {
        println("Start finding for start point")
        println("For start_point $startPoint")
        println("Process index $index")

        while (startPoint < endPoints.size && endPoint >= startPoints[startPoint]) {
            println("start_point $startPoint")
            println("Each end_point $endPoint")

            val intersect = startPoint - index
            println("Intersect value calculated $intersect")

            counter += intersect
            println("Total intersect changed to $counter")

            startPoint += 1
        }

        if (counter > 10000000) {
            return -1
        }
    }

    return counter
}

// ALTERNATIVE: Using helper function to calculate and sort points
// Same complexity but cleaner code
private fun solutionWithHelper(A: IntArray): Int {
    if (A.isEmpty()) {
        return 0
    }

    // Use helper function twice
    val endPoints = calculateAndSortPoints(A, isEndPoint = true)
    val startPoints = calculateAndSortPoints(A, isEndPoint = false)

    var startPoint = 0
    var counter = 0

    for ((index, endPoint) in endPoints.withIndex()) {
        while (startPoint < endPoints.size && endPoint >= startPoints[startPoint]) {
            val intersect = startPoint - index
            counter += intersect
            startPoint += 1
        }

        if (counter > 10000000) {
            return -1
        }
    }

    return counter
}

// Helper function: Calculate points (end or start) and sort them
// isEndPoint = true  → calculate k + v (end points)
// isEndPoint = false → calculate k - v (start points)
private fun calculateAndSortPoints(A: IntArray, isEndPoint: Boolean): IntArray {
    // Step 1: Calculate points (manual mapIndexed)
    val points = IntArray(A.size)
    for (k in A.indices) {
        points[k] = if (isEndPoint) {
            k + A[k]  // End point formula
        } else {
            k - A[k]  // Start point formula
        }
    }

    // Step 2: Sort the points (manual sort)
    points.sort()

    return points
}

// ALTERNATIVE 2: Completely manual (NO library functions at all)
// Without using .sort(), .mapIndexed(), .withIndex()
private fun solutionCompletelyManual(A: IntArray): Int {
    if (A.isEmpty()) {
        return 0
    }

    // Calculate and sort end points manually
    val endPoints = calculateAndSortManual(A, isEndPoint = true)
    val startPoints = calculateAndSortManual(A, isEndPoint = false)

    var startPoint = 0
    var counter = 0

    // Manual loop (without withIndex())
    for (index in endPoints.indices) {
        val endPoint = endPoints[index]

        while (startPoint < endPoints.size && endPoint >= startPoints[startPoint]) {
            val intersect = startPoint - index
            counter += intersect
            startPoint += 1
        }

        if (counter > 10000000) {
            return -1
        }
    }

    return counter
}

// Helper: Calculate and sort manually (NO library sort)
private fun calculateAndSortManual(A: IntArray, isEndPoint: Boolean): IntArray {
    val n = A.size
    val points = IntArray(n)

    // Step 1: Calculate points (manual loop)
    for (k in 0 until n) {
        points[k] = if (isEndPoint) {
            k + A[k]
        } else {
            k - A[k]
        }
    }

    // Step 2: Manual bubble sort (simple, not optimized)
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (points[j] > points[j + 1]) {
                // Swap
                val temp = points[j]
                points[j] = points[j + 1]
                points[j + 1] = temp
            }
        }
    }

    return points
}

/*
================================================================================
COMPARISON: Three Approaches
================================================================================

APPROACH 1: numberOfDiscIntersections() - Using library functions
- Uses: mapIndexed() and sorted()
- Code: val endPoints = A.mapIndexed { k, v -> k + v }.sorted()
- Pros: Concise, readable, fast (optimized sort)
- Cons: Uses library functions

APPROACH 2: solutionWithHelper() - Custom helper with library sort
- Uses: Helper function calculateAndSortPoints()
- Code: Called twice (for endPoints and startPoints)
- Pros: Cleaner code, reusable helper, still fast
- Cons: Still uses .sort()

APPROACH 3: solutionCompletelyManual() - NO library functions
- Uses: Manual loops and bubble sort
- Code: calculateAndSortManual() with bubble sort
- Pros: No library dependencies, shows algorithm understanding
- Cons: Slower bubble sort O(n²) vs library sort O(n log n)

All three get the same result: 11 ✓

Recommendation: Use Approach 1 for production, Approach 3 for learning! ✓
================================================================================
*/
