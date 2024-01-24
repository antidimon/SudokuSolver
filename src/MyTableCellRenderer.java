import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int rowIndex, int ColIndex) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, ColIndex);
        switch (ColIndex) {
            case 2, 5 -> {
                if (rowIndex == 2 || rowIndex == 5) {
                    makeBorder(5, 5);
                }else makeBorder(1, 5);
            }
            case 0, 1, 3, 4, 6, 7, 8 -> {
                if (rowIndex == 2 || rowIndex == 5) {
                    makeBorder(5, 1);
                }else makeBorder(1, 1);
            }
        }
        if(isSelected){
            cell.setBackground(new Color(0xEEEEA0));
        }else{
            cell.setBackground(new Color(0xEEEEF5));
        }
        return cell;
    }
    private void makeBorder(int a3, int a4){
        setBorder(new MatteBorder(1, 1, a3, a4, Color.BLACK));
    }
}
