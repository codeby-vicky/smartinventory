package com.example.smartinventory.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartinventory.R;
import com.example.smartinventory.ui.dashboard.DashboardActivity;
import com.example.smartinventory.util.SessionManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ THIS WAS MISSING OR BROKEN
        setContentView(R.layout.activity_login);

        TextInputEditText etUsername = findViewById(R.id.etUsername);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText() != null
                    ? etUsername.getText().toString().trim()
                    : "";

            String password = etPassword.getText() != null
                    ? etPassword.getText().toString().trim()
                    : "";

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔐 TEMP LOGIN (ADMIN / STAFF)
            if (username.equals("admin") && password.equals("admin123")) {
                SessionManager.setRole(this, "ADMIN");
            } else {
                SessionManager.setRole(this, "STAFF");
            }

            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });
    }
}
