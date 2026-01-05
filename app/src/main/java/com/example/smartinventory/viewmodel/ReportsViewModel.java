package com.example.smartinventory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartinventory.data.database.AppDatabase;
import com.example.smartinventory.data.entity.SalesDayTotal;

import java.util.List;

public class ReportsViewModel extends AndroidViewModel {

    private final LiveData<List<SalesDayTotal>> salesPerDay;

    public ReportsViewModel(@NonNull Application application) {
        super(application);

        salesPerDay =
                AppDatabase.getInstance(application)
                        .salesDao()
                        .getSalesGroupedByDay();
    }

    public LiveData<List<SalesDayTotal>> getSalesPerDay() {
        return salesPerDay;
    }
}
