public static void paintFill(int[][] grid, int row, int col, int newColor) {
    int oldColor = grid[row][col];

    if (oldColor == newColor) return;

    dfs(grid, row, col, oldColor, newColor);
}

private static void dfs(int[][] grid, int r, int c, int oldColor, int newColor) {
    int ROWS = grid.length;
    int COLS = grid[0].length;

    // out of bounds
    if (r < 0 || c < 0 || r >= ROWS || c >= COLS) {
        return;
    }

    // stop if color doesn't match original
    if (grid[r][c] != oldColor) {
        return;
    }

    // paint current cell
    grid[r][c] = newColor;

    // explore 4 directions
    dfs(grid, r + 1, c, oldColor, newColor); // down
    dfs(grid, r - 1, c, oldColor, newColor); // up
    dfs(grid, r, c + 1, oldColor, newColor); // right
    dfs(grid, r, c - 1, oldColor, newColor); // left
}

//Time Complexity: O(m * n)
//Space Complexity: O(m * n) worst-case recursion stack


//BFS APPROACH

import java.util.LinkedList;
import java.util.Queue;

public static void paintFillBFS(int[][] grid, int row, int col, int newColor) {
    int oldColor = grid[row][col];

    if (oldColor == newColor) return;

    int ROWS = grid.length;
    int COLS = grid[0].length;

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{row, col});

    while (!queue.isEmpty()) {
        int[] cell = queue.poll();
        int r = cell[0];
        int c = cell[1];

        // safety check (important in BFS if duplicates get queued)
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS) continue;
        if (grid[r][c] != oldColor) continue;

        // color it
        grid[r][c] = newColor;

        // push 4 neighbors
        queue.offer(new int[]{r + 1, c});
        queue.offer(new int[]{r - 1, c});
        queue.offer(new int[]{r, c + 1});
        queue.offer(new int[]{r, c - 1});
    }
}