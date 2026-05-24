// DFS + memoization

class Box {
    int w, h, d;
    Box(int w, int h, int d) {
        this.w = w;
        this.h = h;
        this.d = d;
    }
}

public class Solution {

    int[] memo;

    public int tallestStack(Box[] boxes) {
        memo = new int[boxes.length];
        int max = 0;

        for (int i = 0; i < boxes.length; i++) {
            max = Math.max(max, dfs(boxes, i));
        }

        return max;
    }

    private int dfs(Box[] boxes, int i) {
        if (memo[i] != 0) return memo[i];

        int maxHeight = 0;

        for (int j = 0; j < boxes.length; j++) {
            if (canPlace(boxes[j], boxes[i])) {
                maxHeight = Math.max(maxHeight, dfs(boxes, j));
            }
        }

        memo[i] = boxes[i].h + maxHeight;
        return memo[i];
    }

    private boolean canPlace(Box top, Box bottom) {
        return top.w < bottom.w &&
               top.h < bottom.h &&
               top.d < bottom.d;
    }
}

// Time: O(n²) (each box compares with all others)
// Space: O(n) recursion + memo