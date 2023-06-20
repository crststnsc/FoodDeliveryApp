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

    @ColumnInfo(name = "itemId")
    private int itemId;

    @ColumnInfo(name = "quantity")
    private int quantity;

}
