import java.util.*;

public class Solution {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }
}


// ⏱ Complexity
// Let:
// n = number of words
// k = max word length
// Time:
// Sorting each word → O(k log k)
// For n words → O(n * k log k)
// Space:
// HashMap storage → O(n * k)


//Instead of sorting, we could use a frequency array (26 letters) → O(k) per word instead of O(k log k)




import java.util.*;

public class Solution {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            int[] freq = new int[26];

            // build frequency count
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            // convert frequency array into a unique key
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                key.append('#');      // separator to avoid ambiguity
                key.append(freq[i]);
            }

            String signature = key.toString();

            map.putIfAbsent(signature, new ArrayList<>());
            map.get(signature).add(word);
        }

        return new ArrayList<>(map.values());
    }
}


// ⏱ Complexity
// Time:
// Build frequency: O(k) per word
// Total: O(n * k) ✅ (better than sorting version)
// Space:
// HashMap + keys: O(n * k)