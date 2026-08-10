# Special Notes & Interview Tips

## Kotlin-Specific Tips

### 1. Use Built-in Functions
```kotlin
// ❌ Verbose
var sum = 0
for (num in nums) {
    sum += num
}

// ✅ Idiomatic
val sum = nums.sum()
```

### 2. Destructuring
```kotlin
// For pairs/maps
for ((key, value) in map) {
    println("$key -> $value")
}

// For indices
for ((index, value) in list.withIndex()) {
    println("list[$index] = $value")
}
```

### 3. Safe Calls
```kotlin
// ❌ Null check
if (node != null) {
    process(node.value)
}

// ✅ Safe call
node?.let { process(it.value) }
```

### 4. Elvis Operator
```kotlin
val value = map[key] ?: 0  // Default value
val length = str?.length ?: 0
```

### 5. When Expression (Better than if-else)
```kotlin
when {
    x > 0 -> "positive"
    x < 0 -> "negative"
    else -> "zero"
}
```

## Common Pitfalls

### 1. Integer Overflow
```kotlin
// ❌ Can overflow
val sum = a + b  // If a, b are Int

// ✅ Use Long
val sum = a.toLong() + b.toLong()
```

### 2. Array Comparison
```kotlin
// ❌ Wrong - compares references
arr1 == arr2

// ✅ Correct - compares content
arr1.contentEquals(arr2)
```

### 3. String Concatenation in Loop
```kotlin
// ❌ Inefficient - creates many strings
var result = ""
for (i in 1..1000) {
    result += i
}

// ✅ Efficient - use StringBuilder
val sb = StringBuilder()
for (i in 1..1000) {
    sb.append(i)
}
val result = sb.toString()
```

### 4. Modifying Collection While Iterating
```kotlin
// ❌ ConcurrentModificationException
for (item in list) {
    if (shouldRemove(item)) {
        list.remove(item)  // Error!
    }
}

// ✅ Use iterator or create new list
list.removeIf { shouldRemove(it) }
// OR
val filtered = list.filter { !shouldRemove(it) }
```

### 5. Division by Zero
```kotlin
// Always check
if (divisor != 0) {
    val result = dividend / divisor
}
```

## Edge Cases Checklist

### Arrays/Lists
- [ ] Empty array
- [ ] Single element
- [ ] All elements same
- [ ] Already sorted
- [ ] Reverse sorted
- [ ] Duplicates
- [ ] Negative numbers
- [ ] Very large numbers (overflow)

### Strings
- [ ] Empty string
- [ ] Single character
- [ ] All same characters
- [ ] Special characters
- [ ] Spaces
- [ ] Case sensitivity

### Trees
- [ ] Empty tree (null)
- [ ] Single node
- [ ] Skewed tree (like linked list)
- [ ] All left/right children
- [ ] Balanced vs unbalanced

### Linked Lists
- [ ] Empty list (null)
- [ ] Single node
- [ ] Two nodes
- [ ] Cycle

### Numbers
- [ ] Zero
- [ ] Negative
- [ ] Very large (overflow)
- [ ] Prime numbers
- [ ] Perfect squares

## Problem-Solving Framework

### 1. Understand (5 min)
- [ ] Read problem carefully
- [ ] Identify inputs and outputs
- [ ] Ask clarifying questions
- [ ] Understand constraints
- [ ] Walk through examples

### 2. Plan (10 min)
- [ ] Think of brute force first
- [ ] Identify pattern
- [ ] Choose data structure
- [ ] Optimize approach
- [ ] Calculate complexity

### 3. Code (20 min)
- [ ] Write clean code
- [ ] Use meaningful names
- [ ] Add comments for complex logic
- [ ] Handle edge cases
- [ ] Modularize if needed

### 4. Test (5 min)
- [ ] Walk through with example
- [ ] Test edge cases
- [ ] Check for bugs
- [ ] Verify complexity

## Time Complexity Quick Reference

```
n = 100,000

O(1)        - Instant
O(log n)    - ~17 operations
O(n)        - 100,000 operations
O(n log n)  - ~1,700,000 operations
O(n²)       - 10,000,000,000 operations (too slow!)
O(2ⁿ)       - Only works for n ≤ 20
O(n!)       - Only works for n ≤ 10
```

### Choosing Algorithm by Constraints

| n ≤ | Complexity |
|-----|-----------|
| 10 | O(n!), O(2ⁿ) |
| 20 | O(2ⁿ), O(n³) |
| 100 | O(n³) |
| 1,000 | O(n²) |
| 100,000 | O(n log n) |
| 1,000,000 | O(n) or O(log n) |

## Common Optimization Techniques

### 1. Use HashMap for O(1) Lookup
```kotlin
// Instead of nested loops
val seen = mutableSetOf<Int>()
for (num in nums) {
    if (target - num in seen) return true
    seen.add(num)
}
```

### 2. Two Pointers to Avoid Nested Loops
```kotlin
// O(n) instead of O(n²)
var left = 0
var right = arr.size - 1
```

### 3. Sliding Window for Subarrays
```kotlin
// O(n) instead of O(n²)
var windowSum = arr.take(k).sum()
```

### 4. Binary Search on Sorted Data
```kotlin
// O(log n) instead of O(n)
arr.binarySearch(target)
```

### 5. DP for Overlapping Subproblems
```kotlin
// O(n) instead of O(2ⁿ)
val memo = mutableMapOf<Int, Int>()
```

## Kotlin Interview Gotchas

### 1. IntArray vs Array<Int>
```kotlin
IntArray(5)      // Primitive int[] - faster
Array<Int>(5)    // Boxed Integer[] - slower
```

### 2. List vs MutableList
```kotlin
val list = listOf(1, 2, 3)        // Immutable
val mList = mutableListOf(1, 2, 3) // Mutable
```

### 3. Let vs Also vs Apply vs Run
```kotlin
obj?.let { it.doSomething() }       // Returns result
obj?.also { it.doSomething() }      // Returns obj
obj?.apply { doSomething() }        // Returns obj, this = obj
obj?.run { doSomething() }          // Returns result, this = obj
```

### 4. Ranges
```kotlin
1..10        // Inclusive: 1 to 10
1 until 10   // Exclusive: 1 to 9
10 downTo 1  // Descending: 10 to 1
1..10 step 2 // Skip: 1, 3, 5, 7, 9
```

## Interview Communication Tips

### Before Coding
1. "Let me make sure I understand the problem..."
2. "Can I assume...?"
3. "What are the constraints on...?"
4. "Should I optimize for time or space?"

### While Coding
1. "I'm using a HashMap here because..."
2. "This will handle the edge case of..."
3. "The time complexity of this approach is..."
4. "Let me add a comment to explain..."

### After Coding
1. "Let me walk through an example..."
2. "For edge cases, we handle..."
3. "The time complexity is O(n) because..."
4. "We could optimize space by..."

## Final Checklist Before Submitting

- [ ] Code compiles/runs
- [ ] Handles all edge cases
- [ ] No syntax errors
- [ ] Variable names are clear
- [ ] Complexity is optimal
- [ ] Explained approach
- [ ] Tested with examples
- [ ] Confident in solution

## Red Flags to Avoid

1. **Silent coding** - Always explain your thinking
2. **Jumping to code** - Plan first
3. **Ignoring hints** - Listen to interviewer
4. **Giving up** - Keep trying different approaches
5. **Defensive behavior** - Accept feedback gracefully
6. **Not asking questions** - Clarify requirements
7. **Skipping edge cases** - Always consider them
8. **Poor variable names** - Use meaningful names

## Remember

> "Code is read more often than it is written"

- Write clean, readable code
- Comment complex logic
- Test thoroughly
- Communicate clearly
- Stay calm and confident

Good luck with your interviews! 🚀
