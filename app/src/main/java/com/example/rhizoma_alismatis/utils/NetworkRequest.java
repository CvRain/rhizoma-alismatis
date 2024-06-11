package com.example.rhizoma_alismatis.utils;

import android.os.AsyncTask;

import com.example.rhizoma_alismatis.models.WeatherForecast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetworkRequest extends AsyncTask<String, Void, List<WeatherForecast>> {

    private OnRequestCompletedListener listener;

    public NetworkRequest(OnRequestCompletedListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<WeatherForecast> doInBackground(String... params) {
        String urlString = params[0];
        List<WeatherForecast> forecasts = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String date = jsonObject.getString("date");
                    int temperatureC = jsonObject.getInt("temperatureC");
                    int temperatureF = jsonObject.getInt("temperatureF");
                    String summary = jsonObject.getString("summary");

                    WeatherForecast forecast = new WeatherForecast(date, temperatureC, temperatureF, summary);
                    forecasts.add(forecast);
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return forecasts;
    }

    @Override
    protected void onPostExecute(List<WeatherForecast> forecasts) {
        if (listener != null) {
            listener.onRequestCompleted(forecasts);
        }
    }

    public interface OnRequestCompletedListener {
        void onRequestCompleted(List<WeatherForecast> forecasts);
    }
}
