# Arrays in Kotlin

## Overview
Arrays in Kotlin are fixed-size collections that store elements of the same type. Understanding array operations is crucial for coding interviews.

## Creating Arrays

### IntArray (Primitive Array)
```kotlin
// Create with size
val arr = IntArray(5)  // [0, 0, 0, 0, 0]

// Create with values
val arr = intArrayOf(1, 2, 3, 4, 5)

// Create with lambda
val arr = IntArray(5) { it * 2 }  // [0, 2, 4, 6, 8]
```

### Array<T> (Generic Array)
```kotlin
// Create Array of Integers (boxed)
val arr = arrayOf(1, 2, 3, 4, 5)

// Create Array with nulls
val arr = arrayOfNulls<Int>(5)

// Create with lambda
val arr = Array(5) { it * 2 }
```

## Key Differences

| Feature | IntArray | Array<Int> |
|---------|----------|------------|
| Type | Primitive (int[]) | Boxed (Integer[]) |
| Performance | Faster | Slower |
| Memory | Less | More |
| Null values | No | Yes (Array<Int?>) |

## Common Operations

### Accessing Elements
```kotlin
val first = arr[0]
val last = arr[arr.size - 1]
val lastAlt = arr.last()

// Safe access
val element = arr.getOrNull(10)  // Returns null if out of bounds
val element = arr.getOrElse(10) { -1 }  // Returns default value
```

### Modifying Elements
```kotlin
arr[0] = 10
arr.set(1, 20)

// Fill array
arr.fill(0)  // Fill entire array with 0
arr.fill(5, 1, 3)  // Fill indices 1-2 with 5
```

### Searching
```kotlin
val index = arr.indexOf(3)
val lastIndex = arr.lastIndexOf(3)
val contains = arr.contains(3)
val contains = 3 in arr  // Same as above

// Binary search (array must be sorted)
val index = arr.binarySearch(5)
```

### Sorting
```kotlin
// Sort in-place
arr.sort()
arr.sortDescending()

// Sort and return new array
val sorted = arr.sorted()
val sortedDesc = arr.sortedDescending()

// Custom comparator
arr.sortWith(compareBy { it })
```

### Filtering
```kotlin
val filtered = arr.filter { it > 5 }
val evens = arr.filter { it % 2 == 0 }
val odds = arr.filterIndexed { index, value -> index % 2 == 0 }
```

### Transformation
```kotlin
val doubled = arr.map { it * 2 }
val indexed = arr.mapIndexed { index, value -> index to value }
val flattened = arrayOf(arr1, arr2).flatten()
```

### Aggregation
```kotlin
val sum = arr.sum()
val avg = arr.average()
val max = arr.maxOrNull()
val min = arr.minOrNull()
val count = arr.count { it > 5 }
```

### Iteration
```kotlin
// For loop
for (element in arr) {
    println(element)
}

// For loop with index
for (i in arr.indices) {
    println("arr[$i] = ${arr[i]}")
}

// forEach
arr.forEach { println(it) }
arr.forEachIndexed { index, value ->
    println("arr[$index] = $value")
}
```

## Common Interview Patterns

### Two Pointers
```kotlin
fun twoSum(arr: IntArray, target: Int): IntArray {
    var left = 0
    var right = arr.size - 1

    while (left < right) {
        val sum = arr[left] + arr[right]
        when {
            sum == target -> return intArrayOf(left, right)
            sum < target -> left++
            else -> right--
        }
    }
    return intArrayOf(-1, -1)
}
```

### Sliding Window
```kotlin
fun maxSubarraySum(arr: IntArray, k: Int): Int {
    var windowSum = arr.take(k).sum()
    var maxSum = windowSum

    for (i in k until arr.size) {
        windowSum = windowSum - arr[i - k] + arr[i]
        maxSum = maxOf(maxSum, windowSum)
    }
    return maxSum
}
```

### Array Rotation
```kotlin
fun rotateArray(arr: IntArray, k: Int) {
    val n = arr.size
    val rotate = k % n
    arr.reverse(0, n - rotate)
    arr.reverse(n - rotate, n)
    arr.reverse()
}

fun IntArray.reverse(from: Int = 0, to: Int = size) {
    var left = from
    var right = to - 1
    while (left < right) {
        val temp = this[left]
        this[left] = this[right]
        this[right] = temp
        left++
        right--
    }
}
```

## Performance Considerations

### Time Complexity
- Access by index: O(1)
- Search (unsorted): O(n)
- Search (sorted, binary): O(log n)
- Insert/Delete: O(n) - requires shifting
- Sort: O(n log n)

### Space Complexity
- Fixed size: O(n)
- Operations creating new arrays: O(n) additional space

## Interview Tips

1. **Always check bounds** before accessing elements
2. **Use IntArray** for primitive types to save memory
3. **Remember arrays are 0-indexed** in Kotlin
4. **Arrays are mutable** but fixed-size
5. **Use `contentEquals()`** for array comparison, not `==`
6. **Use `contentToString()`** for printing arrays

## Common Pitfalls

```kotlin
// ❌ Wrong - compares references
arr1 == arr2

// ✅ Correct - compares content
arr1.contentEquals(arr2)

// ❌ Wrong - doesn't show content
println(arr)  // Prints: [I@15db9742

// ✅ Correct - shows array content
println(arr.contentToString())  // Prints: [1, 2, 3, 4, 5]
```

## Additional Resources
- [Kotlin Collections Overview](https://kotlinlang.org/docs/collections-overview.html)
- [Array API Reference](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/)
