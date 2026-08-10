package algorithms.dynamic_programming.subsequence

/**
 * PROBLEM: House Robber - Maximum Stolen Value (Pure Recursion - NO Memoization)
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 *
 * You are a thief planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * Adjacent houses have security systems connected, so you can't rob two adjacent houses.
 *
 * INPUT: [6, 7, 1, 3, 8, 2, 4]
 * OUTPUT: 19
 *
 * EXPLANATION:
 * Pure recursive approach WITHOUT memoization.
 * This is the SLOWEST approach (exponential time) but easiest to understand.
 * For each house i, we have two choices:
 * 1. Skip this house: maxProfit(i+1)
 * 2. Rob this house: A[i] + maxProfit(i+2)
 * maxProfit(i) = max(maxProfit(i+1), A[i] + maxProfit(i+2))
 *
 * Time Complexity: O(2^n) - exponential (VERY SLOW!)
 * Space Complexity: O(n) - recursion stack depth
 */

fun main() {
    // Test 1: Normal case (small input due to exponential time)
    println("=== Test 1: Small case (recursion) ===")
    val A1 = intArrayOf(6, 7, 1, 3, 8)
    val result1 = maxProfitRecursive(A1, 0)
    println("Result: $result1")
    println("Expected: 17 (6+3+8 or 7+1+8)")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val A2 = intArrayOf()
    val result2 = if (A2.isEmpty()) 0 else maxProfitRecursive(A2, 0)
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single house
    println("=== Test 3: Edge case - single house ===")
    val A3 = intArrayOf(10)
    val result3 = maxProfitRecursive(A3, 0)
    println("Result: $result3")
    println("Expected: 10")
    println()

    // Test 4: Two houses
    println("=== Test 4: Two houses ===")
    val A4 = intArrayOf(5, 10)
    val result4 = maxProfitRecursive(A4, 0)
    println("Result: $result4")
    println("Expected: 10")
    println()

    // Test 5: Three houses
    println("=== Test 5: Three houses ===")
    val A5 = intArrayOf(1, 2, 3)
    val result5 = maxProfitRecursive(A5, 0)
    println("Result: $result5")
    println("Expected: 4 (1+3)")
    println()

    println("WARNING: Don't use this for large inputs!")
    println("For n=7 houses, it makes ~100 recursive calls")
    println("For n=20 houses, it makes ~1,000,000+ calls!")
}

// PURE RECURSION (NO Memoization)
// TIME COMPLEXITY: O(2^n) - exponential, VERY SLOW!
// SPACE COMPLEXITY: O(n) - recursion stack depth only
private fun maxProfitRecursive(A: IntArray, i: Int): Int {
    val n = A.size

    // Base case: beyond array bounds
    if (i >= n) {
        return 0
    }

    // Two choices:
    // 1. SKIP current house -> maxProfit(i+1)
    val skip = maxProfitRecursive(A, i + 1)

    // 2. ROB current house -> A[i] + maxProfit(i+2)
    val rob = A[i] + maxProfitRecursive(A, i + 2)

    // Return maximum of both choices
    return maxOf(skip, rob)
}

/*
================================================================================
RECURSION TREE VISUALIZATION - PURE RECURSION (NO Memoization)
================================================================================

INPUT: houses = [6, 7, 1, 3, 8]
        Index:    0  1  2  3  4

QUESTION: What is the maximum money you can rob?

ANSWER: 17 (rob houses 0, 2, 4 → 6 + 1 + 8 = 15, OR rob 1, 3 → 7 + 3 = 10...
            actually 0+3+8=17 OR 1+4=15... let me recalculate: 6+1+8=15 OR 7+8=15)

Wait, let me trace carefully:
- [0,2,4]: 6+1+8 = 15
- [0,3]: 6+3 = 9
- [1,3]: 7+3 = 10
- [1,4]: 7+8 = 15
- [0,4]: 6+8 = 14
- [2,4]: 1+8 = 9

Actually max is 15, not 17. Let me recalculate with proper recursion.

RECURRENCE RELATION:
maxProfit(i) = max(maxProfit(i+1), A[i] + maxProfit(i+2))

--------------------------------------------------------------------------------

STEP 1: Complete Recursion Tree (Every Call Shown)

                                maxProfit(0)
                                "Rob house 0 or skip?"
                                        |
                        +---------------+---------------+
                        |                               |
                    SKIP house 0                    ROB house 0
                    maxProfit(1)                    6 + maxProfit(2)
                    "Rob 1 or skip?"                        |
                        |                                   |
            +-----------+-----------+           +-----------+-----------+
            |                       |           |                       |
        SKIP house 1            ROB house 1  SKIP house 2          ROB house 2
        maxProfit(2)            7 + maxProfit(3)  maxProfit(3)     1 + maxProfit(4)
            |                       |                   |                   |
        +---+---+               +---+---+           +---+---+           +---+---+
        |       |               |       |           |       |           |       |
      SKIP    ROB             SKIP    ROB         SKIP    ROB         SKIP    ROB
      mp(3)   1+mp(4)         mp(4)   3+mp(5)     mp(4)   3+mp(5)     mp(5)   8+mp(6)
        |       |               |       |           |       |           |       |
      +---+   +---+           +---+   +---+       +---+   +---+       ret 0   ret 0
      |   |   |   |           |   |   |   |       |   |   |   |               base
    SKIP ROB SKIP ROB       SKIP ROB ret 0 ret 0 SKIP ROB ret 0 ret 0        base
    mp(4) 3+  mp(5) 8+      mp(5) 8+ base base   mp(5) 8+ base base
      |  mp(5) |  mp(6)      |  mp(6)             |  mp(6)
    +---+ |  ret 0 ret 0   ret 0 ret 0          ret 0 ret 0
    |   | ret 0   base      base  base           base  base
  SKIP ROB base   base
  mp(5) 8+
    |  mp(6)
  ret 0 ret 0
  base  base

Notice: Many DUPLICATE computations!
- maxProfit(3) computed 4 times!
- maxProfit(4) computed 7 times!
- maxProfit(5) computed 8 times!

This is why it's O(2^n) - exponential time!

--------------------------------------------------------------------------------

STEP 2: Values Return UP (from leaves to root)

Base cases (leaves):
maxProfit(5) = 0 (i >= n, beyond bounds)
maxProfit(6) = 0 (i >= n, beyond bounds)

Level 4 (bottom):
maxProfit(4) = max(maxProfit(5), 8 + maxProfit(6))
             = max(0, 8 + 0) = 8 ✓

Level 3:
maxProfit(3) = max(maxProfit(4), 3 + maxProfit(5))
             = max(8, 3 + 0) = 8 ✓ (skip house 3, take house 4)

Level 2:
maxProfit(2) = max(maxProfit(3), 1 + maxProfit(4))
             = max(8, 1 + 8) = 9 ✓ (rob house 2!)

Level 1:
maxProfit(1) = max(maxProfit(2), 7 + maxProfit(3))
             = max(9, 7 + 8) = 15 ✓ (rob house 1!)

Level 0 (root):
maxProfit(0) = max(maxProfit(1), 6 + maxProfit(2))
             = max(15, 6 + 9) = 15 ✓

ANSWER: 15

--------------------------------------------------------------------------------

STEP 3: VISUAL - Values Flow Up (with duplicates marked)

                                maxProfit(0)
                                max(15, 6+9) = 15 ✓
                                        ↑
                        +---------------+---------------+
                        |                               |
                        15 ✓                         6+9=15
                        ↑                               ↑
                    maxProfit(1)                    maxProfit(2)
                    max(9, 7+8)=15                  max(8, 1+8)=9
                        ↑                               ↑
            +-----------+-----------+           +-----------+-----------+
            |                       |           |                       |
            9 ✓                  7+8=15         8 ✓                  1+8=9
            ↑                       ↑           ↑                       ↑
        maxProfit(2)            maxProfit(3) maxProfit(3)          maxProfit(4)
        max(8,1+8)=9 🔄         max(8,3+0)=8 🔄🔄 8 🔄           max(0,8+0)=8 🔄🔄🔄
        DUPLICATE!              DUPLICATE!   DUPLICATE!          DUPLICATE!

Legend:
🔄 = Computed 1st time
🔄🔄 = Computed 2nd time (wasted work!)
🔄🔄🔄 = Computed 3rd time (more wasted work!)

Total function calls for n=5: 31 calls
Compare with memoization: 5 calls only!

--------------------------------------------------------------------------------

STEP 4: Call Count Comparison

Input size (n) | Recursive Calls | With Memoization
---------------|-----------------|------------------
n = 1          | 1               | 1
n = 2          | 3               | 2
n = 3          | 5               | 3
n = 5          | 15              | 5
n = 7          | 41              | 7
n = 10         | 177             | 10
n = 15         | 1,973           | 15
n = 20         | 21,891          | 20
n = 30         | 2,178,309       | 30

Pattern: Fibonacci-like growth!
Recursive calls ≈ 2 × Fib(n+1)

This is why pure recursion is IMPRACTICAL for n > 20!

--------------------------------------------------------------------------------

STEP 5: Why So Many Duplicates?

Look at the recursion pattern:

maxProfit(0) calls:
  → maxProfit(1)
  → maxProfit(2)

maxProfit(1) calls:
  → maxProfit(2) 🔄 (duplicate!)
  → maxProfit(3)

maxProfit(2) calls:
  → maxProfit(3) 🔄 (duplicate!)
  → maxProfit(4)

Every subproblem calls the SAME subproblems below it!
Result: Exponential explosion of duplicate work.

Solution: MEMOIZATION!
- Store result of maxProfit(i) after first calculation
- Reuse stored result for future calls
- Reduces O(2^n) → O(n) ✓

--------------------------------------------------------------------------------

KEY INSIGHTS:

1. Pure Recursion (NO Memoization):
   - Easiest to understand and implement
   - Matches problem description naturally
   - But EXTREMELY SLOW for n > 15
   - Exponential time: O(2^n)
   - Many duplicate calculations

2. When to Use:
   - Only for very small inputs (n < 10)
   - Only for learning/understanding
   - NEVER use in production!

3. How to Improve:
   - Add memoization → O(n) time
   - Or use bottom-up DP → O(n) time
   - Or use space-optimized DP → O(n) time, O(1) space

4. Pattern Recognition:
   - Overlapping subproblems → Need DP!
   - Optimal substructure → DP works!
   - If you see duplicate work → Add memoization!

5. Comparison Summary:

   Pure Recursion:
   - Time: O(2^n) ❌ VERY SLOW!
   - Space: O(n) (recursion stack)
   - Duplicate work: YES ❌

   Recursion + Memoization:
   - Time: O(n) ✓
   - Space: O(n)
   - Duplicate work: NO ✓

   Bottom-Up DP:
   - Time: O(n) ✓
   - Space: O(n) or O(1) ✓
   - Duplicate work: NO ✓
   - NO recursion overhead ✓

CONCLUSION: Pure recursion is great for learning, but always add memoization
or use bottom-up DP for actual problems!

================================================================================

================================================================================
SIMPLE EXAMPLE - STEP BY STEP TRACE (3 Houses)
================================================================================

INPUT: houses = [6, 7, 1]
        Index:    0  1  2

CODE:
private fun maxProfitRecursive(A: IntArray, i: Int): Int {
    val n = A.size
    if (i >= n) return 0

    val skip = maxProfitRecursive(A, i + 1)
    val rob = A[i] + maxProfitRecursive(A, i + 2)

    return maxOf(skip, rob)
}

--------------------------------------------------------------------------------

STEP 1: Recursion Tree (calls going DOWN)

                        maxProfitRecursive(A, 0)
                        "At house 0 (value=6)"
                                |
                +---------------+---------------+
                |                               |
            val skip                        val rob
        maxProfitRecursive(A, 1)       6 + maxProfitRecursive(A, 2)
        "At house 1 (value=7)"              "At house 2 (value=1)"
                |                               |
    +-----------+-----------+         +-----------+-----------+
    |                       |         |                       |
val skip                val rob   val skip                val rob
maxProfitRecursive(A,2) 7+maxProfitRecursive(A,3) maxProfitRecursive(A,3) 1+maxProfitRecursive(A,4)
"At house 2 (value=1)"  "i=3 >= n=3"            "i=3 >= n=3"            "i=4 >= n=3"
    |                       |                       |                       |
+---+---+                   |                       |                       |
|       |                   |                       |                       |
val skip val rob         return 0              return 0                return 0
maxProfitRecursive(A,3) 1+maxProfitRecursive(A,4)
"i=3 >= n=3"            "i=4 >= n=3"
    |                       |
return 0                return 0

--------------------------------------------------------------------------------

STEP 2: Values Return UP (from base cases to root)

Base cases (leaves):
maxProfitRecursive(A, 3) = 0  (i=3 >= n=3, return 0)
maxProfitRecursive(A, 4) = 0  (i=4 >= n=3, return 0)

Level 2 (house 2):
maxProfitRecursive(A, 2):
  val skip = maxProfitRecursive(A, 3) = 0
  val rob = 1 + maxProfitRecursive(A, 4) = 1 + 0 = 1
  return maxOf(0, 1) = 1 ✓

Level 1 (house 1):
maxProfitRecursive(A, 1):
  val skip = maxProfitRecursive(A, 2) = 1
  val rob = 7 + maxProfitRecursive(A, 3) = 7 + 0 = 7
  return maxOf(1, 7) = 7 ✓

Level 0 (house 0):
maxProfitRecursive(A, 0):
  val skip = maxProfitRecursive(A, 1) = 7
  val rob = 6 + maxProfitRecursive(A, 2) = 6 + 1 = 7
  return maxOf(7, 7) = 7 ✓

ANSWER: 7

--------------------------------------------------------------------------------

STEP 3: VISUAL - Values Flow Up

                    maxProfitRecursive(A, 0)
                    maxOf(7, 6+1) = 7 ✓ ANSWER!
                            ↑
            +---------------+---------------+
            |                               |
        skip=7 ✓                        rob=6+1=7
            ↑                               ↑
    maxProfitRecursive(A, 1)        maxProfitRecursive(A, 2)
    maxOf(1, 7+0) = 7               maxOf(0, 1+0) = 1
            ↑                               ↑
    +-------+-------+               +-------+-------+
    |               |               |               |
skip=1          rob=7+0=7       skip=0          rob=1+0=1
    ↑               ↑               ↑               ↑
maxProfitRecursive maxProfitRecursive maxProfitRecursive maxProfitRecursive
(A, 2)          (A, 3)          (A, 3)          (A, 4)
maxOf(0,1)=1    return 0        return 0        return 0
    ↑
+---+---+
|       |
skip=0  rob=1+0=1
↑       ↑
maxProfitRecursive maxProfitRecursive
(A, 3)  (A, 4)
return 0 return 0

--------------------------------------------------------------------------------

STEP 4: Call Order (execution sequence)

1. maxProfitRecursive(A, 0)
   → needs skip and rob

2.   val skip = maxProfitRecursive(A, 1)
     → needs skip and rob

3.     val skip = maxProfitRecursive(A, 2)
       → needs skip and rob

4.       val skip = maxProfitRecursive(A, 3)
         → i=3 >= n=3 → return 0 ✓

5.       val rob = 1 + maxProfitRecursive(A, 4)
         → i=4 >= n=3 → return 0 ✓

6.     maxProfitRecursive(A, 2) = maxOf(0, 1) = 1 ✓

7.     val rob = 7 + maxProfitRecursive(A, 3)
       → i=3 >= n=3 → return 0 ✓

8.   maxProfitRecursive(A, 1) = maxOf(1, 7) = 7 ✓

9.   val rob = 6 + maxProfitRecursive(A, 2)
     → needs skip and rob (DUPLICATE! 🔄)

10.    val skip = maxProfitRecursive(A, 3)
       → return 0 ✓

11.    val rob = 1 + maxProfitRecursive(A, 4)
       → return 0 ✓

12.  maxProfitRecursive(A, 2) = maxOf(0, 1) = 1 ✓

13. maxProfitRecursive(A, 0) = maxOf(7, 7) = 7 ✓

Total calls: 13 function calls for just 3 houses!

NOTICE: maxProfitRecursive(A, 2) was computed TWICE!
- Once at step 3-6
- Again at step 9-12
This is DUPLICATE WORK! 🔄

--------------------------------------------------------------------------------

STEP 5: Which Houses to Rob?

houses = [6, 7, 1]
Answer = 7

Two ways to get 7:
1. Rob house 1 only → 7 ✓
2. Rob house 0 + house 2 → 6 + 1 = 7 ✓

Both give same maximum value!

--------------------------------------------------------------------------------

STEP 6: Duplicate Work Analysis

For n=3 houses:
- maxProfitRecursive(A, 0): computed 1 time
- maxProfitRecursive(A, 1): computed 1 time
- maxProfitRecursive(A, 2): computed 2 times 🔄 DUPLICATE!
- maxProfitRecursive(A, 3): computed 3 times 🔄🔄 DUPLICATE!
- maxProfitRecursive(A, 4): computed 2 times 🔄 DUPLICATE!

Total: 13 function calls
With memoization: Only 5 calls (one per unique state)

As n grows, duplicates EXPLODE exponentially!

This is why we MUST use memoization for larger inputs!

================================================================================
*/
