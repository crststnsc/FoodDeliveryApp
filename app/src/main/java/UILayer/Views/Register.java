package UILayer.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;
import com.example.fooddelivery.databinding.ActivityRegisterBinding;

import UILayer.ViewModels.Action;
import UILayer.ViewModels.RegisterVM;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterVM mRegisterVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterVM = new ViewModelProvider(this).get(RegisterVM.class);
        mRegisterVM.getAction().observe(this, this::handleAction);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setRegisterVM(mRegisterVM);
    }

    private void handleAction(Action action){
        switch (action.getValue()){
            case Action.SHOW_HOME:
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case Action.SHOW_NAME_TAKEN:
                Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
                break;
            case Action.SHOW_INVALID_PASSWORD:
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

