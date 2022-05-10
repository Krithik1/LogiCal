package ui;

import ui.input.InputTruthTableFour;
import ui.input.InputTruthTableOne;
import ui.input.InputTruthTableThree;
import ui.input.InputTruthTableTwo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//REPRESENTS THE TRUTH TABLE FRAME
public class TruthTableFrame extends JFrame {
    //EFFECTS : constructs the truth table frame
    public TruthTableFrame() {
        JPanel jp = new JPanel();
        jp.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        jp.setLayout(new GridLayout(0, 1));
        jp.add(askForNumVariables(), BorderLayout.WEST);
        jp.add(inputTextField(this), BorderLayout.EAST);
        add(jp, BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("LogiCal");
        pack();
        setVisible(true);
    }

    //REQUIRES: jf != null
    //EFFECTS : Creates the input text field
    private JTextField inputTextField(JFrame jf) {
        JTextField textField = new JTextField();
        Action act = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("1")) {
                    new InputTruthTableOne(jf);
                } else if (textField.getText().equals("2")) {
                    new InputTruthTableTwo(jf);
                } else if (textField.getText().equals("3")) {
                    new InputTruthTableThree(jf);
                } else if (textField.getText().equals("4")) {
                    new InputTruthTableFour(jf);
                } else {
                    JOptionPane.showMessageDialog(jf, "Only values between 1 and 4 are allowed");
                }
            }
        };
        textField.addActionListener(act);
        return textField;
    }

    //EFFECTS : asks the number of Variables
    private JTextField askForNumVariables() {
        JTextField jt = new JTextField();
        jt.setText("Please input the number of variables: ");
        jt.setEditable(false);
        jt.setVisible(true);
        return jt;
    }
}
