package com.example.rhizoma_alismatis.models;

public class UserInfo {
    public String UserId;
    public String UserName;
    public String UserEmail;
    public String UserToken;
    public String UserIcon;

    public UserInfo(String userId, String userName, String userEmail, String userToken, String userIcon){
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        UserToken = userToken;
        UserIcon = userIcon;
    }

    public UserInfo(){
        UserId = "";
        UserName = "";
        UserEmail = "";
        UserToken = "";
        UserIcon = "";
    }
}
