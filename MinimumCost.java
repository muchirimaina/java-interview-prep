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

        int [] minCostForDestination = new int[n];
        //Assigning the biggest integer to compare with
        Arrays.fill(minCostForDestination, Integer.MAX_VALUE);

        // 3. Start the algorithm/ Dijkstra's -- finding the minimumCost & time
        int startNode = 0;
        minCostForDestination[startNode] = passingFees[startNode];
        // The starting State 
        pq.add(new int []{0,passingFees[startNode],startNode});

        while(!pq.isEmpty()){
            // The queue has only one item at the moment | Update the values as you get the shortest paths, next array in the pq
            int [] current = pq.poll();
            int currentTime = current[0];
            int currentCost = current[1];
            int homeCity = current[2];
            
            //If the currentTime exceeds the limit or the cost is higher, the path is subOptimal
            if(currentTime > maxTime || currentCost > minCostForDestination[homeCity]){
                continue;
            }

            

            // 4. Explore the neighbors

            for(int[] edge : citiesGraph.getOrDefault(homeCity, Collections.emptyList())){
                int neighborCity = edge[0];
                int travelTime = edge[1];
                int nextTime = currentTime + travelTime;
                int nextCost = currentCost + passingFees[neighborCity];

                // If the path is valid(within time Limit) and cost is better than any prev found cost to node "neighborCity" at a comparable time, update the pq
                if(nextTime <= maxTime && nextCost < minCostForDestination[neighborCity]){
                    minCostForDestination[neighborCity] = nextCost;
                    // Add new state to the pq: [nextTime, nextCost, neighborCity]
                    pq.add(new int []{nextTime, nextCost, neighborCity});
                }
            }
        }

        // 5. Return the minimum cost after the algorithmn if no path found return -1 since minimun will still be the MaxValue

        if(minCostForDestination[n-1] == Integer.MAX_VALUE){
            return -1;
        }else{
            return minCostForDestination[n-1];
        }  
    }

    
    public static void main (String[] args){
        int maxTime = 30;
        int[][] edges = {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}};
        int[] passingFees = {5,1,2,20,20,3};

        System.out.println(minCost(maxTime,edges,passingFees));

    }
}


