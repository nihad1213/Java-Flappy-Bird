
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FlappyBird extends JPanel{
    int width = 360;
    int height = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    public FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLUE);

        backgroundImg = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png"))
            .getImage();

        birdImg = new ImageIcon(getClass().getResource("./assets/flappybird.png"))
            .getImage();
            
        topPipeImg = new ImageIcon(getClass().getResource("./assets/toppipe.png"))
            .getImage();

        bottomPipeImg = new ImageIcon(getClass().getResource("./assets/bottompipe.png"))
            .getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, width, height, null);   
    }

    
}