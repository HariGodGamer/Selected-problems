# 🌊 Sum of Waviness of Numbers

<div align="center">

![LeetCode](https://img.shields.io/badge/LeetCode-Hard-orange?style=for-the-badge&logo=leetcode)
![Language](https://img.shields.io/badge/Language-Java-blue?style=for-the-badge&logo=openjdk)
![Topic](https://img.shields.io/badge/Topic-Digit%20DP-success?style=for-the-badge)

### Count Peaks and Valleys Across a Range of Numbers

</div>

---

## 📝 Problem

You are given two integers `num1` and `num2` representing an inclusive range `[num1, num2]`.

The **waviness** of a number is defined as the total count of its **peaks** and **valleys**.

### ⛰️ Peak
A digit is a peak if it is strictly greater than both of its immediate neighbors.

Example:

```text
1 9 2
  ↑
Peak
```

### 🏔️ Valley
A digit is a valley if it is strictly less than both of its immediate neighbors.

Example:

```text
9 1 8
  ↑
Valley
```

### 📌 Rules

- The first digit cannot be a peak or valley.
- The last digit cannot be a peak or valley.
- Numbers with fewer than 3 digits have waviness = 0.

Return the **total sum of waviness** for all numbers in the range `[num1, num2]`.

---

## Example 1

### Input

```text
num1 = 120
num2 = 130
```

### Output

```text
3
```

### Explanation

| Number | Waviness |
|----------|----------|
| 120 | 1 |
| 121 | 1 |
| 130 | 1 |
| Others | 0 |

Total = 3

---

## Example 2

### Input

```text
num1 = 198
num2 = 202
```

### Output

```text
3
```

### Explanation

| Number | Waviness |
|----------|----------|
| 198 | Peak at 9 |
| 201 | Valley at 0 |
| 202 | Valley at 0 |

Total = 3

---

## Example 3

### Input

```text
num1 = 4848
num2 = 4848
```

### Output

```text
2
```

### Explanation

```text
4 8 4 8
  ↑ ↑
Peak Valley
```

Waviness = 2

---

# 💡 Intuition

A straightforward solution would iterate through every number in the range and calculate its waviness.

Unfortunately, that approach becomes far too slow when:

```text
num2 - num1
```

is very large.

Instead, we calculate:

```text
Waviness(0 → num2)
-
Waviness(0 → num1 - 1)
```

This transforms the problem into a classic **Digit DP** problem.

---

# 🔍 Observation

To determine whether a digit is a peak or valley, we need:

```text
Previous Digit
Current Digit
Next Digit
```

Whenever three consecutive digits become available:

```text
a b c
```

We check:

### Peak

```text
b > a && b > c
```

### Valley

```text
b < a && b < c
```

If either condition is true:

```text
waviness += 1
```

---

# 🚀 Approach

Use **Digit Dynamic Programming (Digit DP)**.

While constructing numbers digit-by-digit:

- Keep track of previous two digits.
- Maintain tight constraint with the upper bound.
- Handle leading zeros correctly.
- Count every peak/valley contribution as digits are formed.

To answer the range:

```text
answer =
solve(num2)
-
solve(num1 - 1)
```

---

# 📊 Complexity Analysis

| Complexity | Value |
|------------|--------|
| Time | O(D × 10 × 10 × States) |
| Space | O(DP States) |

Where:

```text
D = Number of Digits
```

For 64-bit integers:

```text
D ≤ 19
```

making the solution extremely efficient.

---

# ✅ Accepted Java Solution

```java
// Paste Accepted Solution Here
```

---

# 🧠 Key Learnings

- Digit DP
- Dynamic Programming
- Range Query Optimization
- Peak & Valley Pattern Detection
- State Memoization

---

# 🏷️ Tags

```text
Hard
Digit DP
Dynamic Programming
Memoization
Math
Range Queries
```

---

<div align="center">

⭐ If you found this solution helpful, consider starring the repository.

</div>