# Mathematical Algorithms

## Overview
Mathematical algorithms solve problems using mathematical concepts, formulas, and number theory. These are essential for many coding interviews.

## Number Theory

### 1. Prime Numbers

#### Check if Prime
```python
def is_prime(n):
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False

    # Check odd divisors up to sqrt(n)
    for i in range(3, int(n**0.5) + 1, 2):
        if n % i == 0:
            return False
    return True
```

**Time**: O(√n)

#### Sieve of Eratosthenes (All Primes up to N)
```python
def sieve_of_eratosthenes(n):
    is_prime = [True] * (n + 1)
    is_prime[0] = is_prime[1] = False

    for i in range(2, int(n**0.5) + 1):
        if is_prime[i]:
            # Mark multiples as non-prime
            for j in range(i*i, n + 1, i):
                is_prime[j] = False

    return [i for i in range(n + 1) if is_prime[i]]
```

**Time**: O(n log log n), **Space**: O(n)

#### Count Prime Factors
```python
def count_factors(n):
    count = 0
    # Check for 2s
    while n % 2 == 0:
        count += 1
        n //= 2

    # Check odd factors
    for i in range(3, int(n**0.5) + 1, 2):
        while n % i == 0:
            count += 1
            n //= i

    # If n is still > 1, it's a prime factor
    if n > 1:
        count += 1

    return count
```

### 2. GCD and LCM

#### Greatest Common Divisor (Euclidean Algorithm)
```python
def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

# Recursive version
def gcd_recursive(a, b):
    if b == 0:
        return a
    return gcd_recursive(b, a % b)
```

**Time**: O(log min(a, b))

#### Least Common Multiple
```python
def lcm(a, b):
    return (a * b) // gcd(a, b)
```

### 3. Modular Arithmetic

```python
# Modular exponentiation (a^b mod m)
def power_mod(a, b, m):
    result = 1
    a = a % m

    while b > 0:
        if b % 2 == 1:
            result = (result * a) % m
        b = b >> 1  # Divide by 2
        a = (a * a) % m

    return result
```

**Time**: O(log b)

**Use**: Large exponentials, RSA, cryptography

## Combinatorics

### 1. Factorial
```python
def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)

# Iterative
def factorial_iter(n):
    result = 1
    for i in range(2, n + 1):
        result *= i
    return result
```

### 2. Combinations (nCr)
```python
def combination(n, r):
    if r > n:
        return 0
    if r == 0 or r == n:
        return 1

    # Use smaller r for efficiency
    r = min(r, n - r)

    result = 1
    for i in range(r):
        result = result * (n - i) // (i + 1)

    return result
```

**Formula**: C(n, r) = n! / (r! × (n-r)!)

### 3. Permutations (nPr)
```python
def permutation(n, r):
    if r > n:
        return 0
    result = 1
    for i in range(n, n - r, -1):
        result *= i
    return result
```

**Formula**: P(n, r) = n! / (n-r)!

## Special Sequences

### 1. Fibonacci
Already covered in DP section

### 2. Catalan Numbers
```python
def catalan(n):
    """nth Catalan number"""
    if n <= 1:
        return 1

    catalan = [0] * (n + 1)
    catalan[0] = catalan[1] = 1

    for i in range(2, n + 1):
        for j in range(i):
            catalan[i] += catalan[j] * catalan[i-1-j]

    return catalan[n]
```

**Formula**: C(n) = C(0)×C(n-1) + C(1)×C(n-2) + ... + C(n-1)×C(0)

**Or**: C(n) = (2n)! / ((n+1)! × n!)

**Applications**: Number of BSTs, valid parentheses, etc.

## Geometry

### 1. Distance
```python
# Euclidean distance
def distance(p1, p2):
    return ((p1[0]-p2[0])**2 + (p1[1]-p2[1])**2)**0.5

# Manhattan distance
def manhattan(p1, p2):
    return abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])
```

### 2. Area of Triangle
```python
def triangle_area(x1, y1, x2, y2, x3, y3):
    return abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2.0)
```

### 3. Check if Point in Triangle
```python
def point_in_triangle(px, py, x1, y1, x2, y2, x3, y3):
    """Using area method"""
    area = triangle_area(x1, y1, x2, y2, x3, y3)
    area1 = triangle_area(px, py, x2, y2, x3, y3)
    area2 = triangle_area(x1, y1, px, py, x3, y3)
    area3 = triangle_area(x1, y1, x2, y2, px, py)

    return abs(area - (area1 + area2 + area3)) < 1e-10
```

## Common Math Problems

### 1. Power of Two/Three/N
```python
def is_power_of_two(n):
    return n > 0 and (n & (n - 1)) == 0

def is_power_of_three(n):
    if n < 1:
        return False
    while n % 3 == 0:
        n //= 3
    return n == 1
```

### 2. Perfect Square
```python
def is_perfect_square(n):
    if n < 0:
        return False
    root = int(n ** 0.5)
    return root * root == n
```

### 3. Sum of Divisors
```python
def sum_of_divisors(n):
    total = 0
    for i in range(1, int(n**0.5) + 1):
        if n % i == 0:
            total += i
            if i != n // i:
                total += n // i
    return total
```

### 4. Happy Number
```python
def is_happy(n):
    def get_next(num):
        total = 0
        while num > 0:
            digit = num % 10
            total += digit * digit
            num //= 10
        return total

    seen = set()
    while n != 1 and n not in seen:
        seen.add(n)
        n = get_next(n)

    return n == 1
```

### 5. Trailing Zeros in Factorial
```python
def trailing_zeros(n):
    """Count trailing zeros in n!"""
    count = 0
    power_of_5 = 5
    while power_of_5 <= n:
        count += n // power_of_5
        power_of_5 *= 5
    return count
```

**Logic**: Count factors of 5 (there are always more 2s)

### 6. Ugly Number
Numbers whose only prime factors are 2, 3, 5

```python
def is_ugly(n):
    if n <= 0:
        return False
    for p in [2, 3, 5]:
        while n % p == 0:
            n //= p
    return n == 1
```

## Mathematical Tricks

### 1. Sum Formulas
```python
# Sum of first n natural numbers
sum_n = n * (n + 1) // 2

# Sum of squares
sum_squares = n * (n + 1) * (2*n + 1) // 6

# Sum of cubes
sum_cubes = (n * (n + 1) // 2) ** 2
```

### 2. Missing Number
```python
def missing_number(nums):
    """Find missing number in range [0, n]"""
    n = len(nums)
    expected_sum = n * (n + 1) // 2
    actual_sum = sum(nums)
    return expected_sum - actual_sum
```

### 3. Single Number (XOR Trick)
```python
def single_number(nums):
    """Find number appearing once, others twice"""
    result = 0
    for num in nums:
        result ^= num
    return result
```

**Property**: a ^ a = 0, a ^ 0 = a

## Game Theory

### 1. Nim Game
```python
def can_win_nim(n):
    """Can you win if both play optimally?"""
    return n % 4 != 0
```

### 2. Stone Game (DP version in DP section)

## Interview Tips

### When to Use Math
- Problem mentions: prime, divisor, factorial, power
- Number theory concepts
- Geometric calculations
- Counting/combinatorics problems
- Game theory "optimal play"

### Common Patterns
1. **Iterate up to √n**: For divisors, primes
2. **Use formulas**: Don't compute when formula exists
3. **Modular arithmetic**: For large numbers
4. **XOR properties**: For pair problems
5. **Mathematical induction**: For proofs

### Optimization
- Pre-compute when possible (e.g., sieve for primes)
- Use formulas over iteration
- Modular exponentiation for large powers
- Early termination in loops

### Edge Cases
- Negative numbers
- Zero
- Very large numbers (overflow)
- Division by zero
- Non-integer results

## Related Topics
- Dynamic Programming (Catalan, Fibonacci)
- Bit Manipulation (XOR, power of 2)
- Number Theory
- Combinatorics
- Probability
