package DataLayer.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(foreignKeys = {@ForeignKey(entity = Restaurant.class, parentColumns = "restaurantId", childColumns = "restaurantId", onDelete = ForeignKey.CASCADE)})
public class MenuItem {

    @PrimaryKey(autoGenerate = true)
    private int menuItemId;

    @ColumnInfo(name = "restaurantId")
    private int restaurantId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

}
