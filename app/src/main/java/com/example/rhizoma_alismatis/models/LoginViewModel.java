package com.example.rhizoma_alismatis.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel {
    private Boolean isLoggedIn;
    private String loginEmail;
    private static LoginViewModel instance;

    public static LoginViewModel getInstance() {
        System.out.println("LoginViewModel::getInstance");
        if(instance == null){
            instance = new LoginViewModel();
        }
        return instance;
    }

    private LoginViewModel() {
        isLoggedIn = Boolean.FALSE;
        loginEmail = "";
    }

    public void setIsLoggedIn(boolean value, String email) {
        isLoggedIn = value;
        loginEmail = email;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

}

