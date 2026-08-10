# Interview Patterns & Problem-Solving Strategies

## Common Problem Patterns

### 1. Two Pointers

**When to use:**
- Array/String is sorted
- Finding pairs
- Reversing
- Removing duplicates
- Partitioning

**Template:**
```kotlin
fun twoPointers(arr: IntArray) {
    var left = 0
    var right = arr.size - 1

    while (left < right) {
        // Process
        if (condition) {
            left++
        } else {
            right--
        }
    }
}
```

**Problems:**
- Two Sum (sorted)
- 3Sum
- Container With Most Water
- Trapping Rain Water
- Remove Duplicates
- Valid Palindrome

### 2. Sliding Window

**When to use:**
- Subarray/substring problems
- Fixed or variable window size
- Maximum/minimum in window

**Fixed Window:**
```kotlin
fun maxSumSubarray(arr: IntArray, k: Int): Int {
    var windowSum = 0
    var maxSum = 0

    // First window
    for (i in 0 until k) {
        windowSum += arr[i]
    }
    maxSum = windowSum

    // Slide window
    for (i in k until arr.size) {
        windowSum = windowSum - arr[i - k] + arr[i]
        maxSum = maxOf(maxSum, windowSum)
    }

    return maxSum
}
```

**Variable Window:**
```kotlin
fun longestSubstring(s: String): Int {
    val seen = mutableSetOf<Char>()
    var left = 0
    var maxLen = 0

    for (right in s.indices) {
        while (s[right] in seen) {
            seen.remove(s[left])
            left++
        }
        seen.add(s[right])
        maxLen = maxOf(maxLen, right - left + 1)
    }

    return maxLen
}
```

**Problems:**
- Maximum Sum Subarray of Size K
- Longest Substring Without Repeating Characters
- Minimum Window Substring
- Permutation in String
- Longest Repeating Character Replacement

### 3. Fast & Slow Pointers (Floyd's Cycle Detection)

**When to use:**
- Linked list cycles
- Finding middle
- Detecting patterns

**Template:**
```kotlin
fun hasCycle(head: ListNode?): Boolean {
    var slow = head
    var fast = head

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow == fast) return true
    }

    return false
}
```

**Problems:**
- Linked List Cycle
- Find Middle of Linked List
- Happy Number
- Find Duplicate Number

### 4. Merge Intervals

**When to use:**
- Overlapping intervals
- Scheduling problems
- Range problems

**Template:**
```kotlin
fun mergeIntervals(intervals: Array<IntArray>): List<IntArray> {
    if (intervals.isEmpty()) return emptyList()

    intervals.sortBy { it[0] }
    val merged = mutableListOf<IntArray>()
    merged.add(intervals[0])

    for (i in 1 until intervals.size) {
        val last = merged.last()
        val curr = intervals[i]

        if (curr[0] <= last[1]) {
            last[1] = maxOf(last[1], curr[1])
        } else {
            merged.add(curr)
        }
    }

    return merged
}
```

**Problems:**
- Merge Intervals
- Insert Interval
- Meeting Rooms I & II
- Non-overlapping Intervals

### 5. Cyclic Sort

**When to use:**
- Array contains numbers in range [1, n] or [0, n-1]
- Finding missing/duplicate numbers

**Template:**
```kotlin
fun cyclicSort(nums: IntArray) {
    var i = 0
    while (i < nums.size) {
        val correctPos = nums[i] - 1
        if (nums[i] != nums[correctPos]) {
            nums[i] = nums[correctPos].also { nums[correctPos] = nums[i] }
        } else {
            i++
        }
    }
}
```

**Problems:**
- Find Missing Number
- Find All Missing Numbers
- Find Duplicate Number
- First Missing Positive

### 6. In-place Reversal of Linked List

**When to use:**
- Reversing linked list or parts of it

**Template:**
```kotlin
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var curr = head

    while (curr != null) {
        val next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    }

    return prev
}
```

**Problems:**
- Reverse Linked List
- Reverse Linked List II
- Reverse Nodes in k-Group
- Palindrome Linked List

### 7. Tree BFS (Level Order Traversal)

**When to use:**
- Level-by-level tree traversal
- Finding levels/depths
- Zigzag traversal

**Template:**
```kotlin
fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()

    val result = mutableListOf<List<Int>>()
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size
        val level = mutableListOf<Int>()

        repeat(levelSize) {
            val node = queue.removeFirst()
            level.add(node.`val`)

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }

        result.add(level)
    }

    return result
}
```

**Problems:**
- Binary Tree Level Order Traversal
- Zigzag Level Order Traversal
- Average of Levels
- Minimum Depth
- Right Side View

### 8. Tree DFS

**When to use:**
- Path problems
- Recursion-based tree problems

**Template:**
```kotlin
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) return false
    if (root.left == null && root.right == null) return root.`val` == targetSum

    return hasPathSum(root.left, targetSum - root.`val`) ||
           hasPathSum(root.right, targetSum - root.`val`)
}
```

**Types:**
- Preorder: Root → Left → Right
- Inorder: Left → Root → Right
- Postorder: Left → Right → Root

**Problems:**
- Path Sum
- Diameter of Binary Tree
- Maximum Depth
- Lowest Common Ancestor
- Validate BST

### 9. Two Heaps

**When to use:**
- Finding median
- Scheduling with priorities

**Template:**
```kotlin
class MedianFinder {
    private val maxHeap = PriorityQueue<Int>(reverseOrder())
    private val minHeap = PriorityQueue<Int>()

    fun addNum(num: Int) {
        maxHeap.add(num)
        minHeap.add(maxHeap.poll())

        if (minHeap.size > maxHeap.size) {
            maxHeap.add(minHeap.poll())
        }
    }

    fun findMedian(): Double {
        return if (maxHeap.size > minHeap.size) {
            maxHeap.peek().toDouble()
        } else {
            (maxHeap.peek() + minHeap.peek()) / 2.0
        }
    }
}
```

**Problems:**
- Find Median from Data Stream
- Sliding Window Median
- IPO

### 10. K-way Merge

**When to use:**
- Merging K sorted lists/arrays

**Template:**
```kotlin
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val pq = PriorityQueue<ListNode>(compareBy { it.`val` })

    for (head in lists) {
        head?.let { pq.add(it) }
    }

    val dummy = ListNode(0)
    var curr = dummy

    while (pq.isNotEmpty()) {
        val node = pq.poll()
        curr.next = node
        curr = curr.next!!

        node.next?.let { pq.add(it) }
    }

    return dummy.next
}
```

**Problems:**
- Merge K Sorted Lists
- Kth Smallest Element in Sorted Matrix
- Smallest Range Covering K Lists

### 11. Top K Elements

**When to use:**
- Finding K largest/smallest
- Frequency-based problems

**Template:**
```kotlin
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val freq = mutableMapOf<Int, Int>()
    for (num in nums) {
        freq[num] = freq.getOrDefault(num, 0) + 1
    }

    val minHeap = PriorityQueue<Int>(compareBy { freq[it] })

    for ((num, _) in freq) {
        minHeap.add(num)
        if (minHeap.size > k) {
            minHeap.poll()
        }
    }

    return minHeap.toIntArray()
}
```

**Problems:**
- Kth Largest Element
- Top K Frequent Elements
- K Closest Points to Origin

### 12. Modified Binary Search

**When to use:**
- Sorted/rotated arrays
- Finding peak/valley
- Searching in matrix

**Template:**
```kotlin
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            nums[mid] == target -> return mid
            // Custom logic based on problem
            condition -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return -1
}
```

**Problems:**
- Search in Rotated Sorted Array
- Find Peak Element
- Search in 2D Matrix
- Find Minimum in Rotated Sorted Array

### 13. Backtracking

**When to use:**
- Generate all combinations/permutations
- Constraint satisfaction
- Decision trees

**Template:**
```kotlin
fun backtrack(result: MutableList<List<Int>>, current: MutableList<Int>, nums: IntArray) {
    if (isComplete(current)) {
        result.add(current.toList())
        return
    }

    for (choice in getChoices(nums, current)) {
        current.add(choice)
        backtrack(result, current, nums)
        current.removeAt(current.size - 1)  // Backtrack
    }
}
```

**Problems:**
- Permutations
- Subsets
- Combination Sum
- N-Queens
- Sudoku Solver
- Generate Parentheses

### 14. Dynamic Programming

**When to use:**
- Optimization problems
- Counting problems
- Overlapping subproblems

**Template:**
```kotlin
fun dp(n: Int): Int {
    val dp = IntArray(n + 1)
    // Base cases
    dp[0] = baseCase

    for (i in 1..n) {
        dp[i] = transition(dp, i)
    }

    return dp[n]
}
```

**Problems:**
- Climbing Stairs
- House Robber
- Coin Change
- Longest Common Subsequence
- Edit Distance

### 15. Union Find (Disjoint Set)

**When to use:**
- Connected components
- Graph connectivity
- Cycle detection

**Template:**
```kotlin
class UnionFind(n: Int) {
    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 1 }

    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])  // Path compression
        }
        return parent[x]
    }

    fun union(x: Int, y: Int): Boolean {
        val rootX = find(x)
        val rootY = find(y)

        if (rootX == rootY) return false

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX
        } else {
            parent[rootY] = rootX
            rank[rootX]++
        }

        return true
    }
}
```

**Problems:**
- Number of Connected Components
- Redundant Connection
- Accounts Merge

## Pattern Recognition Guide

| Keywords in Problem | Pattern |
|-------------------|---------|
| Sorted array + pair | Two Pointers |
| Subarray/substring | Sliding Window |
| Linked list + cycle | Fast & Slow Pointers |
| Overlapping ranges | Merge Intervals |
| Array [1..n] | Cyclic Sort |
| Reverse linked list | In-place Reversal |
| Tree levels | BFS |
| Tree paths | DFS |
| K largest/smallest | Heap/Top K |
| All combinations | Backtracking |
| Optimal solution | Dynamic Programming |
| Connected components | Union Find |

## Interview Strategy

1. **Listen carefully** - understand the problem
2. **Ask clarifying questions** - constraints, edge cases
3. **Think out loud** - explain your approach
4. **Start with brute force** - establish baseline
5. **Identify the pattern** - map to known patterns
6. **Optimize** - improve time/space complexity
7. **Code cleanly** - use meaningful names
8. **Test thoroughly** - edge cases, normal cases
9. **Analyze complexity** - time and space

## Time Management

- **Understanding (5 min)** - clarify problem
- **Planning (10 min)** - think through approach
- **Coding (20 min)** - implement solution
- **Testing (5 min)** - verify with test cases
- **Total: ~40 min** per problem

Remember: **Pattern recognition comes with practice!**
