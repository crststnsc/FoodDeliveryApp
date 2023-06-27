package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;

import DataLayer.Models.Order;

@Dao
public interface OrderRepository extends BaseRepository<Order>{
    @Query("DELETE FROM `Order`")
    void deleteAll();
}
