
import java.util.*;

public class PowerSet {

    public static List<List<Character>> getSubsets(List<Character> set, int index) {

        List<List<Character>> allSubsets;

        // Base case: reached end of set
        if (index == set.size()) {
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>()); // add empty subset
        } else {
            // Get subsets from the rest of the list
            allSubsets = getSubsets(set, index + 1);

            Character item = set.get(index);
            List<List<Character>> moreSubsets = new ArrayList<>();

            // Duplicate each existing subset and add current character
            for (List<Character> subset : allSubsets) {
                List<Character> newSubset = new ArrayList<>(subset);
                newSubset.add(item);
                moreSubsets.add(newSubset);
            }

            // Merge subsets without item + subsets with item
            allSubsets.addAll(moreSubsets);
        }

        return allSubsets;

    }

}
