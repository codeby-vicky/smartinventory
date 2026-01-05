package com.example.smartinventory.ui.sales;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartinventory.R;
import com.example.smartinventory.viewmodel.SalesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SalesActivity extends AppCompatActivity {

    private SalesViewModel viewModel;

    // TEMP: replace with actual selected product ID later
    private final int productId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        TextInputEditText etQuantitySold =
                findViewById(R.id.etQuantitySold);
        MaterialButton btnConfirmSale =
                findViewById(R.id.btnConfirmSale);

        viewModel = new ViewModelProvider(this)
                .get(SalesViewModel.class);

        btnConfirmSale.setOnClickListener(v -> {
            String qtyStr = etQuantitySold.getText() != null
                    ? etQuantitySold.getText().toString().trim()
                    : "";

            if (TextUtils.isEmpty(qtyStr)) {
                etQuantitySold.setError("Enter quantity");
                return;
            }

            int quantity = Integer.parseInt(qtyStr);
            if (quantity <= 0) {
                etQuantitySold.setError("Invalid quantity");
                return;
            }

            viewModel.recordSale(productId, quantity);

            Toast.makeText(this,
                    "Sale recorded successfully",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
