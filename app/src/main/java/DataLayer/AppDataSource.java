package DataLayer;


import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import DataLayer.Models.MenuItem;
import DataLayer.Models.Order;
import DataLayer.Models.OrderItem;
import DataLayer.Models.Restaurant;
import DataLayer.Models.User;
import DataLayer.Repositories.BaseRepository;

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
        // Implement your logic to process the server response data
        Log.e("INFO","Server Response: " + responseData);
        // Parse the JSON data, update your local database, etc.
        try{
            JSONObject jsonObject = new JSONObject(responseData);

            JSONArray users = jsonObject.getJSONArray("users");
            fillTable(users, database.userRepository(), User.class);

            JSONArray restaurants = jsonObject.getJSONArray("restaurants");
            fillTable(restaurants, database.restaurantRepository(), Restaurant.class);

            JSONArray menuItems = jsonObject.getJSONArray("menuItems");
            fillTable(menuItems, database.menuItemRepository(), MenuItem.class);

            JSONArray orders = jsonObject.getJSONArray("orders");
            fillTable(orders, database.orderRepository(), Order.class);

            JSONArray orderItems = jsonObject.getJSONArray("orderItems");
            fillTable(orderItems, database.orderItemRepository(), OrderItem.class);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    private <T> void fillTable(JSONArray data, BaseRepository<T> repository, Type listType) {
        Gson gson = new Gson();

        List<T> itemList = gson.fromJson(data.toString(), listType);
        repository.insertAll(itemList.toArray((T[]) new Object[0]));
    }


    @Override
    public void onTerminate(){
        super.onTerminate();
        networkThread.interrupt();
    }
}
