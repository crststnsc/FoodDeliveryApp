package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import DataLayer.Models.MenuItem;

@Dao
public interface MenuItemRepository extends BaseRepository<MenuItem>{
    @Query("DELETE FROM MenuItem")
    void deleteAll();
}
