package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Represents the LogiCal ui class
public class LogiCalApp {
    //Fields
    private Scanner sc = new Scanner(System.in);
    private ListOfLogs logs = new ListOfLogs();
    boolean running = true;
    private static final String JSON_STORE = "./data/logical.json";
    private static int[] truthTable;
    private LogiCal logiCal;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean isSaved = true;

    //MODIFIES : this
    //EFFECTS  : Constructs the LogiCal app
    public LogiCalApp() throws FileNotFoundException {
        logiCal = new LogiCal("Krithik's Workspace",  logs.toStringLogs(), logs.toStringImportantLogs());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        printStartingScreen();
        try {
            if (!jsonReader.checkEmpty()) {
                System.out.print("Type y if you want to load the previous workspace: ");
                String ans = sc.next();
                if (ans.equals("y")) {
                    loadLogiCal();
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        runLogiCalApp();
    }

    //MODIFIES : this
    //EFFECTS  : Runs the LogiCal app
    private void runLogiCalApp() {
        while (running) {
            System.out.print("Please type the number of variables in the truth table: ");
            int numVariables = sc.nextInt();
            int[] input = inputTruthTable(numVariables);
            KarnaughMap kmap = new KarnaughMap(numVariables, input);
            System.out.println();
            String ans = kmap.formLogic();
            System.out.println("The logic for the truth table is: " + ans);
            logs.addLog(new Log(numVariables, truthTable, ans));
            logiCal.addTruthTable(new TruthTableWithLogic(numVariables, truthTable, ans));
            logiCal.updateLogs(logs.toStringLogs());
            logiCal.updateImpLogs(logs.toStringImportantLogs());
            isSaved = false;
            afterCalculation(new Log(numVariables, truthTable, ans));
        }
        System.out.println("THANK YOU FOR USING THE APPLICATION!!!");
        System.out.print("SEE YOU AGAIN");
    }

    //EFFECTS  : prints the starting screen
    private static void printStartingScreen() {
        System.out.println("                               WELCOME TO LogiCal");
        System.out.println();
        System.out.println("At LogiCal, you can input a truth table and I can create a logical expression for you");
        System.out.println("     For now I can only create logical expressions with a maximum of 4 variables     ");
        System.out.println();
        System.out.println();
    }

    //REQUIRES : numvariables <= 4 && numvariables != 0
    //EFFECTS  : inputs the truth table from the user
    private static int[] inputTruthTable(int numVariables) {
        Scanner sc = new Scanner(System.in);
        int[] input;
        if (numVariables == 1) {
            input = inputTruthTable1(sc);
        } else if (numVariables == 2) {
            input = inputTruthTable2(sc);
        } else if (numVariables == 3) {
            input = inputTruthTable3(sc);
        } else {
            input = inputTruthTable4(sc);
        }
        return input;
    }

    //EFFECTS  : inputs the truth table when numvariables == 1
    private static int[] inputTruthTable1(Scanner sc) {
        truthTable = new int[2];
        int[] input = new int[2];
        System.out.println("A  |  Logic");
        System.out.print("0  |  ");
        input[0] = sc.nextInt();
        System.out.print("");
        System.out.print("1  |  ");
        input[1] = sc.nextInt();
        truthTable = input;
        return input;
    }

    //EFFECTS  : inputs the truth table when numvariables == 2
    private static int[] inputTruthTable2(Scanner sc) {
        truthTable = new int[4];
        int[] input = new int[4];
        System.out.println("B  |  A  |  Logic");
        System.out.print("0  |  0  |  ");
        input[0] = sc.nextInt();
        System.out.print("");
        System.out.print("0  |  1  |  ");
        input[1] = sc.nextInt();
        System.out.print("");
        System.out.print("1  |  0  |  ");
        input[2] = sc.nextInt();
        System.out.print("");
        System.out.print("1  |  1  |  ");
        input[3] = sc.nextInt();
        truthTable = input;
        return input;
    }

    //EFFECTS  : inputs the truth table when numvariables == 3
    private static int[] inputTruthTable3(Scanner sc) {
        truthTable = new int[8];
        int[] input = new int[8];
        int x = 0;
        System.out.println("C  |  A  |  B  |  Logic");
        for (int i = 0; i < 2; i++) {
            System.out.print(i + "  |  0  |  0  |  ");
            input[x] = sc.nextInt();
            truthTable[x] = input[x];
            System.out.print("");
            System.out.print(i + "  |  0  |  1  |  ");
            input[x + 1] = sc.nextInt();
            truthTable[x + 1] = input[x + 1];
            System.out.print("");
            System.out.print(i + "  |  1  |  0  |  ");
            input[x + 3] = sc.nextInt();
            truthTable[x + 2] = input[x + 3];
            System.out.print("");
            System.out.print(i + "  |  1  |  1  |  ");
            input[x + 2] = sc.nextInt();
            truthTable[x + 3] = input[x + 2];
            x += 4;
        }
        return input;
    }

    //EFFECTS  : inputs the truth table when numvariables == 4
    private static int[] inputTruthTable4(Scanner sc) {
        truthTable = new int[16];
        int[] input = new int[16];
        int x = 0;
        System.out.println("C  |  D  |  A  |  B  |  Logic");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == 1) {
                    if (j == 0) {
                        x = 12;
                        input = inputTruthTable4Help(input, x, sc, i, j);
                    } else {
                        x = 8;
                        input = inputTruthTable4Help(input, x, sc, i, j);
                    }
                } else {
                    input = inputTruthTable4Help(input, x, sc, i, j);
                    x += 4;
                }
            }
        }
        truthTable = input;
        return input;
    }

    //EFFECTS  : Helper for inputting truth table when numvariable == 4
    private static int[] inputTruthTable4Help(int[] input, int x, Scanner sc, int i, int j) {
        System.out.print(i + "  |  " + j + "  |  0  |  0  |  ");
        input[x] = sc.nextInt();
        System.out.print("");
        System.out.print(i + "  |  " + j + "  |  0  |  1  |  ");
        input[x + 1] = sc.nextInt();
        System.out.print("");
        System.out.print(i + "  |  " + j + "  |  1  |  0  |  ");
        input[x + 3] = sc.nextInt();
        System.out.print("");
        System.out.print(i + "  |  " + j + "  |  1  |  1  |  ");
        input[x + 2] = sc.nextInt();
        return input;
    }

    //MODIFIES : this
    //EFFECTS  : prints the input screen and stores the user input
    private void inputScreen(Log log) {
        printOptions();
        String ans = sc.next();
        if (ans.equals("1")) {
            System.out.println("The list of logs are:-\n" + logiCal.getLogsString());
        } else if (ans.equals("2")) {
            System.out.println("The list of Important Logs are:-\n" + logiCal.getImplolString());
        } else if (ans.equals("3")) {
            logs.markImportant(log);
            logiCal.updateImpLogs(logs.toStringImportantLogs());
        } else if (ans.equals("4")) {
            System.out.print("Please enter the index of the log you want to delete: ");
            int index = sc.nextInt();
            logs.deleteLog(index);
            System.out.println(logs);
        } else if (ans.equals("5")) {
            saveLogiCal();
        } else if (ans.toLowerCase().equals("q")) {
            askToSave();
            running = false;
        } else {
            System.out.println("Selection INVALID");
            inputScreen(log);
        }
    }

    //EFFECTS  : Asks the user to save the file if not saved
    private void askToSave() {
        //logiCal.toJson().getJSONArray("truthtables").length() - 1 != logs.getLogs().size() + numPreviousLogs
        if (!isSaved) {
            System.out.println("Latest changes are not saved");
            System.out.print("Type y if you want to save them?: ");
            if (sc.next().toLowerCase().equals("y")) {
                saveLogiCal();
            }
        }
    }

    //EFFECTS  : prints the options to the user
    private void printOptions() {
        System.out.println("Input the following for the following functions:- ");
        System.out.println("Input 1 for printing the logs");
        System.out.println("Input 2 for printing the important logs");
        System.out.println("Input 3 for adding the information of the logical expression to important logs ");
        System.out.println("Input 4 for deleting a log from the important logs by the index");
        System.out.println("Input 5 for saving work space to a file");
        System.out.println("Input q for quitting");
        System.out.print("Input : ");
    }

    //MODIFIES : this
    //EFFECTS  : prints the screen after the logic is calculated
    private void afterCalculation(Log l) {
        boolean more = true;
        while (more) {
            System.out.println();
            System.out.println("For calculating another logical expression for another truth table type y");
            System.out.println("To quit type q");
            System.out.println("Else type s");
            String answer = sc.next();
            if (answer.toLowerCase().equals("y")) {
                break;
            } else if (answer.toLowerCase().equals("q")) {
                askToSave();
                running = false;
                break;
            }
            inputScreen(l);
            if (!running) {
                more = false;
            }
        }
    }

    // EFFECTS: saves the data to file
    private void saveLogiCal() {
        try {
            jsonWriter.open();
            jsonWriter.write(logiCal);
            jsonWriter.close();
            System.out.println("Saved " + logiCal.getName() + " to " + JSON_STORE);
            isSaved = true;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads data from file
    private void loadLogiCal() {
        try {
            logiCal = jsonReader.read();
            System.out.println("Loaded " + logiCal.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}