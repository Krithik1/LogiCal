package ui;

import model.ListOfLogs;
import model.Log;
import model.LogiCal;
import model.TruthTableWithLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//REPRESENTS THE LIST OF LOGS FRAME
public class ListOfImportantLogsFrame extends JFrame {
    //Fields
    static ListOfLogs lol;
    int currIndex;

    //MODIFIES: this
    //EFFECTS : constructs the List Of Important Logs Frame
    public ListOfImportantLogsFrame(ListOfLogs lol, int currIndex) {
        this.lol = lol;
        JPanel jp = new JPanel(new BorderLayout());
        this.currIndex = currIndex;
        try {
            LogPanel lp = new LogPanel(lol, lol.getImportant().get(currIndex), true);
            lp.setImportant();
            jp.add(lp, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jp.add(rightButton(this, lol, this.currIndex), BorderLayout.EAST);
        jp.add(leftButton(this, lol, this.currIndex), BorderLayout.WEST);
        add(jp);
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("List of Important Logs");
        setVisible(true);
    }

    //EFFECTS : updates logiCal with the new logs
    public static LogiCal updateLogical() {
        LogiCal lc = new LogiCal("Krithik's Workspace",  lol.toStringLogs(), lol.toStringImportantLogs());
        for (Log i : lol.getLogs()) {
            lc.addTruthTable(new TruthTableWithLogic(i.getNumVariables(), i.getInput(), i.getLogic()));
        }
        return lc;
    }

    //REQUIRES: currIndex between 0 and lol.getLogs().size()-1
    //EFFECTS : creates a right button
    private JButton rightButton(JFrame jf, ListOfLogs lol, int currIndex) {
        JButton rightButton = new JButton(">");
        final int[] currIndexCopy = {currIndex};
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currIndexCopy[0] == lol.getImportant().size() - 1) {
                    currIndexCopy[0] = 0;
                } else {
                    currIndexCopy[0]++;
                }
                new ListOfImportantLogsFrame(lol, currIndexCopy[0]);
                jf.dispose();
            }
        });
        return rightButton;
    }

    //REQUIRES: currIndex between 0 and lol.getLogs().size()-1
    //EFFECTS : creates a left button
    private JButton leftButton(JFrame jf, ListOfLogs lol, int currIndex) {
        JButton leftButton = new JButton("<");
        final int[] currIndexCopy = {currIndex};
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currIndexCopy[0] == 0) {
                    currIndexCopy[0] = lol.getImportant().size() - 1;
                } else {
                    currIndexCopy[0]--;
                }
                new ListOfImportantLogsFrame(lol, currIndexCopy[0]);
                jf.dispose();
            }
        });
        return leftButton;
    }
}