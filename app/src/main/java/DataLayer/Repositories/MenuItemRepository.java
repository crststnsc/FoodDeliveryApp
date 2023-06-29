package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import DataLayer.Models.MenuItem;

@Dao
public interface MenuItemRepository extends BaseRepository<MenuItem>{
    @Query("DELETE FROM MenuItem")
    void deleteAll();

    @Query("SELECT * FROM MenuItem WHERE restaurantId = :restaurantId")
    List<MenuItem> getMenuItemsByRestaurantId(int restaurantId);

    @Query("SELECT * FROM MenuItem WHERE menuItemId = :menuItemId")
    MenuItem getMenuItemById(int menuItemId);
}
