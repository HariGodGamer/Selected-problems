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
class Solution {

    private String s;
    private int n;
    private long[][][] memo_cnt;
    private long[][][] memo_sum;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    // calculate the sum of fluctuation values of all numbers in the range [0, num]
    private long solve(long num) {
        // if the fluctuation value of numbers less than 3 is 0
        if (num < 100) {
            return 0L;
        }
        s = Long.toString(num);
        n = s.length();

        // memoized search uses two independent arrays
        // memo_cnt[pos][x][y]: the number of valid filling schemes where the current digit is at position pos, and the previous two digits are x and y
        memo_cnt = new long[16][10][10];
        // memo_sum[pos][x][y]: the fluctuation value when the current position is pos, and the two left digits are x and y
        memo_sum = new long[16][10][10];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(memo_cnt[i][j], -1);
                Arrays.fill(memo_sum[i][j], -1);
            }
        }

        long[] result = dfs(0, -1, -1, true, true);
        return result[1];
    }

    private long[] dfs(
        int pos,
        int prev,
        int curr,
        boolean isLimit,
        boolean isLeading
    ) {
        // end position
        if (pos == n) {
            return new long[] { 1L, 0L };
        }
        // use memoization only when not bounded by an upper limit and without leading zeros
        if (!isLimit && !isLeading && prev >= 0 && curr >= 0) {
            if (memo_cnt[pos][prev][curr] != -1) {
                return new long[] {
                    memo_cnt[pos][prev][curr],
                    memo_sum[pos][prev][curr],
                };
            }
        }

        // calculate the number of schemes and fluctuation value under the current conditions
        long cnt = 0, sum = 0;
        int up = isLimit ? (s.charAt(pos) - '0') : 9;
        for (int digit = 0; digit <= up; ++digit) {
            boolean newLeading = isLeading && (digit == 0);
            // the previous number is updated to curr
            int newPrev = curr;
            // the current number is updated to digit
            int newCurr = newLeading ? -1 : digit;
            long[] sub = dfs(
                pos + 1,
                newPrev,
                newCurr,
                isLimit && (digit == up),
                newLeading
            );
            long subCnt = sub[0], subSum = sub[1];
            // only calculate the fluctuation value when there are no leading zeros
            if (!newLeading && prev >= 0 && curr >= 0) {
                // when the digit is a peak or a valley, update the current fluctuation value
                if (
                    (prev < curr && curr > digit) ||
                    (prev > curr && curr < digit)
                ) {
                    sum += subCnt;
                }
            }

            cnt += subCnt;
            sum += subSum;
        }

        if (!isLimit && !isLeading && prev >= 0 && curr >= 0) {
            // update the memoization array
            memo_cnt[pos][prev][curr] = cnt;
            memo_sum[pos][prev][curr] = sum;
        }

        return new long[] { cnt, sum };
    }
}
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