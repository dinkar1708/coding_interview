package algorithms.strings.palindrome

/**
 * PROBLEM: Check if Integer is Palindrome
 *
 * A palindrome number reads the same backward as forward.
 *
 * INPUT: 121
 * OUTPUT: true (Palindrome)
 *
 * EXPLANATION:
 * Two approaches:
 * 1. Reverse the number and check with current number
 * 2. Check first and last digits progressively
 *
 * Time Complexity: O(n) where n is the number of digits
 * Space Complexity: O(1) for approach 1, O(n) for approach 2
 */

fun main() {
    // Test 1: Normal case - palindrome (approach 1)
    println("=== Test 1: Normal case - palindrome (approach 1) ===")
    var output1 = isPalindrome(121)
    println("Result: $output1")
    println("Expected: true (Palindrome)")
    println()

    // Test 2: Normal case - not palindrome (approach 2)
    println("=== Test 2: Normal case - not palindrome (approach 2) ===")
    var output2 = isPalindrome2(1211)
    println("Result: $output2")
    println("Expected: false (Not palindrome)")
    println()

    // Test 3: Edge case - single digit
    println("=== Test 3: Edge case - single digit ===")
    val output3 = isPalindrome(7)
    println("Result: $output3")
    println("Expected: true")
    println()

    // Test 4: Edge case - zero
    println("=== Test 4: Edge case - zero ===")
    val output4 = isPalindrome(0)
    println("Result: $output4")
    println("Expected: true")
    println()

    // Test 5: Edge case - negative number
    println("=== Test 5: Edge case - negative number ===")
    val output5 = isPalindrome(-121)
    println("Result: $output5")
    println("Expected: false (negatives are not palindromes)")
    println()

    // Test 6: Large palindrome
    println("=== Test 6: Large palindrome ===")
    val output6 = isPalindrome2(12321)
    println("Result: $output6")
    println("Expected: true")
}

// TIME COMPLEXITY: O(log n) - iterate through digits of number (log base 10)
// SPACE COMPLEXITY: O(1) - only constant variables for reversed number
private fun isPalindrome(n: Int): Boolean {
    // Edge case: negative numbers are not palindromes
    if (n < 0) {
        return false
    }

    // Reverse number and check with current number
    var temp = n
    var rev = 0
    var num = n

    while (num > 0) {
        // Get digit one by one
        val digit = num % 10
        // Find reverse number
        rev = rev * 10 + digit
        // Divide the number
        num = num / 10
    }

    return temp == rev
}

// TIME COMPLEXITY: O(log n) - convert to string and check characters
// SPACE COMPLEXITY: O(log n) - string representation of number
private fun isPalindrome2(n: Int): Boolean {
    // Edge case: negative numbers are not palindromes
    if (n < 0) {
        return false
    }

    // Check for first and last value of int digit
    val numberAsList = n.toString().toMutableList()

    for (char in n.toString()) {
        // Check if current item is not equal to last item in the list
        println("List at each iteration... $numberAsList")
        if (numberAsList.isEmpty()) {
            break
        }
        val first = numberAsList.removeAt(0)
        if (numberAsList.isEmpty()) {
            break
        }
        val last = numberAsList.removeAt(numberAsList.size - 1)
        if (first != last) {
            return false
        }
    }
    return true
}
