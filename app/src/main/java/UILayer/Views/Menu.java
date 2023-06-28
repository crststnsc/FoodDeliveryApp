package UILayer.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMenuBinding;

import java.util.List;

import DataLayer.AppDataSource;
import DataLayer.Models.MenuItem;
import UILayer.ViewModels.MenuVM;

public class Menu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuVM menuVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        int restaurantId = intent.getIntExtra("restaurantId", 0);

        menuVM = new MenuVM(restaurantId);

        recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setAdapter(menuVM.getMenuAdapter());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView restaurantImage = findViewById(R.id.imageView);

        String imageName = menuVM.restaurant.getImageName();
        int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        restaurantImage.setImageResource(imageId);

        TextView restaurantName = findViewById(R.id.restaurant_menu_name);
        restaurantName.setText(menuVM.restaurant.getName());
    }

}
