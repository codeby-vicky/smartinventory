package com.example.smartinventory.ui.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartinventory.R;
import com.example.smartinventory.data.entity.Product;
import com.example.smartinventory.util.NotificationUtil;
import com.example.smartinventory.viewmodel.InventoryViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private boolean lowStockNotified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        /* ---------- Toolbar ---------- */
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Manage Inventory");

        /* ---------- Views ---------- */
        RecyclerView recyclerView = findViewById(R.id.recyclerInventory);
        FloatingActionButton fabAddProduct = findViewById(R.id.fabAddProduct);

        /* ---------- RecyclerView ---------- */
        ProductAdapter adapter = new ProductAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        /* ---------- ViewModel ---------- */
        InventoryViewModel viewModel =
                new ViewModelProvider(this).get(InventoryViewModel.class);

        viewModel.getAllProducts().observe(this, products -> {
            adapter.setProducts(products);
            checkLowStock(products);
        });

        /* ---------- FAB (ALWAYS ENABLED) ---------- */
        fabAddProduct.setVisibility(View.VISIBLE);
        fabAddProduct.setEnabled(true);

        fabAddProduct.setOnClickListener(v ->
                startActivity(new Intent(this, AddProductActivity.class))
        );
    }

    /* ---------- Low Stock Notification ---------- */
    private void checkLowStock(List<Product> products) {
        boolean hasLowStock = false;

        for (Product product : products) {
            if (product.getQuantity() <= 5) {
                hasLowStock = true;
                break;
            }
        }

        if (hasLowStock && !lowStockNotified) {
            NotificationUtil.showLowStockAlert(this);
            lowStockNotified = true;
        }

        if (!hasLowStock) {
            lowStockNotified = false;
        }
    }
}
