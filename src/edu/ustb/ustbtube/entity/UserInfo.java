package edu.ustb.ustbtube.entity;

public class UserInfo {


    private String userId;
    private String favoritesId;
    private String historyId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(String favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", favoritesId='" + favoritesId + '\'' +
                ", historyId='" + historyId + '\'' +
                '}' + '\n';
    }
}
