package com.example.smartinventory.ui.reports;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartinventory.R;
import com.example.smartinventory.data.entity.SalesDayTotal;
import com.example.smartinventory.viewmodel.ReportsViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        BarChart barChart = findViewById(R.id.barChartSales);

        ReportsViewModel viewModel =
                new ViewModelProvider(this).get(ReportsViewModel.class);

        viewModel.getSalesPerDay().observe(this, data -> {
            List<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                SalesDayTotal item = data.get(i);
                entries.add(new BarEntry(i, item.total));
            }

            BarDataSet dataSet = new BarDataSet(entries, "Units Sold");
            dataSet.setColor(getColor(android.R.color.holo_blue_dark));

            BarData barData = new BarData(dataSet);
            barChart.setData(barData);
            barChart.getDescription().setEnabled(false);
            barChart.invalidate();
        });
    }
}
