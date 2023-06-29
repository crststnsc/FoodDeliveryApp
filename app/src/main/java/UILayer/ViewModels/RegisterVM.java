package UILayer.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

import DataLayer.AppDataSource;
import DataLayer.Models.StaticUser;
import DataLayer.Models.User;

public class RegisterVM extends ViewModel {
    private List<User> users;
    public String username;
    public String password;
    public String confirmPassword;
    public String email;
    public String phoneNumber;
    public String address;

    private MutableLiveData<Action> action = new MutableLiveData<>();
    public LiveData<Action> getAction(){
        return action;
    }

    public RegisterVM() {
        users = AppDataSource.database.userRepository().getAllUsers();
    }

    public void registerUser(){
        if(!validateUser(username)){
            showInvalidRegister();
            return;
        }

        if(!password.equals(confirmPassword)){
            showInvalidPassword();
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);

        AppDataSource.database.userRepository().insertAll(user);

        User finalUser = AppDataSource.database.userRepository().getUserByUsername(username);

        StaticUser.user = finalUser;

        Thread thread = new Thread(() -> sendUserToServer(finalUser));
        thread.start();

        showHome();
    }

    public boolean validateUser(String username){
        for (User user : users){
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    public void showHome(){
        action.setValue(new Action(Action.SHOW_HOME));
    }

    public void showInvalidRegister(){
        action.setValue(new Action(Action.SHOW_NAME_TAKEN));
    }

    public void showInvalidPassword(){
        action.setValue(new Action(Action.SHOW_INVALID_PASSWORD));
    }

    private void sendUserToServer(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);

        try{
            URL url = new URL("http://10.0.2.2:5000/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            connection.setDoOutput(true);
            connection.getOutputStream().write(json.getBytes());

            int responseCode = connection.getResponseCode();

            Log.e("RESPONSE CODE", String.valueOf(responseCode));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
