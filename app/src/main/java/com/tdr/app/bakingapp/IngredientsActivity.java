package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsActivity extends AppCompatActivity {

    private static final String TAG = IngredientsActivity.class.getSimpleName();

    private IngredientsAdapter mAdapter;
    private Recipe recipe;
    @BindView(R.id.servings_text_view)
    TextView servingsTextView;
    @BindView(R.id.stepsButton)
    Button stepsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.ingredients_recycler_view);
        mAdapter = new IngredientsAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        Intent recipeIntent = getIntent();
        if (recipeIntent != null) {
            recipe = recipeIntent.getParcelableExtra("Recipe");
        }

        if (recipe != null) {
            setTitle(recipe.getName()); }

        stepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSteps(recipe);
            }
        });

        loadRecipeDataIntoViews();


    }

    public void loadRecipeDataIntoViews() {
        String servings = String.valueOf(recipe.getServings());
        servingsTextView.setText(servings);
        mAdapter.setIngredients(recipe.getIngredients());

        Log.d(TAG, "loadRecipeDataIntoViews: " + mAdapter.getItemCount());

    }

    public void goToSteps(Recipe recipeData) {
        Intent steps = new Intent(this, StepsActivity.class);
        steps.putExtra("RECIPE", recipeData);
        startActivity(steps);
    }
}