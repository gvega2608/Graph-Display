import javax.swing.*;
import java.util.ArrayList;

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
