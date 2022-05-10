package ui;

import model.ListOfLogs;
import model.Log;
import model.LogiCal;
import model.TruthTableWithLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;

//REPRESENTS THE LIST OF LOGS FRAME
public class ListOfLogsFrame extends JFrame {
    //Fields
    static ListOfLogs lol;
    int currIndex;

    //MODIFIES: this
    //EFFECTS : constructs the List Of Logs Frame
    public ListOfLogsFrame(ListOfLogs lol, int currIndex) {
        this.lol = lol;
        JPanel jp = new JPanel(new BorderLayout());
        this.currIndex = currIndex;
        try {
            LogPanel lp = new LogPanel(lol, lol.getLogs().get(currIndex), false);
            String[] logs = lol.toStringLogs().split("\n\n");
            if (Arrays.asList(lol.toStringImportantLogs().split("\n\n")).contains(logs[currIndex])
                    && !lol.toStringImportantLogs().equals("")) {
                lp.setImportant();
            }
            jp.add(lp, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jp.add(rightButton(this, lol, this.currIndex), BorderLayout.EAST);
        jp.add(leftButton(this, lol, this.currIndex), BorderLayout.WEST);
        add(jp);
        detectIfClosed();
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("List of Logs");
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
                if (currIndexCopy[0] == lol.getLogs().size() - 1) {
                    currIndexCopy[0] = 0;
                } else {
                    currIndexCopy[0]++;
                }
                new ListOfLogsFrame(lol, currIndexCopy[0]);
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
                    currIndexCopy[0] = lol.getLogs().size() - 1;
                } else {
                    currIndexCopy[0]--;
                }
                new ListOfLogsFrame(lol, currIndexCopy[0]);
                jf.dispose();
            }
        });
        return leftButton;
    }

    //EFFECTS : detects if ListOfLogs Frame is closed
    private void detectIfClosed() {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {
                ListOfLogsFrame.updateLogical();
            }

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
}
