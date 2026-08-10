package algorithms.dynamic_programming.fibonacci

/**
 * PROBLEM: Fibonacci using Top-Down with Memoization
 *
 * Calculate the nth Fibonacci number using recursion with memoization.
 *
 * INPUT: n = 5
 * OUTPUT: 5
 *
 * EXPLANATION:
 * Memoization caches already calculated values to avoid redundant computation.
 * Store results in array A and check before recalculating.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (n=5) ===")
    val A1 = IntArray(6)
    println("Result: ${fibTopDown(A1, 5)}")
    println("Expected: 5")
    println()

    // Test 2: Edge case - zero
    println("=== Test 2: Edge case - zero (n=0) ===")
    val A2 = IntArray(1)
    println("Result: ${fibTopDown(A2, 0)}")
    println("Expected: 0")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (n=1) ===")
    val A3 = IntArray(2)
    println("Result: ${fibTopDown(A3, 1)}")
    println("Expected: 1")
    println()

    // Test 4: Larger number
    println("=== Test 4: Larger number (n=10) ===")
    val A4 = IntArray(11)
    println("Result: ${fibTopDown(A4, 10)}")
    println("Expected: 55")
    println()

    // Test 5: Even larger
    println("=== Test 5: Even larger (n=15) ===")
    val A5 = IntArray(16)
    println("Result: ${fibTopDown(A5, 15)}")
    println("Expected: 610")
    println()

    // Test 6: Very large
    println("=== Test 6: Very large (n=20) ===")
    val A6 = IntArray(21)
    println("Result: ${fibTopDown(A6, 20)}")
    println("Expected: 6765")
}

// TOP-DOWN MEMOIZATION
// TIME COMPLEXITY: O(n) - each fib(i) computed only once
// SPACE COMPLEXITY: O(n) - memo array + O(n) recursion stack
private fun fibTopDown(A: IntArray, n: Int): Int {
    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return 1
    }

    // Check if already calculated
    if (A[n] != 0) {
        println("Already calculated for $n ${A.contentToString()}")
        return A[n]
    }

    // Calculate and store
    A[n] = fibTopDown(A, n - 1) + fibTopDown(A, n - 2)
    return A[n]
}

/*
================================================================================
RECURSION TREE VISUALIZATION - FIBONACCI WITH MEMOIZATION
================================================================================

INPUT: n = 5
QUESTION: What is fib(5)?

WITHOUT Memoization (Naive Recursion):
- Many duplicate calculations
- Time: O(2^n) - exponential!

                            fib(5)
                              |
                    +---------+---------+
                    |                   |
                  fib(4)              fib(3) ← DUPLICATE!
                    |                   |
            +-------+-------+     +-----+-----+
            |               |     |           |
          fib(3)          fib(2) fib(2)    fib(1)
            |               |
        +---+---+       +---+---+
        |       |       |       |
      fib(2) fib(1)  fib(1)  fib(0)
        |
    +---+---+
    |       |
  fib(1) fib(0)

Notice: fib(3) computed TWICE, fib(2) computed THREE times!
Total calls: 15 (very slow!)

--------------------------------------------------------------------------------

WITH Memoization (Top-Down DP):
- Each fib(i) computed ONLY ONCE
- Store in memo array A[]
- Time: O(n) - linear!

STEP 1: Tree Goes DOWN (recursive calls)

                            fib(5)
                            "Need fib(4) and fib(3)"
                              |
                    +---------+---------+
                    |                   |
                  fib(4)              fib(3) ← Will use CACHE!
                  "Not in A[]"        "Check A[3]"
                  COMPUTE                |
                    |                    |
            +-------+-------+     [CACHE HIT! 🔥]
            |               |     Return A[3]=2
          fib(3)          fib(2) (NO recursion!)
          "Not in A[]"    "Not in A[]"
          COMPUTE         COMPUTE
            |               |
        +---+---+       +---+---+
        |       |       |       |
      fib(2) fib(1)  fib(1)  fib(0)
      COMPUTE ret 1   ret 1   ret 0
        |
    +---+---+
    |       |
  fib(1) fib(0)
  ret 1  ret 0

STEP 2: Memo Array Evolution

Initial:
A = [0, 0, 0, 0, 0, 0]
     0  1  2  3  4  5

After fib(0) and fib(1):
A = [0, 0, 0, 0, 0, 0]
     ↑  ↑
   ret 0, ret 1 (base cases, not stored)

After fib(2) = fib(1) + fib(0) = 1 + 0 = 1:
A = [0, 0, 1, 0, 0, 0]
           ↑
         A[2]=1 stored ✓

After fib(3) = fib(2) + fib(1) = 1 + 1 = 2:
A = [0, 0, 1, 2, 0, 0]
              ↑
            A[3]=2 stored ✓

After fib(4) = fib(3) + fib(2):
  fib(3) → Check A[3] → 2 ✓ (CACHE HIT!)
  fib(2) → Check A[2] → 1 ✓ (CACHE HIT!)
  Result: 2 + 1 = 3
A = [0, 0, 1, 2, 3, 0]
                 ↑
               A[4]=3 stored ✓

After fib(5) = fib(4) + fib(3):
  fib(4) → Check A[4] → 3 ✓ (CACHE HIT!)
  fib(3) → Check A[3] → 2 ✓ (CACHE HIT!)
  Result: 3 + 2 = 5
A = [0, 0, 1, 2, 3, 5]
                    ↑
                  A[5]=5 ✓ ANSWER!

STEP 3: VISUAL - Call Order and Cache Hits

Call order:
1. fib(5) → not in A[], need fib(4) and fib(3)
2. fib(4) → not in A[], need fib(3) and fib(2)
3. fib(3) → not in A[], need fib(2) and fib(1)
4. fib(2) → not in A[], need fib(1) and fib(0)
5. fib(1) → base case, return 1
6. fib(0) → base case, return 0
7. fib(2) = 1 + 0 = 1, store A[2]=1 ✓
8. fib(1) → base case, return 1
9. fib(3) = fib(2) + fib(1) = 1 + 1 = 2, store A[3]=2 ✓
10. fib(2) → A[2]=1 ✓ CACHE HIT! (no recursion)
11. fib(4) = fib(3) + fib(2) = 2 + 1 = 3, store A[4]=3 ✓
12. fib(3) → A[3]=2 ✓ CACHE HIT! (no recursion)
13. fib(5) = fib(4) + fib(3) = 3 + 2 = 5 ✓

Total calls: 9 (vs 15 without memoization)
Cache hits: 2

For larger n, the difference is HUGE:
- fib(20) without memo: 21,891 calls
- fib(20) with memo: 39 calls

--------------------------------------------------------------------------------

KEY BENEFITS OF MEMOIZATION:

1. Time Complexity:
   Without memo: O(2^n) - exponential
   With memo:    O(n) - linear ✓

2. Space Complexity:
   Array: O(n)
   Recursion stack: O(n)
   Total: O(n)

3. Pattern:
   - Check cache first: if (A[n] != 0) return A[n]
   - Compute once: A[n] = fibTopDown(A, n-1) + fibTopDown(A, n-2)
   - Reuse forever: all future calls use cached value

4. Trade-off:
   - Uses more space (O(n) array)
   - Saves MASSIVE time (exponential → linear)
   - Perfect example of time-space tradeoff!

================================================================================
*/
