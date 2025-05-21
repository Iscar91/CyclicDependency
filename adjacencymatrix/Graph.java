package adjacencymatrix;

import java.util.Arrays;

public class Graph {
    private int[][] adjMatrix;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination) {
        if (source >= 0 && source < numVertices && destination >= 0 && destination < numVertices) {
            adjMatrix[source][destination] = 1;
        } else {
            System.err.println("Invalid source or destination vertex");
        }
    }

    public void removeEdge(int source, int destination) {
        if (source >= 0 && source < numVertices && destination >= 0 && destination < numVertices) {
            adjMatrix[source][destination] = 0;
        } else {
            System.err.println("Invalid source or destination vertex");
        }
    }

    public int[][] getAdjMatrix() {
        return Arrays.stream(adjMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void display() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
