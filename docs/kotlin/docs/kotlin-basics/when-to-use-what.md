# When To Use What - Data Structures & Algorithms Guide

## Quick Reference

| Problem Type | Best Data Structure | Time Complexity |
|--------------|-------------------|-----------------|
| Fast lookup | HashMap | O(1) average |
| Order matters | LinkedHashMap, List | O(1), O(n) |
| Sorted data | TreeMap, TreeSet | O(log n) |
| Fast insert/delete at ends | Deque, LinkedList | O(1) |
| LIFO (Last In First Out) | Stack | O(1) |
| FIFO (First In First Out) | Queue | O(1) |
| Priority-based | PriorityQueue | O(log n) |
| Unique elements | Set, HashSet | O(1) |
| Range queries | Segment Tree, Fenwick Tree | O(log n) |
| Graph problems | Adjacency List/Matrix | Varies |

## Collections

### Array/IntArray
**Use when:**
- Fixed size known in advance
- Need fast random access
- Memory efficiency matters (IntArray for primitives)
- Working with indices frequently

**Examples:**
- Two pointers problems
- Sliding window
- Kadane's algorithm
- Most sorting algorithms

```kotlin
val arr = IntArray(100)
val arr = intArrayOf(1, 2, 3, 4, 5)
```

### ArrayList/MutableList
**Use when:**
- Size changes dynamically
- Need fast random access
- Order matters
- Frequent additions at end

**Avoid when:**
- Frequent insertions/deletions in middle (O(n))
- Need guaranteed performance

**Examples:**
- Building result lists
- Dynamic arrays
- Storing intermediate results

```kotlin
val list = mutableListOf<Int>()
list.add(1)  // O(1) amortized
list[0]      // O(1) access
```

### LinkedList
**Use when:**
- Frequent insertions/deletions at both ends
- Implementing queue/deque
- Don't need random access

**Avoid when:**
- Need fast random access (O(n))
- Memory overhead is a concern

**Examples:**
- Queue implementation
- Undo functionality
- Browser history

```kotlin
val linkedList = LinkedList<Int>()
linkedList.addFirst(1)  // O(1)
linkedList.addLast(2)   // O(1)
```

## Maps

### HashMap
**Use when:**
- Need fast key-value lookups
- Order doesn't matter
- Most common map choice

**Examples:**
- Two Sum problem
- Frequency counting
- Memoization
- Anagram grouping

```kotlin
val map = mutableMapOf<String, Int>()
map["key"] = value  // O(1) average
```

### LinkedHashMap
**Use when:**
- Need insertion order preserved
- Order of iteration matters
- Predictable traversal order required

```kotlin
val map = linkedMapOf<String, Int>()
// Iterates in insertion order
```

### TreeMap
**Use when:**
- Need sorted keys
- Range queries (subMap, headMap, tailMap)
- Need first/last key frequently

**Examples:**
- Meeting rooms problems
- Interval-based problems
- Sorted map iteration

```kotlin
val map = sortedMapOf<Int, String>()
map.firstKey()   // O(log n)
map.lastKey()    // O(log n)
```

## Sets

### HashSet
**Use when:**
- Need unique elements
- Fast lookup/add/remove
- Order doesn't matter

**Examples:**
- Remove duplicates
- Check membership
- Set operations (union, intersection)

```kotlin
val set = mutableSetOf<Int>()
set.add(1)       // O(1)
1 in set         // O(1)
```

### LinkedHashSet
**Use when:**
- Need unique elements
- Insertion order matters

```kotlin
val set = linkedSetOf<Int>()
```

### TreeSet
**Use when:**
- Need unique sorted elements
- Need min/max frequently
- Range queries needed

```kotlin
val set = sortedSetOf<Int>()
set.first()  // O(log n)
set.last()   // O(log n)
```

## Stack & Queue

### Stack
**Use when:**
- LIFO operations needed
- Parentheses matching
- Function call stack
- Backtracking problems

**Examples:**
- Valid parentheses
- Min stack
- Evaluate postfix expression
- DFS iterative

```kotlin
val stack = ArrayDeque<Int>()
stack.addLast(1)    // push - O(1)
stack.removeLast()  // pop - O(1)
stack.last()        // peek - O(1)
```

### Queue
**Use when:**
- FIFO operations needed
- BFS traversal
- Level-order processing

**Examples:**
- BFS
- Level order traversal
- Sliding window maximum

```kotlin
val queue = ArrayDeque<Int>()
queue.addLast(1)     // enqueue - O(1)
queue.removeFirst()  // dequeue - O(1)
queue.first()        // peek - O(1)
```

### Deque (Double-ended queue)
**Use when:**
- Need to add/remove from both ends
- Implementing sliding window
- Monotonic queue

```kotlin
val deque = ArrayDeque<Int>()
deque.addFirst(1)    // O(1)
deque.addLast(2)     // O(1)
deque.removeFirst()  // O(1)
deque.removeLast()   // O(1)
```

### PriorityQueue (Heap)
**Use when:**
- Need min/max element quickly
- K-th largest/smallest problems
- Merge K sorted lists
- Median finding

**Examples:**
- Top K frequent elements
- Kth largest element
- Meeting rooms II
- Dijkstra's algorithm

```kotlin
val minHeap = PriorityQueue<Int>()
val maxHeap = PriorityQueue<Int>(reverseOrder())
minHeap.add(1)   // O(log n)
minHeap.poll()   // O(log n) - removes min
minHeap.peek()   // O(1) - views min
```

## Algorithms

### Sorting

#### Built-in Sort
**Use when:**
- Need O(n log n) sorting
- Stable sort needed
- Simple sorting required

```kotlin
arr.sort()              // In-place
val sorted = arr.sorted()  // Returns new
```

#### Quick Sort
**Use when:**
- Average O(n log n) acceptable
- In-place sorting needed
- Small additional space OK

#### Merge Sort
**Use when:**
- Stable sort required
- Guaranteed O(n log n) needed
- External sorting

#### Counting Sort
**Use when:**
- Range of values is small (0 to k)
- All values are integers
- Need O(n + k) time

### Searching

#### Binary Search
**Use when:**
- Array is sorted
- Need O(log n) search
- Finding insertion point

```kotlin
val index = arr.binarySearch(target)
```

#### DFS (Depth-First Search)
**Use when:**
- Exploring all paths
- Backtracking problems
- Topological sorting
- Connected components

```kotlin
fun dfs(node: Int, visited: BooleanArray, graph: List<List<Int>>) {
    visited[node] = true
    for (neighbor in graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, visited, graph)
        }
    }
}
```

#### BFS (Breadth-First Search)
**Use when:**
- Shortest path in unweighted graph
- Level-order traversal
- Finding minimum steps

```kotlin
fun bfs(start: Int, graph: List<List<Int>>) {
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(graph.size)

    queue.add(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        for (neighbor in graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true
                queue.add(neighbor)
            }
        }
    }
}
```

## Techniques

### Two Pointers
**Use when:**
- Sorted array
- Finding pairs
- Removing duplicates
- Reversing

**Examples:**
- Two sum (sorted)
- Container with most water
- Trapping rain water

### Sliding Window
**Use when:**
- Subarray/substring problems
- Fixed or variable window size
- Optimization problems

**Examples:**
- Maximum sum subarray of size K
- Longest substring without repeating characters
- Minimum window substring

### Dynamic Programming
**Use when:**
- Optimal substructure
- Overlapping subproblems
- Counting/optimization problems

**Approaches:**
- Top-down (memoization)
- Bottom-up (tabulation)

### Greedy
**Use when:**
- Local optimal leads to global optimal
- Proof of correctness exists
- Activity selection type problems

### Backtracking
**Use when:**
- Generate all combinations/permutations
- Constraint satisfaction
- NP-complete problems

**Examples:**
- N-Queens
- Sudoku solver
- Generate parentheses

## Problem Patterns

### Frequency Counting → HashMap
```kotlin
val freq = mutableMapOf<Char, Int>()
for (char in str) {
    freq[char] = freq.getOrDefault(char, 0) + 1
}
```

### Finding Duplicates → HashSet
```kotlin
val seen = mutableSetOf<Int>()
for (num in nums) {
    if (num in seen) return true
    seen.add(num)
}
```

### Top K Elements → PriorityQueue
```kotlin
val minHeap = PriorityQueue<Int>()
for (num in nums) {
    minHeap.add(num)
    if (minHeap.size > k) {
        minHeap.poll()
    }
}
```

### Graph Traversal → BFS/DFS
```kotlin
// BFS for shortest path
// DFS for all paths
```

### Interval Problems → TreeMap/Sorting
```kotlin
intervals.sortBy { it[0] }
```

### Palindrome → Two Pointers
```kotlin
var left = 0
var right = s.length - 1
```

## Decision Tree

```
Need fast lookup?
├─ Yes → HashMap/HashSet
└─ No
   ├─ Need order?
   │  ├─ Insertion order → LinkedHashMap/LinkedHashSet
   │  └─ Sorted order → TreeMap/TreeSet
   └─ Need LIFO? → Stack
      └─ Need FIFO? → Queue
         └─ Need min/max? → PriorityQueue
```

## Interview Strategy

1. **Identify the pattern** from problem description
2. **Choose data structure** based on requirements
3. **Consider time/space tradeoffs**
4. **Implement and test** with edge cases
5. **Optimize** if needed

Remember: **The simplest solution that meets requirements is often the best!**
