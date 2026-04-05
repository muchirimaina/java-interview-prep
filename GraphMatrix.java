import java.util.*;

public class GraphMatrix{
    private int vertices;
    private int[][] matrix;
    
    
    public GraphMatrix(int vertices){
        this.vertices = vertices;
       matrix = new int[vertices][vertices];
    }
    
    
    // Add Edge(undirected)
    public void addEdge(int u, int v){
        matrix[u][v] = 1;
        matrix[v][u] = 1;
    }
    
    
    public void printGraph(){
        for(int i= 0; i < vertices; i++){
           for(int j = 0; j < vertices; j++){
               System.out.print(matrix[i][j]+" ");
           }
           System.out.println();
        }
        
        
    }
    
    
    public static void main(String[] args){
        GraphMatrix g = new GraphMatrix(5);
        
        g.addEdge(0,1);
        g.addEdge(0,4);
        g.addEdge(1,2);
        g.addEdge(1,3);
        
        
        g.printGraph();
    }
}
