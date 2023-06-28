package UILayer.ViewModels;

import androidx.lifecycle.ViewModel;

import java.util.List;

import DataLayer.Adapters.MenuAdapter;
import DataLayer.AppDataSource;
import DataLayer.Models.MenuItem;
import DataLayer.Models.Restaurant;

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
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }
}
