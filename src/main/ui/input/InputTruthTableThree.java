package ui.input;

import javax.swing.*;

//REPRESENTS THE INPUT TRUTH TABLE THREE CLASS
public class InputTruthTableThree extends InputTruthTable {
    //EFFECTS : Creates the input truth table for 3 variables
    public InputTruthTableThree(JFrame jf) {
        super(3, 350, 400, jf);
    }

    //EFFECTS : sets the column values from the table
    @Override
    public void getLogicColumnValues(JTable jt) {
        int[] truthTable = new int[8];
        for (int i = 0; i < 8; i++) {
            truthTable[i] = (int) jt.getValueAt(i, 3);
        }
        int x = 0;
        for (int i = 0; i < 2; i++) {
            super.setInput(x, truthTable[x]);
            super.setInput(x + 1, truthTable[x + 1]);
            super.setInput(x + 2, truthTable[x + 3]);
            super.setInput(x + 3, truthTable[x + 2]);
            x += 4;
        }
    }

    //EFFECTS : sets the data
    @Override
    public Object[][] setData() {
        Object[][] data = {
                {"0", "0", "0", " "},
                {"0", "0", "1", " "},
                {"0", "1", "0", " "},
                {"0", "1", "1", " "},
                {"1", "0", "0", " "},
                {"1", "0", "1", " "},
                {"1", "1", "0", " "},
                {"1", "1", "1", " "}
        };
        return data;
    }

    //EFFECTS : sets the columns
    @Override
    public Object[] setColumns() {
        return new Object[]{"C", "A", "B", "Logic"};
    }
}
