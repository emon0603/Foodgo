package com.emon.foodgo.Order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emon.foodgo.R;

public class Order_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Payment_select();

    }

    private void Payment_select() {

        boolean ischecked = true;

        RadioButton radio1 = findViewById(R.id.radio1);
        RadioButton radio2 = findViewById(R.id.radio2);
        LinearLayout layout1 = findViewById(R.id.layout1);
        LinearLayout layout2 = findViewById(R.id.layout2);
        TextView credit_card_tv = findViewById(R.id.credit_card_tv);
        TextView debit_card_tv = findViewById(R.id.debit_card_tv);

// Default: radio2 selected
        radio1.setChecked(true);
        layout1.setBackgroundResource(R.drawable.bg_selected);
        layout2.setBackgroundResource(R.drawable.bg_unselected);
        credit_card_tv.setTextColor(getResources().getColor(R.color.white));

        // Listener for radio1
        radio1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                radio2.setChecked(false);
                layout1.setBackgroundResource(R.drawable.bg_selected);
                layout2.setBackgroundResource(R.drawable.bg_unselected);
                credit_card_tv.setTextColor(getResources().getColor(R.color.white));
                debit_card_tv.setTextColor(getResources().getColor(R.color.black));
            }
        });

// Listener for radio2
        radio2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                radio1.setChecked(false);
                layout2.setBackgroundResource(R.drawable.bg_selected);
                layout1.setBackgroundResource(R.drawable.bg_unselected);
                debit_card_tv.setTextColor(getResources().getColor(R.color.white));
                credit_card_tv.setTextColor(getResources().getColor(R.color.black));
            }
        });



    }

    public void back_btn_details(View view) {
        onBackPressed();
    }

    public void Pay_Btn(View view) {
        startActivity(new Intent(this, Order_Complete.class));
    }
}