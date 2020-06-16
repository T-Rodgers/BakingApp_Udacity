package com.tdr.app.bakingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> mRecipeList;
    private final RecipeAdapterOnClickHandler mRecipeClickHandler;

    public RecipeAdapter(RecipeAdapterOnClickHandler handler) {
        mRecipeClickHandler = handler;
    }

    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipeData);
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        Recipe recipe = mRecipeList.get(position);
        holder.recipeNameTextView.setText(recipe.getName());
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView recipeNameTextView;

        public RecipeViewHolder(View itemView) {
            super(itemView);

            recipeNameTextView = itemView.findViewById(R.id.recipe_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Recipe recipe = mRecipeList.get(position);
            mRecipeClickHandler.onClick(recipe);
        }
    }

    @Override
    public int getItemCount() {
        if (mRecipeList != null) {
            return mRecipeList.size();
        }
        return 0;
    }

    public void setRecipeData(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
