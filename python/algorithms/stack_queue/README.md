# Stack and Queue

## Overview
Stacks and queues are fundamental data structures that restrict how elements are added and removed.

- **Stack**: Last-In-First-Out (LIFO)
- **Queue**: First-In-First-Out (FIFO)

## Stack

### Operations
- `push(x)`: Add element to top - O(1)
- `pop()`: Remove top element - O(1)
- `peek()/top()`: View top element - O(1)
- `is_empty()`: Check if empty - O(1)

### Implementation
```python
# Using list
stack = []
stack.append(x)  # push
x = stack.pop()  # pop
top = stack[-1]  # peek

# Using collections.deque (more efficient)
from collections import deque
stack = deque()
stack.append(x)
x = stack.pop()
```

### When to Use Stack
- Function call stack / recursion
- Undo mechanism
- Parsing expressions (parentheses matching)
- Backtracking (DFS)
- Reverse operations
- **Pattern**: Process in reverse order or need most recent element

## Queue

### Operations
- `enqueue(x)`: Add to rear - O(1)
- `dequeue()`: Remove from front - O(1)
- `front()`: View front element - O(1)

### Implementation
```python
# Using collections.deque (recommended)
from collections import deque
queue = deque()
queue.append(x)      # enqueue
x = queue.popleft()  # dequeue
front = queue[0]     # peek front

# Using list (inefficient for dequeue)
queue = []
queue.append(x)      # enqueue O(1)
x = queue.pop(0)     # dequeue O(n) - avoid!
```

### When to Use Queue
- BFS in graphs/trees
- Level-order traversal
- Task scheduling
- Buffering
- **Pattern**: Process in order of arrival

## Common Stack Problems

### 1. Valid Parentheses
```python
def is_valid(s):
    stack = []
    pairs = {'(': ')', '[': ']', '{': '}'}

    for char in s:
        if char in pairs:
            stack.append(char)
        elif not stack or pairs[stack.pop()] != char:
            return False

    return len(stack) == 0
```

### 2. Next Greater Element
```python
def next_greater_elements(nums):
    """For each element, find next greater element"""
    result = [-1] * len(nums)
    stack = []  # Stack of indices

    for i in range(len(nums)):
        while stack and nums[stack[-1]] < nums[i]:
            result[stack.pop()] = nums[i]
        stack.append(i)

    return result
```

**Pattern**: Monotonic stack

### 3. Min Stack (O(1) getMin)
```python
class MinStack:
    def __init__(self):
        self.stack = []
        self.min_stack = []

    def push(self, x):
        self.stack.append(x)
        if not self.min_stack or x <= self.min_stack[-1]:
            self.min_stack.append(x)

    def pop(self):
        if self.stack.pop() == self.min_stack[-1]:
            self.min_stack.pop()

    def getMin(self):
        return self.min_stack[-1]
```

### 4. Evaluate Reverse Polish Notation
```python
def eval_rpn(tokens):
    stack = []
    operators = {'+', '-', '*', '/'}

    for token in tokens:
        if token in operators:
            b = stack.pop()
            a = stack.pop()
            if token == '+': stack.append(a + b)
            elif token == '-': stack.append(a - b)
            elif token == '*': stack.append(a * b)
            else: stack.append(int(a / b))
        else:
            stack.append(int(token))

    return stack[0]
```

### 5. Largest Rectangle in Histogram
```python
def largest_rectangle_area(heights):
    stack = []
    max_area = 0
    heights.append(0)  # Sentinel

    for i, h in enumerate(heights):
        while stack and heights[stack[-1]] > h:
            height = heights[stack.pop()]
            width = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, height * width)
        stack.append(i)

    return max_area
```

## Common Queue Problems

### 1. BFS Template
```python
from collections import deque

def bfs(start):
    queue = deque([start])
    visited = {start}

    while queue:
        node = queue.popleft()
        # Process node

        for neighbor in get_neighbors(node):
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
```

### 2. Level Order Traversal
```python
def level_order(root):
    if not root:
        return []

    result = []
    queue = deque([root])

    while queue:
        level_size = len(queue)
        level = []

        for _ in range(level_size):
            node = queue.popleft()
            level.append(node.val)

            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)

        result.append(level)

    return result
```

### 3. Moving Average from Data Stream
```python
class MovingAverage:
    def __init__(self, size):
        self.size = size
        self.queue = deque()
        self.sum = 0

    def next(self, val):
        self.queue.append(val)
        self.sum += val

        if len(self.queue) > self.size:
            self.sum -= self.queue.popleft()

        return self.sum / len(self.queue)
```

## Advanced: Deque (Double-Ended Queue)

```python
from collections import deque

dq = deque()
dq.append(x)      # Add to right
dq.appendleft(x)  # Add to left
dq.pop()          # Remove from right
dq.popleft()      # Remove from left
```

### Use Cases for Deque
- Sliding window maximum/minimum
- Palindrome checking
- Implementing both stack and queue
- Maintaining order while adding/removing from both ends

## Monotonic Stack/Queue

### Monotonic Increasing Stack
Elements in stack are in increasing order
```python
stack = []
for num in nums:
    while stack and stack[-1] > num:
        stack.pop()
    stack.append(num)
```

**Use for**: Next smaller element, trap water

### Monotonic Decreasing Stack
Elements in stack are in decreasing order
```python
stack = []
for num in nums:
    while stack and stack[-1] < num:
        stack.pop()
    stack.append(num)
```

**Use for**: Next greater element, largest rectangle

### Sliding Window Maximum (Monotonic Deque)
```python
def max_sliding_window(nums, k):
    result = []
    dq = deque()  # Store indices

    for i, num in enumerate(nums):
        # Remove indices outside window
        while dq and dq[0] <= i - k:
            dq.popleft()

        # Remove smaller elements
        while dq and nums[dq[-1]] < num:
            dq.pop()

        dq.append(i)

        if i >= k - 1:
            result.append(nums[dq[0]])

    return result
```

## Common Patterns

### Pattern 1: Matching Pairs
Use stack to match opening/closing brackets, tags, etc.

### Pattern 2: Monotonic Stack
Maintain stack in sorted order to find next greater/smaller

### Pattern 3: DFS with Stack
```python
def dfs_iterative(start):
    stack = [start]
    visited = {start}

    while stack:
        node = stack.pop()
        # Process node

        for neighbor in get_neighbors(node):
            if neighbor not in visited:
                visited.add(neighbor)
                stack.append(neighbor)
```

### Pattern 4: Multi-Level Processing
Use multiple stacks or track min/max

## Interview Tips

### Stack Indicators
- Words: "most recent", "undo", "reverse", "nested"
- Parentheses/brackets matching
- Expression evaluation
- Backtracking problems
- Function call simulation

### Queue Indicators
- Words: "first come first serve", "order", "level"
- BFS traversal
- Shortest path in unweighted graph
- Process in arrival order
- Streaming data

### Common Mistakes
- Using list.pop(0) for queue (O(n)) - use deque!
- Forgetting to check if stack/queue is empty before pop
- Not handling edge cases (empty input, single element)
- Using wrong data structure (stack for BFS won't work!)

### Complexity
- Most stack/queue operations: O(1)
- Space: O(n) for storing elements
- With monotonic stack: Still O(n) overall (each element pushed/popped once)

## Related Topics
- Recursion (stack-based)
- DFS and BFS
- Trees and graphs
- Expression parsing
- Backtracking
