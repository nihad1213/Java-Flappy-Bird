
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class FlappyBird extends JPanel{
    int width = 360;
    int height = 640;

    public FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLUE);
    }

    
}