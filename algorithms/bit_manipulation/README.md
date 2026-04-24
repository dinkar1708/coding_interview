# Bit Manipulation

## Overview
Bit manipulation involves operations on individual bits of numbers. These techniques are fast, memory-efficient, and frequently asked in coding interviews.

## Basic Bitwise Operators

### AND (&)
```python
5 & 3    # 0101 & 0011 = 0001 = 1
```
**Use**: Check if bit is set, clear bits, masking

### OR (|)
```python
5 | 3    # 0101 | 0011 = 0111 = 7
```
**Use**: Set bits, combine flags

### XOR (^)
```python
5 ^ 3    # 0101 ^ 0011 = 0110 = 6
```
**Properties**:
- a ^ a = 0
- a ^ 0 = a
- XOR is commutative and associative

**Use**: Flip bits, find unique element, swap without temp

### NOT (~)
```python
~5       # ~0101 = ...11111010 (two's complement)
```
**Use**: Invert all bits

### Left Shift (<<)
```python
5 << 1   # 0101 << 1 = 1010 = 10
```
**Effect**: Multiply by 2^n
```python
x << n   # x * 2^n
```

### Right Shift (>>)
```python
5 >> 1   # 0101 >> 1 = 0010 = 2
```
**Effect**: Divide by 2^n (integer division)
```python
x >> n   # x // 2^n
```

## Common Bit Tricks

### 1. Check if Bit is Set
```python
def is_bit_set(num, i):
    """Check if ith bit (0-indexed from right) is set"""
    return (num & (1 << i)) != 0
```

### 2. Set a Bit
```python
def set_bit(num, i):
    """Set ith bit to 1"""
    return num | (1 << i)
```

### 3. Clear a Bit
```python
def clear_bit(num, i):
    """Set ith bit to 0"""
    return num & ~(1 << i)
```

### 4. Toggle a Bit
```python
def toggle_bit(num, i):
    """Flip ith bit"""
    return num ^ (1 << i)
```

### 5. Check if Power of 2
```python
def is_power_of_2(n):
    """Power of 2 has exactly one bit set"""
    return n > 0 and (n & (n - 1)) == 0
```

**Why it works**: n-1 flips all bits after the rightmost set bit
- 8 = 1000, 7 = 0111, 8 & 7 = 0000
- 6 = 0110, 5 = 0101, 6 & 5 = 0100 (not 0)

### 6. Count Set Bits (Hamming Weight)
```python
def count_set_bits(n):
    count = 0
    while n:
        count += n & 1
        n >>= 1
    return count

# Brian Kernighan's Algorithm (faster)
def count_set_bits_fast(n):
    count = 0
    while n:
        n &= n - 1  # Clears rightmost set bit
        count += 1
    return count
```

**Time**: O(number of set bits)

### 7. Clear Rightmost Set Bit
```python
def clear_rightmost_set_bit(n):
    return n & (n - 1)
```

### 8. Isolate Rightmost Set Bit
```python
def isolate_rightmost_set_bit(n):
    return n & (-n)
```

**Example**: 12 (1100) → 4 (0100)

### 9. Swap Two Numbers Without Temp
```python
def swap(a, b):
    a = a ^ b
    b = a ^ b  # b = a ^ b ^ b = a
    a = a ^ b  # a = a ^ b ^ a = b
    return a, b
```

### 10. Check if Opposite Signs
```python
def opposite_signs(x, y):
    return (x ^ y) < 0
```

**Why**: MSB differs for opposite signs

## Common Problems

### 1. Single Number
**Problem**: Find number appearing once when others appear twice

```python
def single_number(nums):
    result = 0
    for num in nums:
        result ^= num
    return result
```

**Time**: O(n), **Space**: O(1)

### 2. Missing Number
**Problem**: Find missing number in range [0, n]

```python
def missing_number(nums):
    result = len(nums)
    for i, num in enumerate(nums):
        result ^= i ^ num
    return result
```

### 3. Reverse Bits
```python
def reverse_bits(n):
    result = 0
    for i in range(32):
        result <<= 1
        result |= n & 1
        n >>= 1
    return result
```

### 4. Hamming Distance
**Problem**: Number of differing bits between two numbers

```python
def hamming_distance(x, y):
    xor = x ^ y
    count = 0
    while xor:
        count += xor & 1
        xor >>= 1
    return count
```

### 5. Binary Gap
**Problem**: Longest sequence of zeros between 1s

```python
def binary_gap(n):
    binary = bin(n)[2:]  # Remove '0b'
    gaps = binary.split('1')[1:-1]  # Get gaps between 1s
    return max(len(gap) for gap in gaps) if gaps else 0
```

### 6. Sum of Two Integers (Without +/-)
```python
def get_sum(a, b):
    """Add two integers using bit manipulation"""
    # 32-bit mask
    mask = 0xFFFFFFFF

    while b != 0:
        # Carry
        carry = (a & b) << 1
        # Sum without carry
        a = (a ^ b) & mask
        b = carry & mask

    # Handle negative
    return a if a <= 0x7FFFFFFF else ~(a ^ mask)
```

### 7. Power Set (All Subsets)
```python
def subsets(nums):
    """Generate all subsets using bit manipulation"""
    n = len(nums)
    result = []

    for i in range(2**n):
        subset = []
        for j in range(n):
            if i & (1 << j):
                subset.append(nums[j])
        result.append(subset)

    return result
```

**Time**: O(n × 2^n)

### 8. UTF-8 Validation
Check if byte array is valid UTF-8

```python
def valid_utf8(data):
    n_bytes = 0

    for num in data:
        if n_bytes == 0:
            # Count leading 1s
            if (num >> 5) == 0b110:
                n_bytes = 1
            elif (num >> 4) == 0b1110:
                n_bytes = 2
            elif (num >> 3) == 0b11110:
                n_bytes = 3
            elif (num >> 7):
                return False
        else:
            # Check if continuation byte
            if (num >> 6) != 0b10:
                return False
            n_bytes -= 1

    return n_bytes == 0
```

## Advanced Techniques

### 1. Get All Subsets of Size K
```python
def combinations_k_bits(n, k):
    """Get all k-bit combinations of n bits"""
    result = []
    num = (1 << k) - 1  # Start with k rightmost bits set

    while num < (1 << n):
        result.append(num)
        # Gosper's hack
        c = num & -num
        r = num + c
        num = (((r ^ num) >> 2) // c) | r

    return result
```

### 2. Count Bits from 0 to N
```python
def count_bits(n):
    """Count set bits for all numbers from 0 to n"""
    result = [0] * (n + 1)
    for i in range(1, n + 1):
        result[i] = result[i >> 1] + (i & 1)
    return result
```

**Pattern**: count[i] = count[i//2] + (i % 2)

### 3. Bitwise AND of Range
```python
def range_bitwise_and(left, right):
    """Bitwise AND of all numbers from left to right"""
    shift = 0
    while left < right:
        left >>= 1
        right >>= 1
        shift += 1
    return left << shift
```

**Logic**: Find common prefix of left and right

## Bitmask DP

### Traveling Salesman (simplified)
```python
def tsp(graph):
    n = len(graph)
    # dp[mask][i] = min cost to visit cities in mask, ending at i
    dp = [[float('inf')] * n for _ in range(1 << n)]
    dp[1][0] = 0  # Start at city 0

    for mask in range(1 << n):
        for i in range(n):
            if not (mask & (1 << i)):
                continue
            for j in range(n):
                if mask & (1 << j):
                    continue
                new_mask = mask | (1 << j)
                dp[new_mask][j] = min(dp[new_mask][j],
                                      dp[mask][i] + graph[i][j])

    return min(dp[(1<<n)-1][i] + graph[i][0] for i in range(n))
```

## Interview Tips

### When to Use Bit Manipulation
- Problem mentions: bits, binary, flags, subsets
- Need O(1) space for tracking state
- Optimization of existing solution
- Low-level programming
- Cryptography/hashing

### Common Patterns
1. **XOR**: Finding unique elements, swapping
2. **AND/OR**: Masking, setting/clearing bits
3. **Shifts**: Multiply/divide by powers of 2
4. **n & (n-1)**: Remove rightmost set bit
5. **n & -n**: Isolate rightmost set bit
6. **Bitmask**: Represent subsets, states

### Complexity
- Most bit operations: O(1)
- Iterating through bits: O(log n) or O(32) for 32-bit
- Generating subsets: O(2^n)

### Common Mistakes
- Forgetting operator precedence (use parentheses!)
- Assuming 32-bit when it might be 64-bit
- Sign issues with right shift (arithmetic vs logical)
- Not handling negative numbers correctly

### Testing
- Test with powers of 2
- Test with all bits set (e.g., -1 or 0xFFFFFFFF)
- Test with 0
- Test boundary values

## Related Topics
- Boolean algebra
- Set operations
- Hashing
- Compression
- Cryptography
