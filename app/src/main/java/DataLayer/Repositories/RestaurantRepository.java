package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import DataLayer.Models.Restaurant;

@Dao
public interface RestaurantRepository extends BaseRepository<Restaurant>{

    @Query("SELECT * FROM Restaurant")
    List<Restaurant> getAllRestaurants();

    @Query("DELETE FROM MenuItem")
    void deleteAll();

}
