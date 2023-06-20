package DataLayer;

import androidx.room.Database;

import DataLayer.Repositories.*;

@Database(entities = {
        DataLayer.Models.Restaurant.class,
        DataLayer.Models.OrderItem.class,
        DataLayer.Models.Order.class,
        DataLayer.Models.MenuItem.class,
        DataLayer.Models.User.class},
        version = 1)
public abstract class AppDatabase extends androidx.room.RoomDatabase{
    public abstract RestaurantRepository restaurantRepository();
    public abstract OrderItemRepository orderItemRepository();
    public abstract OrderRepository orderRepository();
    public abstract MenuItemRepository menuItemRepository();
    public abstract UserRepository userRepository();
}
