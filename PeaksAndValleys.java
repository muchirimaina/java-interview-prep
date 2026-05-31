public static void peaksAndValleys(int[] arr) {

    for (int i = 1; i < arr.length; i += 2) {

        int biggest = i;

        if (i - 1 >= 0 && arr[i - 1] > arr[biggest]) {
            biggest = i - 1;
        }

        if (i + 1 < arr.length && arr[i + 1] > arr[biggest]) {
            biggest = i + 1;
        }

        if (biggest != i) {
            int temp = arr[i];
            arr[i] = arr[biggest];
            arr[biggest] = temp;
        }
    }
}


// Time: O(n) (single pass, constant work per index)
// Space: O(1) (in-place swaps)