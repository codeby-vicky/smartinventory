package com.example.smartinventory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.smartinventory.data.database.AppDatabase;
import com.example.smartinventory.data.entity.Sale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SalesViewModel extends AndroidViewModel {

    private final AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public SalesViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
    }

    /**
     * ✅ SINGLE SOURCE OF TRUTH
     * UI only sends productId + quantity
     */
    public void recordSale(int productId, int quantitySold) {
        executor.execute(() -> {
            Sale sale = new Sale(
                    productId,
                    quantitySold,
                    System.currentTimeMillis()
            );
            db.salesDao().insert(sale);
        });
    }
}
