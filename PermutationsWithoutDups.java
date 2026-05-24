import java.util.ArrayList;
import java.util.List;

public class PermutationsWithoutDups {

    public static List<String> getPermutations(String str) {
        List<String> result = new ArrayList<>();
        backtrack("", str, result);
        return result;
    }

    private static void backtrack(String current, String remaining, List<String> result) {

        // Base case: no characters left
        if (remaining.length() == 0) {
            result.add(current);
            return;
        }

        // Try each available character
        for (int i = 0; i < remaining.length(); i++) {

            // Choose
            char chosen = remaining.charAt(i);

            // Build next state
            String newCurrent = current + chosen;
            String newRemaining =
                    remaining.substring(0, i) + remaining.substring(i + 1);

            // Recurse
            backtrack(newCurrent, newRemaining, result);

            // No explicit undo needed because strings are immutable
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        List<String> permutations = getPermutations(str);
        System.out.println(permutations);
    }
}


//Core pattern to remember
// for each choice:
//     choose
//     recurse
//     undo

//For my code
// choose -> charAt(i)
// recurse -> backtrack(...)
// undo -> automatic via strings


// Complexity
// Time

// There are: n! permutations.
// Building each string costs: O(n)
// So: O(n * n!)


// Space
// Recursion depth:
// O(n)
// Output:
// O(n * n!)