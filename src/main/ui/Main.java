package ui;

import java.io.FileNotFoundException;

//Represents the main class
public class Main {
    //EFFECTS : Creates a new LogiCal object and starts the application
    public static void main(String[] args) {
        try {
            new LogiCalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}