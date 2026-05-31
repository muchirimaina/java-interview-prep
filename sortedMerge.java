public static void sortedMerge(int[] A, int[] B, int countA, int countB) {

    int indexA = countA - 1;          // Last element in real part of A
    int indexB = countB - 1;          // Last element in B
    int indexMerged = countA + countB - 1; // End of merged array

    while (indexB >= 0 && indexA >= 0) {

        if (A[indexA] > B[indexB]) {
            A[indexMerged] = A[indexA];
            indexA--;
        } else {
            A[indexMerged] = B[indexB];
            indexB--;
        }

        indexMerged--;
    }

    // Copy remaining elements from B
    while (indexB >= 0) {
        A[indexMerged] = B[indexB];
        indexB--;
        indexMerged--;
    }
}


//TC: O(m+n) //Each iteration moves one pointer backward. Each element is processed at most once.
// SC: O(1) //We only use a few variables indexA indexB indexMerged