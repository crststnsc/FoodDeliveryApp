package UILayer.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;

import DataLayer.Adapters.ItemClickListener;
import DataLayer.Models.Restaurant;
import UILayer.ViewModels.RestaurantsVM;

public class Home extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    RestaurantsVM restaurantsVM = new RestaurantsVM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.restaurantRecyclerView);

        restaurantsVM.getRestaurantAdapter().setClickListener(this);

        recyclerView.setAdapter(restaurantsVM.getRestaurantAdapter());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view, int position) {
        Restaurant restaurant = restaurantsVM.getRestaurants().get(position);

        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("restaurantId", restaurant.getRestaurantId());

        Log.e("Home", "onClick: " + restaurant.getRestaurantId());

        startActivity(intent);
    }
}