# Coin Change Problems

All visualizations and explanations are **inside the code files** as comments.

---

## Files

### 1. CoinChange.kt
**Problem:** Count WAYS to make sum (unlimited coins)
- Recursive + Memoization
- Recursion tree in code comments
- Time: O(n × sum), Space: O(n × sum)

### 2. CoinChange2.kt
**Problem:** Count WAYS to make sum (unlimited coins)
- Bottom-up DP (2D table)
- Table visualization in code comments
- Time: O(n × sum), Space: O(n × sum)

### 3. CoinChangeCombinationTopDown.kt
**Problem:** Count WAYS to make sum (unlimited coins)
- 1D DP array (space optimized)
- Array updates in code comments
- Time: O(n × sum), Space: O(sum)

### 4. CoinChangeNoRepetition.kt
**Problem:** Count WAYS to make sum (each coin used at most ONCE)
- Recursive + Memoization
- Recursion tree showing return flow in code comments
- Time: O(n × sum), Space: O(n × sum)

### 5. CoinChangeMinimumCoins.kt
**Problem:** MINIMUM coins needed to make sum (unlimited coins)
- Recursive + Memoization + DP
- Recursion tree at bottom of file
- Time: O(n × sum), Space: O(sum)

### 6. CoinChangeMinimumCoinsNoRepetition.kt
**Problem:** MINIMUM coins needed to make sum (each coin used at most ONCE)
- Recursive + Memoization
- Recursion tree showing return flow at bottom
- Time: O(n × sum), Space: O(n × sum)

---

## Quick Reference

| Want to | Open File |
|---------|-----------|
| Count ways (unlimited coins) | CoinChange.kt |
| Count ways (each coin once) | CoinChangeNoRepetition.kt |
| Minimum coins needed | CoinChangeMinimumCoins.kt |
| See DP table | CoinChange2.kt |
| See 1D array | CoinChangeCombinationTopDown.kt |

---

## Key Differences

### Count WAYS vs Minimum COINS

**Count Ways:**
- coins = [1, 2, 5], sum = 5
- Answer: 4 ways
- Examples: [1,1,1,1,1], [1,1,1,2], [1,2,2], [5]

**Minimum Coins:**
- coins = [1, 2, 5], sum = 5
- Answer: 1 coin
- Best: [5]

### WITH repetition vs NO repetition

**With Repetition (unlimited):**
- coins = [1, 2], sum = 3
- Answer: 2 ways
- Examples: [1,1,1], [1,2]

**No Repetition (each coin once):**
- coins = [1, 2], sum = 3
- Answer: 1 way
- Examples: [1,2]

---

## All visualizations are in the code files!

Just open any .kt file and scroll to see:
- Problem explanation at top
- Recursion trees as comments
- DP tables as comments
- Complexity analysis

No need for separate markdown files! 🎯
