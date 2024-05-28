package com.example.rhizoma_alismatis;

public class NetworkManager {
    private volatile static NetworkManager instance;

    private NetworkManager() {

    }

    public static NetworkManager getInstance(){
        if(instance == null){
            synchronized (NetworkManager.class){
                if(instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }
}