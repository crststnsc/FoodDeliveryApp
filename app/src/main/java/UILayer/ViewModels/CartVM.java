package UILayer.ViewModels;

import java.util.List;

import DataLayer.Adapters.CartAdapter;
import DataLayer.AppDataSource;
import DataLayer.Models.MenuItem;
import DataLayer.Models.Order;
import DataLayer.Models.OrderItem;

public class CartVM {
    private List<OrderItem> orderItems;
    public CartAdapter getCartAdapter() {
        return cartAdapter;
    }

    private final CartAdapter cartAdapter;
    public String stringAmount;

    public CartVM(int userId){
        Order order = AppDataSource.database.orderRepository().getOrderByUserId(userId);
        if(order == null){
            order = new Order();
        }
        orderItems = AppDataSource.database.orderItemRepository().getOrderItemsByOrderId(order.getOrderId());
        cartAdapter = new CartAdapter(orderItems);

        int totalAmount = calculateTotalAmount();
        stringAmount = "Total amount: " + totalAmount + " RON";
    }

    private int calculateTotalAmount() {
        int totalAmount = 0;
        for(OrderItem orderItem : orderItems){
            MenuItem menuItem = AppDataSource.database.menuItemRepository().getMenuItemById(orderItem.getMenuItemId());
            totalAmount += orderItem.getQuantity() * menuItem.getPrice();
        }
        return totalAmount;
    }
}
