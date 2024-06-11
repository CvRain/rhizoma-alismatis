package com.example.rhizoma_alismatis.utils;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class NetworkManager {
    private volatile static NetworkManager instance;
    private OkHttpClient client;

    private NetworkManager() {
        client = new OkHttpClient();
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

    public <T> T sendRequest(String url, Class<T> responseClass, Object requestBody) {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), new Gson().toJson(requestBody));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new Gson().fromJson(response.body().charStream(), responseClass);
            } else {
                // 处理请求失败情况
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}