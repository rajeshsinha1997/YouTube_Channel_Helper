package models;

public class YouTubeVideo {

    private final String videoId;
    private final String videoURL;

    public YouTubeVideo(String videoId, String videoURL) {
        this.videoId = videoId;
        this.videoURL = videoURL;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
