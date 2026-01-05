package com.example.smartinventory.ui.reports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartinventory.R;
import com.example.smartinventory.data.entity.Sale;

import java.util.ArrayList;
import java.util.List;

public class SalesHistoryAdapter
        extends RecyclerView.Adapter<SalesHistoryAdapter.ViewHolder> {

    private List<Sale> sales = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sales_history, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder, int position) {

        Sale sale = sales.get(position);

        holder.tvProduct.setText(
                "Product ID: " + sale.getProductId()
        );
        holder.tvQuantity.setText(
                "Quantity: " + sale.getQuantitySold()
        );
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProduct, tvQuantity;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
