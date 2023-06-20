package DataLayer.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name="address")
    private String address;

}
