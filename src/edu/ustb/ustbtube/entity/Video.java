package edu.ustb.ustbtube.entity;

public class Video {


    private String videoId;
    private String createTime;
    private String userId;
    private int readTimes;
    private int star;
    private int favorites;
    private int comment;
    private String path;
    private String videoTitle;
    private String img;
    private String tag;
    private String introduction;

    private String userName;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userId='" + userId + '\'' +
                ", readTimes=" + readTimes +
                ", star=" + star +
                ", favorites=" + favorites +
                ", comment=" + comment +
                ", path='" + path + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", img='" + img + '\'' +
                ", tag='" + tag + '\'' +
                ", introduction='" + introduction + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
