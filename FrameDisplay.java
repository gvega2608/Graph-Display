import javax.swing.*;
import java.util.ArrayList;

/**
 * Defines a frame to which a panel with drawings will be added.
 *
 * @author add here name, Panther ID, and class section of all authors
 */
public class FrameDisplay extends JFrame
{
    int WIDTH = 600;
    int HEIGHT = 600;

    public FrameDisplay(ArrayList<Point> points,
                        double localSearchCost,
                        int[] localSearchRoute)
    {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay(points, localSearchCost, localSearchRoute);
        add(panel);
    }
}
