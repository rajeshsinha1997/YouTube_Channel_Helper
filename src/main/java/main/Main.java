package main;

import helpers.TaskManagerHelper;
import helpers.UserInputHelper;

public class Main {

    /**
     * function to initialize application
     */
    private static void initializeApplication() {
        UserInputHelper.initializeInputReader();
        TaskManagerHelper.initializeTaskManagerHelper();
    }

    /**
     * function to run the application and manage
     */
    private static void runApplicationAndManage() {
        char choice = UserInputHelper.displayInitialOptionsAndGetValidatedUserInput();
        switch (choice) {
            case '1':
                TaskManagerHelper.startNewTask();
                runApplicationAndManage();
                break;
            case '2':
                TaskManagerHelper.printListOfRunningTasks();
                runApplicationAndManage();
                break;
            case '3':
                TaskManagerHelper.killRunningTask();
                runApplicationAndManage();
                break;
            case '0': break;
        }
    }

    /**
     * starting point of the application
     * @param args: command line arguments
     */
    public static void main(String[] args) {
        initializeApplication();
        runApplicationAndManage();
    }
}
