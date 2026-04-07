import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class GFG {

    static ArrayList<Integer> topoSort(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        int[] indegree = new int[n];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Compute indegrees
        for (int i = 0; i < n; i++) {
            for (int next : adj.get(i)) {
                indegree[next]++;
            }
        }

        // Add all nodes with indegree 0
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Kahn’s Algorithm (BFS)
        while (!q.isEmpty()) {
            int top = q.poll();
            result.add(top);

            for (int next : adj.get(top)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        // 🔥 Important: Cycle Detection
        if (result.size() != n) {
            throw new RuntimeException("No valid build order (cycle detected)");
        }

        return result;
    }

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static void main(String[] args) {

        // Projects mapped as:
        // a=0, b=1, c=2, d=3, e=4, f=5
        int n = 6;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // dependencies:
        // (a, d), (f, b), (b, d), (f, a), (d, c)

        addEdge(adj, 0, 3); // a → d
        addEdge(adj, 5, 1); // f → b
        addEdge(adj, 1, 3); // b → d
        addEdge(adj, 5, 0); // f → a
        addEdge(adj, 3, 2); // d → c

        ArrayList<Integer> res = topoSort(adj);

        // print result
        for (int vertex : res) {
            System.out.print(vertex + " ");
        }
    }
}




// NON NUMERIC

import java.util.*;

class GFG {

    static ArrayList<Character> topoSort(ArrayList<ArrayList<Integer>> adj,char[] projects) {

        int n = adj.size();
        int[] indegree = new int[n];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Character> result = new ArrayList<>();

        // Compute indegrees
        for (int i = 0; i < n; i++) {
            for (int next : adj.get(i)) {
                indegree[next]++;
            }
        }

        // Add all nodes with indegree 0
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Kahn’s Algorithm (BFS)
        while (!q.isEmpty()) {
            int top = q.poll();
            result.add(projects[top]); // map back to char

            for (int next : adj.get(top)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        // Cycle detection
        if (result.size() != n) {
            throw new RuntimeException("No valid build order (cycle detected)");
        }

        return result;
    }

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static void main(String[] args) {

        char[] projects = {'a', 'b', 'c', 'd', 'e', 'f'};
        int n = projects.length;

        // 🔥 Mapping char → index
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(projects[i], i);
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // dependencies:
        // (a, d), (f, b), (b, d), (f, a), (d, c)

        addEdge(adj, map.get('a'), map.get('d'));
        addEdge(adj, map.get('f'), map.get('b'));
        addEdge(adj, map.get('b'), map.get('d'));
        addEdge(adj, map.get('f'), map.get('a'));
        addEdge(adj, map.get('d'), map.get('c'));

        ArrayList<Character> res = topoSort(adj, projects);

        for (char c : res) {
            System.out.print(c + " ");
        }
    }
}