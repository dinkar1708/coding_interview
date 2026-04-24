# Fibonacci Numbers

## Overview
The Fibonacci sequence is a classic introduction to dynamic programming. Each number is the sum of the two preceding ones: F(n) = F(n-1) + F(n-2).

Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89...

## Implementations

### 1. Recursive (Naive)
```python
def fib(n):
    if n <= 1: return n
    return fib(n-1) + fib(n-2)
```
- **Time Complexity**: O(2^n) - exponential!
- **Space Complexity**: O(n) - recursion stack
- **Not recommended** for n > 40

### 2. Top-Down (Memoization)
```python
def fib(n, memo={}):
    if n <= 1: return n
    if n not in memo:
        memo[n] = fib(n-1, memo) + fib(n-2, memo)
    return memo[n]
```
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- Cache results to avoid recomputation

### 3. Bottom-Up (Tabulation)
```python
def fib(n):
    if n <= 1: return n
    dp = [0] * (n + 1)
    dp[1] = 1
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]
```
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- Iterative approach, builds solution from bottom

### 4. Space Optimized
```python
def fib(n):
    if n <= 1: return n
    prev2, prev1 = 0, 1
    for i in range(2, n + 1):
        curr = prev1 + prev2
        prev2, prev1 = prev1, curr
    return prev1
```
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- Only keep last two values

### 5. Matrix Exponentiation
- Uses matrix multiplication: [[F(n+1), F(n)], [F(n), F(n-1)]]
- **Time Complexity**: O(log n)
- **Space Complexity**: O(1)
- Best for very large n

### 6. Mathematical Formula (Binet's)
```python
phi = (1 + sqrt(5)) / 2
fib(n) = round(phi^n / sqrt(5))
```
- **Time Complexity**: O(1)
- **Space Complexity**: O(1)
- Precision issues for large n

## Key Learning Points
1. Shows evolution from naive recursion to optimal solutions
2. Demonstrates memoization vs tabulation
3. Illustrates space optimization techniques
4. Foundation for understanding DP patterns

## Related Problems
- Climbing stairs (same as Fibonacci)
- Count ways to decode string
- Min cost climbing stairs
- House Robber (Fibonacci variant)
