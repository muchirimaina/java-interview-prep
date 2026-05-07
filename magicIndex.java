// Non Distinct Values

public static int magicIndex(int[] A) {
    return magicIndex(A, 0, A.length - 1);
}

private static int magicIndex(int[] A, int left, int right) {
    if (left > right) return -1;

    int mid = left + (right - left) / 2;

    if (A[mid] == mid) return mid;

    // Search left side
    int leftIndex = Math.min(mid - 1, A[mid]);
    int leftResult = magicIndex(A, left, leftIndex);
    if (leftResult != -1) return leftResult;

    // Search right side
    int rightIndex = Math.max(mid + 1, A[mid]);
    return magicIndex(A, rightIndex, right);
}