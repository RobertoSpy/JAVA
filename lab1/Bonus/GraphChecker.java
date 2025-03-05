import java.util.*;

public class GraphChecker {

   
    public static boolean hasClique(int[][] graph, int k) {
        int n = graph.length;
        
        
        int[] combination = new int[k];
        
        
        return generateCombinations(graph, combination, 0, 0, k);
    }
    
   
    private static boolean isClique(int[][] graph, int[] combination, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                int u = combination[i];
                int v = combination[j];
                if (graph[u][v] == 0) {
                    return false;  
                }
            }
        }
        return true;  
    }
    
    
    private static boolean generateCombinations(int[][] graph, int[] combination, int start, int depth, int k) {
        if (depth == k) {
            
            return isClique(graph, combination, k);
        }
        
        
        for (int i = start; i < graph.length; i++) {
            combination[depth] = i;
            if (generateCombinations(graph, combination, i + 1, depth + 1, k)) {
                return true;  
            }
        }
        
        return false;  
    }

    
    public static int[][] generateComplementGraph(int[][] graph) {
        int n = graph.length;
        int[][] complement = new int[n][n];
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {  
                    if (graph[i][j] == 0) {
                        complement[i][j] = 1;  
                    } else {
                        complement[i][j] = 0;  
                    }
                      
                }
            }
        }
        return complement;
    }
    
    public static int[][] generateGraph(int n) {
        Random rand = new Random();
        int[][] graph = new int[n][n];

        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[i][j] = rand.nextInt(2);
                graph[j][i] = graph[i][j]; 
            }
        }
        
        return graph;
    }

    
    public static void printAdjacencyMatrix(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Must be 2 parameters.");
            return;
        }

        
        int n = Integer.parseInt(args[0]);  
        int k = Integer.parseInt(args[1]);  

        
        int[][] graph = generateGraph(n);

        
        System.out.println("Adjancy Matrix:");
        printAdjacencyMatrix(graph);

        
        System.out.println("Clique at least  " + k + "? " + hasClique(graph, k));
        int[][] complementGraph = generateComplementGraph(graph);
        
        System.out.println("Stable at least " + k + "? " + hasClique(complementGraph, k));
    }
}
