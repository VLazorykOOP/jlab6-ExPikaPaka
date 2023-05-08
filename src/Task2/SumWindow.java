package Task2;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;


public class SumWindow extends JFrame {
    private JTextField fileField;
    JTable table1;
    JTable table2;
    public SumWindow() {
        super("Сумма чисел в файле");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLayout(new BorderLayout());

        fileField = new JTextField(30);
        JButton loadButton = new JButton("Завантажити файл");
        loadButton.addActionListener(new LoadButtonListener());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Файл:"));
        topPanel.add(fileField);
        topPanel.add(loadButton);


        add(topPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private class LoadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(SumWindow.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                fileField.setText(file.getAbsolutePath());
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    int sizeX = 0;
                    int sizeY = 0;
                    int row = 0;

                    int[][] matrix;
                    int[][] out;
                    if((line = reader.readLine()) != null) { // Matrix initialization
                        String[] lineValues = line.split(" ");

                        sizeX = Integer.parseInt(lineValues[0]);
                        sizeY = Integer.parseInt(lineValues[1]);


                        if(sizeX > 5 || sizeY > 5) {
                            throw new ValueGreaterThanFiveException();
                        }

                        if(sizeX == 0 || sizeY == 0) {
                            throw new ArithmeticException("Size is 0");
                        }

                        System.out.println("" + sizeX + " " + sizeY);
                        matrix = new int[sizeY][sizeX];
                        out = new int[sizeY][sizeX];


                        JPanel matrices = new JPanel();
                        matrices.setLayout(null); // set null layout manager
                        table1 = new JTable(sizeY, sizeX);
                        table1.setBounds(5, 5 + 20, 200, 80);

                        table2 = new JTable(sizeY, sizeX);
                        table2.setBounds(5, 145 , 200, 80) ;



                        JLabel label1 = new JLabel("Початкова матриця:");
                        label1.setBounds(5, 5, 200, 10);
                        matrices.add(label1);

                        JLabel label2 = new JLabel("Оброблена матриця:");
                        label2.setBounds(5, 120, 200, 10);
                        matrices.add(label2);

                        matrices.add(table1);
                        matrices.add(table2);
                        add(matrices);
                        setVisible(true);



                        while ((line = reader.readLine()) != null) {
                            try {
                                lineValues = line.split(" ");
                                for (int i = 0; i < sizeX; i++) {
                                    matrix[row][i] = Integer.parseInt(lineValues[i]);
                                    System.out.print(matrix[row][i] + " ");
                                    table1.setValueAt(matrix[row][i], row, i);
                                }
                                row++;
                                System.out.println();
                            } catch (NumberFormatException ex) {
                                // ignore non-numeric lines
                            }
                        }
                        System.out.println();

                        int maxNum = matrix[0][0];
                        int maxRow = 0;

                        // Finding row with a max number
                        for (int i = 0; i < sizeY; i++) {
                            for (int j = 0; j < sizeX; j++) {
                                if (matrix[i][j] > maxNum) {
                                    maxNum = matrix[i][j];
                                    maxRow = i;
                                }
                            }
                        }
                        System.out.println("MaxRow " + maxRow);

                        // Rotating array so, that 1 row will be a row with a max number

                        int ind = 0;


                        if (maxRow != 0) {
                            for (int i = maxRow; i < sizeY; i++) {
                                out[ind] = matrix[i];
                                ind++;
                            }

                            for (int i = 0; i < maxRow; i++) {
                                out[ind] = matrix[i];
                                ind++;
                            }
                        }

                        // Output
                        for (int i = 0; i < sizeY; i++) {
                            for (int j = 0; j < sizeX; j++) {
                                table2.setValueAt(out[i][j], i, j);
                            }
                        }
                        System.out.println(Arrays.deepToString(out));

                        reader.close();


                        JFileChooser saveChooser = new JFileChooser();
                        result = saveChooser.showSaveDialog(SumWindow.this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File saveFile = saveChooser.getSelectedFile();
                            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
                            writer.write(sizeX + " ");
                            writer.write(sizeY + "\n");

                            for (int i = 0; i < sizeY; i++) {
                                for (int j = 0; j < sizeX; j++) {
                                    writer.write(out[i][j] + " ");
                                }
                                writer.write("\n");
                            }
                            writer.close();
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SumWindow.this,
                            "Помилка при читанні файла: " + ex.getMessage(),
                            "Помилка", JOptionPane.ERROR_MESSAGE);
                } catch (ValueGreaterThanFiveException ex) {
                    JOptionPane.showMessageDialog(SumWindow.this,
                            "Розмір матриці занадто великий!: " + ex.getMessage(),
                            "Помилка", JOptionPane.ERROR_MESSAGE);
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(SumWindow.this,
                            "Неправильний розмір матриці!: " + ex.getMessage(),
                            "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new SumWindow();
    }
}