# Prefix Sum Technique

## What is Prefix Sum?

A **preprocessing technique** that allows **O(1)** range sum queries by storing cumulative sums.

**Prefix Sum Array**: `prefix[i]` = sum of all elements from index `0` to `i`

---

## Why Use It?

### Problem Without Prefix Sum:
```kotlin
// Given array: [3, 1, 4, 2, 5]
// Query: "What is sum from index 1 to 3?"

// Brute Force - O(n) per query
fun rangeSum(arr: IntArray, left: Int, right: Int): Int {
    var sum = 0
    for (i in left..right) {
        sum += arr[i]
    }
    return sum
}

// Query sum(1, 3) = 1 + 4 + 2 = 7
// If we have Q queries → O(Q * n) total
```

### Solution With Prefix Sum:
```kotlin
// Preprocessing - O(n) once
val prefix = IntArray(n)
prefix[0] = arr[0]
for (i in 1 until n) {
    prefix[i] = prefix[i-1] + arr[i]
}

// prefix = [3, 4, 8, 10, 15]

// Query - O(1) each
fun rangeSum(left: Int, right: Int): Int {
    return if (left == 0) {
        prefix[right]
    } else {
        prefix[right] - prefix[left - 1]
    }
}

// Query sum(1, 3) = prefix[3] - prefix[0] = 10 - 3 = 7
// Q queries → O(n + Q) total
```

---

## How It Works

### Building Prefix Sum Array:

```
Original array:  [3,  1,  4,  2,  5]
Index:            0   1   2   3   4

Prefix sum:      [3,  4,  8, 10, 15]
                  ↓   ↓   ↓   ↓   ↓
               [3] [3+1] [3+1+4] [3+1+4+2] [3+1+4+2+5]

Formula: prefix[i] = prefix[i-1] + arr[i]
```

### Range Sum Query:

```
Query: sum from index 1 to 3

Visual:
Array:   [3,  1,  4,  2,  5]
          0   1   2   3   4
              └───────┘
              Want this sum

Prefix:  [3,  4,  8, 10, 15]

sum(1, 3) = prefix[3] - prefix[0]
          = 10 - 3
          = 7

Why?
prefix[3] = 3 + 1 + 4 + 2 = 10  (sum from 0 to 3)
prefix[0] = 3                    (sum from 0 to 0)
Difference = 1 + 4 + 2 = 7       (sum from 1 to 3) ✓
```

---

## When to Use Prefix Sum?

### ✅ Use Prefix Sum When:

1. **Multiple range sum queries** on same array
2. **Subarray sum problems** (find count, existence, max/min)
3. **2D matrix range sum** queries
4. **Cumulative frequency** counting
5. **Difference array** manipulation

### ❌ Don't Use When:

1. **Array is frequently updated** (each update requires O(n) to rebuild prefix)
   - Alternative: Use Segment Tree or Fenwick Tree
2. **Single query** (not worth preprocessing overhead)
3. **Non-additive operations** (max, min, product needs different approach)

---

## Problem Types & Templates

### Type 1: Range Sum Queries

**Examples**:
- Range Sum Query - Immutable
- Sum of subarray ranges
- Equilibrium Index

**Template**:
```kotlin
class RangeSumQuery(nums: IntArray) {
    private val prefix = IntArray(nums.size)

    init {
        prefix[0] = nums[0]
        for (i in 1 until nums.size) {
            prefix[i] = prefix[i-1] + nums[i]
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        return if (left == 0) {
            prefix[right]
        } else {
            prefix[right] - prefix[left - 1]
        }
    }
}
```

---

### Type 2: Subarray Sum Equals K

**Pattern**: Use HashMap to track prefix sums

**Examples**:
- Subarray Sum Equals K
- Continuous Subarray Sum
- Make Sum Divisible by P

**Template**:
```kotlin
fun subarraySum(nums: IntArray, k: Int): Int {
    val prefixSumCount = mutableMapOf<Int, Int>()
    prefixSumCount[0] = 1  // Empty subarray

    var currentSum = 0
    var count = 0

    for (num in nums) {
        currentSum += num

        // Check if (currentSum - k) exists
        // If yes, there's a subarray with sum k
        val target = currentSum - k
        count += prefixSumCount.getOrDefault(target, 0)

        // Store current prefix sum
        prefixSumCount[currentSum] =
            prefixSumCount.getOrDefault(currentSum, 0) + 1
    }

    return count
}
```

**How it works**:
```
Array: [1, 2, 3, 4, 5], k = 9

Prefix sums: [1, 3, 6, 10, 15]

At index 4 (currentSum = 15):
  target = 15 - 9 = 6

  If prefix[j] = 6 exists, then:
  sum(j+1, 4) = prefix[4] - prefix[j] = 15 - 6 = 9 ✓

  Found: subarray [4, 5] has sum 9
```

---

### Type 3: Maximum Subarray Sum (Kadane's Algorithm)

**Note**: Kadane's uses running sum concept (similar to prefix sum)

**Examples**:
- Maximum Subarray (Kadane's Algorithm)
- Maximum Sum Circular Subarray
- Best Time to Buy and Sell Stock

**Template**:
```kotlin
fun maxSubArray(nums: IntArray): Int {
    var maxSoFar = nums[0]
    var maxEndingHere = nums[0]

    for (i in 1 until nums.size) {
        maxEndingHere = max(nums[i], maxEndingHere + nums[i])
        maxSoFar = max(maxSoFar, maxEndingHere)
    }

    return maxSoFar
}
```

---

### Type 4: 2D Prefix Sum (Matrix)

**Examples**:
- Range Sum Query 2D - Immutable
- Number of Submatrices That Sum to Target

**Template**:
```kotlin
class NumMatrix(matrix: Array<IntArray>) {
    private val prefix: Array<IntArray>

    init {
        val m = matrix.size
        val n = matrix[0].size
        prefix = Array(m + 1) { IntArray(n + 1) }

        // Build 2D prefix sum
        for (i in 1..m) {
            for (j in 1..n) {
                prefix[i][j] = matrix[i-1][j-1] +
                               prefix[i-1][j] +
                               prefix[i][j-1] -
                               prefix[i-1][j-1]
            }
        }
    }

    fun sumRegion(r1: Int, c1: Int, r2: Int, c2: Int): Int {
        return prefix[r2+1][c2+1] -
               prefix[r1][c2+1] -
               prefix[r2+1][c1] +
               prefix[r1][c1]
    }
}
```

**Visual**:
```
Matrix:
[3, 0, 1, 4]
[5, 6, 3, 2]
[1, 2, 0, 1]

2D Prefix Sum:
[ 3,  3,  4,  8]
[ 8, 14, 18, 24]
[ 9, 17, 21, 28]

Query sum of rectangle (1,1) to (2,2):
= prefix[2+1][2+1] - prefix[1][2+1] - prefix[2+1][1] + prefix[1][1]
= 21 - 4 - 9 + 3
= 11

Which is: 6 + 3 + 2 + 0 = 11 ✓
```

---

### Type 5: Difference Array

**Use Case**: Apply range updates efficiently

**Examples**:
- Corporate Flight Bookings
- Car Pooling
- Range Addition

**Template**:
```kotlin
fun rangeAddition(length: Int, updates: Array<IntArray>): IntArray {
    val diff = IntArray(length)

    // Apply updates to difference array
    for ((start, end, value) in updates) {
        diff[start] += value
        if (end + 1 < length) {
            diff[end + 1] -= value
        }
    }

    // Convert difference array to actual array using prefix sum
    val result = IntArray(length)
    result[0] = diff[0]
    for (i in 1 until length) {
        result[i] = result[i-1] + diff[i]
    }

    return result
}
```

**How it works**:
```
Array length: 5
Updates: [[1, 3, 2], [2, 4, 3]]

After applying to diff array:
diff = [0, 2, 1, -2, -3]
       ↑  ↑  ↑   ↑   ↑
     start:1  start:2  end:3+1  end:4+1
     +2       +3       -2       -3

Convert to result using prefix sum:
result[0] = 0
result[1] = 0 + 2 = 2
result[2] = 2 + 1 = 3  (added by both updates)
result[3] = 3 + (-2) = 1  (first update ended)
result[4] = 1 + (-3) = -2  (second update ended... wait this seems wrong)

Let me recalculate:
Updates: add 2 to indices [1,3], add 3 to indices [2,4]

diff array logic:
  Update [1,3,2]: diff[1] += 2, diff[4] -= 2
  Update [2,4,3]: diff[2] += 3, diff[5] -= 3 (but 5 >= length, skip)

diff = [0, 2, 3, 0, -2]

result:
[0] = 0
[1] = 0 + 2 = 2
[2] = 2 + 3 = 5
[3] = 5 + 0 = 5
[4] = 5 + (-2) = 3

Expected: [0, 2, 5, 5, 3] ✓
```

---

### Type 6: Product of Array Except Self

**Variation**: Use prefix product instead of prefix sum

**Template**:
```kotlin
fun productExceptSelf(nums: IntArray): IntArray {
    val n = nums.size
    val result = IntArray(n)

    // Left prefix product
    result[0] = 1
    for (i in 1 until n) {
        result[i] = result[i-1] * nums[i-1]
    }

    // Right prefix product
    var rightProduct = 1
    for (i in n-1 downTo 0) {
        result[i] *= rightProduct
        rightProduct *= nums[i]
    }

    return result
}
```

---

## Common Patterns

### Pattern 1: HashMap + Prefix Sum

**Use for**: Count subarrays with target sum/property

```kotlin
val prefixSumCount = mutableMapOf<Int, Int>()
prefixSumCount[0] = 1

var currentSum = 0
var count = 0

for (num in nums) {
    currentSum += num
    count += prefixSumCount.getOrDefault(currentSum - k, 0)
    prefixSumCount[currentSum] =
        prefixSumCount.getOrDefault(currentSum, 0) + 1
}
```

### Pattern 2: Two Pointers + Prefix Sum

**Use for**: Find subarrays within range

```kotlin
// Build prefix sum
val prefix = buildPrefixSum(nums)

// Use two pointers
var left = 0
for (right in nums.indices) {
    val rangeSum = prefix[right] - (if (left == 0) 0 else prefix[left-1])

    if (rangeSum > target) {
        left++
    }
}
```

### Pattern 3: Prefix Sum from Both Ends

**Use for**: Maximum subarray, best time to buy/sell stock

```kotlin
val leftMax = IntArray(n)  // Max sum from left
val rightMax = IntArray(n) // Max sum from right

// Calculate both
leftMax[0] = nums[0]
for (i in 1 until n) {
    leftMax[i] = max(leftMax[i-1] + nums[i], nums[i])
}

rightMax[n-1] = nums[n-1]
for (i in n-2 downTo 0) {
    rightMax[i] = max(rightMax[i+1] + nums[i], nums[i])
}
```

---

## Step-by-Step Example: Subarray Sum Equals K

### Problem:
```
Array: [1, 2, 3, 4, 5]
K: 9
Find: Count of subarrays with sum = 9
```

### Solution:
```kotlin
Step 1: Initialize
  prefixSumCount = {0: 1}
  currentSum = 0
  count = 0

Step 2: Process each element

i=0, num=1:
  currentSum = 0 + 1 = 1
  target = 1 - 9 = -8
  count += prefixSumCount[-8] = 0
  prefixSumCount = {0: 1, 1: 1}

i=1, num=2:
  currentSum = 1 + 2 = 3
  target = 3 - 9 = -6
  count += prefixSumCount[-6] = 0
  prefixSumCount = {0: 1, 1: 1, 3: 1}

i=2, num=3:
  currentSum = 3 + 3 = 6
  target = 6 - 9 = -3
  count += prefixSumCount[-3] = 0
  prefixSumCount = {0: 1, 1: 1, 3: 1, 6: 1}

i=3, num=4:
  currentSum = 6 + 4 = 10
  target = 10 - 9 = 1
  count += prefixSumCount[1] = 1  ← Found one!
  prefixSumCount = {0: 1, 1: 1, 3: 1, 6: 1, 10: 1}

  Why? prefix[3] - prefix[0] = 10 - 1 = 9
       Subarray [2, 3, 4] has sum 9

i=4, num=5:
  currentSum = 10 + 5 = 15
  target = 15 - 9 = 6
  count += prefixSumCount[6] = 1  ← Found one!
  prefixSumCount = {0: 1, 1: 1, 3: 1, 6: 1, 10: 1, 15: 1}

  Why? prefix[4] - prefix[2] = 15 - 6 = 9
       Subarray [4, 5] has sum 9

Result: count = 2
Subarrays: [2, 3, 4] and [4, 5]
```

---

## Problems to Practice

### Beginner:
1. ✅ Range Sum Query - Immutable
2. ✅ Find Pivot Index
3. ✅ Running Sum of 1D Array
4. ✅ Find the Middle Index in Array

### Intermediate:
5. ✅ Subarray Sum Equals K
6. ✅ Continuous Subarray Sum
7. ✅ Product of Array Except Self
8. ✅ Contiguous Array (0s and 1s)
9. ✅ Maximum Subarray (Kadane's)
10. ✅ Corporate Flight Bookings

### Advanced:
11. ✅ Range Sum Query 2D - Immutable
12. ✅ Maximum Sum Circular Subarray
13. ✅ Subarray Sums Divisible by K
14. ✅ Count Number of Nice Subarrays
15. ✅ Number of Submatrices That Sum to Target

---

## Time Complexity Comparison

| Operation | Without Prefix | With Prefix |
|-----------|----------------|-------------|
| Preprocessing | - | O(n) |
| Single range query | O(n) | O(1) |
| Q queries | O(Q × n) | O(n + Q) |
| Array update | O(1) | O(n) rebuild |

---

## Space Complexity

| Technique | Space |
|-----------|-------|
| 1D Prefix Sum | O(n) |
| 2D Prefix Sum | O(m × n) |
| HashMap + Prefix | O(n) |
| Difference Array | O(n) |

---

## Common Mistakes

### ❌ Mistake 1: Off-by-one errors
```kotlin
// WRONG - index out of bounds
fun rangeSum(left: Int, right: Int): Int {
    return prefix[right] - prefix[left - 1]  // Error when left = 0!
}

// CORRECT - handle edge case
fun rangeSum(left: Int, right: Int): Int {
    return if (left == 0) {
        prefix[right]
    } else {
        prefix[right] - prefix[left - 1]
    }
}
```

### ❌ Mistake 2: Not initializing prefix[0]
```kotlin
// WRONG - prefix[0] not set
val prefix = IntArray(n)
for (i in 1 until n) {
    prefix[i] = prefix[i-1] + arr[i]
}

// CORRECT
val prefix = IntArray(n)
prefix[0] = arr[0]
for (i in 1 until n) {
    prefix[i] = prefix[i-1] + arr[i]
}
```

### ❌ Mistake 3: Forgetting to initialize HashMap with 0
```kotlin
// WRONG - missing base case
val prefixSumCount = mutableMapOf<Int, Int>()

// CORRECT - empty subarray has sum 0
val prefixSumCount = mutableMapOf<Int, Int>()
prefixSumCount[0] = 1
```

---

## Key Formulas

### 1D Range Sum:
```
sum(left, right) = prefix[right] - prefix[left - 1]
                 = prefix[right] - (if left == 0 then 0 else prefix[left-1])
```

### 2D Range Sum:
```
sum(r1, c1, r2, c2) = prefix[r2+1][c2+1]
                    - prefix[r1][c2+1]
                    - prefix[r2+1][c1]
                    + prefix[r1][c1]
```

### Subarray Sum Check:
```
If currentSum - k exists in previous prefix sums,
then there's a subarray with sum k
```

### Difference Array:
```
To add value to range [L, R]:
  diff[L] += value
  diff[R+1] -= value

To get result:
  result[i] = prefix_sum(diff[0..i])
```

---

## Summary

### When to Use Prefix Sum:

✅ **Multiple range queries** → O(1) per query after O(n) preprocessing
✅ **Subarray sum problems** → Use HashMap + Prefix Sum
✅ **2D matrix queries** → Build 2D prefix sum
✅ **Range updates** → Use difference array + prefix sum

### Key Insight:

**Prefix sum transforms O(n) range queries into O(1) lookups**

The trade-off:
- **Build time**: O(n)
- **Query time**: O(1)
- **Update time**: O(n) (need to rebuild)

For frequent updates, use **Segment Tree** or **Fenwick Tree** instead!

---

## Related Techniques

- **Kadane's Algorithm**: Running sum for max subarray
- **Two Pointers**: Combined with prefix sum for subarray problems
- **Sliding Window**: For fixed-size subarrays
- **Segment Tree**: When array has frequent updates
- **Fenwick Tree (BIT)**: Space-efficient alternative to Segment Tree

---

## Related Files in This Codebase

(Add your prefix sum implementation files here)

---

## Further Reading

- **Prefix Sum vs Segment Tree**: When to use which
- **Fenwick Tree (Binary Indexed Tree)**: More efficient for dynamic arrays
- **2D Prefix Sum Applications**: Image processing, matrix queries
- **Difference Array**: Efficient range update technique
