# Pattern Printing

## Overview
Pattern printing problems involve printing numbers, characters, or symbols in specific geometric shapes. These problems help develop understanding of nested loops, spacing, and symmetry.

## Why Practice Pattern Problems?

1. **Loop Control**: Master nested loops and iteration
2. **Logic Building**: Develop problem-solving approach
3. **Index Manipulation**: Understand row-column relationships
4. **Edge Cases**: Handle first/last rows and columns
5. **Visualization**: Improve spatial thinking
6. **Interview Basics**: Common in beginner-level interviews

## Basic Patterns

### 1. Right Triangle (Increasing)
```
*
**
***
****
*****
```

```python
def right_triangle(n):
    for i in range(1, n + 1):
        print('*' * i)
```

### 2. Right Triangle (Numbers)
```
1
12
123
1234
12345
```

```python
def number_triangle(n):
    for i in range(1, n + 1):
        for j in range(1, i + 1):
            print(j, end='')
        print()
```

### 3. Right Triangle (Decreasing)
```
*****
****
***
**
*
```

```python
def inverted_triangle(n):
    for i in range(n, 0, -1):
        print('*' * i)
```

### 4. Square Pattern
```
*****
*****
*****
*****
*****
```

```python
def square(n):
    for i in range(n):
        print('*' * n)
```

### 5. Hollow Square
```
*****
*   *
*   *
*   *
*****
```

```python
def hollow_square(n):
    for i in range(n):
        if i == 0 or i == n - 1:
            print('*' * n)
        else:
            print('*' + ' ' * (n - 2) + '*')
```

## Pyramid Patterns

### 1. Centered Pyramid
```
    *
   ***
  *****
 *******
*********
```

```python
def pyramid(n):
    for i in range(n):
        # Spaces
        print(' ' * (n - i - 1), end='')
        # Stars
        print('*' * (2 * i + 1))
```

**Formula**:
- Spaces: `n - i - 1`
- Stars: `2 * i + 1`

### 2. Inverted Pyramid
```
*********
 *******
  *****
   ***
    *
```

```python
def inverted_pyramid(n):
    for i in range(n, 0, -1):
        print(' ' * (n - i), end='')
        print('*' * (2 * i - 1))
```

### 3. Number Pyramid
```
    1
   121
  12321
 1234321
123454321
```

```python
def number_pyramid(n):
    for i in range(1, n + 1):
        # Spaces
        print(' ' * (n - i), end='')
        # Ascending
        for j in range(1, i + 1):
            print(j, end='')
        # Descending
        for j in range(i - 1, 0, -1):
            print(j, end='')
        print()
```

### 4. Diamond Pattern
```
    *
   ***
  *****
 *******
*********
 *******
  *****
   ***
    *
```

```python
def diamond(n):
    # Upper half (pyramid)
    for i in range(n):
        print(' ' * (n - i - 1) + '*' * (2 * i + 1))

    # Lower half (inverted pyramid)
    for i in range(n - 2, -1, -1):
        print(' ' * (n - i - 1) + '*' * (2 * i + 1))
```

## Number Patterns

### 1. Floyd's Triangle
```
1
2 3
4 5 6
7 8 9 10
11 12 13 14 15
```

```python
def floyds_triangle(n):
    num = 1
    for i in range(1, n + 1):
        for j in range(i):
            print(num, end=' ')
            num += 1
        print()
```

### 2. Pascal's Triangle
```
    1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1
```

```python
def pascals_triangle(n):
    triangle = [[1]]

    for i in range(1, n):
        row = [1]
        for j in range(1, i):
            row.append(triangle[i-1][j-1] + triangle[i-1][j])
        row.append(1)
        triangle.append(row)

    # Print
    for i in range(n):
        print(' ' * (n - i - 1), end='')
        print(' '.join(map(str, triangle[i])))
```

**Formula**: C(n, k) = C(n-1, k-1) + C(n-1, k)

### 3. Number Spiral
```
1  2  3  4  5
16 17 18 19 6
15 24 25 20 7
14 23 22 21 8
13 12 11 10 9
```

```python
def number_spiral(n):
    matrix = [[0] * n for _ in range(n)]
    num = 1
    top, bottom, left, right = 0, n - 1, 0, n - 1

    while top <= bottom and left <= right:
        # Right
        for i in range(left, right + 1):
            matrix[top][i] = num
            num += 1
        top += 1

        # Down
        for i in range(top, bottom + 1):
            matrix[i][right] = num
            num += 1
        right -= 1

        # Left
        if top <= bottom:
            for i in range(right, left - 1, -1):
                matrix[bottom][i] = num
                num += 1
            bottom -= 1

        # Up
        if left <= right:
            for i in range(bottom, top - 1, -1):
                matrix[i][left] = num
                num += 1
            left += 1

    # Print
    for row in matrix:
        print(' '.join(f'{x:2}' for x in row))
```

## Advanced Patterns

### 1. Hourglass (Bow-tie)
```
*********
 *******
  *****
   ***
    *
   ***
  *****
 *******
*********
```

```python
def hourglass(n):
    # Upper part
    for i in range(n, 0, -1):
        print(' ' * (n - i) + '*' * (2 * i - 1))

    # Lower part
    for i in range(2, n + 1):
        print(' ' * (n - i) + '*' * (2 * i - 1))
```

### 2. Hollow Diamond
```
    *
   * *
  *   *
 *     *
*       *
 *     *
  *   *
   * *
    *
```

```python
def hollow_diamond(n):
    # Upper half
    for i in range(n):
        print(' ' * (n - i - 1), end='')
        if i == 0:
            print('*')
        else:
            print('*' + ' ' * (2 * i - 1) + '*')

    # Lower half
    for i in range(n - 2, -1, -1):
        print(' ' * (n - i - 1), end='')
        if i == 0:
            print('*')
        else:
            print('*' + ' ' * (2 * i - 1) + '*')
```

### 3. Alphabetic Pyramid
```
    A
   ABA
  ABCBA
 ABCDCBA
ABCDEDCBA
```

```python
def alphabet_pyramid(n):
    for i in range(n):
        print(' ' * (n - i - 1), end='')
        # Ascending
        for j in range(i + 1):
            print(chr(65 + j), end='')
        # Descending
        for j in range(i - 1, -1, -1):
            print(chr(65 + j), end='')
        print()
```

## Problem-Solving Approach

### 1. Analyze the Pattern
- Number of rows
- Number of columns
- What changes per row?
- Any symmetry?

### 2. Identify Components
- Leading spaces
- Pattern elements (numbers, stars, etc.)
- Trailing spaces (usually not needed)

### 3. Find Formulas
For row `i` (0-indexed):
- **Pyramid spaces**: `n - i - 1`
- **Pyramid stars**: `2 * i + 1`
- **Triangle elements**: `i + 1`
- **Center to edge**: `abs(center - current_position)`

### 4. Handle Special Cases
- First row
- Last row
- Hollow patterns (edges only)
- Symmetric patterns (mirror)

## Common Formulas

### For Pyramid (n rows, 0-indexed i)
```python
spaces = n - i - 1
stars = 2 * i + 1
```

### For Diamond (2n-1 rows)
```python
# Upper half (i from 0 to n-1)
spaces = n - i - 1
stars = 2 * i + 1

# Lower half (i from n-2 to 0)
spaces = n - i - 1
stars = 2 * i + 1
```

### For Right Triangle
```python
# Row i (1-indexed)
elements = i
```

## Interview Tips

### 1. Start Simple
- Begin with basic patterns (triangle, square)
- Then add complexity (pyramid, diamond)
- Finally hollowness or special symbols

### 2. Test Small Values
- Test with n=1, n=2, n=3
- Verify edge cases
- Check spacing and alignment

### 3. Optimize Code
- Avoid redundant calculations
- Use string multiplication when possible
- Combine loops if applicable

### 4. Common Mistakes
- Off-by-one errors in loop ranges
- Wrong space calculation
- Mixing 0-indexed and 1-indexed
- Forgetting newline after each row

## Complexity

- **Time**: O(n²) for most patterns (nested loops)
- **Space**: O(1) if printing directly, O(n²) if building matrix

## Beyond Basics

These pattern problems are stepping stones to:
- Matrix manipulation
- 2D array problems
- Spiral traversal
- Image processing concepts
- Game board representations

## Practice Problems

### Easy
- Print right triangle
- Print square
- Print pyramid

### Medium
- Print diamond
- Print hollow patterns
- Print number spirals
- Floyd's triangle

### Hard
- Pascal's triangle
- Alphabetic diamond
- Custom patterns with constraints
- Hexagonal patterns
- Snake patterns in matrix
