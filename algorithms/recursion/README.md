# Recursion

## Overview
Recursion is a problem-solving technique where a function calls itself to solve smaller instances of the same problem.

## Key Components

### 1. Base Case
- The terminating condition
- Prevents infinite recursion
- Returns a value without further recursion

### 2. Recursive Case
- Breaks problem into smaller subproblems
- Calls function with modified parameters
- Combines results from recursive calls

### 3. Progress Toward Base Case
- Each recursive call must move closer to base case
- Usually by reducing problem size

## Basic Template

```python
def recursive_function(params):
    # Base case
    if base_condition:
        return base_value

    # Recursive case
    # 1. Do some work
    # 2. Make recursive call(s) with smaller problem
    # 3. Combine results
    return recursive_function(modified_params)
```

## Classic Recursion Problems

### 1. Factorial
```python
def factorial(n):
    # Base case
    if n <= 1:
        return 1
    # Recursive case
    return n * factorial(n - 1)
```

**Time**: O(n), **Space**: O(n) for call stack

### 2. Fibonacci
```python
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)
```

**Time**: O(2^n), **Space**: O(n)
**Problem**: Lots of repeated computation!

**With Memoization**:
```python
def fibonacci(n, memo={}):
    if n <= 1:
        return n
    if n not in memo:
        memo[n] = fibonacci(n-1, memo) + fibonacci(n-2, memo)
    return memo[n]
```

**Time**: O(n), **Space**: O(n)

### 3. Sum of Array
```python
def array_sum(arr):
    if not arr:
        return 0
    return arr[0] + array_sum(arr[1:])
```

### 4. Power Function
```python
def power(base, exp):
    # Base cases
    if exp == 0:
        return 1
    if exp == 1:
        return base

    # Recursive case
    half = power(base, exp // 2)
    if exp % 2 == 0:
        return half * half
    else:
        return half * half * base
```

**Time**: O(log n)

### 5. Reverse String
```python
def reverse(s):
    if len(s) <= 1:
        return s
    return s[-1] + reverse(s[:-1])
```

## Types of Recursion

### 1. Linear Recursion
Single recursive call

```python
def linear_recursion(n):
    if n == 0:
        return
    # Do something
    linear_recursion(n - 1)
```

**Examples**: Factorial, sum, reverse

### 2. Binary Recursion
Two recursive calls

```python
def binary_recursion(n):
    if n == 0:
        return
    binary_recursion(n - 1)
    binary_recursion(n - 1)
```

**Examples**: Fibonacci, Tower of Hanoi, Binary tree traversal

### 3. Tail Recursion
Recursive call is the last operation

```python
def tail_factorial(n, accumulator=1):
    if n <= 1:
        return accumulator
    return tail_factorial(n - 1, n * accumulator)
```

**Benefit**: Some languages can optimize to avoid stack growth

### 4. Multiple Recursion
More than two recursive calls

```python
def multiple_recursion(n):
    if n == 0:
        return
    for i in range(n):
        multiple_recursion(i)
```

**Examples**: Tree with multiple children

### 5. Mutual Recursion
Functions call each other

```python
def is_even(n):
    if n == 0:
        return True
    return is_odd(n - 1)

def is_odd(n):
    if n == 0:
        return False
    return is_even(n - 1)
```

## Recursion vs Iteration

| Aspect | Recursion | Iteration |
|--------|-----------|-----------|
| Code | Often cleaner, more intuitive | Can be more verbose |
| Space | O(n) for call stack | O(1) usually |
| Performance | Function call overhead | Generally faster |
| Problem Fit | Trees, graphs, divide-and-conquer | Simple loops |

### Converting Recursion to Iteration

**Recursion**:
```python
def sum_recursive(n):
    if n == 0:
        return 0
    return n + sum_recursive(n - 1)
```

**Iteration**:
```python
def sum_iterative(n):
    total = 0
    for i in range(n + 1):
        total += i
    return total
```

**With Stack** (for complex cases):
```python
def iterative_with_stack(root):
    if not root:
        return
    stack = [root]
    while stack:
        node = stack.pop()
        # Process node
        if node.right:
            stack.append(node.right)
        if node.left:
            stack.append(node.left)
```

## Common Recursion Patterns

### Pattern 1: Decrease and Conquer
Solve smaller problem, combine with current

**Example**: Binary search, merge sort

### Pattern 2: Divide and Conquer
Split into subproblems, solve, merge

**Example**: Quick sort, merge sort

### Pattern 3: Backtracking
Try all possibilities, backtrack on failure

**Example**: N-Queens, Sudoku, permutations

### Pattern 4: Top-Down (Memoization)
Solve recursively, cache results

**Example**: DP problems

## Backtracking Template

```python
def backtrack(path, choices):
    if is_solution(path):
        result.append(path[:])
        return

    for choice in choices:
        # Make choice
        path.append(choice)

        # Recurse
        backtrack(path, get_next_choices())

        # Undo choice (backtrack)
        path.pop()
```

**Examples**:
- Generate all permutations
- Generate all subsets
- N-Queens problem
- Sudoku solver

## Advanced Recursion

### Permutations
```python
def permute(nums):
    result = []

    def backtrack(path, remaining):
        if not remaining:
            result.append(path[:])
            return

        for i in range(len(remaining)):
            path.append(remaining[i])
            backtrack(path, remaining[:i] + remaining[i+1:])
            path.pop()

    backtrack([], nums)
    return result
```

### Subsets
```python
def subsets(nums):
    result = []

    def backtrack(start, path):
        result.append(path[:])

        for i in range(start, len(nums)):
            path.append(nums[i])
            backtrack(i + 1, path)
            path.pop()

    backtrack(0, [])
    return result
```

### Tree Traversals
```python
def inorder(root):
    if not root:
        return []
    return inorder(root.left) + [root.val] + inorder(root.right)

def preorder(root):
    if not root:
        return []
    return [root.val] + preorder(root.left) + preorder(root.right)

def postorder(root):
    if not root:
        return []
    return postorder(root.left) + postorder(root.right) + [root.val]
```

## Debugging Recursion

### 1. Trace the Calls
```python
def factorial(n, depth=0):
    indent = "  " * depth
    print(f"{indent}factorial({n})")
    if n <= 1:
        print(f"{indent}return 1")
        return 1
    result = n * factorial(n-1, depth+1)
    print(f"{indent}return {result}")
    return result
```

### 2. Verify Base Cases
- Check if all base cases are covered
- Ensure base cases return correct values

### 3. Check Progress
- Ensure each call moves toward base case
- Verify parameters are modified correctly

## Interview Tips

### When to Use Recursion
- Problem has recursive structure (trees, graphs)
- Naturally divides into similar subproblems
- Backtracking needed
- Code clarity over performance

### Common Mistakes
1. **No base case** → Infinite recursion
2. **Wrong base case** → Incorrect results
3. **Not making progress** → Stack overflow
4. **Modifying global state** → Unexpected behavior
5. **Forgetting to return** → None results

### Optimization Techniques
1. **Memoization**: Cache results
2. **Tail recursion**: Some compilers optimize
3. **Convert to iteration**: If stack space is concern
4. **Pruning**: Skip unnecessary recursive calls

### Complexity Analysis
- **Time**: Count recursive calls × work per call
- **Space**: Maximum depth of recursion tree
- Draw recursion tree to visualize

## Related Topics
- Dynamic Programming (memoization)
- Backtracking
- Tree/Graph traversal
- Divide and Conquer
- Call stack
