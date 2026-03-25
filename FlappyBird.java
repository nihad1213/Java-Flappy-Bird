import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird extends JPanel implements ActionListener {
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

    double velocityY = 0;
    double gravity = 0.4;
    int jumpStrength = -8;

    int backgroundX = 0;
    int backgroundSpeed = 2;

    boolean gameOver = false;

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

    class Pipe {
        int x, topHeight, bottomY, bottomHeight;
        boolean scored = false;

        Pipe(int topHeight) {
            this.x = width;
            this.topHeight = topHeight;
            this.bottomY = topHeight + pipeGap;
            this.bottomHeight = height - bottomY;
        }
    }

    ArrayList<Pipe> pipes = new ArrayList<>();
    int pipeSpeed = 4;
    int pipeGap = 160;
    int pipeInterval = 90;
    int frameCount = 0;
    int score = 0;
    Random random = new Random();

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
                    if (gameOver) {
                        restartGame();
                    } else {
                        velocityY = jumpStrength;
                    }
                }
            }
        });

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    void restartGame() {
        bird.y = birdY;
        velocityY = 0;
        pipes.clear();
        frameCount = 0;
        score = 0;
        gameOver = false;
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, backgroundX, 0, width, height, null);
        g.drawImage(backgroundImg, backgroundX + width, 0, width, height, null);

        for (Pipe p : pipes) {
            g.drawImage(topPipeImg,    p.x, 0,         64, p.topHeight,    null);
            g.drawImage(bottomPipeImg, p.x, p.bottomY, 64, p.bottomHeight, null);
        }

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(String.valueOf(score), width / 2 - 10, 50);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("GAME OVER", width / 2 - 80, height / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Score: " + score, width / 2 - 35, height / 2 + 15);
            g.drawString("Press SPACE to restart", width / 2 - 85, height / 2 + 45);
        }
    }

    public void move() {
        backgroundX -= backgroundSpeed;
        if (backgroundX <= -width) {
            backgroundX = 0;
        }

        velocityY += gravity;
        bird.y += (int) velocityY;

        if (bird.y < 0) {
            bird.y = 0;
            velocityY = 0;
        }

        if (bird.y + bird.height >= height) {
            bird.y = height - bird.height;
            velocityY = 0;
            gameOver = true;
            gameLoop.stop();
            System.out.println("Game Over! Score: " + score);
        }

        frameCount++;
        if (frameCount % pipeInterval == 0) {
            int minTop = 80;
            int maxTop = height - pipeGap - 80;
            int topHeight = minTop + random.nextInt(maxTop - minTop);
            pipes.add(new Pipe(topHeight));
        }

        Rectangle birdRect = new Rectangle(bird.x + 4, bird.y + 4, bird.width - 8, bird.height - 8);

        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe p = pipes.get(i);
            p.x -= pipeSpeed;

            if (!p.scored && p.x + 64 < bird.x) {
                p.scored = true;
                score++;
            }

            Rectangle topRect = new Rectangle(p.x, 0, 64, p.topHeight);
            Rectangle botRect = new Rectangle(p.x, p.bottomY, 64, p.bottomHeight);
            if (birdRect.intersects(topRect) || birdRect.intersects(botRect)) {
                gameOver = true;
                gameLoop.stop();
                System.out.println("Game Over! Score: " + score);
            }

            if (p.x + 64 < 0) {
                pipes.remove(i);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
}