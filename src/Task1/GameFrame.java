package Task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private boolean isDragging = false;
    private int BORDER_SIZE = 10;
    private Point dragStartPoint;
    public GameFrame() {
        int width = 600;
        int height = 400;
        // ініціалізація фрейму з назвою "Pong" та розмірами 600x400
        setTitle("Pong");
        setSize(width, height);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        //setOpacity(0.7f);


        // add mouse listener to allow window dragging and resizing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // start resizing
                    Point p = e.getPoint();
                    if (isLowerRightCorner(p)) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    } else {
                        // start dragging
                        isDragging = true;
                        dragStartPoint = e.getPoint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // end dragging or resizing
                isDragging = false;
                setCursor(Cursor.getDefaultCursor());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    // move window
                    Point current = e.getLocationOnScreen();
                    setLocation(current.x - dragStartPoint.x, current.y - dragStartPoint.y);
                } else if (getCursor().getType() == Cursor.SE_RESIZE_CURSOR) {
                    // resize window
                    int dx = e.getX() - getWidth();
                    int dy = e.getY() - getHeight();
                    setSize(getWidth() + dx, getHeight() + dy);
                }
            }
        });


        // створення об'єкту класу GamePanel та додавання його до фрейму
        gamePanel = new GamePanel(width, height);
        add(gamePanel);

        // відображення фрейму та запуск гри
        setVisible(true);
        startGame();
    }

    private boolean isLowerRightCorner(Point p) {
        return (p.x >= getWidth() - BORDER_SIZE && p.y >= getHeight() - BORDER_SIZE);
    }



    private void startGame() {
        // цикл гри
        while (true) {
            gamePanel.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    public static void main(String[] args) {
        new GameFrame();
    }
}
