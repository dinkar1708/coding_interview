# IntArray Reference Behavior in Kotlin

## Overview
Understanding how arrays behave with references is crucial to avoid bugs in your code, especially during coding interviews where you might manipulate arrays in-place.

## Arrays are Reference Types

```kotlin
// Creating an IntArray
val arr1 = intArrayOf(1, 2, 3, 4, 5)

// This creates a reference, NOT a copy
val arr2 = arr1

// Modifying arr2 also modifies arr1!
arr2[0] = 99
println(arr1.contentToString())  // [99, 2, 3, 4, 5]
println(arr2.contentToString())  // [99, 2, 3, 4, 5]
```

## Copying Arrays

### copyOf() - Creates a Deep Copy
```kotlin
val arr1 = intArrayOf(1, 2, 3, 4, 5)
val arr2 = arr1.copyOf()

arr2[0] = 99
println(arr1.contentToString())  // [1, 2, 3, 4, 5] - unchanged
println(arr2.contentToString())  // [99, 2, 3, 4, 5]
```

### copyOfRange() - Copy a Portion
```kotlin
val arr1 = intArrayOf(1, 2, 3, 4, 5)
val arr2 = arr1.copyOfRange(1, 4)  // Copies indices 1 to 3

println(arr2.contentToString())  // [2, 3, 4]
```

### clone() - Creates a Shallow Copy
```kotlin
val arr1 = intArrayOf(1, 2, 3, 4, 5)
val arr2 = arr1.clone()

arr2[0] = 99
println(arr1.contentToString())  // [1, 2, 3, 4, 5]
println(arr2.contentToString())  // [99, 2, 3, 4, 5]
```

## Function Parameters

### Arrays are Passed by Reference
```kotlin
fun modifyArray(arr: IntArray) {
    arr[0] = 999
}

val myArray = intArrayOf(1, 2, 3)
modifyArray(myArray)
println(myArray.contentToString())  // [999, 2, 3] - MODIFIED!
```

### Protecting Original Array
```kotlin
fun safeFunction(arr: IntArray) {
    val copy = arr.copyOf()
    // Work with copy instead
    copy[0] = 999
}

val myArray = intArrayOf(1, 2, 3)
safeFunction(myArray)
println(myArray.contentToString())  // [1, 2, 3] - unchanged
```

## Comparison Pitfalls

### ❌ Wrong: Using ==
```kotlin
val arr1 = intArrayOf(1, 2, 3)
val arr2 = intArrayOf(1, 2, 3)

println(arr1 == arr2)  // false - compares references!
```

### ✅ Correct: Using contentEquals()
```kotlin
val arr1 = intArrayOf(1, 2, 3)
val arr2 = intArrayOf(1, 2, 3)

println(arr1.contentEquals(arr2))  // true - compares content
```

## Printing Arrays

### ❌ Wrong: Direct println
```kotlin
val arr = intArrayOf(1, 2, 3)
println(arr)  // [I@15db9742 - prints object reference!
```

### ✅ Correct: Using contentToString()
```kotlin
val arr = intArrayOf(1, 2, 3)
println(arr.contentToString())  // [1, 2, 3]
```

## 2D Arrays - Reference Complexity

### Shallow vs Deep Copy
```kotlin
// 2D array
val matrix = arrayOf(
    intArrayOf(1, 2, 3),
    intArrayOf(4, 5, 6)
)

// ❌ Shallow copy - only copies outer array
val copy1 = matrix.copyOf()
copy1[0][0] = 99
println(matrix[0].contentToString())  // [99, 2, 3] - MODIFIED!

// ✅ Deep copy - copies all inner arrays too
val copy2 = Array(matrix.size) { matrix[it].copyOf() }
copy2[0][0] = 88
println(matrix[0].contentToString())  // [99, 2, 3] - unchanged
```

## Common Interview Scenarios

### Scenario 1: Modifying Array In-Place
```kotlin
fun reverseArray(arr: IntArray) {
    var left = 0
    var right = arr.size - 1

    while (left < right) {
        val temp = arr[left]
        arr[left] = arr[right]
        arr[right] = temp
        left++
        right--
    }
}

// This modifies the original array
val nums = intArrayOf(1, 2, 3, 4, 5)
reverseArray(nums)
println(nums.contentToString())  // [5, 4, 3, 2, 1]
```

### Scenario 2: Returning Modified Copy
```kotlin
fun getReversed(arr: IntArray): IntArray {
    return arr.reversedArray()  // Creates new array
}

val nums = intArrayOf(1, 2, 3, 4, 5)
val reversed = getReversed(nums)
println(nums.contentToString())      // [1, 2, 3, 4, 5] - unchanged
println(reversed.contentToString())  // [5, 4, 3, 2, 1]
```

### Scenario 3: Multiple References
```kotlin
val arr = intArrayOf(1, 2, 3)
val ref1 = arr
val ref2 = arr
val copy = arr.copyOf()

ref1[0] = 99

println(arr.contentToString())   // [99, 2, 3]
println(ref1.contentToString())  // [99, 2, 3]
println(ref2.contentToString())  // [99, 2, 3]
println(copy.contentToString())  // [1, 2, 3] - only copy is unchanged
```

## Best Practices

### 1. Document In-Place Modifications
```kotlin
/**
 * Rotates the array in-place.
 * @param arr The array to rotate (will be modified)
 */
fun rotateInPlace(arr: IntArray, k: Int) {
    // Implementation
}
```

### 2. Make Defensive Copies When Needed
```kotlin
class DataProcessor {
    private var data: IntArray = intArrayOf()

    fun setData(newData: IntArray) {
        data = newData.copyOf()  // Defensive copy
    }

    fun getData(): IntArray {
        return data.copyOf()  // Return copy, not reference
    }
}
```

### 3. Use toList() for Immutability
```kotlin
fun processData(arr: IntArray): List<Int> {
    return arr.toList()  // Returns immutable list
}
```

## Memory Implications

```kotlin
// Large array
val large = IntArray(1_000_000) { it }

// Reference - O(1) space, instant
val ref = large  // Just a reference

// Copy - O(n) space, O(n) time
val copy = large.copyOf()  // Full copy

// Consider memory when deciding between reference and copy
```

## Interview Tips

1. **Always clarify**: Can I modify the input array in-place?
2. **Default assumption**: Modify in-place unless stated otherwise (saves space)
3. **Test with references**: After modifying, check if original changed
4. **Use contentEquals()**: For array comparison in tests
5. **Be explicit**: Make it clear when you're creating copies

## Common Mistakes

```kotlin
// Mistake 1: Thinking = creates a copy
val arr1 = intArrayOf(1, 2, 3)
val arr2 = arr1  // ❌ Reference, not copy
arr2[0] = 99     // arr1 also changed!

// Mistake 2: Returning internal array directly
class Container {
    private val data = intArrayOf(1, 2, 3)

    fun getData(): IntArray {
        return data  // ❌ Exposes internal state
    }
}

// Caller can modify internal state:
val container = Container()
container.getData()[0] = 999  // Internal data corrupted!

// Mistake 3: Shallow copy of 2D array
val matrix = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
val copy = matrix.copyOf()  // ❌ Shallow copy
copy[0][0] = 99  // Original matrix also modified!
```

## Summary

| Operation | Creates Copy? | Modifies Original? |
|-----------|---------------|-------------------|
| `val b = a` | No | If you modify b |
| `a.copyOf()` | Yes (1D) | No |
| `a.clone()` | Yes (1D) | No |
| `a.reversedArray()` | Yes | No |
| `a.reverse()` | No | Yes |
| `2D.copyOf()` | Shallow only | Yes (inner arrays) |

Remember: **In Kotlin, arrays are always reference types!**
