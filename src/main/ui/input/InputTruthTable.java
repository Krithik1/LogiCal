package ui.input;

import model.KarnaughMap;
import model.Log;
import ui.LogiCalFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

//REPRESENTS THE ABSTRACT INPUT TRUTH TABLE FRAME
public abstract class InputTruthTable extends JFrame {
    //Fields
    private JPanel jp;
    private String logic;
    private JTextField logicText = new JTextField("");
    private int numVariables;
    private int[] input;
    private GridBagConstraints constraints = new GridBagConstraints();
    private static JFrame truthTableFrame;

    //MODIFIES: this
    //EFFECTS : Creates the InputTruthTable constructor
    public InputTruthTable(int numVariables, int width, int height, JFrame jf) {
        initializeFields(numVariables, jf);
        setTitle("Input Truth Table");
        jp = new JPanel();
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = setData();
        dm.setDataVector(data, setColumns());
        JTable jt = new JTable(dm);
        Object[] options = {0, 1};
        JComboBox combo = new JComboBox(options);
        TableColumn col = jt.getColumnModel().getColumn(numVariables);
        col.setCellEditor(new DefaultCellEditor(combo));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
        jp.setLayout(new GridBagLayout());
        jp.add(jt.getTableHeader(), changeConstraints(0, 0));
        jp.add(jt, changeConstraints(0,1));
        jp.add(addEnterButton(this, jt), changeConstraints(0, 2));
        logicText.setEditable(false);
        jp.add(logicText, changeConstraints(0, 3));
        jp.add(addCloseButton(this), changeConstraints(0, 4));
        add(jp);
    }

    //MODIFIES: this
    //EFFECTS : changes the constraints for the components in the GridBagLayout
    private GridBagConstraints changeConstraints(int gridx, int gridy) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 2;
        constraints.weighty = 2;
        return constraints;
    }

    //MODIFIES: this
    //EFFECTS : initializes all fields
    private void initializeFields(int numVariables, JFrame jf) {
        this.numVariables = numVariables;
        truthTableFrame = jf;
        input = new int[(int) Math.pow(2, numVariables)];
    }

    //MODIFIES: this
    //EFFECTS : adds Enter Button
    private JButton addEnterButton(JFrame jf, JTable jt) {
        JButton enter = new JButton("Enter") {
            {
                setSize(150, 75);
                setMaximumSize(getSize());
            }
        };
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getLogicColumnValues(jt);
                    KarnaughMap kmap = new KarnaughMap(numVariables, input);
                    logic = kmap.formLogic();
                    logicText.setText("Logic is: " + logic);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(jf, "Please enter all values");
                }
            }
        };
        enter.addActionListener(action);
        return enter;
    }

    //MODIFIES: this
    //EFFECTS : adds Close Button
    private JButton addCloseButton(JFrame jf) {
        JButton close = new JButton("Close");
        Action closeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!logicText.getText().equals("")) {
                    LogiCalFrame.updateLogs(new Log(numVariables, input, logic));
                    int ans = JOptionPane.showConfirmDialog(null,
                            "Do you want to put the truth table with logic in the important logs?",
                            "Important Logs",
                            JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        LogiCalFrame.updateImpLogs(new Log(numVariables, input, logic));
                    }
                    LogiCalFrame.setSavedState(false);
                    LogiCalFrame.setLogiCal(numVariables, input, logic);
                }
                InputTruthTable.truthTableFrame.dispose();
                jf.dispose();
            }
        };
        close.addActionListener(closeAction);
        return close;
    }

    //MODIFIES: this
    //EFFECTS : sets the input Value at index i
    public void setInput(int i, int inputValue) {
        input[i] = inputValue;
    }

    //EFFECTS : sets the column values from the table
    public abstract void getLogicColumnValues(JTable jt);

    //EFFECTS : sets the data
    public abstract Object[][] setData();

    //EFFECTS : sets the columns
    public abstract Object[] setColumns();
}
