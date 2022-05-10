package ui.input;

import javax.swing.*;

//REPRESENTS THE INPUT TRUTH TABLE TWO CLASS
public class InputTruthTableTwo extends InputTruthTable {
    //EFFECTS : Creates the input truth table for 3 variables
    public InputTruthTableTwo(JFrame jf) {
        super(2, 100, 325, jf);
    }

    //EFFECTS : sets the column values from the table
    public void getLogicColumnValues(JTable jt) {
        for (int i = 0; i < 4; i++) {
            super.setInput(i, (int) jt.getValueAt(i, 2));
        }
    }

    //EFFECTS : sets the data
    @Override
    public Object[][] setData() {
        Object[][] data = {
                {"0", "0", " "},
                {"0", "1", " "},
                {"1", "0", " "},
                {"1", "1", " "}
        };
        return data;
    }

    //EFFECTS : sets the columns
    @Override
    public Object[] setColumns() {
        return new Object[]{"B", "A", "Logic"};
    }
}
