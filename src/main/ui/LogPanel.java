package ui;

import model.ListOfLogs;
import model.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

//REPRESENTS THE LOG PANEL
public class LogPanel extends JPanel {
    //Fields
    GridBagConstraints constraints = new GridBagConstraints();
    static ListOfLogs logs;
    Log log;
    boolean isImportant = false;
    boolean importantFrame;
    JButton starButton;

    //MODIFIES: this
    //EFFECTS : Constructs the LogPanel
    public LogPanel(ListOfLogs logs, Log log, boolean importantFrame) throws IOException {
        LogPanel.logs = logs;
        this.log = log;
        this.importantFrame = importantFrame;
        setLayout(new GridBagLayout());
        add(new JLabel(" "), changeConstraints(GridBagConstraints.VERTICAL, 0, 0));
        add(new JLabel("Log"), changeConstraints(GridBagConstraints.HORIZONTAL, 1, 0));
        starButton = createStarButton();
        add(starButton, changeConstraints(GridBagConstraints.HORIZONTAL,2, 0));
        add(new JLabel("Number of Variables: " + log.getNumVariables()),
                changeConstraints(GridBagConstraints.HORIZONTAL,1, 2));
        JTable jt = formTruthTable();
        add(jt.getTableHeader(), changeConstraints(GridBagConstraints.HORIZONTAL,1, 3));
        add(jt, changeConstraints(GridBagConstraints.HORIZONTAL,1, 4));
        add(new JLabel("Logic is: " + log.getLogic()), changeConstraints(GridBagConstraints.HORIZONTAL,1, 5));
    }

    //MODIFIES: this
    //EFFECTS : sets the panel to important
    public void setImportant() {
        Image starButtonActive = null;
        try {
            starButtonActive = ImageIO.read(new File("data/fullStar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        starButtonActive = starButtonActive.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        starButton.setIcon(new ImageIcon(starButtonActive));
        isImportant = true;
    }

    //MODIFIES: this
    //EFFECTS : changes the constraints for the components in the GridBagLayout
    private GridBagConstraints changeConstraints(int layout, int gridx, int gridy) {
        constraints.fill = layout;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 2;
        constraints.weighty = 4;
        return constraints;
    }

    //EFFECTS : creates the truth table JTable
    private JTable formTruthTable() {
        if (log.getNumVariables() == 1) {
            return formTruthTable1();
        } else if (log.getNumVariables() == 2) {
            return formTruthTable2();
        } else if (log.getNumVariables() == 3) {
            return formTruthTable3();
        } else {
            return formTruthTable4();
        }
    }

    //EFFECTS : creates the truth table JTable for numVariables = 1
    private JTable formTruthTable1() {
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = {
                {"0", log.getInput()[0]},
                {"1", log.getInput()[1]}
        };
        dm.setDataVector(data, new Object[]{"A", "Logic"});
        JTable jt = new JTable(dm);
        return jt;
    }

    //EFFECTS : creates the truth table JTable for numVariables = 2
    private JTable formTruthTable2() {
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = {
                {"0", "0", log.getInput()[0]},
                {"0", "1", log.getInput()[1]},
                {"1", "0", log.getInput()[2]},
                {"1", "1", log.getInput()[3]}
        };
        dm.setDataVector(data, new Object[]{"B", "A", "Logic"});
        JTable jt = new JTable(dm);
        return jt;
    }

    //EFFECTS : creates the truth table JTable for numVariables = 3
    private JTable formTruthTable3() {
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = {
                {"0", "0", "0", log.getInput()[0]},
                {"0", "0", "1", log.getInput()[1]},
                {"0", "1", "0", log.getInput()[2]},
                {"0", "1", "1", log.getInput()[3]},
                {"1", "0", "0", log.getInput()[4]},
                {"1", "0", "1", log.getInput()[5]},
                {"1", "1", "0", log.getInput()[6]},
                {"1", "1", "1", log.getInput()[7]}
        };
        dm.setDataVector(data, new Object[]{"C", "A", "B", "Logic"});
        JTable jt = new JTable(dm);
        return jt;
    }

    //EFFECTS : creates the truth table JTable for numVariables = 4
    private JTable formTruthTable4() {
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = {
                {"0", "0", "0", "0", log.getInput()[0]},
                {"0", "0", "0", "1", log.getInput()[1]},
                {"0", "0", "1", "0", log.getInput()[2]},
                {"0", "0", "1", "1", log.getInput()[3]},
                {"0", "1", "0", "0", log.getInput()[4]},
                {"0", "1", "0", "1", log.getInput()[5]},
                {"0", "1", "1", "0", log.getInput()[6]},
                {"0", "1", "1", "1", log.getInput()[7]},
                {"1", "0", "0", "0", log.getInput()[8]},
                {"1", "0", "0", "1", log.getInput()[9]},
                {"1", "0", "1", "0", log.getInput()[10]},
                {"1", "0", "1", "1", log.getInput()[11]},
                {"1", "1", "0", "0", log.getInput()[12]},
                {"1", "1", "0", "1", log.getInput()[13]},
                {"1", "1", "1", "0", log.getInput()[14]},
                {"1", "1", "1", "1", log.getInput()[15]}
        };
        dm.setDataVector(data, new Object[]{"C", "D", "A", "B", "Logic"});
        JTable jt = new JTable(dm);
        return jt;
    }

    //EFFECTS : creates the star button
    private JButton createStarButton() throws IOException {
        Image emptyStarButton = ImageIO.read(new File("data/emptyStar.png"));
        emptyStarButton = emptyStarButton.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        Image starButtonHover = ImageIO.read(new File("data/fullStar.png"));
        starButtonHover = starButtonHover.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        Image starButtonActive = ImageIO.read(new File("data/fullStar.png"));
        starButtonActive = starButtonActive.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        JButton starButton = new JButton(new ImageIcon(emptyStarButton));
        starButtonProperties(starButton, starButtonHover, starButtonActive);
        Image finalStarButtonActive = starButtonActive;
        Image finalEmptyStarButton = emptyStarButton;
        if (importantFrame) {
            starButton.setIcon(new ImageIcon(starButtonActive));
        } else {
            starButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    starButtonHelper(starButton, finalStarButtonActive, finalEmptyStarButton);
                }
            });
        }
        return starButton;
    }

    //EFFECTS : sets the properties of the starButton
    private void starButtonProperties(JButton starButton, Image i1, Image i2) {
        starButton.setRolloverIcon(new ImageIcon(i1));
        starButton.setPressedIcon(new ImageIcon(i2));
        starButton.setBorder(BorderFactory.createEmptyBorder());
        starButton.setContentAreaFilled(false);
        starButton.setFocusable(false);
    }

    //MODIFIES: this
    //EFFECTS : changes the icon of the starButton
    private void starButtonHelper(JButton starButton, Image i1, Image i2) {
        if (!isImportant) {
            starButton.setIcon(new ImageIcon(i1));
            logs.markImportant(log);
            LogiCalFrame.setSavedState(false);
            ListOfLogsFrame.updateLogical();
            this.isImportant = true;
        } else {
            starButton.setIcon(new ImageIcon(i2));
            logs.deleteLog(logs.getImportant().indexOf(log));
            LogiCalFrame.setSavedState(false);
            ListOfLogsFrame.updateLogical();
            this.isImportant = false;
        }
    }
}
