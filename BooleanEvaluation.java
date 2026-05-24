import java.util.*;

public class BooleanEvaluation {

    private Map<String, Integer> memo = new HashMap<>();

    public int countEval(String expr, boolean result) {
        return solve(expr, result);
    }

    private int solve(String expr, boolean result) {
        String key = expr + "@" + result;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // base case: single digit
        if (expr.length() == 1) {
            boolean val = expr.charAt(0) == '1';
            return val == result ? 1 : 0;
        }

        int ways = 0;

        for (int i = 1; i < expr.length(); i += 2) {
            char op = expr.charAt(i);

            String left = expr.substring(0, i);
            String right = expr.substring(i + 1);

            int leftTrue = solve(left, true);
            int leftFalse = solve(left, false);
            int rightTrue = solve(right, true);
            int rightFalse = solve(right, false);

            int total = 0;

            if (op == '&') {
                if (result) {
                    total = leftTrue * rightTrue;
                } else {
                    total = leftTrue * rightFalse
                          + leftFalse * rightTrue
                          + leftFalse * rightFalse;
                }
            }

            else if (op == '|') {
                if (result) {
                    total = leftTrue * rightTrue
                          + leftTrue * rightFalse
                          + leftFalse * rightTrue;
                } else {
                    total = leftFalse * rightFalse;
                }
            }

            else if (op == '^') {
                if (result) {
                    total = leftTrue * rightFalse
                          + leftFalse * rightTrue;
                } else {
                    total = leftTrue * rightTrue
                          + leftFalse * rightFalse;
                }
            }

            ways += total;
        }

        memo.put(key, ways);
        return ways;
    }

    public static void main(String[] args) {
        BooleanEvaluation be = new BooleanEvaluation();

        System.out.println(be.countEval("1^0|0|1", false)); // example
    }
}


// Time: O(n^3) (roughly: O(n²) subproblems × O(n) splits)
// Space: O(n²) for memo + recursion stack