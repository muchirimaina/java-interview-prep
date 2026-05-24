import java.util.*;

public class TowerOfHanoi {

    public static void solveHanoi(int n,Stack<Integer> source,Stack<Integer> auxiliary,Stack<Integer> destination) {
        // Base case
        if (n == 1) {
            destination.push(source.pop());
            return;
        }

        // Step 1: move n-1 from source -> auxiliary
        solveHanoi(n - 1, source, destination, auxiliary);

        // Step 2: move largest disk
        destination.push(source.pop());

        // Step 3: move n-1 from auxiliary -> destination
        solveHanoi(n - 1, auxiliary, source, destination);
    }
}