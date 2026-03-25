import javax.swing.JFrame;



public class Main {

    public static void main(String[] args) {
        int width = 360;
        int height = 640;

        JFrame jframe = new JFrame("Flappy Bird");
        jframe.setSize(width, height);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird = new FlappyBird();
        jframe.add(flappyBird);
        jframe.pack();
        jframe.setVisible(true);
        

    }
}