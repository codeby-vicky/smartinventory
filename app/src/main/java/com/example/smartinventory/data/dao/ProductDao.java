package com.example.smartinventory.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartinventory.data.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products ORDER BY id DESC")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT COUNT(*) FROM products")
    LiveData<Integer> getProductCount();

    @Query("SELECT COUNT(*) FROM products WHERE quantity <= 5")
    LiveData<Integer> getLowStockCount();

    @Query("UPDATE products SET quantity = quantity - :soldQty WHERE id = :productId")
    void reduceStock(int productId, int soldQty);

    @Query("SELECT * FROM products WHERE id = :id")
    Product getProductById(int id);

}
