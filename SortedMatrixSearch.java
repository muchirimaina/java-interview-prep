public static boolean sortedgridSearch(int[][] grid, int target) {

    int R = grid.length;
    int C = grid[0].length;

    int r = 0;
    int c = C - 1; // start top-right

    while (r < R && c >= 0) {

        if (grid[r][c] == target) {
            return true;
        }

        else if (grid[r][c] > target) {
            c--; // move left
        }

        else {
            r++; // move down
        }
    }

    return false;
}



// Time Complexity

// Worst case:

// move left at most C times
// move down at most R times

// So:

// TC = O(R + C)

// Space:

// SC = O(1)