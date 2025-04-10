package com.emon.foodgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emon.foodgo.Order.Order_product;
import com.google.android.material.button.MaterialButton;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FoodDetails extends AppCompatActivity {

    private SeekBar spicySeekBar;
    private MaterialButton btnUp, btnDown,pricetv,order_btn;
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
        pricetv = findViewById(R.id.pricetv);
        order_btn = findViewById(R.id.order_btn);


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

                float price = 8.24f;
                float progress_int = Float.parseFloat(String.valueOf(progress));
                float total_price = price * progress_int;

                BigDecimal bd = new BigDecimal(total_price).setScale(2, RoundingMode.HALF_UP);
                float final_price = bd.floatValue();

                Log.d("TAG", "onProgressChanged: " + final_price);
                pricetv.setText("$" + final_price);

                if (progress==0){
                    order_btn.setEnabled(false);
                    return;
                } else {
                    order_btn.setEnabled(true);
                }

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

    public void back_btn_details(View view) {
        onBackPressed();
    }

    public void Order_btn(View view) {
        startActivity(new Intent(this, Order_product.class));
    }

}