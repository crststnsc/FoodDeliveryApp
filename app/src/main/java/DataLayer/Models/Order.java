package DataLayer.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Restaurant.class, parentColumns = "restaurantId", childColumns = "restaurantId", onDelete = ForeignKey.CASCADE)})
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int orderId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "restaurantId")
    private int restaurantId;

    @ColumnInfo(name = "orderDate")
    private Date orderDate;

    @ColumnInfo(name = "totalAmount")
    private double totalAmount;

}
