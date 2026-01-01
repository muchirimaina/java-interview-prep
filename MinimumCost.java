import java.util.*;

class SolutionDjistra{

    public static  int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;

        // 1. Build the undirected Graph representing cities using Hashmap
        Map<Integer, List<int[]>> citiesGraph = new HashMap<>();
        for(int [] edge: edges){
            int homeCity = edge[0];
            int neighborCity = edge[1];
            int time = edge[2];

            citiesGraph.computeIfAbsent(homeCity, k -> new ArrayList<>()).add(new int[]{neighborCity, time});
            citiesGraph.computeIfAbsent(neighborCity, k -> new ArrayList<>()).add(new int[]{homeCity,time});
        }

        // 2. Use a priority queue as the main Datastructure
        // This pq will store [currentTime, currentCost, cityId]
        PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

       
        //Assigning the biggest integer to compare with
        int [][] best = new int [n][maxTime +1];
        for(int i = 0; i<n; i++){
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }

        // 3. Start the algorithm/ Dijkstra's -- finding the minimumCost & time
        int startNode = 0;
        // The starting State 
        pq.add(new int []{0,passingFees[startNode],startNode});

        while(!pq.isEmpty()){
            // The queue has only one item at the moment | Update the values as you get the shortest paths, next array in the pq
            int [] current = pq.poll();
            int currentTime = current[0];
            int currentCost = current[1];
            int homeCity = current[2];
            
            //If the currentTime exceeds the time limit or the best cost - the path is subOptimal
            if(currentTime > maxTime || currentCost > best[homeCity][currentTime]){
                continue;
            }

        
            

            // 4. Explore the neighbors

            for(int[] edge : citiesGraph.getOrDefault(homeCity, Collections.emptyList())){
                int neighborCity = edge[0];
                int travelTime = edge[1];
                int nextTime = currentTime + travelTime;
                int nextCost = currentCost + passingFees[neighborCity];

                // If the path is valid(within time Limit) and cost is better than any prev found cost to node "neighborCity" at a comparable time, update the pq
                if(nextTime <= maxTime && nextCost < best[neighborCity][nextTime]){
                    best[neighborCity][nextTime] = nextCost;
                    // Add new state to the pq: [nextTime, nextCost, neighborCity]
                    pq.add(new int []{nextTime, nextCost, neighborCity});
                }
            }
        }

        // 5. Return the minimum cost after the algorithmn if no path found return -1 since minimun will still be the MaxValue

        int ans = Integer.MAX_VALUE;
        for(int t = 0; t <= maxTime; t++){
            ans = Math.min(ans, best[n-1][t]);
        }

        return ans == Integer.MAX_VALUE? -1 : ans;
    }

    
        public static void main (String[] args){
            int maxTime = 10;
            int[][] edges = {
        {0, 1, 2},
        {0, 2, 1},
        {0, 3, 10},
        {1, 3, 2},
        {3, 2, 2},
        {4, 3, 1}
    };
            int[] passingFees = {1,1,3,2,1};

            System.out.println(minCost(maxTime,edges,passingFees));

    }
}


