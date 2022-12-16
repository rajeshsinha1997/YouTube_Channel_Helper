package helpers;

import constants.BrowserConstants;

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
            e.printStackTrace();
            return displayTaskTypesAndGetValidatedUserInput();
        }
    }

    /**
     * function to get user's choice of browser name
     * @return user's choice of browser name as string
     */
    public static BrowserConstants getBrowserChoiceInput() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.println("- SELECT BROWSER:               -");
        System.out.println("---------------------------------");
        System.out.println("- 1. GOOGLE CHROME              -");
        System.out.println("- 2. MICROSOFT EDGE             -");
        System.out.println("- 3. MOZILLA FIREFOX            -");
        System.out.println("---------------------------------");
        System.out.print("- ENTER YOUR CHOICE (1/2/3): ");
        try {
            String browserName = inputReader.readLine().trim();
            switch (browserName) {
                case "1" -> {
                    return BrowserConstants.Google_Chrome;
                }
                case "2" -> {
                    return BrowserConstants.Microsoft_Edge;
                }
                case "3" -> {
                    return BrowserConstants.Mozilla_Firefox;
                }
                default -> {
                    waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                    return getBrowserChoiceInput();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return getBrowserChoiceInput();
        }
    }

    /**
     * function to get validated YouTube video id from user
     * @return validated YouTube video id
     */
    public static String getValidatedYouTubeVideoIdFromUser() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.print("ENTER YOUTUBE VIDEO ID: ");
        try {
            String id = inputReader.readLine().trim();
            if (id.isBlank()) {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return getValidatedYouTubeVideoIdFromUser();
            }
            return id;
        }
        catch (IOException e) {
            return getValidatedYouTubeVideoIdFromUser();
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
            if (url.isBlank() || !url.startsWith("https://")) {
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
     * function to check if headless mode is required from user
     * @return headless mode is required or not
     */
    public static boolean getIfHeadlessModeRequiredInput() {
        clearConsoleWindow();
        System.out.println("---------------------------------");
        System.out.print("HEADLESS MODE REQUIRED? (Y/N): ");
        try {
            char choice = inputReader.readLine().trim().toLowerCase().charAt(0);
            if (choice == 'y') return true;
            else if (choice == 'n') return false;
            else {
                waitForUserToContinue("INVALID INPUT!!! PRESS ANY KEY TO CONTINUE!");
                return getIfHeadlessModeRequiredInput();
            }
        }
        catch (IOException e) {
            waitForUserToContinue("SOME ERROR OCCURRED! PRESS ANY KEY TO CONTINUE!");
            return getIfHeadlessModeRequiredInput();
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
