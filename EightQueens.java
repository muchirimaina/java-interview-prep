import java.util.*;

public class EightQueens {

    private static final int N = 8;

    private static List<List<String>> solutions = new ArrayList<>();

    // Tracks columns and diagonals
    private static boolean[] cols = new boolean[N];
    private static boolean[] diag1 = new boolean[2 * N - 1]; // row - col + (N-1)
    private static boolean[] diag2 = new boolean[2 * N - 1]; // row + col

    private static int[] board = new int[N]; // board[row] = col position

    public static void solve() {
        backtrack(0);

        // Print results
        for (List<String> sol : solutions) {
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    private static void backtrack(int row) {
        // base case: placed all queens
        if (row == N) {
            solutions.add(buildBoard());
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {

                placeQueen(row, col);

                backtrack(row + 1);

                removeQueen(row, col); // backtrack
            }
        }
    }

    private static boolean isSafe(int row, int col) {
        return !cols[col]
                && !diag1[row - col + (N - 1)]
                && !diag2[row + col];
    }

    private static void placeQueen(int row, int col) {
        board[row] = col;
        cols[col] = true;
        diag1[row - col + (N - 1)] = true;
        diag2[row + col] = true;
    }

    private static void removeQueen(int row, int col) {
        cols[col] = false;
        diag1[row - col + (N - 1)] = false;
        diag2[row + col] = false;
    }

    private static List<String> buildBoard() {
        List<String> result = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            char[] row = new char[N];
            Arrays.fill(row, '.');
            row[board[r]] = 'Q';
            result.add(new String(row));
        }

        return result;
    }

    public static void main(String[] args) {
        solve();
    }
}


//Time Complexity: O(N!) (pruned permutation-like search)
//Space Complexity: O(N) auxiliary (recursion + tracking arrays)


// The goal is to place one queen per row. For each row, we try all columns and
// place a queen only if it does not conflict in the same column or diagonals with
// previously placed queens. If we reach a row where no valid placement exists, we
// backtrack. Every time we successfully place queens in all rows, we record a valid configuration.