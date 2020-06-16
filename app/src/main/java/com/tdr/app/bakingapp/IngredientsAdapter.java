package com.tdr.app.bakingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private List<Ingredient> mIngredientList;


    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list_item, parent, false);

        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        Ingredient ingredient = mIngredientList.get(position);

        holder.ingredientTextView.setText(ingredient.getIngredient());
        holder.quantityTextView.setText(String.valueOf(ingredient.getQuantity()));
        holder.measurementTextView.setText(ingredient.getMeasure());


    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder{

        private TextView ingredientTextView;
        private TextView quantityTextView;
        private TextView measurementTextView;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientTextView = itemView.findViewById(R.id.ingredient_text_view);
            quantityTextView = itemView.findViewById(R.id.quantity_text_view);
            measurementTextView = itemView.findViewById(R.id.measurement_text_view);

        }
    }

    @Override
    public int getItemCount() {
        if (mIngredientList != null) {
            return mIngredientList.size();
        }
        return 0;
    }

    public void setIngredients(List<Ingredient> ingredientList) {
        mIngredientList = ingredientList;
        notifyDataSetChanged();
    }
}

