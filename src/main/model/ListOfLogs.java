package model;

import java.util.ArrayList;

//Represents a list of logs
public class ListOfLogs {
    //Fields
    private ArrayList<Log> logs = new ArrayList<Log>();
    private ArrayList<Log> important = new ArrayList<Log>();

    //EFFECTS  : returns the logs
    public ArrayList<Log> getLogs() {
        return logs;
    }

    //EFFECTS  : returns the important logs
    public ArrayList<Log> getImportant() {
        return important;
    }

    //MODIFIES : this
    //EFFECTS  : adds the log at the end of the list
    public void addLog(Log l) {
        logs.add(l);
        EventLog.getInstance().logEvent(new Event("Log with logic " + l.getLogic()
                + " has been added to logs"));
    }

    //MODIFIES : this
    //EFFECTS  : deletes the log at the given index
    public void deleteLog(int index) {
        EventLog.getInstance().logEvent(new Event("Log with logic " + important.get(index).getLogic()
                + " has been removed from important logs"));
        important.remove(index);
    }

    //REQUIRES : log in logs
    //MODIFIES : this
    //EFFECTS  : marks the given log as an important log
    public void markImportant(Log log) {
        important.add(log);
        EventLog.getInstance().logEvent(new Event("Log with logic " + log.getLogic()
                + " has been added to important logs"));
    }

    //EFFECTS  : returns the list of logs and important logs as a string
    public String toStringLogs() {
        String s = "";
        for (Log i : logs) {
            s += i.toString() + "\n";
        }
        return s;
    }

    public String toStringImportantLogs() {
        String s = "";
        for (Log i : important) {
            s += i.toString() + "\n";
        }
        return s;
    }
}
