package UILayer.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;

import UILayer.ViewModels.Action;
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

    private void handleAction(Action action) {
        switch (action.getValue()) {
            case Action.SHOW_HOME:
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case Action.SHOW_INVALID_LOGIN:
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                break;
            case Action.SHOW_REGISTER:
                intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }
}