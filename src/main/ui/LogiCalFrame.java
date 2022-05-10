package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//REPRESENTS THE LOGICAL FRAME
public class LogiCalFrame extends JFrame {
    //Fields
    private static LogiCal logiCal;
    private static ListOfLogs logs = new ListOfLogs();
    private static final String JSON_STORE = "./data/logical.json";
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private static boolean isSaved = true;

    //MODIFIES: this
    //EFFECTS : Creates the main frame with all the buttons
    public LogiCalFrame() {
        super("LogiCal");
        logiCal = new LogiCal("Krithik's Workspace",  logs.toStringLogs(), logs.toStringImportantLogs());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);
        add(createPanel());
        pack();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS : Changes the state of the application from saved to not saved and vice-versa
    public static void setSavedState(boolean state) {
        isSaved = state;
    }

    //REQUIRES: log != null
    //MODIFIES: this
    //EFFECTS : Adds the log to logs
    public static void updateLogs(Log log) {
        logs.addLog(log);
    }

    //REQUIRES: log != null
    //MODIFIES: this
    //EFFECTS : Adds the important log to logs
    public static void updateImpLogs(Log log) {
        logs.markImportant(log);
    }

    //REQUIRES: 1 <= numVariables <=4 && input.length in {2, 4, 8, 16}
    //MODIFIES: this
    //EFFECTS : sets logical according to the parameters
    public static void setLogiCal(int numVariables, int[] input, String ans) {
        logiCal.addTruthTable(new TruthTableWithLogic(numVariables, input, ans));
        logiCal.updateLogs(logs.toStringLogs());
        logiCal.updateImpLogs(logs.toStringImportantLogs());
    }

    //REQUIRES: jf != null
    //MODIFIES: this
    //EFFECTS : saves the state of the app
    public static void save(JFrame jf) {
        try {
            if (!isSaved) {
                jsonWriter.open();
                jsonWriter.write(logiCal);
                jsonWriter.close();
                JOptionPane.showMessageDialog(jf, "Saved " + logiCal.getName() + " to " + JSON_STORE);
                isSaved = true;
            } else {
                JOptionPane.showMessageDialog(jf, "Nothing to save");
            }
        } catch (FileNotFoundException f) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS : create the panel for the frame
    private JPanel createPanel() {
        JPanel jp = new JPanel();
        JTextArea jt = new JTextArea();
        jt.setText("LogiCal");
        jt.setEditable(false);
        jt.setVisible(true);
        jt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jp.add(jt, BorderLayout.CENTER);
        jp.add(addTruthTableButton(this));
        jp.add(saveButton(this));
        jp.add(loadButton(this));
        jp.add(listOfLogsButton(this));
        jp.add(listOfImportantLogsButton(this));
        jp.add(quitButton(this));
        jp.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        jp.setLayout(new GridLayout(0,1));
        return jp;
    }

    //REQUIRES: jf != null
    //EFFECTS : creates an addTruthTable Button
    private JButton addTruthTableButton(JFrame jf) {
        JButton truthTable = new JButton("Add a truth table");
        truthTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askToLoad(jf);
                new TruthTableFrame();
            }
        });
        return truthTable;
    }

    //REQUIRES: jf != null
    //EFFECTS : creates a save Button
    private JButton saveButton(JFrame jf) {
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askToLoad(jf);
                save(jf);
            }
        });
        return save;
    }

    //REQUIRES: jf != null
    //EFFECTS : creates a load Button
    private JButton loadButton(JFrame jf) {
        JButton load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(jf);
            }
        });
        return load;
    }

    //REQUIRES: jf != null
    //EFFECTS : creates a ListOfLogs Button
    private JButton listOfLogsButton(JFrame jf) {
        JButton lol = new JButton("Logs");
        lol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askToLoad(jf);
                if (logs.getLogs().size() == 0) {
                    JOptionPane.showMessageDialog(jf, "No logs");
                } else {
                    new ListOfLogsFrame(logs, 0);
                    logiCal = ListOfLogsFrame.updateLogical();
                }
            }
        });
        return lol;
    }

    //REQUIRES: jf != null
    //EFFECTS : creates a ListOfImportantLogs Button
    private JButton listOfImportantLogsButton(JFrame jf) {
        JButton loil = new JButton("Important Logs");
        loil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askToLoad(jf);
                if (logs.getImportant().size() == 0) {
                    JOptionPane.showMessageDialog(jf, "No important logs");
                } else {
                    new ListOfImportantLogsFrame(logs, 0);
                    logiCal = ListOfImportantLogsFrame.updateLogical();
                }
            }
        });
        return loil;
    }

    //REQUIRES: jf != null
    //EFFECTS : creates a quit Button
    private JButton quitButton(JFrame jf) {
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askToSave();
                jf.dispose();
                EventLog el = EventLog.getInstance();
                for (Event event : el) {
                    System.out.println(event.toString());
                }
            }
        });
        return quit;
    }

    //EFFECTS : asks the user to save the app if not saved
    private void askToSave() {
        if (!isSaved) {
            int ans = JOptionPane.showConfirmDialog(this, "Latest changes are not saved. "
                            + "Do you want to save the changes?",
                    "Save", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                save(this);
            }
        }
    }

    //REQUIRES: jf != null
    //MODIFIES: this
    //EFFECTS : loads the app
    private void load(JFrame jf) {
        try {
            if (!jsonReader.checkEmpty()) {
                logiCal = jsonReader.read();
                constructLogs(logiCal);
                JOptionPane.showMessageDialog(jf, "Loaded " + logiCal.getName() + " from " + JSON_STORE);
            } else {
                JOptionPane.showMessageDialog(jf, "Nothing to load");
            }
        } catch (IOException f) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //REQUIRES: jf != null
    //EFFECTS : asks the user to load the app if not loaded
    private void askToLoad(JFrame jf) {
        try {
            if (logs.getLogs().size() == 0 && !jsonReader.checkEmpty()) {
                int ans = JOptionPane.showConfirmDialog(this, "Do you want to load the previous state of application",
                        "Load", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    load(jf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS : constructs logs according to logical
    private void constructLogs(LogiCal logiCal) {
        int size = logiCal.getImplolString().length();
        String[] impLolString = logiCal.getImplolString().substring(0, size - 2).split("\n\n");
        for (TruthTableWithLogic i : logiCal.getTruthTables()) {
            logs.addLog(new Log(i.getNumVariables(), i.getTruthtableinputs(), i.getLogic()));
        }
        for (Log i : logs.getLogs()) {
            for (String j : impLolString) {
                String[] k = j.split("\n");
                String logic = k[2].substring(7);
                if (i.getLogic().equals(logic)) {
                    logs.markImportant(i);
                }
            }
        }
    }

    //EFFECTS : Runs the logical
    public static void main(String[] args) {
        new LogiCalFrame();
    }
}
