import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame() {
        int width = 600;
        int height = 400;
        // ініціалізація фрейму з назвою "Pong" та розмірами 600x400
        setTitle("Pong");
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // створення об'єкту класу GamePanel та додавання його до фрейму
        gamePanel = new GamePanel(width, height);
        add(gamePanel);

        // відображення фрейму та запуск гри
        setVisible(true);
        startGame();
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
