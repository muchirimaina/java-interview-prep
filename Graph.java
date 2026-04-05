import java.util.*;

public class Graph{
    private int vertices;
    private List<List<Integer>> adjList;
    
    
    public Graph(int vertices){
        this.vertices = vertices;
        adjList = new ArrayList<>();
        
        for(int i = 0; i < vertices; i++){
            adjList.add(new ArrayList<>());
        }
    }
    
    
    
    public void addEdge(int u, int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    
    
    public void printGraph(){
        for(int i= 0; i < vertices; i++){
            System.out.print(i + "->");
            
            for(int neighbor: adjList.get(i)){
                System.out.print(neighbor + " ");
            }
            
            
            System.out.println();
        }
    }
    
    
    public static void main(String[] args){
        Graph g = new Graph(5);
        
        g.addEdge(0,1);
        g.addEdge(0,4);
        g.addEdge(1,2);
        g.addEdge(1,3);
        
        
        g.printGraph();
    }
}
