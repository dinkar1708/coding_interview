package algorithms.dynamic_programming.knapsack

/**
 * PROBLEM: 0/1 Knapsack Problem
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 *
 * Given weights and values of n items, put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack.
 *
 * INPUT: value = [60, 100, 120], weight = [10, 20, 30], W = 50
 * OUTPUT: 220
 *
 * EXPLANATION:
 * In the 0-1 Knapsack problem, we are not allowed to break items.
 * We either take the whole item or don't take it.
 * For each item, we have two choices:
 * 1. Include the item (if it fits) and add its value
 * 2. Exclude the item
 * We recursively try both options and take the maximum.
 *
 * Time Complexity: O(2^n) - exponential (without memoization)
 * Space Complexity: O(n) - recursion stack
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = kNapsack(intArrayOf(60, 100, 120), intArrayOf(10, 20, 30), 50, 3)
    println("Result: $result1")
    println("Expected: 220 (items 2 and 3)")
    println()

    // Test 2: Edge case - zero capacity
    println("=== Test 2: Edge case - zero capacity ===")
    val result2 = kNapsack(intArrayOf(60, 100, 120), intArrayOf(10, 20, 30), 0, 3)
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - no items
    println("=== Test 3: Edge case - no items ===")
    val result3 = kNapsack(intArrayOf(), intArrayOf(), 50, 0)
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Single item that fits
    println("=== Test 4: Single item that fits ===")
    val result4 = kNapsack(intArrayOf(100), intArrayOf(20), 50, 1)
    println("Result: $result4")
    println("Expected: 100")
    println()

    // Test 5: All items too heavy
    println("=== Test 5: All items too heavy ===")
    val result5 = kNapsack(intArrayOf(60, 100), intArrayOf(100, 200), 50, 2)
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: Can take all items
    println("=== Test 6: Can take all items ===")
    val result6 = kNapsack(intArrayOf(10, 20, 30), intArrayOf(1, 2, 3), 10, 3)
    println("Result: $result6")
    println("Expected: 60 (all items)")

    /*
    Note: This is exponential time without memoization.
    Each function call.....
    >>>Items , W 3 50
    Items , W 3 50
    Each function call.....
    >>>Items , W 2 20
    Items , W 2 20
    Each function call.....
    >>>Items , W 1 20
    Items , W 1 20
    Each function call.....
    >>>Items , W 2 50
    Items , W 2 50
    Each function call.....
    >>>Items , W 1 30
    Items , W 1 30
    Each function call.....
    >>>Items , W 1 50
    Items , W 1 50
    Max value: 220
    */
}

// TIME COMPLEXITY: O(2^n) - explore all subsets recursively without memoization
// SPACE COMPLEXITY: O(n) - recursion stack depth
private fun kNapsack(value: IntArray, weight: IntArray, W: Int, n: Int): Int {
    // Edge case: empty arrays or value/weight length mismatch
    if (value.isEmpty() || weight.isEmpty() || value.size != weight.size) {
        return 0
    }

    // Base case: no capacity left
    if (W <= 0) {
        return 0
    }

    // Base case: no items left
    if (n == 0) {
        return 0
    }

    println("Each function call.....")
    println(">>>Items , W $n $W")

    // If weight of the nth item is more than Knapsack capacity W,
    // then this item cannot be included in the optimal solution
    if (weight[n - 1] > W) {
        return kNapsack(value, weight, W, n - 1)
    } else {
        // Return the maximum of two cases:
        // (1) nth item included
        // (2) nth item not included
        println("Items , W $n $W")
        return maxOf(
            value[n - 1] + kNapsack(value, weight, W - weight[n - 1], n - 1),
            kNapsack(value, weight, W, n - 1)
        )
    }
}
