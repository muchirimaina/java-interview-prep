import java.util.*;

public class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result; 
    }

    private void backtrack(List<String> result,
                           StringBuilder current,
                           int openUsed,
                           int closeUsed,
                           int n) {

        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        if (openUsed < n) {
            current.append('(');
            backtrack(result, current, openUsed + 1, closeUsed, n);
            current.deleteCharAt(current.length() - 1); // undo
        }

        if (closeUsed < openUsed) {
            current.append(')');
            backtrack(result, current, openUsed, closeUsed + 1, n);
            current.deleteCharAt(current.length() - 1); // undo
        }
    }
}


//Time: O(4^n / √n)  OR Time = O(Catalan(n) * n)
// Space: O(n) excluding output