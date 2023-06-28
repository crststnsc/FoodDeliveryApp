package DataLayer;


import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import DataLayer.Models.MenuItem;
import DataLayer.Models.Order;
import DataLayer.Models.OrderItem;
import DataLayer.Models.Restaurant;
import DataLayer.Models.User;
import DataLayer.Repositories.BaseRepository;

public class AppDataSource extends Application {

    public static AppDatabase database;
    private Thread networkThread;

    @Override
    public void onCreate(){
        super.onCreate();
        database = Room.databaseBuilder(this, AppDatabase.class, "FoodDeliveryAppDatabase").allowMainThreadQueries().build();
        networkThread = new Thread(this::fetchDataFromServer);
        networkThread.start();
    }

    private void fetchDataFromServer(){
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
                processServerResponse(responseData);
            }
            else{
                Log.e("ERROR","HTTP GET request failed with response code: " + responseCode);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void processServerResponse(String responseData){
        try{
            JSONObject jsonObject = new JSONObject(responseData);

            JSONArray users = jsonObject.getJSONArray("users");
            fillTable(users, database.userRepository(), User[].class);

            JSONArray restaurants = jsonObject.getJSONArray("restaurants");
            fillTable(restaurants, database.restaurantRepository(), Restaurant[].class);

            JSONArray menuItems = jsonObject.getJSONArray("menuItems");
            fillTable(menuItems, database.menuItemRepository(), MenuItem[].class);

            JSONArray orders = jsonObject.getJSONArray("orders");
            fillTable(orders, database.orderRepository(), Order[].class);

            JSONArray orderItems = jsonObject.getJSONArray("orderItems");
            fillTable(orderItems, database.orderItemRepository(), OrderItem[].class);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private <T> void fillTable(JSONArray data, BaseRepository<T> repository, Type listType) {
        Gson gson = new Gson();

        T[] itemList = gson.fromJson(data.toString(), listType);
        repository.insertAll(itemList);
    }

    @Override
    public void onTerminate(){
        Log.e("TERMINATE", "TERMINATE");
        database.clearAllTables();
        networkThread.interrupt();
        super.onTerminate();
    }

}
