package Task1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private int circlesCount = 10;
    private Circle[] circles;


    public GamePanel(int width, int height) {
        Random ran = new Random();

        circles = new Circle[circlesCount];

        for(int i = 0; i < circlesCount; i++) {
            circles[i] = new Circle(ran.nextInt(width), ran.nextInt(height), ran.nextInt(40) + 1, ran.nextInt(10) + 1, new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        for(int i = 0; i < circlesCount; i++) {
            circles[i].draw(g);
        }
    }



    public void update() {
        for(int i = 0; i < circlesCount; i++) {
            circles[i].update(0, 0, getWidth(), getHeight());
        }
        repaint();
    }
}
