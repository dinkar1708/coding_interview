# Greedy Algorithms

## Overview
Greedy algorithms make locally optimal choices at each step with the hope of finding a global optimum. They are characterized by making the best choice at the current moment without considering future consequences.

## Key Characteristics

1. **Greedy Choice Property**: A global optimum can be arrived at by selecting local optimums
2. **Optimal Substructure**: An optimal solution contains optimal solutions to subproblems
3. **No Backtracking**: Once a choice is made, it's never reconsidered

## When to Use Greedy

### Greedy Works When:
- Problem has greedy choice property
- Problem has optimal substructure
- Local optimum leads to global optimum
- Can prove correctness (important!)

### Examples Where Greedy Works:
- Activity selection
- Fractional knapsack
- Huffman coding
- Minimum spanning tree (Kruskal's, Prim's)
- Dijkstra's shortest path
- Task scheduling

### Examples Where Greedy Fails:
- 0/1 Knapsack (need dynamic programming)
- Longest path problem
- Traveling salesman problem
- Many NP-hard problems

## Common Greedy Patterns

### Pattern 1: Activity/Interval Selection
**Problem**: Select maximum number of non-overlapping activities/intervals

**Strategy**: Sort by end time, pick earliest ending activity

```python
def max_activities(activities):
    """activities = [(start, end), ...]"""
    activities.sort(key=lambda x: x[1])  # Sort by end time

    count = 0
    last_end = -float('inf')

    for start, end in activities:
        if start >= last_end:
            count += 1
            last_end = end

    return count
```

**Complexity**: O(n log n)

### Pattern 2: Fractional Knapsack
**Problem**: Maximize value, can take fractions of items

**Strategy**: Sort by value-to-weight ratio

```python
def fractional_knapsack(items, capacity):
    """items = [(value, weight), ...]"""
    # Sort by value/weight ratio (descending)
    items.sort(key=lambda x: x[0]/x[1], reverse=True)

    total_value = 0
    for value, weight in items:
        if capacity >= weight:
            # Take whole item
            total_value += value
            capacity -= weight
        else:
            # Take fraction
            total_value += value * (capacity / weight)
            break

    return total_value
```

### Pattern 3: Huffman Encoding
**Problem**: Build optimal prefix code

**Strategy**: Always merge two smallest frequency nodes

```python
import heapq

def huffman_encoding(freq):
    """freq = {char: frequency}"""
    heap = [[freq, char] for char, freq in freq.items()]
    heapq.heapify(heap)

    while len(heap) > 1:
        lo = heapq.heappop(heap)
        hi = heapq.heappop(heap)
        merged = [lo[0] + hi[0], lo, hi]
        heapq.heappush(heap, merged)

    return heap[0]
```

### Pattern 4: Two-Pointer Greedy
**Problem**: Arrange elements to satisfy constraints

**Example**: Rearrange string so no adjacent characters are same

```python
from collections import Counter
import heapq

def rearrange_string(s):
    # Count frequencies
    freq = Counter(s)
    # Max heap (negate for max heap in Python)
    heap = [(-count, char) for char, count in freq.items()]
    heapq.heapify(heap)

    result = []
    prev_count, prev_char = 0, ''

    while heap:
        count, char = heapq.heappop(heap)
        result.append(char)

        # Push previous char back if it has remaining count
        if prev_count < 0:
            heapq.heappush(heap, (prev_count, prev_char))

        # Update previous
        prev_count = count + 1  # Increment because we negated
        prev_char = char

    return ''.join(result) if len(result) == len(s) else ""
```

## Common Greedy Problems

### 1. Jump Game
Can reach last index by jumping?

```python
def can_jump(nums):
    max_reach = 0
    for i, jump in enumerate(nums):
        if i > max_reach:
            return False
        max_reach = max(max_reach, i + jump)
    return True
```

### 2. Gas Station
Find starting gas station to complete circular tour

```python
def can_complete_circuit(gas, cost):
    if sum(gas) < sum(cost):
        return -1

    start = 0
    tank = 0

    for i in range(len(gas)):
        tank += gas[i] - cost[i]
        if tank < 0:
            start = i + 1
            tank = 0

    return start
```

### 3. Non-Overlapping Intervals
Minimum intervals to remove to make non-overlapping

```python
def erase_overlap_intervals(intervals):
    if not intervals:
        return 0

    intervals.sort(key=lambda x: x[1])  # Sort by end
    end = intervals[0][1]
    count = 0

    for i in range(1, len(intervals)):
        if intervals[i][0] < end:
            # Overlapping - remove
            count += 1
        else:
            # Non-overlapping - update end
            end = intervals[i][1]

    return count
```

### 4. Partition Labels
Partition string into parts so each letter appears in at most one part

**Strategy**: Track last occurrence, extend partition

### 5. Task Scheduler
Schedule tasks with cooldown period

**Strategy**: Greedy with frequency + heap

## Proving Greedy Correctness

### Greedy Stays Ahead
Show that greedy solution is at least as good as optimal at each step

### Exchange Argument
Show that any optimal solution can be transformed into greedy solution without making it worse

### Structural Approach
- Greedy gives feasible solution
- Prove it's optimal by contradiction or induction

## Interview Tips

### 1. Identify Greedy Problems
Look for keywords:
- "Maximum/minimum number of..."
- "Optimal selection/arrangement"
- "Scheduling/intervals"
- "Make greedy choice at each step"

### 2. Common Strategies
- **Sort first** (by start, end, value, etc.)
- **Use heap** for dynamic greedy choices
- **Track state** (last chosen, current max, etc.)
- **Interval problems**: Sort by end time

### 3. Verify Correctness
- Not all optimization problems can be solved greedily
- Try counter-example to verify
- If greedy fails, consider dynamic programming

### 4. Complexity
- Often O(n log n) due to sorting
- With heap: O(n log n) or O(n log k)
- After sorting, greedy part usually O(n)

## Greedy vs Dynamic Programming

| Aspect | Greedy | Dynamic Programming |
|--------|--------|-------------------|
| Approach | Make best local choice | Consider all choices |
| Correctness | Needs proof | Always correct if formulated right |
| Efficiency | Usually faster | Can be slower |
| Problems | Limited applicable problems | More general |
| Examples | Activity selection | 0/1 Knapsack |

## Related Topics
- Sorting algorithms
- Heap/Priority Queue
- Interval scheduling
- Graph algorithms (MST, Shortest path)
