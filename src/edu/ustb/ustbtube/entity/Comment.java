package edu.ustb.ustbtube.entity;

public class Comment {


    private String id;
    private String videoId;
    private String userId;
    private String text;
    private String createTime;
    private String userName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", videoId='" + videoId + '\'' +
                ", userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
