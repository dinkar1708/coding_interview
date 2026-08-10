# Array Rotation

## Overview
Array rotation involves shifting elements of an array to the left or right by a specified number of positions. This is a fundamental array manipulation technique commonly asked in coding interviews.

## Key Concepts
- **Left Rotation**: Move elements towards the beginning of the array
- **Right Rotation**: Move elements towards the end of the array
- **Cyclic Rotation**: Elements that fall off one end appear at the other end

## Common Approaches

### 1. Brute Force (One by One)
- Rotate array one element at a time
- **Time Complexity**: O(n × d) where d is rotation count
- **Space Complexity**: O(1)

### 2. Using Temporary Array
- Store elements in a temporary array and rearrange
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)

### 3. Juggling Algorithm
- Divide array into sets and rotate elements within each set
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- Most efficient for multiple rotations

### 4. Using Modulus Operator
- Calculate new positions using modulus arithmetic
- **Time Complexity**: O(n)
- **Space Complexity**: O(n) or O(1) depending on implementation

## When to Use
- Circular array problems
- Queue/buffer implementations
- String rotation problems
- Shift/rotate operations in games or simulations

## Practice Problems
- Rotate array left/right by k positions
- Check if one string is rotation of another
- Maximum sum of array after k rotations
