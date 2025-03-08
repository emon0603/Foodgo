package com.emon.foodgo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.emon.foodgo.FoodDetails;
import com.emon.foodgo.ModelClass.FoodItem;
import com.emon.foodgo.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private Context context;
    private List<FoodItem> foodList;

    public FoodAdapter(FragmentActivity context, List<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.restaurant.setText(food.getRestaurant());
        holder.rating.setText(String.valueOf(food.getRating()));
        holder.foodImage.setImageResource(food.getImageResId());

        // ✅ ফেভারিট স্ট্যাটাস অনুযায়ী হার্ট আইকন সেট করুন
        if (food.isFavorite()) {
            holder.favoriteIcon.setImageResource(R.drawable.ic_heart);
            holder.favoriteIcon.setColorFilter(Color.RED);
        } else {
            holder.favoriteIcon.setImageResource(R.drawable.ic_cart);
            holder.favoriteIcon.setColorFilter(Color.GRAY);
        }

        // ✅ ফেভারিট আইকন ক্লিক ইভেন্ট
        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food.setFavorite(!food.isFavorite()); // স্ট্যাটাস পরিবর্তন

                // ✅ আবার আইকন পরিবর্তন করুন
                if (food.isFavorite()) {
                    holder.favoriteIcon.setImageResource(R.drawable.ic_heart);
                    holder.favoriteIcon.setColorFilter(Color.RED);
                } else {
                    holder.favoriteIcon.setImageResource(R.drawable.ic_cart);
                    holder.favoriteIcon.setColorFilter(Color.GRAY);
                }
            }
        });

        // ✅ আইটেম ক্লিক করলে স্ট্যাটাস আপডেট
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetails.class);
            context.startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, restaurant, rating;
        ImageView foodImage, favoriteIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            restaurant = itemView.findViewById(R.id.restaurant_name);
            rating = itemView.findViewById(R.id.food_rating);
            foodImage = itemView.findViewById(R.id.food_image);
            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
        }
    }
}

