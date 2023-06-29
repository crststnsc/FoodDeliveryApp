package DataLayer.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;

import org.w3c.dom.Text;

import java.util.List;

import DataLayer.Models.MenuItem;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>{
    final private List<MenuItem> menuItems;

    public void setOnItemAddListener(OnItemAddListener onItemAddListener) {
        this.onItemAddListener = onItemAddListener;
    }

    private OnItemAddListener onItemAddListener;
    public MenuAdapter(List<MenuItem> menuItems){
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_card, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.nameTextView.setText(menuItem.getName());

        String price = String.valueOf(menuItem.getPrice());
        holder.priceTextView.setText(price);

        holder.descriptionTextView.setText(menuItem.getDescription());

        String imageName = menuItem.getImageName();

        int resourceId = holder.imageView.getContext().getResources().getIdentifier(imageName, "drawable", holder.imageView.getContext().getPackageName());

        holder.imageView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public interface OnItemAddListener{
        void onItemAdd(int position);
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;
        TextView descriptionTextView;
        ImageButton addToCartButton;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.itemImage);
            nameTextView = itemView.findViewById(R.id.itemName);
            priceTextView = itemView.findViewById(R.id.itemPrice);
            descriptionTextView = itemView.findViewById(R.id.itemDescription);

            addToCartButton = itemView.findViewById(R.id.addItemButton);
            addToCartButton.setOnClickListener(v -> {
                if(onItemAddListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onItemAddListener.onItemAdd(position);
                    }
                }
            });
        }
    }
}
