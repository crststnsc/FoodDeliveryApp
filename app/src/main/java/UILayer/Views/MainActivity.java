package UILayer.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.fooddelivery.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v ->
            new Thread(() -> {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start()
        );
    }

    private void login() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Log.e("MainActivity", "responseCode: " + responseCode);
    }

}