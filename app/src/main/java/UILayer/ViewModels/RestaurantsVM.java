package UILayer.ViewModels;

import android.util.Log;
import android.view.View;


import java.util.List;

import DataLayer.AppDataSource;
import DataLayer.Models.Restaurant;


public class RestaurantsVM {
    List<Restaurant> restaurants;

    public RestaurantsVM(){
        restaurants = AppDataSource.database.restaurantRepository().getAllRestaurants();
    }

    public void showHelloWorld(){
        Log.e("HELLO","Hello World");
    }

}
