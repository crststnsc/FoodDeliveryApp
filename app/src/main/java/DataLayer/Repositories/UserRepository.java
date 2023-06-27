package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import DataLayer.Models.User;

@Dao
public interface UserRepository extends BaseRepository<User>{

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE userId = :userId")
    User getUserById(int userId);

    @Query("DELETE FROM User")
    void deleteAll();
}
