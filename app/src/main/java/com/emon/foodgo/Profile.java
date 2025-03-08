package com.emon.foodgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Profile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private TextInputEditText edName, edEmail, edAddress, edPin;
    TextView btn_name_tv;
    int edit_btn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edName = findViewById(R.id.edname);
        edEmail = findViewById(R.id.edemail);
        edAddress = findViewById(R.id.edaddress);
        edPin = findViewById(R.id.edpin);
        btn_name_tv = findViewById(R.id.btn_name_tv);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        disableEditTexts();


    }

    // Handle Toolbar Back Button Click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    public void back_btn(View view) {
        onBackPressed();
    }


    public void Logout_btn(View view) {

        editor.putString("email", "");
        editor.apply();

        startActivity(new Intent(Profile.this, MainActivity.class));
        finish();
    }

    // Function to disable EditText fields
    private void disableEditTexts() {
        edName.setFocusable(false);
        edName.setFocusableInTouchMode(false);
        edEmail.setFocusable(false);
        edEmail.setFocusableInTouchMode(false);
        edAddress.setFocusable(false);
        edAddress.setFocusableInTouchMode(false);
        edPin.setFocusable(false);
        edPin.setFocusableInTouchMode(false);
    }

    // Function to enable EditText fields
    private void enableEditTexts() {
        edName.setFocusable(true);
        edName.setFocusableInTouchMode(true);
        edEmail.setFocusable(true);
        edEmail.setFocusableInTouchMode(true);
        edAddress.setFocusable(true);
        edAddress.setFocusableInTouchMode(true);
        edPin.setFocusable(true);
        edPin.setFocusableInTouchMode(true);
    }

    public void Edit_Btn(View view) {

        if (edit_btn == 0) {
            enableEditTexts();
            btn_name_tv.setText("Save");

            edit_btn = 1;
        } else {
            btn_name_tv.setText("Edit Profile");
            disableEditTexts();
            edit_btn = 0;
        }





    }
}