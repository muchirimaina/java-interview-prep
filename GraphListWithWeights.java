import java.util.*;


    class Pair{
        int node;
        int weight;

        Pair(int node,int weight){
            this.node = node;
            this.weight = weight;
        }
    }
public class Graph{
    private int vertices;
    private List<List<Pair>> adjList;
    
    
    public Graph(int vertices){
        this.vertices = vertices;
        adjList = new ArrayList<>();
        
        for(int i = 0; i < vertices; i++){
            adjList.add(new ArrayList<>());
        }
    }
    
    
    // For undirected graphs, you have to add for both vertices, If Directed, You'll only add once
    
    public void addEdge(int u, int v, int w){
        adjList.get(u).add(new Pair(v,w));
        adjList.get(v).add(new Pair(u,w));
    }
    
    
    public void printGraph(){
        for(int i= 0; i < vertices; i++){
            System.out.print(i + "->");
            
            for(Pair neighbor: adjList.get(i)){
                System.out.print("("+ neighbor.node + ","+ neighbor.weight +")");
            }
            
            
            System.out.println();
        }
    }
    
    
    public static void main(String[] args){
        Graph g = new Graph(5);
        
        g.addEdge(0,1,4);
        g.addEdge(0,4,6);
        g.addEdge(1,2,6);
        g.addEdge(1,3,4);
        
        
        g.printGraph();
    }
}
