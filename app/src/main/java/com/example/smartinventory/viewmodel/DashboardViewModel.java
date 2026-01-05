package com.example.smartinventory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartinventory.data.repository.ProductRepository;

public class DashboardViewModel extends AndroidViewModel {

    private final ProductRepository repository;
    private final LiveData<Integer> totalProducts;
    private final LiveData<Integer> lowStockCount;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        totalProducts = repository.getProductCount();
        lowStockCount = repository.getLowStockCount();
    }

    public LiveData<Integer> getTotalProducts() {
        return totalProducts;
    }

    public LiveData<Integer> getLowStockCount() {
        return lowStockCount;
    }
}
