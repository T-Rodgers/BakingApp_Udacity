package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Recipe;

public class StepsActivity extends AppCompatActivity {

    private static final String TAG = StepsActivity.class.getSimpleName();

    private StepsAdapter mAdapter;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        RecyclerView recyclerView = findViewById(R.id.steps_recycler_view);
        mAdapter = new StepsAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        Intent recipeIntent = getIntent();
        if (recipeIntent != null) {
            recipe = recipeIntent.getParcelableExtra("RECIPE");

            if (recipe != null) {
                setTitle(recipe.getName());
            }
        }

        loadRecipeDataIntoViews();
    }

    public void loadRecipeDataIntoViews() {
        mAdapter.setRecipeSteps(recipe.getSteps());

        Log.d(TAG, "loadRecipeDataIntoViews: " + mAdapter.getItemCount());
    }
}