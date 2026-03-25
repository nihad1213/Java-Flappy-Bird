
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird extends JPanel implements ActionListener{
    int width = 360;
    int height = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int birdX = width / 8;
    int birdY = height / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    int velocityY = 0;
    int gravity = 1;
    int jumpStrength = -15;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    Bird bird;

    Timer gameLoop;


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

        bird = new Bird(birdImg);

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
                    velocityY = jumpStrength;
                }
            }
        });

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        System.out.println("draw");
        g.drawImage(backgroundImg, 0, 0, width, height, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
    
        if (bird.y < 0) {
            bird.y = 0;
            velocityY = 0;
        }

        if (bird.y + bird.height > height) {
            bird.y = height - bird.height;
            velocityY = 0;
        }

        bird.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    
}