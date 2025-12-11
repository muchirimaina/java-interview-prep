import java.util.*;

class Solution{

    public static int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        
        // 1. Build the adjacency list representation of the graph
        // Map<City A, List<[City B, Travel Time]>>
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, time});
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, time}); // Graph is undirected
        }

        // 2. Data Structures for the algorithm
        // Use int[] instead of a custom State record/class
        // Each array stores: [currentTime, currentCost, nodeId]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[] minCostForNode = new int[n];
        Arrays.fill(minCostForNode, Integer.MAX_VALUE);

        // 3. Start the algorithm
        int startNode = 0;
        minCostForNode[startNode] = passingFees[startNode];
        // Add the starting state: [currentTime, currentCost, nodeId]
        pq.add(new int[]{0, passingFees[startNode], startNode}); 

        while (!pq.isEmpty()) {
            // Deconstruct the array pulled from the queue
            int[] current = pq.poll();
            int currentTime = current[0];
            int currentCost = current[1];
            int u = current[2]; // nodeId

            // Optimization: If current time exceeds the limit or we found a cheaper way to
            // reach this node with less time (which implies this path is suboptimal), skip.
            if (currentTime > maxTime || currentCost > minCostForNode[u]) {
                 continue;
            }
            
            // 4. Explore neighbors
            for (int[] edge : adj.getOrDefault(u, Collections.emptyList())) {
                int v = edge[0];
                int travelTime = edge[1];
                int nextTime = currentTime + travelTime;
                int nextCost = currentCost + passingFees[v];

                // If the path is valid (within time limits) and the new cost is better 
                // than any previously found cost to node 'v' at a comparable time, update and add to PQ.
                if (nextTime <= maxTime && nextCost < minCostForNode[v]) {
                    minCostForNode[v] = nextCost;
                    // Add the new state array to the PQ: [nextTime, nextCost, v]
                    pq.add(new int[]{nextTime, nextCost, v});
                }
            }
        }
        
        // 5. After exploring all time-valid paths, the answer is the minimum cost 
        // recorded for the destination node (n-1).
        if (minCostForNode[n - 1] == Integer.MAX_VALUE) {
            return -1; // Destination is unreachable within maxTime
        } else {
            return minCostForNode[n - 1];
        }
    }

    public static void main (String[] args){
        int maxTime = 30;
        int[][] edges = {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}};
        int[] passingFees = {5,1,2,20,20,3};

        System.out.println(minCost(maxTime,edges,passingFees));

    }
}
