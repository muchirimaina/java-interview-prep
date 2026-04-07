import java.util.*;

class GFG {

    static List<Character> topoSort(Map<Character, List<Character>> adj) {
        
        // Step 1: Compute indegree of each node
        Map<Character, Integer> indegree = new HashMap<>();
        
        // Initialize indegree for all nodes
        for (char node : adj.keySet()) {
            indegree.put(node, 0);
        }
        
        // Compute indegrees
        for (char node : adj.keySet()) {
            for (char neighbor : adj.get(node)) {
                indegree.put(neighbor, indegree.getOrDefault(neighbor, 0) + 1);
            }
        }
        
        // Step 2: Initialize queue with indegree 0 nodes
        Queue<Character> q = new LinkedList<>();
        for (char node : indegree.keySet()) {
            if (indegree.get(node) == 0) {
                q.add(node);
            }
        }
        
        // Step 3: BFS processing
        List<Character> result = new ArrayList<>();
        
        while (!q.isEmpty()) {
            char curr = q.poll();
            result.add(curr);
            
            // Step 4: Update neighbors
            for (char neighbor : adj.getOrDefault(curr, new ArrayList<>())) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                
                if (indegree.get(neighbor) == 0) {
                    q.add(neighbor);
                }
            }
        }
        
        // Step 6: Check for cycle
        if (result.size() != indegree.size()) {
            System.out.println("Cycle detected");
            return new ArrayList<>();
        }
        
        return result;
    }
}