package com.emon.foodgo.Order;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emon.foodgo.MainActivity;
import com.emon.foodgo.R;
import com.google.android.material.button.MaterialButton;

public class Order_Complete extends AppCompatActivity {

    MaterialButton go_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_complete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showCustomDialog();

    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(Order_Complete.this);
        dialog.setContentView(R.layout.item_payment_status);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        go_back_btn = dialog.findViewById(R.id.go_back_btn);

        go_back_btn.setOnClickListener(v -> {
           startActivity(new Intent(Order_Complete.this, MainActivity.class));
           finish();
           dialog.dismiss();
        });

        dialog.show();
    }
}


