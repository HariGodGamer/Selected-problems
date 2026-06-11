# 🌳 Number of Ways to Assign Edge Weights I

<div align="center">

![LeetCode](https://img.shields.io/badge/LeetCode-Medium-yellow?style=for-the-badge&logo=leetcode)
![Language](https://img.shields.io/badge/Language-Java-blue?style=for-the-badge&logo=openjdk)
![Topic](https://img.shields.io/badge/Topic-Tree%20%7C%20Math-success?style=for-the-badge)

### Count Valid Edge Weight Assignments Producing an Odd Path Cost

</div>

---

## 📝 Problem

You are given an undirected tree rooted at node `1`.

Each edge initially has weight:

```text
0
```

You must assign every edge on a selected root-to-leaf path a weight of either:

```text
1 or 2
```

Choose any node `x` having the **maximum depth** in the tree.

The cost of the path:

```text
1 → x
```

is the sum of all edge weights along that path.

Return the number of ways to assign weights such that the total path cost is:

```text
Odd
```

Since the answer can be very large, return it modulo:

```text
10^9 + 7
```

---

## Example 1

### Input

```text
edges = [[1,2]]
```

### Output

```text
1
```

### Tree

<p align="center">
  <img src="./example 1.png" width="300"/>
</p>

### Explanation

Path:

```text
1 → 2
```

Possible assignments:

| Edge Weight | Cost | Odd? |
|------------|--------|------|
| 1 | 1 | ✅ |
| 2 | 2 | ❌ |

Answer:

```text
1
```

---

## Example 2

### Input

```text
edges =
[
 [1,2],
 [1,3],
 [3,4],
 [3,5]
]
```

### Output

```text
2
```

### Tree

<p align="center">
  <img src="./example 2.png" width="300"/>
</p>

Maximum depth:

```text
2
```

Nodes:

```text
4 and 5
```

Choose either one.

For path:

```text
1 → 3 → 4
```

Possible assignments:

| Weights | Cost | Odd? |
|----------|--------|------|
| (1,1) | 2 | ❌ |
| (1,2) | 3 | ✅ |
| (2,1) | 3 | ✅ |
| (2,2) | 4 | ❌ |

Valid assignments:

```text
2
```

---

## Constraints

```text
2 <= n <= 100000

edges.length == n - 1

1 <= ui, vi <= n

edges forms a valid tree
```

---

# 💡 Intuition

The actual structure of the tree is mostly irrelevant.

The only thing that matters is:

```text
Length of the path
from node 1
to a deepest node.
```

Suppose this path contains:

```text
d edges
```

Each edge can be assigned:

```text
1 or 2
```

Therefore:

```text
Total assignments = 2^d
```

---

# 🔍 Key Observation

The parity of the total cost depends only on:

```text
Odd + Even
```

not on the exact value.

Observe:

```text
1 = Odd
2 = Even
```

So each edge contributes either:

```text
Odd
or
Even
```

to the sum.

---

# 🧠 Important Pattern

For a path containing:

```text
d edges
```

Total assignments:

```text
2^d
```

Among all assignments:

```text
Exactly half produce an odd sum
Exactly half produce an even sum
```

Why?

Because flipping the first edge:

```text
1 ↔ 2
```

changes parity and creates a one-to-one mapping between:

```text
Odd sums
↔
Even sums
```

Therefore:

```text
Odd assignments = 2^(d-1)
```

for every:

```text
d ≥ 1
```

---

# 🚀 Approach

### Step 1

Build the tree using an adjacency list.

---

### Step 2

Perform DFS or BFS from node:

```text
1
```

to find the maximum depth.

---

### Step 3

Let:

```text
depth = maximum depth
```

Since depth equals the number of edges in the chosen path:

```text
Path Length = depth
```

---

### Step 4

Answer:

```text
2^(depth - 1)
```

modulo:

```text
1,000,000,007
```

---

# 📊 Complexity Analysis

| Operation | Complexity |
|------------|------------|
| Build Graph | O(n) |
| DFS / BFS | O(n) |
| Fast Power | O(log n) |
| Total | O(n) |

---

# ✅ Accepted Java Solution

```java
// Paste Accepted Solution Here
```

---

# 🧠 What This Problem Teaches

- Tree Traversal
- Maximum Depth Calculation
- Modular Exponentiation
- Parity Analysis
- Mathematical Counting

---

# 🏷️ Tags

```text
Medium
Tree
DFS
BFS
Math
Combinatorics
Modular Arithmetic
```

---

# 🎯 Interview Insight

At first glance, the problem appears to require exploring every possible edge assignment.

However:

```text
2^100000
```

assignments are impossible to enumerate.

The breakthrough observation is:

```text
Only parity matters.
```

Once we realize that:

```text
1 = Odd
2 = Even
```

the problem becomes a simple counting exercise.

For a path containing:

```text
d edges
```

exactly half of all assignments produce an odd total cost.

Thus:

```text
Answer = 2^(d-1)
```

after finding the maximum depth of the tree.

---

<div align="center">

⭐ If you found this solution helpful, consider starring the repository.

</div>