# HashMap Patterns for Problem Solving

## What is HashMap Pattern?

Using **HashMap** (or HashSet) to store:
- **Key → Count**: Track frequency of elements
- **Key → Boolean**: Track existence/seen status
- **Key → Index**: Track position of elements
- **Key → Value**: Store computed results

**Time Complexity**: O(1) average for lookup/insert
**Space Complexity**: O(n) for storing entries

---

## Why Use HashMap?

### Without HashMap:
```kotlin
// Check if duplicate exists - O(n²)
fun hasDuplicate(nums: IntArray): Boolean {
    for (i in nums.indices) {
        for (j in i+1 until nums.size) {
            if (nums[i] == nums[j]) return true
        }
    }
    return false
}
```

### With HashMap:
```kotlin
// Check if duplicate exists - O(n)
fun hasDuplicate(nums: IntArray): Boolean {
    val seen = mutableSetOf<Int>()
    for (num in nums) {
        if (num in seen) return true
        seen.add(num)
    }
    return false
}
```

**Key Benefit**: Reduces O(n²) brute force to O(n) using O(n) extra space

---

## HashMap Variants

### HashMap (Default) - Unordered
```kotlin
val map = mutableMapOf<K, V>()  // Random iteration order
// Use when: Order doesn't matter, need maximum speed
```

### LinkedHashMap - Insertion Order
```kotlin
val map = linkedMapOf<K, V>()  // Maintains insertion order
// Use when: Need predictable iteration order
// Examples: First unique character, ordered processing
// See: algorithms/basics/LinkedHashMapExample.kt for detailed examples
```

### TreeMap - Sorted Order
```kotlin
val map = sortedMapOf<K, V>()  // Maintains sorted order by key
// Use when: Need sorted keys
// Time: O(log n) for operations (slower than HashMap)
```

**Comparison**:
| Type | Order | Time | Use Case |
|------|-------|------|----------|
| HashMap | Random | O(1) | Default (fastest) |
| LinkedHashMap | Insertion | O(1) | Preserve order |
| TreeMap | Sorted | O(log n) | Need sorted keys |

**Detailed Guide**: See `docs/special_notes.md` → "HashMap vs LinkedHashMap vs TreeMap"

---

## Pattern Categories

### 1. Key → Boolean (Existence Check)
### 2. Key → Count (Frequency Tracking)
### 3. Key → Index (Position Tracking)
### 4. Key → Value (Memoization/Cache)
### 5. Two HashMaps (Bidirectional Mapping)

---

## Pattern 1: Key → Boolean (Existence/Seen)

**Use Case**: Track whether element has been seen before

### Use HashSet When:
- Only need to check **existence** (true/false)
- Don't care about **count** or **order**

### Template:
```kotlin
val seen = mutableSetOf<Int>()

for (element in array) {
    if (element in seen) {
        // Element seen before - handle duplicate
    }
    seen.add(element)
}
```

---

### Problem Type 1A: Contains Duplicate

**Goal**: Check if any value appears twice

```kotlin
fun containsDuplicate(nums: IntArray): Boolean {
    val seen = mutableSetOf<Int>()

    for (num in nums) {
        if (num in seen) return true
        seen.add(num)
    }

    return false
}
```

**Examples**:
- Contains Duplicate
- Unique Email Addresses
- Happy Number (detect cycle)

---

### Problem Type 1B: Two Sum

**Goal**: Find two numbers that sum to target

```kotlin
fun twoSum(nums: IntArray, target: Int): IntArray {
    val seen = mutableMapOf<Int, Int>()  // value → index

    for (i in nums.indices) {
        val complement = target - nums[i]

        if (complement in seen) {
            return intArrayOf(seen[complement]!!, i)
        }

        seen[nums[i]] = i
    }

    return intArrayOf()
}
```

**How it works**:
```
Array: [2, 7, 11, 15], target = 9

i=0, num=2:
  complement = 9 - 2 = 7
  7 not in seen
  seen = {2: 0}

i=1, num=7:
  complement = 9 - 7 = 2
  2 in seen! ✓
  return [0, 1]
```

**Examples**:
- Two Sum
- Two Sum II (sorted array)
- Two Sum III (data structure design)

---

### Problem Type 1C: Valid Anagram

**Goal**: Check if two strings are anagrams

```kotlin
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val count = mutableMapOf<Char, Int>()

    // Count characters in s
    for (ch in s) {
        count[ch] = count.getOrDefault(ch, 0) + 1
    }

    // Subtract characters from t
    for (ch in t) {
        val currentCount = count.getOrDefault(ch, 0)
        if (currentCount == 0) return false
        count[ch] = currentCount - 1
    }

    return true
}
```

**Examples**:
- Valid Anagram
- Group Anagrams
- Find All Anagrams in a String

---

## Pattern 2: Key → Count (Frequency Tracking)

**Use Case**: Count occurrences of each element

### Use HashMap<Key, Count> When:
- Need to know **how many times** each element appears
- Find most/least frequent elements
- Track frequency for matching/validation

### Template:
```kotlin
val frequencyMap = mutableMapOf<Int, Int>()

for (element in array) {
    frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
}

// Or using merge
for (element in array) {
    frequencyMap.merge(element, 1, Int::plus)
}
```

---

### Problem Type 2A: Frequency Counter

**Goal**: Count occurrences of each element

```kotlin
fun frequencyCount(nums: IntArray): Map<Int, Int> {
    val freq = mutableMapOf<Int, Int>()

    for (num in nums) {
        freq[num] = freq.getOrDefault(num, 0) + 1
    }

    return freq
}
```

**Example**:
```
Input: [1, 2, 2, 3, 3, 3]
Output: {1=1, 2=2, 3=3}
```

**Examples**:
- Top K Frequent Elements
- Sort Characters By Frequency
- First Unique Character in a String

---

### Problem Type 2B: Majority Element

**Goal**: Find element appearing more than n/2 times

```kotlin
fun majorityElement(nums: IntArray): Int {
    val count = mutableMapOf<Int, Int>()
    val majority = nums.size / 2

    for (num in nums) {
        count[num] = count.getOrDefault(num, 0) + 1
        if (count[num]!! > majority) {
            return num
        }
    }

    return -1  // No majority element
}
```

**Examples**:
- Majority Element
- Majority Element II
- Check If Array Pairs Are Divisible by k

---

### Problem Type 2C: Subarray Sum Equals K (with count)

**Goal**: Count subarrays with sum = k

```kotlin
fun subarraySum(nums: IntArray, k: Int): Int {
    val prefixSumCount = mutableMapOf<Int, Int>()
    prefixSumCount[0] = 1  // Empty subarray

    var currentSum = 0
    var count = 0

    for (num in nums) {
        currentSum += num

        // Check if (currentSum - k) exists
        val target = currentSum - k
        count += prefixSumCount.getOrDefault(target, 0)

        // Store current prefix sum count
        prefixSumCount[currentSum] =
            prefixSumCount.getOrDefault(currentSum, 0) + 1
    }

    return count
}
```

**Why count matters**:
```
Array: [1, 1, 1], k = 2

Prefix sums: 0 → 1 → 2 → 3

At index 2 (sum=3):
  target = 3 - 2 = 1
  prefixSumCount[1] = 2 (appeared twice!)

  Two subarrays with sum 2:
  - [1, 1] from index 0-1
  - [1, 1] from index 1-2
```

**Examples**:
- Subarray Sum Equals K
- Contiguous Array
- Count Number of Nice Subarrays

---

## Pattern 3: Key → Index (Position Tracking)

**Use Case**: Remember where element was last seen

### Use HashMap<Element, Index> When:
- Need to find **distance** between elements
- Track **first/last occurrence**
- Avoid duplicates within range

### Template:
```kotlin
val indexMap = mutableMapOf<Int, Int>()

for (i in array.indices) {
    val element = array[i]

    if (element in indexMap) {
        val lastIndex = indexMap[element]!!
        // Calculate distance: i - lastIndex
    }

    indexMap[element] = i  // Update position
}
```

---

### Problem Type 3A: Contains Duplicate II

**Goal**: Check if duplicate exists within k distance

```kotlin
fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val indexMap = mutableMapOf<Int, Int>()

    for (i in nums.indices) {
        if (nums[i] in indexMap) {
            val lastIndex = indexMap[nums[i]]!!
            if (i - lastIndex <= k) {
                return true
            }
        }
        indexMap[nums[i]] = i
    }

    return false
}
```

**Example**:
```
nums = [1, 2, 3, 1], k = 3

i=0: indexMap = {1: 0}
i=1: indexMap = {1: 0, 2: 1}
i=2: indexMap = {1: 0, 2: 1, 3: 2}
i=3: 1 exists at index 0
     distance = 3 - 0 = 3 <= 3 ✓
     return true
```

**Examples**:
- Contains Duplicate II
- Longest Substring Without Repeating Characters
- Max Points on a Line

---

### Problem Type 3B: Longest Substring Without Repeating

**Goal**: Find longest substring with all unique characters

```kotlin
fun lengthOfLongestSubstring(s: String): Int {
    val lastSeen = mutableMapOf<Char, Int>()
    var maxLength = 0
    var start = 0

    for (i in s.indices) {
        val ch = s[i]

        if (ch in lastSeen && lastSeen[ch]!! >= start) {
            // Character seen in current window
            start = lastSeen[ch]!! + 1
        }

        lastSeen[ch] = i
        maxLength = max(maxLength, i - start + 1)
    }

    return maxLength
}
```

**Example**:
```
s = "abcabcbb"

i=0, ch='a': lastSeen={a:0}, start=0, length=1
i=1, ch='b': lastSeen={a:0, b:1}, start=0, length=2
i=2, ch='c': lastSeen={a:0, b:1, c:2}, start=0, length=3
i=3, ch='a': 'a' seen at 0 >= start(0)
             start = 0 + 1 = 1
             lastSeen={a:3, b:1, c:2}, length=3 (bca)
i=4, ch='b': 'b' seen at 1 >= start(1)
             start = 1 + 1 = 2
             lastSeen={a:3, b:4, c:2}, length=3 (cab)
...
```

**Examples**:
- Longest Substring Without Repeating Characters
- Longest Substring with At Most K Distinct Characters
- Fruit Into Baskets

---

## Pattern 4: Key → Value (Memoization/Cache)

**Use Case**: Store computed results to avoid recomputation

### Use HashMap<Input, Result> When:
- **Expensive computation** that repeats
- **Recursion** with overlapping subproblems (DP)
- **Caching** for optimization

### Template:
```kotlin
val memo = mutableMapOf<Int, Int>()

fun solve(n: Int): Int {
    if (n in memo) {
        return memo[n]!!  // Return cached result
    }

    val result = computeExpensiveResult(n)
    memo[n] = result  // Cache for future

    return result
}
```

---

### Problem Type 4A: Fibonacci with Memoization

```kotlin
val memo = mutableMapOf<Int, Int>()

fun fib(n: Int): Int {
    if (n <= 1) return n

    if (n in memo) return memo[n]!!

    val result = fib(n - 1) + fib(n - 2)
    memo[n] = result

    return result
}
```

**Without memo**: O(2^n) time
**With memo**: O(n) time, O(n) space

**Examples**:
- Fibonacci Number
- Climbing Stairs
- Word Break (with memo)

---

### Problem Type 4B: Group Anagrams

**Goal**: Group strings that are anagrams

```kotlin
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val groups = mutableMapOf<String, MutableList<String>>()

    for (str in strs) {
        val key = str.toCharArray().sorted().joinToString("")
        groups.getOrPut(key) { mutableListOf() }.add(str)
    }

    return groups.values.toList()
}
```

**Example**:
```
Input: ["eat", "tea", "tan", "ate", "nat", "bat"]

Key generation:
  "eat" → sorted → "aet"
  "tea" → sorted → "aet"
  "tan" → sorted → "ant"
  "ate" → sorted → "aet"
  "nat" → sorted → "ant"
  "bat" → sorted → "abt"

groups = {
  "aet": ["eat", "tea", "ate"],
  "ant": ["tan", "nat"],
  "abt": ["bat"]
}

Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
```

**Examples**:
- Group Anagrams
- Group Shifted Strings
- Isomorphic Strings

---

## Pattern 5: Two HashMaps (Bidirectional)

**Use Case**: Maintain two-way mapping

### Use Two HashMaps When:
- Need **forward and reverse** lookup
- Validate **one-to-one** correspondence
- **Encoder/Decoder** problems

### Template:
```kotlin
val map1 = mutableMapOf<Char, Char>()  // Forward mapping
val map2 = mutableMapOf<Char, Char>()  // Reverse mapping

for (i in s1.indices) {
    val ch1 = s1[i]
    val ch2 = s2[i]

    if (ch1 in map1 && map1[ch1] != ch2) return false
    if (ch2 in map2 && map2[ch2] != ch1) return false

    map1[ch1] = ch2
    map2[ch2] = ch1
}
```

---

### Problem Type 5A: Isomorphic Strings

**Goal**: Check if two strings have 1-to-1 character mapping

```kotlin
fun isIsomorphic(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val sToT = mutableMapOf<Char, Char>()
    val tToS = mutableMapOf<Char, Char>()

    for (i in s.indices) {
        val charS = s[i]
        val charT = t[i]

        // Check forward mapping
        if (charS in sToT && sToT[charS] != charT) {
            return false
        }

        // Check reverse mapping
        if (charT in tToS && tToS[charT] != charS) {
            return false
        }

        sToT[charS] = charT
        tToS[charT] = charS
    }

    return true
}
```

**Why two maps needed**:
```
s = "ab", t = "aa"

With only one map (sToT):
  i=0: sToT = {a: a}
  i=1: sToT = {a: a, b: a}  ✓ No conflict!
  Would return true ✗ WRONG

With two maps:
  i=0: sToT = {a: a}, tToS = {a: a}
  i=1: charS = b, charT = a
       b not in sToT ✓
       a in tToS and tToS[a] = a ≠ b ✗
       return false ✓ CORRECT
```

**Examples**:
- Isomorphic Strings
- Word Pattern
- Find and Replace Pattern

---

## Common HashMap Methods in Kotlin

### 1. Check Existence:
```kotlin
if (key in map) { }
if (map.containsKey(key)) { }
```

### 2. Get with Default:
```kotlin
val value = map.getOrDefault(key, 0)
val value = map[key] ?: 0
```

### 3. Get or Put:
```kotlin
val list = map.getOrPut(key) { mutableListOf() }
list.add(element)
```

### 4. Increment Count:
```kotlin
// Method 1
map[key] = map.getOrDefault(key, 0) + 1

// Method 2
map.merge(key, 1, Int::plus)

// Method 3
map[key] = (map[key] ?: 0) + 1
```

### 5. Remove:
```kotlin
map.remove(key)
val value = map.remove(key)  // Returns removed value
```

---

## Problems by Pattern

### Pattern 1: Boolean/Existence (HashSet)
1. Contains Duplicate
2. Two Sum
3. Valid Anagram
4. Happy Number
5. Intersection of Two Arrays

### Pattern 2: Count/Frequency
6. Top K Frequent Elements
7. Majority Element
8. First Unique Character
9. Sort Characters by Frequency
10. Subarray Sum Equals K

### Pattern 3: Index/Position
11. Contains Duplicate II
12. Longest Substring Without Repeating
13. Minimum Window Substring
14. Logger Rate Limiter
15. Design HashMap

### Pattern 4: Value/Memoization
16. Group Anagrams
17. Valid Sudoku
18. Word Pattern
19. Clone Graph
20. Copy List with Random Pointer

### Pattern 5: Bidirectional
21. Isomorphic Strings
22. Word Pattern
23. Find and Replace Pattern
24. Verifying an Alien Dictionary
25. Custom Sort String

---

## Time & Space Complexity

| Operation | Average | Worst Case |
|-----------|---------|------------|
| Insert | O(1) | O(n) |
| Lookup | O(1) | O(n) |
| Delete | O(1) | O(n) |
| Space | O(n) | O(n) |

**Note**: Worst case O(n) occurs with hash collisions

---

## HashMap vs HashSet

| Feature | HashMap | HashSet |
|---------|---------|---------|
| **Stores** | Key-Value pairs | Only keys |
| **Duplicates** | Keys unique, values can repeat | No duplicates |
| **Use when** | Need to store associated data | Only check existence |
| **Example** | `{1: "one", 2: "two"}` | `{1, 2, 3}` |

---

## Common Mistakes

### ❌ Mistake 1: Null pointer when key missing
```kotlin
// WRONG - can throw NPE
val count = map[key]!! + 1

// CORRECT
val count = map.getOrDefault(key, 0) + 1
val count = (map[key] ?: 0) + 1
```

### ❌ Mistake 2: Modifying map while iterating
```kotlin
// WRONG - ConcurrentModificationException
for ((key, value) in map) {
    if (value == 0) {
        map.remove(key)  // ERROR!
    }
}

// CORRECT - use iterator
val iterator = map.iterator()
while (iterator.hasNext()) {
    val entry = iterator.next()
    if (entry.value == 0) {
        iterator.remove()
    }
}
```

### ❌ Mistake 3: Using mutable objects as keys
```kotlin
// WRONG - HashMap uses hashCode()
val list = mutableListOf(1, 2, 3)
map[list] = "value"
list.add(4)  // Changes hashCode!
map[list]  // May not find the key!

// CORRECT - use immutable keys
val list = listOf(1, 2, 3)  // Immutable
map[list] = "value"
```

---

## When NOT to Use HashMap

### ❌ Don't use when:
1. **Maintaining order matters**
   - Alternative: Use LinkedHashMap (insertion order) or TreeMap (sorted)
2. **Need range queries**
   - Alternative: Use TreeMap or sorted array with binary search
3. **Memory is very limited**
   - HashMap has overhead, consider array/list if possible
4. **Keys are sequential integers**
   - Use array instead: `val freq = IntArray(maxValue + 1)`

---

## Summary

### Pattern Decision Tree:

**Need to check existence?**
→ Use **HashSet** (Boolean pattern)

**Need to count frequency?**
→ Use **HashMap<Element, Count>**

**Need to track position?**
→ Use **HashMap<Element, Index>**

**Need to cache results?**
→ Use **HashMap<Input, Output>** (Memoization)

**Need bidirectional mapping?**
→ Use **Two HashMaps**

---

## Quick Reference

```kotlin
// Pattern 1: Existence
val seen = mutableSetOf<Int>()
if (num in seen) { /* duplicate */ }

// Pattern 2: Frequency
val freq = mutableMapOf<Int, Int>()
freq[num] = freq.getOrDefault(num, 0) + 1

// Pattern 3: Index
val lastIndex = mutableMapOf<Int, Int>()
lastIndex[num] = i

// Pattern 4: Memoization
val memo = mutableMapOf<Int, Int>()
if (n in memo) return memo[n]!!

// Pattern 5: Bidirectional
val map1 = mutableMapOf<Char, Char>()
val map2 = mutableMapOf<Char, Char>()
```

---

## Related Files in This Codebase

(Add your HashMap implementation files here)

---

## Further Reading

- **Hash Function Design**: How HashMap works internally
- **Collision Resolution**: Chaining vs Open Addressing
- **LinkedHashMap**: Maintains insertion/access order
- **TreeMap**: Sorted map using Red-Black Tree
- **ConcurrentHashMap**: Thread-safe HashMap
