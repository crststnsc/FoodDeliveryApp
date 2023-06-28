package UILayer.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;

import UILayer.ViewModels.RestaurantsVM;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RestaurantsVM restaurantsVM = new RestaurantsVM();
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setAdapter(restaurantsVM.getRestaurantAdapter());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}