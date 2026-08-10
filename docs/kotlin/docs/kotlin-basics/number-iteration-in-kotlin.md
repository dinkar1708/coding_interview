# Number Iteration in Kotlin

## Overview
Number manipulation and iteration patterns are fundamental for solving mathematical and algorithmic problems in coding interviews.

## Basic Ranges

### Integer Ranges
```kotlin
// Inclusive range (1 to 10)
for (i in 1..10) {
    println(i)  // 1, 2, 3, ..., 10
}

// Until (exclusive end)
for (i in 1 until 10) {
    println(i)  // 1, 2, 3, ..., 9
}

// Downward range
for (i in 10 downTo 1) {
    println(i)  // 10, 9, 8, ..., 1
}

// With step
for (i in 1..10 step 2) {
    println(i)  // 1, 3, 5, 7, 9
}

for (i in 10 downTo 1 step 2) {
    println(i)  // 10, 8, 6, 4, 2
}
```

### Range Operations
```kotlin
val range = 1..10

// Check if number in range
val contains = 5 in range  // true
val contains = 15 in range  // false

// Convert to list
val list = (1..10).toList()

// Map/filter
val doubled = (1..10).map { it * 2 }
val evens = (1..10).filter { it % 2 == 0 }
```

## Digit Manipulation

### Extract Digits
```kotlin
fun extractDigits(n: Int): List<Int> {
    var num = Math.abs(n)
    val digits = mutableListOf<Int>()

    if (num == 0) return listOf(0)

    while (num > 0) {
        digits.add(num % 10)
        num /= 10
    }

    return digits.reversed()  // Return in original order
}

// Alternative using string
fun extractDigits(n: Int): List<Int> {
    return n.toString().map { it.digitToInt() }
}
```

### Count Digits
```kotlin
fun countDigits(n: Int): Int {
    if (n == 0) return 1

    var count = 0
    var num = Math.abs(n)

    while (num > 0) {
        count++
        num /= 10
    }

    return count
}

// Alternative
fun countDigits(n: Int): Int {
    return n.toString().length
}

// Mathematical approach
fun countDigits(n: Int): Int {
    if (n == 0) return 1
    return (Math.log10(Math.abs(n.toDouble())) + 1).toInt()
}
```

### Reverse Number
```kotlin
fun reverseNumber(n: Int): Int {
    var num = Math.abs(n)
    var reversed = 0

    while (num > 0) {
        reversed = reversed * 10 + num % 10
        num /= 10
    }

    return if (n < 0) -reversed else reversed
}
```

### Check Palindrome
```kotlin
fun isPalindrome(n: Int): Boolean {
    if (n < 0) return false
    return n == reverseNumber(n)
}
```

### Sum of Digits
```kotlin
fun sumOfDigits(n: Int): Int {
    var num = Math.abs(n)
    var sum = 0

    while (num > 0) {
        sum += num % 10
        num /= 10
    }

    return sum
}

// Alternative
fun sumOfDigits(n: Int): Int {
    return n.toString().map { it.digitToInt() }.sum()
}
```

## Prime Numbers

### Check if Prime
```kotlin
fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    if (n <= 3) return true
    if (n % 2 == 0 || n % 3 == 0) return false

    var i = 5
    while (i * i <= n) {
        if (n % i == 0 || n % (i + 2) == 0) return false
        i += 6
    }

    return true
}
```

### Sieve of Eratosthenes
```kotlin
fun sieveOfEratosthenes(n: Int): List<Int> {
    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i..n step i) {
                isPrime[j] = false
            }
        }
    }

    return (2..n).filter { isPrime[it] }
}
```

## Factorization

### Find All Factors
```kotlin
fun getFactors(n: Int): List<Int> {
    val factors = mutableListOf<Int>()

    for (i in 1..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            factors.add(i)
            if (i != n / i) {
                factors.add(n / i)
            }
        }
    }

    return factors.sorted()
}
```

### Prime Factorization
```kotlin
fun primeFactors(n: Int): List<Int> {
    var num = n
    val factors = mutableListOf<Int>()

    // Factor out 2s
    while (num % 2 == 0) {
        factors.add(2)
        num /= 2
    }

    // Factor out odd numbers
    var i = 3
    while (i * i <= num) {
        while (num % i == 0) {
            factors.add(i)
            num /= i
        }
        i += 2
    }

    if (num > 2) {
        factors.add(num)
    }

    return factors
}
```

## GCD and LCM

### Greatest Common Divisor
```kotlin
fun gcd(a: Int, b: Int): Int {
    var x = Math.abs(a)
    var y = Math.abs(b)

    while (y != 0) {
        val temp = y
        y = x % y
        x = temp
    }

    return x
}

// Recursive
fun gcdRecursive(a: Int, b: Int): Int {
    return if (b == 0) Math.abs(a) else gcdRecursive(b, a % b)
}
```

### Least Common Multiple
```kotlin
fun lcm(a: Int, b: Int): Int {
    return Math.abs(a * b) / gcd(a, b)
}
```

## Power and Exponentiation

### Power Calculation
```kotlin
// Basic
fun power(base: Int, exp: Int): Long {
    var result = 1L
    repeat(exp) {
        result *= base
    }
    return result
}

// Optimized (Binary Exponentiation)
fun powerOptimized(base: Long, exp: Int): Long {
    if (exp == 0) return 1

    var result = 1L
    var b = base
    var e = exp

    while (e > 0) {
        if (e % 2 == 1) {
            result *= b
        }
        b *= b
        e /= 2
    }

    return result
}

// With modulo
fun powerMod(base: Long, exp: Int, mod: Int): Long {
    var result = 1L
    var b = base % mod
    var e = exp

    while (e > 0) {
        if (e % 2 == 1) {
            result = (result * b) % mod
        }
        b = (b * b) % mod
        e /= 2
    }

    return result
}
```

## Fibonacci

### Iterative
```kotlin
fun fibonacci(n: Int): Long {
    if (n <= 1) return n.toLong()

    var prev = 0L
    var curr = 1L

    repeat(n - 1) {
        val next = prev + curr
        prev = curr
        curr = next
    }

    return curr
}
```

### With Memoization
```kotlin
fun fibonacci(n: Int, memo: MutableMap<Int, Long> = mutableMapOf()): Long {
    if (n <= 1) return n.toLong()
    if (n in memo) return memo[n]!!

    val result = fibonacci(n - 1, memo) + fibonacci(n - 2, memo)
    memo[n] = result
    return result
}
```

## Factorial

```kotlin
fun factorial(n: Int): Long {
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

// Recursive
fun factorialRecursive(n: Int): Long {
    return if (n <= 1) 1L else n * factorialRecursive(n - 1)
}
```

## Number Patterns

### Armstrong Number
```kotlin
fun isArmstrong(n: Int): Boolean {
    val digits = n.toString().map { it.digitToInt() }
    val power = digits.size
    val sum = digits.sumOf { Math.pow(it.toDouble(), power.toDouble()).toLong() }
    return sum == n.toLong()
}
```

### Perfect Number
```kotlin
fun isPerfect(n: Int): Boolean {
    if (n <= 1) return false
    val sum = (1 until n).filter { n % it == 0 }.sum()
    return sum == n
}
```

## Bit Manipulation with Numbers

### Count Set Bits
```kotlin
fun countSetBits(n: Int): Int {
    var count = 0
    var num = n

    while (num > 0) {
        count += num and 1
        num = num shr 1
    }

    return count
}

// Brian Kernighan's Algorithm
fun countSetBits(n: Int): Int {
    var count = 0
    var num = n

    while (num > 0) {
        num = num and (num - 1)
        count++
    }

    return count
}
```

### Check if Power of 2
```kotlin
fun isPowerOfTwo(n: Int): Boolean {
    return n > 0 && (n and (n - 1)) == 0
}
```

## Common Interview Problems

### Happy Number
```kotlin
fun isHappy(n: Int): Boolean {
    val seen = mutableSetOf<Int>()
    var num = n

    while (num != 1 && num !in seen) {
        seen.add(num)
        num = num.toString().map { it.digitToInt() }
            .sumOf { it * it }
    }

    return num == 1
}
```

### Ugly Number
```kotlin
fun isUgly(n: Int): Boolean {
    if (n <= 0) return false

    var num = n
    for (factor in arrayOf(2, 3, 5)) {
        while (num % factor == 0) {
            num /= factor
        }
    }

    return num == 1
}
```

## Performance Tips

1. **Avoid repeated calculations** - use memoization
2. **Integer overflow** - use Long for large numbers
3. **Modulo operations** - for very large results
4. **Math functions** - can be slower than manual calculations
5. **Bitwise operations** - faster than multiplication/division by powers of 2

## Interview Tips

1. **Handle edge cases**: 0, 1, negative numbers
2. **Check for overflow** in multiplication/power operations
3. **Use appropriate data types** (Int vs Long)
4. **Mathematical optimization** often beats brute force
5. **Remember number properties**: even/odd, prime, perfect square

## Common Pitfalls

```kotlin
// ❌ Wrong - integer overflow
fun factorial(n: Int): Int {
    var result = 1
    for (i in 2..n) {
        result *= i  // Overflows quickly!
    }
    return result
}

// ✅ Correct - use Long
fun factorial(n: Int): Long {
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

// ❌ Wrong - inefficient
fun isPrime(n: Int): Boolean {
    for (i in 2 until n) {  // Checks all numbers
        if (n % i == 0) return false
    }
    return n > 1
}

// ✅ Correct - only check up to sqrt(n)
fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}
```
