package ui.input;

import javax.swing.*;

//REPRESENTS THE INPUT TRUTH TABLE FOUR CLASS
public class InputTruthTableFour extends InputTruthTable {
    //EFFECTS : Creates the input truth table for 4 variables
    public InputTruthTableFour(JFrame jf) {
        super(4, 500, 500, jf);
    }

    //EFFECTS : sets the column values from the table
    @Override
    public void getLogicColumnValues(JTable jt) {
        int[] truthTable = new int[16];
        for (int i = 0; i < 16; i++) {
            super.setInput(i, (int) jt.getValueAt(i, 4));
        }
        int x = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == 1) {
                    if (j == 0) {
                        x = 12;
                        inputTruthTable4Help(x, truthTable);
                    } else {
                        x = 8;
                        inputTruthTable4Help(x, truthTable);
                    }
                } else {
                    inputTruthTable4Help(x, truthTable);
                    x += 4;
                }
            }
        }
    }

    //EFFECTS : sets the data
    @Override
    public Object[][] setData() {
        Object[][] data = {
                {"0", "0", "0", "0", " "},
                {"0", "0", "0", "1", " "},
                {"0", "0", "1", "0", " "},
                {"0", "0", "1", "1", " "},
                {"0", "1", "0", "0", " "},
                {"0", "1", "0", "1", " "},
                {"0", "1", "1", "0", " "},
                {"0", "1", "1", "1", " "},
                {"1", "0", "0", "0", " "},
                {"1", "0", "0", "1", " "},
                {"1", "0", "1", "0", " "},
                {"1", "0", "1", "1", " "},
                {"1", "1", "0", "0", " "},
                {"1", "1", "0", "1", " "},
                {"1", "1", "1", "0", " "},
                {"1", "1", "1", "1", " "}
        };
        return data;
    }

    //EFFECTS : sets the columns
    @Override
    public Object[] setColumns() {
        return new Object[]{"C", "D", "A", "B", "Logic"};
    }

    //EFFECTS : helper for setting the truth table
    private void inputTruthTable4Help(int x, int[] truthTable) {
        super.setInput(x, truthTable[x]);
        super.setInput(x + 1, truthTable[x + 1]);
        super.setInput(x + 2, truthTable[x + 3]);
        super.setInput(x + 3, truthTable[x + 2]);
    }
}
