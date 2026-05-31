class Listy {
    private int[] arr;

    public Listy(int[] arr) {
        this.arr = arr;
    }

    public int elementAt(int index) {
        if (index < 0 || index >= arr.length) {
            return -1;
        }
        return arr[index];
    }
}

public class Solution {

    public static int sortedSearchNoSize(Listy listy, int x) {

        // Phase 1: Find range
        int index = 1;

        while (listy.elementAt(index) != -1 &&
               listy.elementAt(index) < x) {

            index *= 2;
        }

        // Phase 2: Binary Search
        return binarySearch(listy, x, index / 2, index);
    }

    private static int binarySearch(Listy listy,
                                    int x,
                                    int left,
                                    int right) {

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int value = listy.elementAt(mid);

            // out of bounds OR too large
            if (value == -1 || value > x) {

                right = mid - 1;

            } else if (value < x) {

                left = mid + 1;

            } else {

                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Listy listy =
            new Listy(new int[]{1,3,5,7,9,12,15,20});

        System.out.println(sortedSearchNoSize(listy, 15));
    }
}


// # Time Complexity

// The algorithm has **2 phases**:

// ---

// # 1. Expanding the Range

// We double the index each time:

// ```text id="mjlwm1"
// 1, 2, 4, 8, 16, 32 ...
// ```

// Suppose the target is at position `p`.

// How many doublings until we reach/past `p`?

// We solve:

// ```text id="g2xzye"
// 2^k >= p
// ```

// Taking log:

// ```text id="nttlcq"
// k = log₂(p)
// ```

// So this phase is:

// ```text id="0ybgw6"
// O(log p)
// ```

// ---

// # 2. Binary Search

// After expansion, we binary search within a range of size at most about `p`.

// Binary search complexity:

// ```text id="vq7xmw"
// O(log p)
// ```

// ---

// # Total Time Complexity

// ```text id="wuzj1q"
// O(log p) + O(log p)
// = O(log p)
// ```

// where:

// * `p` = position/index of the target element

// Sometimes interviewers also accept:

// ```text id="qad0y8"
// O(log n)
// ```

// if `n` is the number of elements.

// ---

// # Space Complexity

// We only use a few variables:

// ```text id="djxjlwm"
// index, left, right, mid
// ```

// No extra data structures.

// So:

// ```text id="55gfhl"
// O(1)
// ```

// ---

// # Final Answer

// | Complexity       | Value                    |
// | ---------------- | ------------------------ |
// | Time Complexity  | `O(log p)` or `O(log n)` |
// | Space Complexity | `O(1)`                   |
