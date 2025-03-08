package com.emon.foodgo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emon.foodgo.Adapter.FoodAdapter;
import com.emon.foodgo.Fragment.Cart;
import com.emon.foodgo.Fragment.Home;
import com.emon.foodgo.Fragment.Notification;
import com.emon.foodgo.ModelClass.FoodItem;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<FoodItem> foodList;
    private MaterialButton selectedButton = null; // ট্র্যাক করার জন্য যে কোন Button সিলেক্টেড আছে
    boolean Exit = false;
    boolean Exit2 = true;
    boolean isHome = true;
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String email = sharedPreferences.getString("email", "");

        if (email.length() <= 0) {
            startActivity(new Intent(this, Login.class));
            finish();
        }



        LoadBottomNavigation();
    }



    private void LoadBottomNavigation() {

        BadgeDrawable badge = bottomNavigationView.getOrCreateBadge(R.id.bottom_noti);
        badge.setVisible(true); // Show the badge
        badge.setNumber(1); // Set the badge number (you can change this based on your logic)

// Optionally, you can customize the badge
        badge.setBadgeTextColor(getResources().getColor(android.R.color.white));
        badge.setMaxCharacterCount(3); // Limit the number of characters in the badge

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Home()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.bottom_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Home()).commit();
                    return true;
                }  else if (itemId == R.id.bottom_scan_QR) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Cart()).commit();
                    return true;
                } else if (itemId == R.id.bottom_noti) {

                    badge.clearNumber();
                    badge.setVisible(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Notification()).commit();
                    return true;
                }
                else if (itemId == R.id.bottom_profile) {
                    startActivity(new Intent(MainActivity.this, Profile.class));
                    return true;
                }


                return false;
            }
        });
    }


    public void onBackPressed() {

        if (isHome && Exit2) {
            finishAffinity();
        }

    }

}
