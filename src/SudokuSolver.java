import javax.swing.*;

public class SudokuSolver {
    public static void solver(int[][] array){
        if (checkSudoku(array)) {
            if (recursionSolve(array)) {
                MyFrame.label.setText("Answer");
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        MyFrame.table.setValueAt(array[i][j], i, j);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(MyFrame.panel, "There is no answer", "ANSWER", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(MyFrame.panel, "Input conflict the rules", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
    private static boolean checkSudoku(int[][] array){

        for (int row = 0; row < array.length; row++){
            for (int column = 0; column < array[row].length; column++){
                if (array[row][column] != 0) {
                    int number = array[row][column];
                    array[row][column] = 0;
                    if (!isValid(array, row, column, number)) return false;
                    else array[row][column] = number;
                }
            }
        }
        return true;
    }

    private static boolean isNumberInRow(int[][] array, int row, int number){
        for (int i: array[row]){
            if (i == number) return true;
        }
        return false;
    }
    private static boolean isNumberInColumn(int[][] array, int column, int number){
        for (int[] row : array) {
            if (row[column] == number) return true;
        }
        return false;
    }
    private static boolean isNumberInBox(int[][] array, int row, int column, int number){
        int boxRow = row - row % 3;
        int boxColumn = column - column % 3;
        for (int i = boxRow; i < boxRow + 3; i++){
            for (int j = boxColumn; j < boxColumn + 3; j++){
                if (number == array[i][j]) return true;
            }
        }
        return false;
    }
    private static boolean isValid(int[][] array, int row, int column, int number){
        return !isNumberInRow(array, row, number) && !isNumberInColumn(array, column, number) && !isNumberInBox(array, row, column, number);
    }
    private static boolean recursionSolve(int[][] array){
        for (int row = 0; row < array.length; row++){
            for (int column = 0; column < array[row].length; column++){
                if (array[row][column] == 0){
                    for (int number = 1; number <= 9; number++){
                        if (isValid(array, row, column, number)){
                            array[row][column] = number;
                            if (recursionSolve(array)){
                                return true;
                            }
                            else array[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
