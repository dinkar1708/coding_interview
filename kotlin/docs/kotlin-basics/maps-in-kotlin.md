# Maps in Kotlin

## Overview
Maps are key-value pair collections. Understanding map operations is crucial for solving hash-based problems in interviews.

## Creating Maps

### HashMap
```kotlin
// Mutable HashMap
val map = mutableMapOf<String, Int>()
val map = hashMapOf("a" to 1, "b" to 2)

// Immutable Map
val map = mapOf("a" to 1, "b" to 2, "c" to 3)
```

### LinkedHashMap (Maintains Insertion Order)
```kotlin
val map = linkedMapOf("a" to 1, "b" to 2, "c" to 3)
```

### TreeMap (Sorted by Keys)
```kotlin
val map = sortedMapOf("c" to 3, "a" to 1, "b" to 2)
// Iteration order: a, b, c
```

## Basic Operations

### Adding/Updating Elements
```kotlin
map["key"] = value
map.put("key", value)
map.putIfAbsent("key", value)

// Add multiple
map.putAll(mapOf("d" to 4, "e" to 5))
```

### Accessing Elements
```kotlin
val value = map["key"]  // Returns null if not found
val value = map.get("key")
val value = map.getOrDefault("key", 0)
val value = map.getOrElse("key") { 0 }
val value = map.getValue("key")  // Throws exception if not found
```

### Removing Elements
```kotlin
map.remove("key")
map.remove("key", value)  // Removes only if key maps to value
map.clear()  // Remove all
```

### Checking Existence
```kotlin
val hasKey = map.containsKey("key")
val hasKey = "key" in map
val hasValue = map.containsValue(10)
val isEmpty = map.isEmpty()
val isNotEmpty = map.isNotEmpty()
```

## Iteration

### Iterate Over Entries
```kotlin
for ((key, value) in map) {
    println("$key -> $value")
}

map.forEach { (key, value) ->
    println("$key -> $value")
}

map.forEach { entry ->
    println("${entry.key} -> ${entry.value}")
}
```

### Iterate Over Keys
```kotlin
for (key in map.keys) {
    println(key)
}

map.keys.forEach { println(it) }
```

### Iterate Over Values
```kotlin
for (value in map.values) {
    println(value)
}

map.values.forEach { println(it) }
```

## Common Interview Patterns

### Frequency Counter
```kotlin
fun charFrequency(s: String): Map<Char, Int> {
    val freq = mutableMapOf<Char, Int>()
    for (char in s) {
        freq[char] = freq.getOrDefault(char, 0) + 1
    }
    return freq
}

// Using groupingBy
fun charFrequency(s: String): Map<Char, Int> {
    return s.groupingBy { it }.eachCount()
}
```

### Two Sum Problem
```kotlin
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()  // value to index

    for (i in nums.indices) {
        val complement = target - nums[i]
        if (complement in map) {
            return intArrayOf(map[complement]!!, i)
        }
        map[nums[i]] = i
    }

    return intArrayOf(-1, -1)
}
```

### Group Anagrams
```kotlin
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()

    for (str in strs) {
        val key = str.toCharArray().sorted().joinToString("")
        map.getOrPut(key) { mutableListOf() }.add(str)
    }

    return map.values.toList()
}
```

### First Non-Repeating Character
```kotlin
fun firstUniqChar(s: String): Int {
    val freq = mutableMapOf<Char, Int>()

    // Count frequencies
    for (char in s) {
        freq[char] = freq.getOrDefault(char, 0) + 1
    }

    // Find first with frequency 1
    for (i in s.indices) {
        if (freq[s[i]] == 1) return i
    }

    return -1
}
```

### Subarray Sum Equals K
```kotlin
fun subarraySum(nums: IntArray, k: Int): Int {
    val map = mutableMapOf<Int, Int>()  // prefix sum to frequency
    map[0] = 1

    var sum = 0
    var count = 0

    for (num in nums) {
        sum += num
        count += map.getOrDefault(sum - k, 0)
        map[sum] = map.getOrDefault(sum, 0) + 1
    }

    return count
}
```

## Advanced Operations

### Transformations
```kotlin
// Map values
val doubled = map.mapValues { it.value * 2 }

// Map keys
val upperKeys = map.mapKeys { it.key.uppercase() }

// Filter
val filtered = map.filter { it.value > 10 }
val filtered = map.filterKeys { it.startsWith("a") }
val filtered = map.filterValues { it > 10 }
```

### Merging Maps
```kotlin
val map1 = mapOf("a" to 1, "b" to 2)
val map2 = mapOf("b" to 3, "c" to 4)

// Simple merge (map2 overwrites map1)
val merged = map1 + map2  // {a=1, b=3, c=4}

// Custom merge logic
val merged = (map1.keys + map2.keys).associateWith { key ->
    (map1[key] ?: 0) + (map2[key] ?: 0)
}  // {a=1, b=5, c=4}
```

### Compute Methods
```kotlin
// Compute new value
map.compute("key") { key, value ->
    (value ?: 0) + 1
}

// Compute if absent
map.computeIfAbsent("key") { 0 }

// Compute if present
map.computeIfPresent("key") { key, value ->
    value + 1
}

// Merge
map.merge("key", 1) { oldValue, newValue ->
    oldValue + newValue
}
```

## getOrPut Pattern

```kotlin
// Common pattern
if (key !in map) {
    map[key] = expensiveComputation()
}
val value = map[key]

// Better with getOrPut
val value = map.getOrPut(key) { expensiveComputation() }

// Example: Group by
val groups = mutableMapOf<String, MutableList<String>>()
for (item in items) {
    val key = extractKey(item)
    groups.getOrPut(key) { mutableListOf() }.add(item)
}
```

## Performance Considerations

### Time Complexity
| Operation | HashMap | LinkedHashMap | TreeMap |
|-----------|---------|---------------|---------|
| get/put | O(1) | O(1) | O(log n) |
| remove | O(1) | O(1) | O(log n) |
| containsKey | O(1) | O(1) | O(log n) |
| iteration | O(n) | O(n) ordered | O(n) sorted |

### Space Complexity
- HashMap: O(n)
- LinkedHashMap: O(n) + overhead for ordering
- TreeMap: O(n) + overhead for tree structure

## When to Use Which Map

### HashMap
- Need fast lookups
- Don't care about order
- Most common choice

### LinkedHashMap
- Need insertion order preserved
- Predictable iteration order
- Ordered processing requirements

### TreeMap
- Need sorted keys
- Range queries (subMap, headMap, tailMap)
- Natural ordering required

## Interview Tips

1. **Use getOrDefault()** instead of null checks
2. **Use getOrPut()** for lazy initialization
3. **HashMap is O(1)** average case, but can degrade to O(n) with hash collisions
4. **Consider frequency maps** for counting problems
5. **Maps can solve many problems** that seem to require nested loops

## Common Pitfalls

```kotlin
// ❌ Wrong - can throw NullPointerException
val value = map["key"]!!

// ✅ Correct - safe access
val value = map.getOrDefault("key", 0)

// ❌ Wrong - verbose
if (key !in map) {
    map[key] = mutableListOf()
}
map[key]!!.add(value)

// ✅ Correct - concise
map.getOrPut(key) { mutableListOf() }.add(value)
```
