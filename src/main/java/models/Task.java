package models;

import constants.TaskTypeEnum;
import helpers.DateTimeHelper;

public abstract class Task extends Thread {

    private final String taskID;
    private final TaskTypeEnum taskType;
    private final String startDateTime;

    public Task(String taskID, TaskTypeEnum taskType) {
        this.taskID = taskID;
        this.taskType = taskType;
        this.startDateTime = DateTimeHelper.getCurrentLocalDateTimeString();
    }

    /**
     * function to get ID of a task
     * @return ID of a task as String
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * function to get task type
     * @return task type
     */
    public TaskTypeEnum getTaskType() {
        return taskType;
    }

    /**
     * function to get task start date and time as String
     * @return task start date and time as String
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    public abstract String toString();
}
