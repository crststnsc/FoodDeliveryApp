package UILayer.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import DataLayer.AppDataSource;
import DataLayer.Models.StaticUser;
import DataLayer.Models.User;
import UILayer.Views.Home;

public class LoginVM extends ViewModel {
    private List<User> users;
    public String username;
    public String password;
    private MutableLiveData<Action> action = new MutableLiveData<>();

    public LiveData<Action> getAction(){
        return action;
    }

    public LoginVM() {
        users = AppDataSource.database.userRepository().getAllUsers();
    }

    public void loginUser(){
        if(username == null || password == null){
            Log.e("LOGIN","NULL username or password");
            return;
        }

        users = AppDataSource.database.userRepository().getAllUsers();

        if(!validateUser(username, password)){
            showInvalidLogin();
            return;
        }
        showHome();
    }

    private void showHome(){
        action.setValue(new Action(Action.SHOW_HOME));
    }

    private void showInvalidLogin(){
        action.setValue(new Action(Action.SHOW_INVALID_LOGIN));
    }

    public void goToRegister(){
        action.setValue(new Action(Action.SHOW_REGISTER));
    }

    public boolean validateUser(String username, String password){
        for (User user : users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                StaticUser.user = user;
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
