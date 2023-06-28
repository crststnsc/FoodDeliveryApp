package UILayer.ViewModels;

import android.util.Log;
import android.view.View;


import java.util.List;

import DataLayer.Adapters.RestaurantAdapter;
import DataLayer.AppDataSource;
import DataLayer.Models.Restaurant;


public class RestaurantsVM {
    private List<Restaurant> restaurants;

    public RestaurantAdapter getRestaurantAdapter() {
        return restaurantAdapter;
    }

    private RestaurantAdapter restaurantAdapter;

    public RestaurantsVM(){
        restaurants = AppDataSource.database.restaurantRepository().getAllRestaurants();
        restaurantAdapter = new RestaurantAdapter(restaurants);
    }

    public void onRestaurantClick(View view){
        Log.d("RestaurantsVM", "onRestaurantClick: " + view.getId());
    }

}
