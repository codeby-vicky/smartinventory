package com.example.smartinventory.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "sales")
public class Sale {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int productId;
    private int quantitySold;
    private long timestamp;

    // ✅ Constructor used by Room
    public Sale(int productId, int quantitySold, long timestamp) {
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.timestamp = timestamp;
    }

    // ❌ Ignored extra constructor
    @Ignore
    public Sale(int id, int productId, int quantitySold, long timestamp) {
        this.id = id;
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.timestamp = timestamp;
    }

    // ✅ REQUIRED getters
    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // ✅ REQUIRED setter ONLY for auto-generated ID
    public void setId(int id) {
        this.id = id;
    }
}
