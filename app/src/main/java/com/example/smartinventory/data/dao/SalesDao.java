package com.example.smartinventory.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartinventory.data.entity.Sale;
import com.example.smartinventory.data.entity.SalesDayTotal;

import java.util.List;

@Dao
public interface SalesDao {

    @Insert
    void insert(Sale sale);

    @Query("SELECT * FROM sales ORDER BY timestamp DESC")
    LiveData<List<Sale>> getAllSales();

    @Query("SELECT IFNULL(SUM(quantitySold), 0) FROM sales")
    LiveData<Integer> getTotalUnitsSold();

    @Query(
            "SELECT date(timestamp / 1000, 'unixepoch') AS day, " +
                    "SUM(quantitySold) AS total " +
                    "FROM sales " +
                    "GROUP BY day " +
                    "ORDER BY day ASC"
    )
    LiveData<List<SalesDayTotal>> getSalesGroupedByDay();

    @Query(
            "SELECT SUM(quantitySold) * 1.0 / " +
                    "COUNT(DISTINCT date(timestamp / 1000, 'unixepoch')) " +
                    "FROM sales WHERE productId = :productId"
    )
    Double getAverageDailySales(int productId);
}
