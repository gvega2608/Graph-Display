import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Defines the panel the drawings will be made in.
 *
 * @author Gabriela Vega-Lobatos; 6295304; U01
 * @author Anabella Amanau; 6318148; U01
 */
public class GraphDisplay extends JPanel
{
    ArrayList<Point> points;
    double localSearchCost;
    int[] localSearchRoute;

    public GraphDisplay(ArrayList<Point> points,
                        double localSearchCost,
                        int[] localSearchRoute)
    {
        this.points = points;
        this.localSearchCost = localSearchCost;
        this.localSearchRoute = localSearchRoute;
    }

    public void paint(Graphics g)
    {
        super.paint(g); // This line will ensure that the panel is cleared before painting

        // Draw edges according to the localSearchRoute
        g.setColor(Color.BLUE);
        for (int i = 0; i < localSearchRoute.length - 1; i++) {
            int pointIndex1 = localSearchRoute[i];
            int pointIndex2 = localSearchRoute[i + 1];
            Point point1 = points.get(pointIndex1);
            Point point2 = points.get(pointIndex2);
            g.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        }

        // Close the loop by drawing a line from the last point to the first
        if (localSearchRoute.length > 0) {
            Point lastPoint = points.get(localSearchRoute[localSearchRoute.length - 1]);
            Point firstPoint = points.get(localSearchRoute[0]);
            g.drawLine(lastPoint.getX(), lastPoint.getY(), firstPoint.getX(), firstPoint.getY());
        }

        // Draw the nodes
        g.setColor(Color.YELLOW);
        int radius = 5; // Adjust radius as needed
        for (Point point : points) {
            g.fillOval(point.getX() - radius, point.getY() - radius, radius * 2, radius * 2);
        }

        // Draw the algorithm name and path total distance at the bottom
        g.setColor(Color.BLACK);
        g.drawString("Algorithm: Local Search", 10, getHeight() - 35);
        g.drawString("Path Total Distance: " + localSearchCost, 10, getHeight() - 20);
    }
}
