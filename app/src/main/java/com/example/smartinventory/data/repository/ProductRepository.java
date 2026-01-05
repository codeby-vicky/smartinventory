package com.example.smartinventory.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.smartinventory.data.dao.ProductDao;
import com.example.smartinventory.data.database.AppDatabase;
import com.example.smartinventory.data.entity.Product;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {

    private final ProductDao productDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ProductRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        productDao = database.productDao();
    }

    public LiveData<List<Product>> getAllProducts() {
        return productDao.getAllProducts();
    }

    public LiveData<Integer> getProductCount() {
        return productDao.getProductCount();
    }

    public LiveData<Integer> getLowStockCount() {
        return productDao.getLowStockCount();
    }

    public void insert(Product product) {
        executorService.execute(() -> productDao.insert(product));
    }

    public void recordSale(Product product, int qty) {
        executorService.execute(() -> {
            productDao.reduceStock(product.getId(), qty);
        });
    }

}
