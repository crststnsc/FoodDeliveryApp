package UILayer.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FragmentCartBinding;

import DataLayer.Models.StaticUser;
import UILayer.ViewModels.CartVM;

public class Cart extends Fragment {

    private CartVM cartVM;
    private RecyclerView recyclerView;
    private FragmentCartBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartVM = new CartVM(StaticUser.user.getUserId());
        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(cartVM.getCartAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        binding = FragmentCartBinding.bind(view);
        binding.setCartViewModel(cartVM);
    }
}