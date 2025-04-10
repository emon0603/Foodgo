package com.emon.foodgo.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.emon.foodgo.Adapter.FoodAdapter;
import com.emon.foodgo.ModelClass.FoodItem;
import com.emon.foodgo.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<FoodItem> foodList;
    private ShimmerFrameLayout shimmerFrameLayout;

    private MaterialButton selectedButton = null; // ট্র্যাক করার জন্য যে কোন Button সিলেক্টেড আছে

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewhome = inflater.inflate(R.layout.fragment_home, container, false);

                // Correctly cast to androidx.appcompat.widget.SearchView
        SearchView searchView = viewhome.findViewById(R.id.searchView);
        searchView.setQueryHint("Search");


        // Optional: Remove underline if required
        searchView.setBackgroundColor(Color.TRANSPARENT);  // Transparent background
        searchView.setIconifiedByDefault(false);  // Ensure SearchView is expanded by default


        LinearLayout buttonLayout = viewhome.findViewById(R.id.button_layout); // আপনার XML এ দেওয়া লিনিয়ার লেআউটের ID
        MaterialButton firstButton = (MaterialButton) buttonLayout.getChildAt(0);

        // প্রথম বাটনটি ডিফল্ট সিলেক্ট করা
        changeButtonStyle(buttonLayout, firstButton);

        for (int i = 0; i < buttonLayout.getChildCount(); i++) {
            View view = buttonLayout.getChildAt(i);
            if (view instanceof MaterialButton) {
                MaterialButton button = (MaterialButton) view;
                button.setOnClickListener(v -> changeButtonStyle(buttonLayout, button));
            }
        }



        recyclerView = viewhome.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



        foodList = new ArrayList<>();
        foodList.add(new FoodItem("Cheeseburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("cvdf", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheesebuffdrger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheefvfvseburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheefefseburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheeseburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheesebufrvrger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheeseburvrvger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Chedv eseburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheesefdfdburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheesdfeburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheedfdseburger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheesebrturger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheeseburrtger", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Cheeseburgertr", "Wendy's", 4.9, R.drawable.cheeseburger));
        foodList.add(new FoodItem("Veggie Burgerrt", "Plant Based", 4.8, R.drawable.cheeseburger));





        adapter = new FoodAdapter(getActivity(), foodList);
        recyclerView.setAdapter(adapter);


        return viewhome;
    }

    private void changeButtonStyle(LinearLayout layout, MaterialButton clickedButton) {
        // প্রথম বাটনটি ডিফল্ট সিলেক্ট করা
        if (selectedButton == null) {
            MaterialButton firstButton = (MaterialButton) layout.getChildAt(0);
            firstButton.setBackgroundTintList(getResources().getColorStateList(R.color.red)); // লাল ব্যাকগ্রাউন্ড
            firstButton.setTextColor(Color.WHITE); // সাদা টেক্সট
            firstButton.setElevation(8f); // shadow বাড়ানো
            selectedButton = firstButton;
        }

        // সব Button এর স্টাইল রিসেট করা
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof MaterialButton) {
                MaterialButton button = (MaterialButton) view;
                button.setBackgroundTintList(getResources().getColorStateList(R.color.gray_light)); // ডিফল্ট কালার
                button.setTextColor(Color.parseColor("#6A6A6A")); // ডিফল্ট টেক্সট কালার
                button.setElevation(0); // সিলেক্ট না হওয়া button এর shadow রিমুভ করা
            }
        }

        // ক্লিক করা Button এর স্টাইল পরিবর্তন করা
        clickedButton.setBackgroundTintList(getResources().getColorStateList(R.color.red)); // লাল ব্যাকগ্রাউন্ড
        clickedButton.setTextColor(Color.WHITE); // সাদা টেক্সট
        clickedButton.setElevation(8f); // shadow বাড়ানোর জন্য elevation সেট করা

        // নতুন সিলেক্টেড Button সেট করা
        selectedButton = clickedButton;
    }


}