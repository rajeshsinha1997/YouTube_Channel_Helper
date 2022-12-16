package models;

import constants.BrowserConstants;
import constants.TaskTypeEnum;
import helpers.DateTimeHelper;
import helpers.SeleniumHelper;
import helpers.UserInputHelper;

public class GenerateViewTask extends Task {
    private final YouTubeVideo youtubeVideo;
    private int generatedViewCount;
    private final boolean headlessRequired;

    public GenerateViewTask(String taskID, YouTubeVideo video, boolean isHeadlessRequired) {
        super(taskID, TaskTypeEnum.GENERATE_VIEW_TASK);
        this.youtubeVideo = video;
        this.headlessRequired = isHeadlessRequired;
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
               "\n--> YouTube Video ID: "+this.youtubeVideo.getVideoId()+
               "\n--> YouTube Video URL: "+this.youtubeVideo.getVideoURL()+
               "\n--> Generated Views: "+this.getGeneratedViewCount();
    }

    /**
     * function to start the task
     */
    @Override
    public void start() {
        BrowserConstants browser = UserInputHelper.getBrowserChoiceInput();
        while (this.getGeneratedViewCount() < Integer.MAX_VALUE) {
            System.out.println("---------------------------------");
            System.out.println("STARTING VIEW GENERATION: "+ DateTimeHelper.getCurrentLocalDateTimeString());
            this.setGeneratedViewCount(this.getGeneratedViewCount()+
                    SeleniumHelper.getView(this.youtubeVideo.getVideoURL(),
                            browser, this.headlessRequired, youtubeVideo.getVideoId()));
            System.out.println("GENERATED VIEW: "+this.getGeneratedViewCount());
        }
    }
}
