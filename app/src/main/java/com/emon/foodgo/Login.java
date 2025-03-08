package com.emon.foodgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {


    public TextInputEditText edemail, edpass;
    TextView forgetbt;
    TextInputLayout edpasslay;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        edemail = findViewById(R.id.edemail);
        edpass = findViewById(R.id.edpin);
        edpasslay = findViewById(R.id.edpasslay);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edpass.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (!TextUtils.isEmpty(charSequence)) {
                    edpass.setError(null);
                    edpasslay.setEndIconVisible(true);
                } else {
                    edpasslay.setEndIconVisible(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!TextUtils.isEmpty(charSequence)) {
                    edpass.setError(null);
                    edpasslay.setEndIconVisible(true);
                }
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {
            }
        });

    }



    public void gotoRegister(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }


    public void Login_Btn(View view) {

        boolean isValid = true;

        if (TextUtils.isEmpty(edemail.getText().toString())) {
            edemail.setError("Input Your Email");
            isValid = false;
        } else {
            edemail.setError(null);
        }

        if (TextUtils.isEmpty(edpass.getText().toString().trim())) {
            edpasslay.setEndIconVisible(false);
            edpass.setError("Input Your Password");
            isValid = false;
        } else {
            edpass.setError(null);
            edpasslay.setEndIconVisible(true);
        }

        if (isValid) {
            // Save credentials if Remember Me is checked

            editor.putString("email", edemail.getText().toString());
            editor.putString("password",  edpass.getText().toString());
            editor.apply();

            startActivity(new Intent(Login.this, MainActivity.class));
            finish();

            // Call login request

        }

    }

    }
