package UILayer.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import DataLayer.AppDataSource;
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
}
