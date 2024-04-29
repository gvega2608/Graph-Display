import java.util.Arrays;
import java.util.Random;

/**
 * Implements a Graph. Uses an adjacency matrix to represent the graph.
 */
public class Graph implements GraphInterface
{

    private int[][] matrix; //adjacency matrix
    private int verticesNumber;

    /**
     * Default constructor. Defines an empty graph of 5 vertices.
     */
    public Graph()
    {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }

    /**
     * Parameterized constructor. Defines an empty graph of n vertices.
     *
     * @param n number of vertices in the graph
     */
    public Graph(int n)
    {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    /**
     * Parameterized constructor. Creates a graph as defined by the matrix
     * parameter.
     *
     * @param matrix given adjacency matrix
     */
    public Graph(int[][] matrix)
    {
        this.verticesNumber = matrix.length;
        this.matrix = new int[verticesNumber][verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, verticesNumber);
        }

    }

    /**
     * Adds an edge between given vertices with given weight.
     *
     * @param v given vertex
     * @param w given vertex
     * @param weight given weight
     */
    public void addEdge(int v, int w, int weight)
    {
        matrix[v][w] = weight;
        matrix[w][v] = weight;
    }

    /**
     * Finds vertices adjacent to a given vertex.
     *
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array; size of array
     * = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v)
    {
        int[] vert = new int[verticesNumber];
        int total = 0;

        for (int i = 0; i < verticesNumber; i++){
            if (matrix[v][i] != 0){
                vert[total] = i;
                total++;
            }
        }
        return Arrays.copyOf(vert, total);
    }

    /**
     * Returns the number of vertices of this graph.
     *
     * @return number of vertices of this graph
     */
    public int getNumberOfVertices()
    {
        return verticesNumber;
    }

    /**
     * Returns weight of edge between given vertices.
     *
     * @param v given vertex
     * @param w given vertex
     * @return
     */
    public int getWeight(int v, int w)
    {
        return matrix[v][w];
    }

    /**
     * Removes edge between given vertices.
     *
     * @param v given vertex
     * @param w given vertex
     */
    public void removeEdge(int v, int w)
    {
        matrix[v][w] = 0;
        matrix[w][v] = 0;
    }

    /**
     * Returns a string description of this graph.
     *
     * @return string description of the graph
     */
    public String toString()
    {
        String s= "";

        for (int i = 0;i < verticesNumber; i++){
            for (int j = 0; i < verticesNumber; j++){
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Calculates distance of given route.
     *
     * @param a route
     *
     * @return distance of route
     */
    private int totalDistance(int[] a)
    {
        int n = verticesNumber;

        int totalWeight = 0;
        for (int i = 0; i < n; i++){
            int weight = matrix[a[i]][a[(i+1)%n]];
            totalWeight += weight;
        }
        return totalWeight;
    }

    /**
     * Given an array, generates random permutation of values in [0, n-1],
     * where n is size of given array; random permutation will be stored
     * in the array. Uses Fisher-Yates shuffle algorithm.
     *
     * @param a output array
     */
    private void randomPermutation(int[] a)
    {
        for (int i = 0; i < a.length; i++){
            a[i] = i;
        }

        Random rnd = new Random();
        for (int i = a.length - 1;i > 0; i--){
            int randomLocation = rnd.nextInt(i+1);

            if (randomLocation != i){
                int temp = a[i];
                a[i] = a[randomLocation];
                a[randomLocation] = temp;
            }
        }
    }

    /**
     * Finds a shortest route that visits every vertex
     * exactly once and returns to the starting point.
     * Uses local search, so optimal solution is not
     * obtained, in general.
     *
     * @param shortestRoute array with a shortest path (return value)
     *
     * @return shortest distance
     */
    public int TSP_localSearch(int[] shortestRoute)
    {
        int bestDistance;

        int[] a = new int[verticesNumber];
        randomPermutation(a);

        System.arraycopy(a,0, shortestRoute, 0, verticesNumber);
        bestDistance = totalDistance(shortestRoute);

        boolean betterSolutionFound;
        do {
            betterSolutionFound = false;
            PermutationNeighborhood pn = new PermutationNeighborhood(shortestRoute);
            while (pn.hasNext()){
                a = pn.next();
                int currentDistance = totalDistance(a);
                if (currentDistance < bestDistance){
                    System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
                    bestDistance = currentDistance;
                    betterSolutionFound = true;
                }
            }
        }while (betterSolutionFound);
        return bestDistance;
    }
}
