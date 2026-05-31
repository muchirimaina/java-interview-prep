public static int searchInRotated(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        // Check if left half is sorted
        if (arr[left] <= arr[mid]) {
            // target lies in left sorted half
            if (arr[left] <= target && target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // Otherwise right half is sorted
        else {
            if (arr[mid] < target && target <= arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
}


// ⏱ Complexity
// Time Complexity: O(log n)
// (we discard half each step)
// Space Complexity:
// Iterative: O(1)
// Recursive: O(log n) due to call stack


//Recursive
public static int searchInRotated(int[] arr, int target, int left, int right) {

    if (left > right) {
        return -1;
    }

    int mid = left + (right - left) / 2;

    if (arr[mid] == target) {
        return mid;
    }

    // LEFT HALF IS SORTED
    if (arr[left] <= arr[mid]) {

        // target is in the left sorted half
        if (arr[left] <= target && target < arr[mid]) {
            return searchInRotated(arr, target, left, mid - 1);
        } else {
            return searchInRotated(arr, target, mid + 1, right);
        }
    }

    // RIGHT HALF IS SORTED
    else {

        // target is in the right sorted half
        if (arr[mid] < target && target <= arr[right]) {
            return searchInRotated(arr, target, mid + 1, right);
        } else {
            return searchInRotated(arr, target, left, mid - 1);
        }
    }
}



// 🧠 Why <= is needed

// A subarray is considered sorted (non-decreasing order) if:

// arr[left] <= arr[mid]

// That includes cases where:

// all elements are increasing → normal sorted half
// or duplicates exist
// or left == mid (single element case)



// If no Duplicates Case
// For the case that an array has no duplicates, I can use arr[left] < arr[mid]
// to detect the sorted half. However, I often still use <= because it generalizes
// better and avoids edge-case issues.