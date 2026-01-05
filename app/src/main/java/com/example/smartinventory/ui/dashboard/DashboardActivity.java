package com.example.smartinventory.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartinventory.R;
import com.example.smartinventory.ui.auth.LoginActivity;
import com.example.smartinventory.ui.inventory.InventoryActivity;
import com.example.smartinventory.ui.reports.ReportsActivity;
import com.example.smartinventory.ui.sales.SalesActivity;
import com.example.smartinventory.util.SessionManager;
import com.example.smartinventory.viewmodel.DashboardViewModel;
import com.google.android.material.button.MaterialButton;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /* ---------- KPIs ---------- */
        TextView tvTotalProducts = findViewById(R.id.tvTotalProducts);
        TextView tvLowStock = findViewById(R.id.tvLowStock);

        /* ---------- Buttons ---------- */
        MaterialButton btnInventory = findViewById(R.id.btnInventory);
        MaterialButton btnSales = findViewById(R.id.btnSales);
        MaterialButton btnReports = findViewById(R.id.btnReports);
        MaterialButton btnLogout = findViewById(R.id.btnLogout);

        /* ---------- ViewModel ---------- */
        DashboardViewModel viewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        viewModel.getTotalProducts().observe(this,
                count -> tvTotalProducts.setText(String.valueOf(count)));

        viewModel.getLowStockCount().observe(this,
                count -> tvLowStock.setText(String.valueOf(count)));

        /* ---------- Navigation ---------- */
        btnInventory.setOnClickListener(v ->
                startActivity(new Intent(this, InventoryActivity.class)));

        btnSales.setOnClickListener(v ->
                startActivity(new Intent(this, SalesActivity.class)));

        btnReports.setOnClickListener(v ->
                startActivity(new Intent(this, ReportsActivity.class)));

        /* ---------- LOGOUT (FIXED) ---------- */
        btnLogout.setOnClickListener(v -> {
            SessionManager.clear(this);

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
