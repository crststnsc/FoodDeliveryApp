package UILayer.ViewModels;

import android.util.Log;

import java.util.List;

import DataLayer.AppDataSource;
import DataLayer.Models.User;

public class UsersVM {
    private List<User> users;
    public String username;
    public String password;

    public UsersVM() {
        users = AppDataSource.database.userRepository().getAllUsers();
    }

    public void loginUser(){
        if(username == null || password == null){
            Log.e("LOGIN","NULL username or password");
            return;
        }

        if(!validateUser(username, password)){
            Log.e("LOGIN","User login failed");
            return;
        }

        Log.e("LOGIN","User login successful");
    }

    public boolean validateUser(String username, String password){
        for (User user : users){
            Log.e("USER", user.getUsername());
            Log.e("PASSWORD", user.getPassword());
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
