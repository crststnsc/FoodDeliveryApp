package UILayer.ViewModels;

import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.List;

import DataLayer.Adapters.MenuAdapter;
import DataLayer.AppDataSource;
import DataLayer.Models.MenuItem;
import DataLayer.Models.Order;
import DataLayer.Models.OrderItem;
import DataLayer.Models.Restaurant;
import DataLayer.Models.StaticUser;

public class MenuVM extends ViewModel {
    private List<MenuItem> menuItems;

    public MenuAdapter getMenuAdapter() {
        return menuAdapter;
    }

    private MenuAdapter menuAdapter;

    public Restaurant restaurant;

    public MenuVM(int restaurantId){
        restaurant = AppDataSource.database.restaurantRepository().getRestaurantById(restaurantId);
        menuItems = AppDataSource.database.menuItemRepository().getMenuItemsByRestaurantId(restaurantId);
        menuAdapter = new MenuAdapter(menuItems);
        menuAdapter.setOnItemAddListener(this::createOrder);
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public void createOrder(int position){
        MenuItem menuItem = menuItems.get(position);
        Order order = AppDataSource.database.orderRepository().getOrderByUserIdAndRestaurantId(StaticUser.user.getUserId(), menuItem.getRestaurantId());
        if(order == null){
            order = new Order();
            order.setUserId(StaticUser.user.getUserId());
            order.setRestaurantId(restaurant.getRestaurantId());
            order.setOrderDate(Date.from(java.time.Instant.now()).toString());
            order.setTotalAmount(0);
            AppDataSource.database.orderRepository().insertAll(order);
        }

        addToOrder(menuItem);
    }

    private void addToOrder(MenuItem menuItem){
        Order order = AppDataSource.database.orderRepository().getOrderByUserIdAndRestaurantId(StaticUser.user.getUserId(), menuItem.getRestaurantId());
        order.setTotalAmount(order.getTotalAmount() + menuItem.getPrice());

        //if the menuItem is already in the order, just increment the quantity
        OrderItem orderItem = AppDataSource.database.orderItemRepository().getOrderItemByOrderIdAndMenuItemId(order.getOrderId(), menuItem.getMenuItemId());
        if(orderItem != null){
            orderItem.setQuantity(orderItem.getQuantity() + 1);
            AppDataSource.database.orderItemRepository().update(orderItem);
            return;
        }

        orderItem = new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setMenuItemId(menuItem.getMenuItemId());
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        AppDataSource.database.orderItemRepository().insertAll(orderItem);
    }
}
