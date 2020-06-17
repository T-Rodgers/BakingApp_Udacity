package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Recipe;
import com.tdr.app.bakingapp.utils.RecipeAsyncTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_RECIPE;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler{


    private static final String TAG = MainActivity.class.getSimpleName();
    private RecipeAdapter recipeAdapter;
    @BindView(R.id.recipe_recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeAdapter = new RecipeAdapter(this);
        recyclerView.setAdapter(recipeAdapter);

       new RecipeTask().execute();

    }

    @Override
    public void onClick(Recipe recipeData) {
        Intent recipeDetailsIntent = new Intent(MainActivity.this, RecipeDetailsActivity.class);
        recipeDetailsIntent.putExtra(EXTRA_RECIPE, recipeData);
        startActivity(recipeDetailsIntent);


    }

    private class RecipeTask extends RecipeAsyncTask {

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            if (recipes != null && recipes.size() != 0) {
                recipeAdapter.setRecipeData(recipes);
                super.onPostExecute(recipes);
            }
        }
    }
}
