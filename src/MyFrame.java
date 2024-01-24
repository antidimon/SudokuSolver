import javax.swing.*;
import java.awt.*;
public class MyFrame extends JFrame {
    protected static JTable table;
    protected static JPanel panel;
    protected static JButton applyButton;
    protected static JButton resetButton;
    protected static JLabel label;
    private final int x = 1200, y = 800;
    private boolean flag;
    MyFrame(String s){
        super(s);
        this.setSize(new Dimension(x, y));
        this.setResizable(false);
        this.add(makePanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel makePanel(){
        panel = new JPanel();
        panel.setLayout(null);
        makeLabel();
        panel.add(label);
        makeTable();
        panel.add(table);
        makeApplyButton();
        panel.add(applyButton);
        makeResetButton();
        panel.add(resetButton);
        panel.setBackground(new Color(0x0C4DE1));
        panel.setVisible(true);
        return panel;
    }
    private void makeLabel(){
        label = new JLabel("Input numbers");
        label.setBounds(x/4, y/20, x/2, y/10);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    private void makeTable() {
        table = new JTable(9, 9);
        table.setBounds(x/4, y*7/40, x/2, y*9/16);
        table.setRowHeight(y / 16);
        table.setFont(new Font("Arial", Font.BOLD, 30));
        table.setGridColor(Color.BLACK);
        for (int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRenderer());
        }
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        table.setVisible(true);
    }
    private void makeApplyButton(){
        applyButton = new JButton("Solve");
        applyButton.setBounds(x/5, y*31/40, x/5, y/10);
        applyButton.setFont(new Font("Arial", Font.BOLD, 50));
        applyButton.addActionListener(e -> fillTheArray());
        applyButton.setVisible(true);
    }
    private void makeResetButton(){
        resetButton = new JButton("Reset");
        resetButton.setBounds(x*3/5, y*31/40, x/5, y/10);
        resetButton.setFont(new Font("Arial", Font.BOLD, 50));
        resetButton.addActionListener(e -> resetAll());
        resetButton.setVisible(true);
    }
    private void fillTheArray(){
        int[][] arraySudoku = new int[9][9];
        flag = true;
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int column = 0; column < table.getColumnCount(); column++) {
                int value = parseValueFromInput(row, column);
                if (value != -1) arraySudoku[row][column] = value;
                else break;
            }
            if (!flag) break;
        }
        if (flag) SudokuSolver.solver(arraySudoku);
    }
    private int parseValueFromInput(int row, int column){
        if (table.getValueAt(row, column) == null) return 0;
        else {
            try {
                int value = Integer.parseInt(String.valueOf(table.getValueAt(row, column)));
                if (value > 9 || value <= 0) {
                    JOptionPane.showMessageDialog(panel, "Wrong number", "ERROR", JOptionPane.WARNING_MESSAGE);
                    flag = false;
                    return -1;
                }
                return value;
            }catch (NumberFormatException exc){
                JOptionPane.showMessageDialog(panel, "Not a digit", "ERROR", JOptionPane.WARNING_MESSAGE);
                flag = false;
                return -1;
            }
        }
    }
    private void resetAll(){
        resetTable();
        label.setText("Input numbers");
    }

    protected static void resetTable(){
        for (int i = 0; i < table.getRowCount(); i++){
            for (int j = 0; j < table.getColumnCount(); j++){
                table.setValueAt(null, i, j);
            }
        }
    }
}
