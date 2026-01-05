package com.example.smartinventory.ui.inventory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartinventory.R;
import com.example.smartinventory.data.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> products = new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ProductViewHolder holder,
            int position
    ) {
        Product product = products.get(position);

        holder.tvName.setText(product.getName());
        holder.tvQty.setText(
                holder.itemView.getContext()
                        .getString(R.string.quantity_format, product.getQuantity())
        );

        if (product.getQuantity() <= 5) {
            holder.tvLowStockBadge.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundResource(R.color.low_stock_bg);
        } else {
            holder.tvLowStockBadge.setVisibility(View.GONE);
            holder.itemView.setBackgroundResource(android.R.color.transparent);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> newProducts) {
        products.clear();
        if (newProducts != null) {
            products.addAll(newProducts);
        }
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        final TextView tvName;
        final TextView tvQty;
        final TextView tvLowStockBadge;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvQty = itemView.findViewById(R.id.tvProductQty);
            tvLowStockBadge = itemView.findViewById(R.id.tvLowStockBadge);
        }
    }
}
