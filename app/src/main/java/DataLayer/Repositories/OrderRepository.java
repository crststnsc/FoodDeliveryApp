package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;

import DataLayer.Models.Order;

@Dao
public interface OrderRepository extends BaseRepository<Order>{
    @Query("DELETE FROM `Order`")
    void deleteAll();

    @Query("SELECT * FROM `Order` WHERE userId = :userId AND restaurantId = :restaurantId")
    Order getOrderByUserIdAndRestaurantId(int userId, int restaurantId);

    @Query("SELECT * FROM `Order` WHERE userId = :userId")
    Order getOrderByUserId(int userId);
}
