package adjacencymatrix;

import java.util.Scanner;

public class CycleDetector {

    public CycleDetector() {
        // Constructor
    }

    // Method to display a matrix
    public static void showMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Matrix is null.");
            return;
        }
        for (int[] row : matrix) {
            if (row == null) {
                System.out.println("Row is null.");
                continue;
            }
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println("--------");
    }

    // Method to multiply two matrices
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length; // b's columns
        int p = b.length;    // a's columns and b's rows must match
        
        if (a[0].length != p) {
            System.err.println("Matrix multiplication dimension mismatch.");
            return null; 
        }

        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // Method to calculate matrix power A^p
    public static int[][] powerMatrix(int[][] A, int p) {
        if (p < 1) throw new IllegalArgumentException("Power must be >= 1");
        int n = A.length;
        if (A.length != A[0].length) throw new IllegalArgumentException("Matrix must be square");

        if (p == 1) {
            // Return a copy of A
            int[][] copy = new int[n][n];
            for(int i=0; i<n; i++) System.arraycopy(A[i], 0, copy[i], 0, n);
            return copy;
        }
        
        if (p % 2 == 1) {
            return multiplyMatrices(A, powerMatrix(A, p - 1));
        } else {
            int[][] half = powerMatrix(A, p / 2);
            return multiplyMatrices(half, half);
        }
    }

    // Method to perform OR operation on two matrices (element-wise)
    public static int[][] orMatrices(int[][] A, int[][] B) {
        int n = A.length;
        if (n != B.length || A[0].length != B[0].length) {
            System.err.println("OR operation dimension mismatch.");
            return null;
        }
        int[][] result = new int[n][A[0].length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1 || B[i][j] == 1) {
                    result[i][j] = 1;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // --- Basic Graph.java Demonstration ---
        System.out.println("--- Basic Graph.java Demonstration ---");
        Graph demoGraph = new Graph(3);
        demoGraph.addEdge(0, 1);
        demoGraph.addEdge(1, 2);
        demoGraph.addEdge(2, 0); // Creates a cycle
        System.out.println("Displaying demoGraph:");
        demoGraph.display();
        System.out.println("--- End of Basic Graph Demonstration ---\n");
        // --- End of Demonstration ---

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of variables (nodes):");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        Graph graph = new Graph(n); // Create Graph instance

        System.out.println("Enter adjacency matrix rows (e.g., 'BC' for A->B, A->C if A is row 0):");
        System.out.println("For " + n + " nodes, input " + n + " lines.");
        System.out.println("Characters correspond to node indices (A=0, B=1, etc.).");

        for (int i = 0; i < n; i++) {
            System.out.print("Connections for node " + (char)('A'+i) + ": ");
            String line = sc.nextLine().toUpperCase();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if ('A' <= c && c < ('A' + n)) { // Ensure character is within valid range
                    graph.addEdge(i, c - 'A'); // Use graph.addEdge
                } else {
                    System.out.println("Ignoring invalid character '" + c + "' for node " + (char)('A'+i) + ". Max node char is " + (char)('A'+n-1));
                }
            }
        }
        
        System.out.println("Initial Adjacency Matrix (from Graph object):");
        graph.display(); // Use graph.display()

        // P is the matrix that will be powered up. Initially, it's the graph's adjacency matrix.
        int[][] P_base = graph.getAdjMatrix(); // Get initial matrix from graph

        // O is the reachability matrix, O = P_base + P_base^2 + ... + P_base^n
        // Initialize O with P_base (for P_base^1)
        int[][] O = new int[n][n];
        for(int i=0; i<n; i++) System.arraycopy(P_base[i], 0, O[i], 0, n);
        
        System.out.println("Calculating reachability matrix O = P_base + P_base^2 + ... + P_base^n");
        System.out.println("Initial P_base (P_base^1):");
        showMatrix(P_base); // Use showMatrix for this intermediate matrix
        System.out.println("Initial O (same as P_base^1):");
        showMatrix(O);

        for (int i = 2; i <= n; i++) {
            // Calculate P_base^i.
            // The powerMatrix function needs the original adjacency matrix (from graph.getAdjMatrix())
            int[][] P_i = powerMatrix(graph.getAdjMatrix(), i); 
            System.out.println("P_base^" + i + ":");
            showMatrix(P_i);
            O = orMatrices(O, P_i);
            System.out.println("Current O matrix (after ORing with P_base^" + i + "):");
            showMatrix(O);
        }

        System.out.println("Final Reachability Matrix O:");
        showMatrix(O);

        boolean cycle = false;
        for (int i = 0; i < n; i++) {
            if (O[i][i] == 1) { // Check diagonal elements
                cycle = true;
                System.out.println("Cycle detected involving node " + (char)('A'+i) + " (O["+i+"]["+i+"] = 1)");
                break;
            }
        }

        if (cycle) {
            System.out.println("Cycle detected in the graph.");
        } else {
            System.out.println("No cycle detected in the graph.");
        }
        sc.close();
    }
}
