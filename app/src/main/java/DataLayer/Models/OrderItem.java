package DataLayer.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Order.class, parentColumns = "orderId", childColumns = "orderId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = MenuItem.class, parentColumns = "menuItemId", childColumns = "menuItemId", onDelete = ForeignKey.CASCADE)})
public class OrderItem {

    @PrimaryKey(autoGenerate = true)
    private int orderItemId;

    @ColumnInfo(name = "orderId")
    private int orderId;

    @ColumnInfo(name = "menuItemId")
    private int menuItemId;

    @ColumnInfo(name = "quantity")
    private int quantity = 0;

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

}
