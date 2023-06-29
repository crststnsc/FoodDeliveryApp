package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import DataLayer.Models.OrderItem;

@Dao
public interface OrderItemRepository extends BaseRepository<OrderItem>{
    @Query("DELETE FROM OrderItem")
    void deleteAll();

    @Query("SELECT * FROM OrderItem WHERE orderId = :orderId")
    List<OrderItem> getOrderItemsByOrderId(int orderId);

    @Query("SELECT * FROM OrderItem WHERE orderId = :orderId AND menuItemId = :menuItemId")
    OrderItem getOrderItemByOrderIdAndMenuItemId(int orderId, int menuItemId);

    @Update
    void update(OrderItem orderItem);
}
