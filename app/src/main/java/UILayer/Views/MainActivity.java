package UILayer.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.os.Bundle;


import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;

import UILayer.ViewModels.UsersVM;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UsersVM usersVM = new UsersVM();

        binding.setUserVM(usersVM);
    }
}