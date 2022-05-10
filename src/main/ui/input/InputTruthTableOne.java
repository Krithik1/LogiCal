package ui.input;

import javax.swing.*;

//REPRESENTS THE INPUT TRUTH TABLE ONE CLASS
public class InputTruthTableOne extends InputTruthTable {
    //EFFECTS : Creates the input truth table for 1 variables
    public InputTruthTableOne(JFrame jf) {
        super(1, 100, 300, jf);
    }

    //EFFECTS : sets the column values from the table
    @Override
    public void getLogicColumnValues(JTable jt) {
        for (int i = 0; i < 2; i++) {
            super.setInput(i, (int) jt.getValueAt(i, 1));
        }
    }

    //EFFECTS : sets the data
    @Override
    public Object[][] setData() {
        Object[][] data = {
                {"0", " "},
                {"1", " "}
        };
        return data;
    }

    //EFFECTS : sets the columns
    @Override
    public Object[] setColumns() {
        return new Object[]{"A", "Logic"};
    }
}
