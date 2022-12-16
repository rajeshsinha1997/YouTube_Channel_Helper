package helpers;

import models.GenerateViewTask;
import models.Task;
import models.YouTubeVideo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaskManagerHelper {

    private static Map<String, Task> taskServicesMap;

    /**
     * function to initialize task manager helper
     */
    public static void initializeTaskManagerHelper() {
        if (taskServicesMap !=null && !taskServicesMap.isEmpty()) {
            taskServicesMap.values().forEach(Thread::interrupt);
        }
        taskServicesMap = new HashMap<>();
    }

    /**
     * function to generate unique task ID
     * @return unique task ID
     */
    private static String generateRandomTaskID() {
        String taskID = String.valueOf(UUID.randomUUID());
        if (taskServicesMap.containsKey(taskID)) {
            return generateRandomTaskID();
        }
        else return taskID;
    }

    /**
     * function to start a new task
     */
    public static void startNewTask() {
        char taskType = UserInputHelper.displayTaskTypesAndGetValidatedUserInput();
        switch (taskType) {
            case '1':
                String taskID = generateRandomTaskID();
                Task generateViewTask = new GenerateViewTask(
                        taskID,
                        new YouTubeVideo(
                                UserInputHelper.getValidatedYouTubeVideoIdFromUser(),
                                UserInputHelper.getValidatedYouTubeVideoURLFromUser()
                        ),
                        UserInputHelper.getIfHeadlessModeRequiredInput()
                );
                taskServicesMap.put(taskID, generateViewTask);
                generateViewTask.start();
                break;
            case '0':
                break;
        }
    }

    /**
     * function to print list of running tasks
     */
    public static void printListOfRunningTasks() {
        UserInputHelper.clearConsoleWindow();
        if (taskServicesMap ==null || taskServicesMap.isEmpty()) {
            UserInputHelper.waitForUserToContinue("NO RUNNING TASK AVAILABLE AT THIS MOMENT! PRESS ANY KEY TO CONTINUE");
        }
        else {
            for (Task task : taskServicesMap.values()) {
                System.out.print(task.toString() + "\n");
            }
            UserInputHelper.waitForUserToContinue("PRESS ANY KEY TO CONTINUE!");
        }
    }

    /**
     * function to kill a running task
     */
    public static void killRunningTask() {
        UserInputHelper.clearConsoleWindow();
        if (taskServicesMap ==null) {
            UserInputHelper.waitForUserToContinue("NO RUNNING TASK AVAILABLE AT THIS MOMENT! PRESS ANY KEY TO CONTINUE");
        }
        else {
            String taskID = UserInputHelper.getTaskIDFromUser();
            if (taskServicesMap.containsKey(taskID)) {
                taskServicesMap.get(taskID).interrupt();
                taskServicesMap.remove(taskID);
                System.out.println("TASK KILLED WITH ID: "+taskID);
            }
            else {
                UserInputHelper.waitForUserToContinue("NO TASK FOUND WITH ID: "+taskID+"! PRESS ANY KEY TO CONTINUE!");
            }
        }
    }
}
