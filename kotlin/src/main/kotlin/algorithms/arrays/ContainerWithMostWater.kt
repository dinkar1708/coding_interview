package algorithms.arrays

/**
 * PROBLEM: Container With Most Water
 *
 * Given an array arr[] of non-negative integers, where each element arr[i] represents
 * the height of vertical lines, find the maximum amount of water that can be contained
 * between any two lines, together with the x-axis.
 *
 * VISUALIZATION:
 * Array: [1, 5, 4, 3]
 *
 *      5 |
 *      4 |  |
 *      3 |  |  |  |
 *      2 |  |  |  |
 *      1 |  |  |  |
 *        +----------
 *        0  1  2  3  (indices)
 *
 * Pick two lines (e.g., index 1 and 3):
 * - Width = 3 - 1 = 2
 * - Height = min(5, 3) = 3 (water level limited by shorter line)
 * - Area = 2 × 3 = 6
 *
 * FORMULA:
 * Area = width × height
 * where:
 *   width = j - i (distance between two lines)
 *   height = min(arr[i], arr[j]) (water can't go higher than shorter line)
 *
 * EXAMPLES:
 *
 * Example 1:
 * Input: arr = [1, 5, 4, 3]
 * Output: 6
 * Explanation:
 *   Lines at index 1 (height=5) and index 3 (height=3)
 *   Width = 3 - 1 = 2
 *   Height = min(5, 3) = 3
 *   Area = 2 × 3 = 6
 *
 * Example 2:
 * Input: arr = [3, 1, 2, 4, 5]
 * Output: 12
 * Explanation:
 *   Lines at index 0 (height=3) and index 4 (height=5)
 *   Width = 4 - 0 = 4
 *   Height = min(3, 5) = 3
 *   Area = 4 × 3 = 12
 *
 * Example 3:
 * Input: arr = [2, 1, 8, 6, 4, 6, 5, 5]
 * Output: 25
 * Explanation:
 *   Lines at index 2 (height=8) and index 7 (height=5)
 *   Width = 7 - 2 = 5
 *   Height = min(8, 5) = 5
 *   Area = 5 × 5 = 25
 *
 * CONSTRAINTS:
 * - 2 ≤ arr.length ≤ 10^5
 * - 0 ≤ arr[i] ≤ 10^4
 *
 * ================================================================================
 * APPROACH 1: BRUTE FORCE - O(n²)
 * ================================================================================
 *
 * IDEA:
 * Check ALL possible pairs of lines and calculate area for each.
 * Keep track of maximum area found.
 *
 * ALGORITHM:
 * 1. Initialize maxArea = 0
 * 2. For each line i from 0 to n-2:
 *      For each line j from i+1 to n-1:
 *        a. Calculate width = j - i
 *        b. Calculate height = min(arr[i], arr[j])
 *        c. Calculate area = width × height
 *        d. Update maxArea = max(maxArea, area)
 * 3. Return maxArea
 *
 * PSEUDOCODE:
 * ```
 * var maxArea = 0
 * for (i in 0 until arr.size) {
 *     for (j in i+1 until arr.size) {
 *         val width = j - i
 *         val height = min(arr[i], arr[j])
 *         val area = width * height
 *         maxArea = max(maxArea, area)
 *     }
 * }
 * return maxArea
 * ```
 *
 * EXAMPLE TRACE: [1, 5, 4, 3]
 * i=0, j=1: area = 1 × min(1,5) = 1×1 = 1
 * i=0, j=2: area = 2 × min(1,4) = 2×1 = 2
 * i=0, j=3: area = 3 × min(1,3) = 3×1 = 3
 * i=1, j=2: area = 1 × min(5,4) = 1×4 = 4
 * i=1, j=3: area = 2 × min(5,3) = 2×3 = 6 ✓ Max!
 * i=2, j=3: area = 1 × min(4,3) = 1×3 = 3
 * Answer: 6
 *
 * TIME COMPLEXITY: O(n²) - nested loops check all pairs
 * SPACE COMPLEXITY: O(1) - only variables
 *
 * PROS:
 * ✓ Simple to understand
 * ✓ Easy to implement
 * ✓ No special techniques needed
 *
 * CONS:
 * ✗ Slow for large inputs (n = 100,000 → 5 billion operations!)
 * ✗ Not optimal
 *
 * ================================================================================
 * APPROACH 2: TWO POINTERS (OPTIMAL) - O(n)
 * ================================================================================
 *
 * IDEA:
 * Start with widest possible container (using first and last lines).
 * Gradually reduce width by moving pointers inward, but ONLY move the
 * pointer at the SHORTER line (the one limiting the height).
 *
 * KEY INSIGHT:
 * If you move the pointer at the TALLER line:
 *   - Width decreases ✓
 *   - Height stays same or decreases (still limited by shorter line) ✗
 *   - Area can only decrease or stay same ✗
 *
 * If you move the pointer at the SHORTER line:
 *   - Width decreases ✓
 *   - Height MIGHT increase (could find taller line) ✓
 *   - Area MIGHT increase ✓
 *
 * ALGORITHM:
 * 1. Initialize left = 0, right = arr.size - 1, maxArea = 0
 * 2. While left < right:
 *      a. Calculate width = right - left
 *      b. Calculate height = min(arr[left], arr[right])
 *      c. Calculate area = width × height
 *      d. Update maxArea = max(maxArea, area)
 *      e. Move the pointer at the SHORTER line:
 *         - If arr[left] < arr[right]: left++
 *         - Else: right--
 * 3. Return maxArea
 *
 * PSEUDOCODE:
 * ```
 * var left = 0
 * var right = arr.size - 1
 * var maxArea = 0
 *
 * while (left < right) {
 *     val width = right - left
 *     val height = min(arr[left], arr[right])
 *     val area = width * height
 *     maxArea = max(maxArea, area)
 *
 *     if (arr[left] < arr[right]) {
 *         left++   // Move shorter side
 *     } else {
 *         right--  // Move shorter side
 *     }
 * }
 * return maxArea
 * ```
 *
 * DETAILED TRACE: [1, 5, 4, 3]
 *
 * Initial state:
 *   Array: [1, 5, 4, 3]
 *           ↑        ↑
 *         left    right
 *
 * Step 1:
 *   left=0 (val=1), right=3 (val=3)
 *   width = 3 - 0 = 3
 *   height = min(1, 3) = 1
 *   area = 3 × 1 = 3
 *   maxArea = 3
 *   arr[left]=1 < arr[right]=3 → move left++ (move shorter)
 *
 * Step 2:
 *   left=1 (val=5), right=3 (val=3)
 *   width = 3 - 1 = 2
 *   height = min(5, 3) = 3
 *   area = 2 × 3 = 6
 *   maxArea = 6 ✓
 *   arr[left]=5 > arr[right]=3 → move right-- (move shorter)
 *
 * Step 3:
 *   left=1 (val=5), right=2 (val=4)
 *   width = 2 - 1 = 1
 *   height = min(5, 4) = 4
 *   area = 1 × 4 = 4
 *   maxArea = 6 (no update)
 *   arr[left]=5 > arr[right]=4 → move right-- (move shorter)
 *
 * Step 4:
 *   left=1, right=1 → STOP (left == right)
 *
 * Answer: 6 ✓
 *
 * TIME COMPLEXITY: O(n) - single pass with two pointers
 * SPACE COMPLEXITY: O(1) - only variables
 *
 * PROS:
 * ✓ Optimal time complexity
 * ✓ Single pass through array
 * ✓ Works even with large inputs (n = 100,000 → just 100,000 operations!)
 *
 * CONS:
 * ✗ Requires understanding of two-pointer technique
 * ✗ Less intuitive than brute force
 *
 * ================================================================================
 * WHY TWO POINTERS WORKS - PROOF
 * ================================================================================
 *
 * CLAIM: We don't miss the optimal solution by moving the shorter pointer.
 *
 * PROOF BY CONTRADICTION:
 *
 * Assume optimal solution uses lines at positions i and j (i < j).
 * Assume at some point our pointers are at (left, right) where:
 *   left < i and j < right
 *
 * Case 1: arr[left] ≤ arr[right]
 *   Our algorithm moves left++
 *   Could we miss optimal (i,j)?
 *
 *   If we kept left and moved right instead:
 *     - Width decreases
 *     - Height ≤ arr[left] (still limited by left)
 *     - Area with (left, j) ≤ Area with (left, right)
 *
 *   So moving right doesn't help us find (i,j) anyway!
 *   Moving left is safe. ✓
 *
 * Case 2: arr[left] > arr[right]
 *   Similar logic, moving right-- is safe. ✓
 *
 * Therefore, we never miss the optimal solution! QED ✓
 *
 * ================================================================================
 * COMPARISON TABLE
 * ================================================================================
 *
 * | Approach | Time | Space | When to Use |
 * |----------|------|-------|-------------|
 * | Brute Force | O(n²) | O(1) | Small inputs, learning |
 * | Two Pointers | O(n) | O(1) | Optimal solution, interviews |
 *
 * ================================================================================
 * COMMON MISTAKES TO AVOID
 * ================================================================================
 *
 * MISTAKE 1: Sorting the array
 * ❌ DON'T DO THIS!
 * Sorting destroys position information needed for width calculation.
 *
 * Example:
 *   Original: [3, 1, 2, 4, 5]
 *   Index 0 (val=3) and Index 4 (val=5) → width = 4
 *
 *   If sorted: [1, 2, 3, 4, 5]
 *   Values 3 and 5 now at different indices → WRONG width!
 *
 * MISTAKE 2: Moving the taller pointer
 * ❌ WRONG LOGIC!
 * Always move the pointer at the SHORTER line, not taller.
 *
 * MISTAKE 3: Using area = (j - i) × arr[i] (not minimum)
 * ❌ WRONG FORMULA!
 * Must use min(arr[i], arr[j]), not just one height.
 *
 * ================================================================================
 * TODO: Implement both approaches and verify
 * ================================================================================
 */

fun main() {
    // Test cases
    println("=== Test 1 ===")
    println("Input: [1, 5, 4, 3]")
    println("Output: ${maxAreaBruteForce(intArrayOf(1, 5, 4, 3))}")
    println("Expected: 6")
    println()

    println("=== Test 2 ===")
    println("Input: [3, 1, 2, 4, 5]")
    println("Output: ${maxAreaBruteForce(intArrayOf(3, 1, 2, 4, 5))}")
    println("Expected: 12")
    println()

    println("=== Test 3 ===")
    println("Input: [2, 1, 8, 6, 4, 6, 5, 5]")
    println("Output: ${maxAreaBruteForce(intArrayOf(2, 1, 8, 6, 4, 6, 5, 5))}")
    println("Expected: 25")
}

// TODO: Implement brute force O(n²) solution
fun maxAreaBruteForce(arr: IntArray): Int {
    // Hint: Check all pairs (i, j) where i < j
    // For each pair: area = (j - i) × min(arr[i], arr[j])
    return 0
}

// TODO: Implement two-pointer O(n) solution
fun maxAreaTwoPointer(arr: IntArray): Int {
    // Hint: Start with left=0, right=arr.size-1
    // Calculate area, then move pointer at shorter height
    return 0
}

/*
================================================================================
CLARIFYING QUESTIONS TO ASK IN INTERVIEW
================================================================================

1. Can the array have less than 2 elements?
   Answer: No, minimum 2 lines needed to form a container

2. Can heights be zero or negative?
   Answer: Non-negative integers only (0 or positive)

3. What if all heights are the same?
   Answer: Pick the two at extreme ends for maximum width

4. Should I optimize for time or space?
   Answer: Time is more important (O(n) preferred over O(n²))

5. Can I modify the input array (like sorting)?
   Answer: NO! Sorting destroys position information needed for width

6. What's the expected input size?
   Answer: Up to 100,000 elements (need O(n) solution, not O(n²))

================================================================================
INTERVIEW TIPS
================================================================================

1. START WITH BRUTE FORCE:
   - Shows you understand the problem
   - Establishes correct logic
   - Then optimize to two-pointer

2. EXPLAIN WHY TWO POINTERS WORKS:
   - Moving shorter side might find taller line
   - Moving taller side can't improve (still limited by shorter)
   - This greedy approach finds optimal solution

3. DRAW DIAGRAMS:
   - Visual representation helps interviewer follow your logic
   - Show how pointers move step by step

4. TEST WITH EXAMPLES:
   - Trace through at least one example
   - Check edge cases (all same height, increasing, decreasing)

================================================================================
*/
