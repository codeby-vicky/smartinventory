package com.example.smartinventory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartinventory.data.database.AppDatabase;
import com.example.smartinventory.data.entity.Product;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InventoryViewModel extends AndroidViewModel {

    private final AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public InventoryViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
    }

    public LiveData<List<Product>> getAllProducts() {
        return db.productDao().getAllProducts();
    }

    // ✅ THIS WAS MISSING
    public void insert(Product product) {
        executor.execute(() -> db.productDao().insert(product));
    }
}
