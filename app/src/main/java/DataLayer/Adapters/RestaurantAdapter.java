package DataLayer.Adapters;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.BuildConfig;
import com.example.fooddelivery.R;

import java.util.List;

import DataLayer.Models.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>{

    final private List<Restaurant> restaurants;

    public RestaurantAdapter(List<Restaurant> restaurants){
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameTextView.setText(restaurant.getName());

        String imageName = restaurant.getImageName();

        Log.e("RESOURCE ID", imageName + " " + holder.imageView.getContext().getPackageName());
        int resourceId = holder.imageView.getContext().getResources().getIdentifier(imageName, "drawable", holder.imageView.getContext().getPackageName());

        holder.imageView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            imageView = itemView.findViewById(R.id.restaurantImage);
        }
    }
}
