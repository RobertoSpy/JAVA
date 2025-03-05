import java.util.*;

public class GraphGenerator {
    private static Random rand = new Random();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Must 2 parameters.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        if (k > n) {
            System.out.println("K must be smaller then n.");
            return;
        }

        long startTime = System.nanoTime();
        int[][] matrix = generateGraph(n, k);
        long endTime = System.nanoTime();

         if (n <= 100) {
           printMatrix(matrix);
        }

        analyzeGraph(matrix);

        if (n > 30000) {
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
        }
    }
        
    private static int[][] generateGraph(int n, int k) {
        int[][] matrix = new int[n][n];  
        boolean[] usedNodes = new boolean[n]; 
        int[] cliqueNodes = new int[k];
        int i = 0;
    
       
        while (i < k) {
           int node = rand.nextInt(n);  
            if (!usedNodes[node]) { 
                cliqueNodes[i] = node;
                usedNodes[node] = true;
                i++;
    
                
                for (int j = 0; j < i - 1; j++) {
                    matrix[cliqueNodes[i - 1]][cliqueNodes[j]] = 1;
                    matrix[cliqueNodes[j]][cliqueNodes[i - 1]] = 1;
                }
            }
        }
    
        
        int[] stableSetNodes = new int[k];
        i = 0;
        while (i < k) {
            int node = rand.nextInt(n); 
            if (!usedNodes[node]) { 
                stableSetNodes[i] = node; 
                usedNodes[node] = true; 
                i++;
    
                
                for (int j = 0; j < i - 1; j++) {
                    matrix[stableSetNodes[i - 1]][stableSetNodes[j]] = 0;
                    matrix[stableSetNodes[j]][stableSetNodes[i - 1]] = 0;
                }
            }
        }
    
        
        for (int x = 0; x < n; x++) {
            for (int y = x + 1; y < n; y++) {
                
                boolean inStableSet = false;
                for (int node : stableSetNodes) {
                    if (x == node || y == node) {
                        inStableSet = true;
                        break;
                    }
                }
                
                if (!inStableSet) {
                    if (matrix[x][y] == 0) {
                        matrix[x][y] = matrix[y][x] = rand.nextInt(2); 
                    }
                }
            }
        }
    
        return matrix;
    }
    
    

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {       
            for (int j = 0; j < matrix[i].length; j++) { 
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void analyzeGraph(int[][] matrix) {
        int n = matrix.length;
        int m = 0;
        int[] degrees = new int[n];

        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                   
                    degrees[i]++;
                    degrees[j]++; m++;
                }
            }
        }

        int maxDegree = degrees[0]; 
        int minDegree = degrees[0];

        for (int i = 1; i < degrees.length; i++) {
            if (degrees[i] > maxDegree) {
             maxDegree = degrees[i]; 
    }
         if (degrees[i] < minDegree) {
        minDegree = degrees[i]; 
    }
}

        int SumDegrees = 0;
     for (int i = 0; i < degrees.length; i++) {
        SumDegrees += degrees[i]; 
}

        boolean Verify;
        if(SumDegrees == 2 * m){
            Verify = true;
        } else{
            Verify = false;
        }

        System.out.println("Number of edges (m): " + m);
        System.out.println("Δ(G): " + maxDegree);
        System.out.println("δ(G): " + minDegree);
        System.out.println("Sum of degrees: " + SumDegrees);
        System.out.println("2 * m = SumDegrees? " + Verify);
    }
}
