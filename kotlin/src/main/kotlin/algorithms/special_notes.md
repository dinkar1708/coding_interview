# Special Notes - Frequently Used in Problem Solving

## Int.MIN_VALUE & Int.MAX_VALUE
```kotlin
// Useful for comparisons (find min/max)
var maxProduct = Int.MIN_VALUE  // -2147483648 (start with smallest for finding max)
var minProduct = Int.MAX_VALUE  //  2147483647 (start with largest for finding min)
```

## pow() Function
```kotlin
// pow() works with Double only, convert for Int
fun powerOfTwo(n: Int): Int = 2.0.pow(n.toDouble()).toInt()
```

## Map Methods
```kotlin
// getOrDefault - returns default if key not found
map.getOrDefault("key", 0)

// getOrPut - inserts default if key missing, then returns it
map.getOrPut("key") { 0 }

// Common pattern: counting
map[ch] = map.getOrDefault(ch, 0) + 1
```

## Character Arithmetic (Array Index Mapping)
```kotlin
// Lowercase letters (a-z) → Index 0-25
val ch = 'b'
val index = ch - 'a'  // Automatically returns Int = 1
// 'a'->0, 'b'->1, 'c'->2, ..., 'z'->25

// Usage: Character counting
val count = IntArray(26)
for (ch in string) {
    count[ch - 'a']++  // Direct array indexing
}

// Uppercase letters (A-Z) → Index 0-25
val index = ch - 'A'  // 'A'->0, 'B'->1, ..., 'Z'->25

// ASCII characters
val index = ch.code  // Use ASCII value directly (0-127)
```

## Array-Based Counting (Instead of HashMap)
```kotlin
// When to use Array instead of HashMap:
// ✓ Small, known range (0-9, a-z, ASCII)
// ✓ Dense data (many values in small range)
// ✓ Need O(1) space (fixed size)
// ✓ Maximum performance

// Example 1: Count distinct numbers in range 0-9
val seen = IntArray(10)
for (num in array) {
    if (num >= 0 && num < 10) {
        seen[num] = 1  // Mark as seen
    }
}
val distinctCount = seen.count { it == 1 }

// Example 2: With offset for negative numbers (-100 to 100)
val range = maxValue - minValue + 1  // 201
val seen = IntArray(range)
for (num in array) {
    val index = num - minValue  // -100->0, 0->100, 100->200
    seen[index] = 1
}
```

## Sorting in Kotlin

### Array Sorting
```kotlin
// IntArray (primitives) - Dual-Pivot Quicksort (in-place)
val arr = intArrayOf(3, 1, 4, 1, 5)
arr.sort()  // Modifies in-place, O(n log n) time, O(log n) space

// Array<Int> (objects) - TimSort (hybrid merge+insertion)
val arr = arrayOf(3, 1, 4, 1, 5)
arr.sort()  // Modifies in-place, O(n log n) time, O(n) space

// Custom comparator
arr.sortWith(compareBy { it })
```

### String Sorting
```kotlin
// String is IMMUTABLE - cannot sort in-place
// No library method to sort String directly
val str = "dcba"

// Option 1: Convert to CharArray, use library sort
val chars = str.toCharArray()
chars.sort()  // Library sort (Dual-Pivot Quicksort)
val sorted = String(chars)  // "abcd"

// Option 2: Convert to CharArray, do manual sort
val chars = str.toCharArray()
// ... do bubble sort, selection sort, etc. manually
val sorted = String(chars)
```

### Algorithm Details
| Type | Algorithm | Time | Space | Stable? |
|------|-----------|------|-------|---------|
| IntArray, DoubleArray | Dual-Pivot Quicksort | O(n log n) | O(log n) | No |
| Array<T>, List<T> | TimSort | O(n log n) | O(n) | Yes |
| String | Must convert to CharArray first | - | - | - |

**Why different algorithms?**
- Primitives: Speed optimized (in-place quicksort)
- Objects: Stability matters (preserve equal element order)

## Size/Length Properties
```kotlin
// String size
string.length  // Property, not a function

// Array size
array.size     // Kotlin uses .size
array.length   // Also works (Java compatibility)

// List size
list.size      // Property
```

## Common Complexity Patterns

### Memoization Space Complexity
```kotlin
// Space = O(number of unique states)
// 1 variable (n) → O(n)
fun fib(n: Int, memo: IntArray): Int

// 2 variables (n, sum) → O(n × sum)
fun coinChange(n: Int, sum: Int, memo: Array<IntArray>): Int

// 3 variables → O(n × m × k)
fun dp(i: Int, j: Int, k: Int, memo: Array<Array<IntArray>>): Int
```

### Space Optimization - Sliding Window
```kotlin
// Instead of full DP array: dp = [6, 7, 7, 10, 15, 15, 19]
// Keep only last 2 values: dpLastToLast, dpLast
// Reduces O(n) → O(1)

var dpLastToLast = A[0]
var dpLast = maxOf(A[1], dpLastToLast)

for (i in 2 until n) {
    val temp = dpLast
    dpLast = maxOf(A[i] + dpLastToLast, dpLast)
    dpLastToLast = temp
}
```

## HashSet Internal Structure
```kotlin
// How Set achieves O(1) lookup:
// 1. Hash function: value → hash code
// 2. Array of buckets (linked lists)
// 3. hash code % array.size → bucket index
// 4. Store value in that bucket
// 5. Collisions handled by linked list

val set = mutableSetOf<Int>()
set.add(5)  // O(1) average
set.contains(5)  // O(1) average
```

## Binary Search Base Cases
```kotlin
fun binarySearch(search: Int, a: IntArray, low: Int, high: Int): Boolean {
    // BASE CASE: Search space is empty
    if (low > high) {
        return false  // No elements left to search
    }

    val mid = (low + high) / 2

    if (search == a[mid]) {
        return true
    } else if (search < a[mid]) {
        return binarySearch(search, a, low, mid - 1)  // Go left
    } else {
        return binarySearch(search, a, mid + 1, high)  // Go right
    }
}
```

---

