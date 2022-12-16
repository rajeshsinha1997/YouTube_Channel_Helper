package models;

import constants.TaskTypeEnum;
import helpers.SeleniumHelper;

public class GenerateViewTask extends Task {
    private final YouTubeVideo youtubeVideo;
    private int generatedViewCount;

    public GenerateViewTask(String taskID, YouTubeVideo video) {
        super(taskID, TaskTypeEnum.GENERATE_VIEW_TASK);
        this.youtubeVideo = video;
        this.generatedViewCount = 0;
    }

    /**
     * function to set value to generated view count
     * @param generatedViewCount: updated value
     */
    public void setGeneratedViewCount(int generatedViewCount) {
        this.generatedViewCount = generatedViewCount;
    }

    /**
     * function to get generated view count
     * @return generated view count
     */
    public int getGeneratedViewCount() {
        return generatedViewCount;
    }

    /**
     * function to print details of a generate view task
     * @return details of generate view task as String
     */
    @Override
    public String toString() {
        return "\n----------------------------------------------------------------"+
               "\n--> ID: "+this.getTaskID()+
               "\n--> Type: "+this.getTaskType()+
               "\n--> Started At: "+this.getStartDateTime()+
               "\n--> YouTube Video Name: "+this.youtubeVideo.getVideoName()+
               "\n--> YouTube Video URL: "+this.youtubeVideo.getVideoURL()+
               "\n--> Generated Views: "+this.getGeneratedViewCount();
    }

    /**
     * function to start the task
     */
    @Override
    public void start() {
        while (this.getGeneratedViewCount() < Integer.MAX_VALUE) {
            SeleniumHelper.initializeSeleniumHelper(60L, false);
            SeleniumHelper.getView(this.youtubeVideo.getVideoURL());
            this.setGeneratedViewCount(this.getGeneratedViewCount()+1);
        }
    }
}
