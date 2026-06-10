# 📈 Maximum Total Subarray Value II

<div align="center">

![LeetCode](https://img.shields.io/badge/LeetCode-Hard-orange?style=for-the-badge&logo=leetcode)
![Language](https://img.shields.io/badge/Language-Java-blue?style=for-the-badge&logo=openjdk)
![Topic](https://img.shields.io/badge/Topic-Heaps%20%7C%20Greedy-success?style=for-the-badge)

### Select Exactly K Distinct Subarrays to Maximize Total Value

</div>

---

## 📝 Problem

You are given an integer array `nums` of length `n` and an integer `k`.

You must select exactly **k distinct subarrays** of `nums`.

A subarray is defined as:

```text
nums[l...r]
```

where:

```text
0 ≤ l ≤ r < n
```

The value of a subarray is:

```text
max(nums[l...r]) - min(nums[l...r])
```

The total value is the sum of the values of all selected subarrays.

Return the **maximum possible total value** obtainable by selecting exactly `k` distinct subarrays.

---

## Example 1

### Input

```text
nums = [1,3,2]
k = 2
```

### Output

```text
4
```

### Explanation

One optimal selection:

| Subarray | Maximum | Minimum | Value |
|-----------|----------|----------|--------|
| [1,3] | 3 | 1 | 2 |
| [1,3,2] | 3 | 1 | 2 |

Total:

```text
2 + 2 = 4
```

---

## Example 2

### Input

```text
nums = [4,2,5,1]
k = 3
```

### Output

```text
12
```

### Explanation

One optimal selection:

| Subarray | Maximum | Minimum | Value |
|-----------|----------|----------|--------|
| [4,2,5,1] | 5 | 1 | 4 |
| [2,5,1] | 5 | 1 | 4 |
| [5,1] | 5 | 1 | 4 |

Total:

```text
4 + 4 + 4 = 12
```

---

## Constraints

```text
1 <= n <= 5 × 10^4

0 <= nums[i] <= 10^9

1 <= k <= min(10^5, n × (n + 1) / 2)
```

---

# 💡 Intuition

For every subarray:

```text
Value =
Maximum Element
-
Minimum Element
```

A brute force solution would:

1. Generate every subarray.
2. Compute its maximum.
3. Compute its minimum.
4. Sort all values.
5. Take the largest k values.

Unfortunately:

```text
Number of subarrays

= n(n+1)/2
```

For:

```text
n = 50,000
```

this is completely impossible.

---

# 🔍 Key Observation

The contribution of a subarray can be written as:

```text
(maximum contribution)
-
(minimum contribution)
```

Instead of evaluating each subarray independently, we can count:

- How many subarrays use an element as maximum.
- How many subarrays use an element as minimum.

Using monotonic stacks, we can efficiently determine these ranges.

---

# 🚀 Approach

### Step 1

For every element:

```text
nums[i]
```

find:

- Previous Greater
- Next Greater

to determine the subarrays where it acts as the maximum.

---

### Step 2

Similarly find:

- Previous Smaller
- Next Smaller

to determine the subarrays where it acts as the minimum.

---

### Step 3

Each element generates many possible subarray values.

These values form intervals that can be processed efficiently without explicitly generating every subarray.

---

### Step 4

Use a priority queue (max heap) to repeatedly extract the largest available contribution.

This allows us to obtain:

```text
Top K subarray values
```

without enumerating all subarrays.

---

# 🧠 Core Insight

The challenge is not computing:

```text
max - min
```

for a single subarray.

The challenge is finding:

```text
The K largest subarray values
```

among billions of possible subarrays.

The solution relies on:

```text
Monotonic Stacks
+
Contribution Counting
+
Priority Queue Expansion
```

to avoid brute force generation.

---

# 📊 Complexity Analysis

| Operation | Complexity |
|------------|------------|
| Monotonic Stack Processing | O(n) |
| Heap Operations | O(k log n) |
| Total | O((n + k) log n) |

Where:

```text
n = nums.length
```

---

# ✅ Accepted Java Solution

```java
// Paste Accepted Solution Here
```

---

# 🧠 What This Problem Teaches

- Monotonic Stack
- Contribution Technique
- Priority Queue Optimization
- K Largest Values Problems
- Range Maximum / Minimum Analysis
- Hard Array Algorithms

---

# 🏷️ Tags

```text
Hard
Arrays
Greedy
Heap
Priority Queue
Monotonic Stack
Sorting
Top K
```

---

# 🎯 Interview Insight

The brute force mindset focuses on:

```text
Generate every subarray
```

The optimal mindset focuses on:

```text
Count contributions
instead of
enumerating subarrays
```

This shift reduces a problem with billions of possibilities into an efficient solution that works within the given constraints.

---

<div align="center">

⭐ If you found this solution helpful, consider starring the repository.

</div>