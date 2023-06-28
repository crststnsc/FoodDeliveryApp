package UILayer.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;


import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;

import UILayer.ViewModels.LoginVM;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginVM mLoginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginVM = new ViewModelProvider(this).get(LoginVM.class);
        mLoginVM.getAction().observe(this, this::handleAction);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLoginVM(mLoginVM);
    }

    private void handleAction(LoginVM.Action action){
        switch (action.getValue()){
            case LoginVM.Action.SHOW_HOME:
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case LoginVM.Action.SHOW_INVALID_LOGIN:
                setContentView(R.layout.activity_main);
                break;
        }
    }
}