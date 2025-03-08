package com.emon.foodgo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class FoodDetails extends AppCompatActivity {

    private SeekBar spicySeekBar;
    private MaterialButton btnUp, btnDown;
    private TextView spicyLevelText,quantity_item_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        quantity_item_tv = findViewById(R.id.quantity_item_tv);
        spicySeekBar = findViewById(R.id.spicySeekBar);
        spicyLevelText = findViewById(R.id.spicyLevelText);


        btnUp.setOnClickListener(v -> {
            int progress = spicySeekBar.getProgress();
            if (progress < spicySeekBar.getMax()) {
                spicySeekBar.setProgress(progress + 1);
                quantity_item_tv.setText(String.valueOf(progress + 1));

            }
        });

        btnDown.setOnClickListener(v -> {
            int progress = spicySeekBar.getProgress();
            if (progress > 0) {
                spicySeekBar.setProgress(progress - 1);
                quantity_item_tv.setText(String.valueOf(progress - 1));
            }
        });



        spicySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 🔥 স্পাইসি লেভেল অনুযায়ী টেক্সট আপডেট করা
                if (progress <= 3) {
                    quantity_item_tv.setText(String.valueOf(progress));
                    spicyLevelText.setText("Mild");
                    spicyLevelText.setTextColor(getResources().getColor(R.color.green));
                } else if (progress <= 7) {
                    quantity_item_tv.setText(String.valueOf(progress));
                    spicyLevelText.setText("Medium");
                    spicyLevelText.setTextColor(getResources().getColor(R.color.orange));
                } else {
                    quantity_item_tv.setText(String.valueOf(progress));
                    spicyLevelText.setText("Hot");
                    spicyLevelText.setTextColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}