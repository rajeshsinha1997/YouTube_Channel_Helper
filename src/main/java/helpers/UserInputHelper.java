package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputHelper {

    private static BufferedReader inputReader;

    /**
     * function to initialize input reader
     */
    public static void initializeInputReader() {
        if (inputReader == null) {
            inputReader = new BufferedReader(new InputStreamReader(System.in));
        }
    }

    /**
     * function to clear the console window screen
     */
    static void clearConsoleWindow() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec(new String[]{"clear"});
        } catch (IOException | InterruptedException ignored) {}
    }

    /**
     * function to get user input for initial options
     * @return initial input option received from user
     */
    public static char displayInitialOptionsAndGetValidatedUserInput() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.println("- PLEASE SELECT AN OPTION:      -");
        System.out.println("---------------------------------");
        System.out.println("- 1. START TASK                 -");
        System.out.println("- 2. SHOW RUNNING TASKS         -");
        System.out.println("- 3. KILL RUNNING TASK          -");
        System.out.println("- 0. EXIT                       -");
        System.out.println("---------------------------------");
        System.out.print("ENTER YOUR CHOICE: ");
        try {
            char choice = inputReader.readLine().trim().charAt(0);
            if (choice < 48 || choice > 51) {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return displayInitialOptionsAndGetValidatedUserInput();
            }
            else return choice;
        }
        catch (IOException e) {
            return displayInitialOptionsAndGetValidatedUserInput();
        }
    }

    /**
     * function to get task type input
     * @return task type input received from user
     */
    public static char displayTaskTypesAndGetValidatedUserInput() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.println("- PLEASE SELECT A TASK:         -");
        System.out.println("---------------------------------");
        System.out.println("- 1. GENERATE VIEWS             -");
        System.out.println("- 0. BACK TO MAIN MENU          -");
        System.out.println("---------------------------------");
        System.out.print("ENTER YOUR CHOICE: ");
        try {
            char choice = inputReader.readLine().trim().charAt(0);
            if (choice < 48 || choice > 49) {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return displayTaskTypesAndGetValidatedUserInput();
            }
            else return choice;
        }
        catch (IOException e) {
            return displayTaskTypesAndGetValidatedUserInput();
        }
    }

    /**
     * function to get validated YouTube video name from user
     * @return validated YouTube video name
     */
    public static String getValidatedYouTubeVideoNameFromUser() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.print("ENTER YOUTUBE VIDEO NAME: ");
        try {
            String name = inputReader.readLine().trim();
            if (name.isBlank()) {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return getValidatedYouTubeVideoNameFromUser();
            }
            return name;
        }
        catch (IOException e) {
            return getValidatedYouTubeVideoNameFromUser();
        }
    }

    /**
     * function to get validated YouTube video URL from user
     * @return validated YouTube video URL
     */
    public static String getValidatedYouTubeVideoURLFromUser() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.print("ENTER YOUTUBE VIDEO URL: ");
        try {
            String url = inputReader.readLine().trim();
            if (url.isBlank() || !url.startsWith("https://www.youtube.com/watch?v=")) {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return getValidatedYouTubeVideoURLFromUser();
            }
            return url;
        }
        catch (IOException e) {
            return getValidatedYouTubeVideoURLFromUser();
        }
    }

    /**
     * function to get task id from user
     * @return task id received from user
     */
    public static String getTaskIDFromUser() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.print("ENTER TASK ID: ");
        try {
            String id = inputReader.readLine().trim();
            if (id.isBlank()) {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return getTaskIDFromUser();
            }
            return id;
        }
        catch (IOException e) {
            return getTaskIDFromUser();
        }
    }

    /**
     * function to wait for one user interaction to continue
     * @param message: message to pass to user
     */
    static void waitForUserToContinue(String message) {
        try {
            System.out.println(message);
            inputReader.readLine();
        }
        catch (IOException e) {
            waitForUserToContinue(message);
        }
    }
}
