package edu.ustb.ustbtube.entity;

public class Favorites {


    private String id;
    private String userId;
    private String videoId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }


    @Override
    public String toString() {
        return "Favorites{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", videoId='" + videoId + '\'' +
                '}' + '\n';
    }
}
