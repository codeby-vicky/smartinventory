package com.example.smartinventory.ui.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartinventory.R;
import com.example.smartinventory.data.entity.Product;
import com.example.smartinventory.ui.scanner.BarcodeScannerActivity;
import com.example.smartinventory.viewmodel.InventoryViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddProductActivity extends AppCompatActivity {

    private InventoryViewModel viewModel;
    private TextInputEditText etName, etQty;

    private final ActivityResultLauncher<Intent> scanLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            String barcode = result.getData().getStringExtra("barcode");
                            if (!TextUtils.isEmpty(barcode)) {
                                etName.setText(barcode);
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etName = findViewById(R.id.etProductName);
        etQty = findViewById(R.id.etQuantity);
        MaterialButton btnSave = findViewById(R.id.btnSave);
        MaterialButton btnScan = findViewById(R.id.btnScan);

        viewModel = new ViewModelProvider(this)
                .get(InventoryViewModel.class);

        btnScan.setOnClickListener(v ->
                scanLauncher.launch(
                        new Intent(this, BarcodeScannerActivity.class)
                )
        );

        btnSave.setOnClickListener(v -> saveProduct());
    }

    private void saveProduct() {
        String name = etName.getText() != null ? etName.getText().toString().trim() : "";
        String qtyStr = etQty.getText() != null ? etQty.getText().toString().trim() : "";

        if (TextUtils.isEmpty(name)) {
            etName.setError("Enter product name");
            return;
        }

        if (TextUtils.isEmpty(qtyStr)) {
            etQty.setError("Enter quantity");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            etQty.setError("Invalid number");
            return;
        }

        if (quantity < 0) {
            etQty.setError("Quantity cannot be negative");
            return;
        }

        viewModel.insert(new Product(name, quantity));
        Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
