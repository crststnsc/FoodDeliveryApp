package DataLayer;


import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AppDataSource extends Application {
    private AppDatabase database;
    private Thread networkThread;

    @Override
    public void onCreate(){
        super.onCreate();
        database = Room.databaseBuilder(this, AppDatabase.class, "FoodDeliveryAppDatabase").build();
        networkThread = new Thread(this::fetchDataFromServer);
        networkThread.start();
    }

    private void fetchDataFromServer() {
        try{
            URL url = new URL("http://10.0.2.2:5000");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            Log.e("RESPONSE CODE", String.valueOf(responseCode));

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String responseData = response.toString();
                // Handle the response data as needed (parse JSON, process data, etc.)

                processServerResponse(responseData);
            } else {
                // Handle unsuccessful response (e.g., error handling, logging)
                Log.e("ERROR","HTTP GET request failed with response code: " + responseCode);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void processServerResponse(String responseData) {
        // Implement your logic to process the server response data
        Log.e("INFO","Server Response: " + responseData);
        // Parse the JSON data, update your local database, etc.
        try{
            JSONObject jsonObject = new JSONObject(responseData);

            JSONArray users = jsonObject.getJSONArray("users");
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        networkThread.interrupt();
    }
}
