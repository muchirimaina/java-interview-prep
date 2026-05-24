import java.util.*;

public class Solution {

    private Map<Character, Integer> count;
    private List<String> res;

    public List<String> permuteUnique(String s) {
        res = new ArrayList<>();
        count = new HashMap<>();
        StringBuilder perm = new StringBuilder();

        // build frequency map
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        dfs(s.length(), perm);
        return res;
    }

    private void dfs(int n, StringBuilder perm) {
        if (perm.length() == n) {
            res.add(perm.toString());
            return;
        }

        for (char c : count.keySet()) {

            if (count.get(c) > 0) {

                // choose
                perm.append(c);
                count.put(c, count.get(c) - 1);

                // explore
                dfs(n, perm);

                // backtrack
                count.put(c, count.get(c) + 1);
                perm.deleteCharAt(perm.length() - 1);
            }
        }
    }
}


// Time complexity: 
// O(n!∗n)
// Space complexity: 
// O(n!∗n) for the output list.