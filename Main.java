import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        new Main();
    }

    /**
     * Reads input file with points, instantiates graph, obtain shortest route,
     * and graphs it.
     */
    public Main()
    {
        //read filename
        Scanner in = new Scanner(System.in);
        System.out.print("Name of file: ");
        String filename = in.nextLine();

        //get points from file
        ArrayList<Point> points = getPointsFromFile(filename);

        //obtain adjacency matrix and define graph with it
        int[][] matrix = generateMatrix(points);
        Graph g = new Graph(matrix);

        //compute solution to problem
        int[] localSearchRoute = new int[points.size()];
        double localSearchCost = g.TSP_localSearch(localSearchRoute);

        FrameDisplay frame = new FrameDisplay(points, localSearchCost, localSearchRoute);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Reads points from given input file and returns them in an array list.
     *
     * @param filename name of input file
     * @return array list of points
     */
    public ArrayList<Point> getPointsFromFile(String filename)
    {
        ArrayList<Point> points = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int numberOfPoints = Integer.parseInt(scanner.nextLine()); // First line contains the number of points

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] coordinates = line.split("\\s+");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                points.add(new Point(x, y));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return points;
    }

    /**
     * Generates a square matrix from the given array list of points.
     * Cell [i][j] of the matrix will contain the distance between points[i] and
     * points[j].
     *
     * @param points array list of points
     * @return matrix of pairwise distances
     */
    public int[][] generateMatrix(ArrayList<Point> points)
    {
        int[][] matrix = new int[points.size()][points.size()];

        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    Point point1 = points.get(i);
                    Point point2 = points.get(j);
                    int dx = point2.getX() - point1.getX();
                    int dy = point2.getY() - point1.getY();
                    matrix[i][j] = (int) Math.round(Math.sqrt(dx * dx + dy * dy));
                }
            }
        }

        return matrix;
    }
}
