package models;

public class YouTubeVideo {

    private final String videoName;
    private final String videoURL;

    public YouTubeVideo(String videoName, String videoURL) {
        this.videoName = videoName;
        this.videoURL = videoURL;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
