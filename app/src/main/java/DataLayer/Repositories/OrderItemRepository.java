package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;

import DataLayer.Models.OrderItem;

@Dao
public interface OrderItemRepository extends BaseRepository<OrderItem>{
    @Query("DELETE FROM OrderItem")
    void deleteAll();
}
