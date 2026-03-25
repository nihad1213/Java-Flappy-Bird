
import javax.swing.JFrame;



public class Main {

    public static void main(String[] args) {
        int width = 360;
        int height = 640;

        JFrame jframe = new JFrame("Flappy Bird");
        jframe.setVisible(true);
        jframe.setSize(width, height);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}