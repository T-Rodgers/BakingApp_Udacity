package com.tdr.app.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tdr.app.bakingapp.model.Ingredient;
import com.tdr.app.bakingapp.model.Recipe;

import java.util.List;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_RECIPE;
import static com.tdr.app.bakingapp.utils.Constants.INGREDIENTS_KEY;
import static com.tdr.app.bakingapp.utils.Constants.NAME_KEY;
import static com.tdr.app.bakingapp.utils.Constants.PREFERENCES_ID;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe recipe;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ActionBar actionBar = getSupportActionBar();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent recipeIntent = getIntent();
        if (recipeIntent != null) {
            recipe = recipeIntent.getParcelableExtra(EXTRA_RECIPE);
            if (recipe != null)
                if (actionBar != null) {
                    actionBar.setTitle(recipe.getName());
                }
        }

        ViewPager viewPager = findViewById(R.id.viewpager);

        DetailAdapter adapter = new DetailAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_to_widget) {
            Toast.makeText(this, recipe.getName() + " has been added to widgets", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit()
                    .putInt(PREFERENCES_ID, recipe.getId())
                    .putString(NAME_KEY, recipe.getName())
                    .putString(INGREDIENTS_KEY, buildIngredientsString())
                    .apply();

            ComponentName provider = new ComponentName(this, RecipeAppWidget.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] ids = appWidgetManager.getAppWidgetIds(provider);
            RecipeAppWidget recipeAppWidget = new RecipeAppWidget();
            recipeAppWidget.onUpdate(this, appWidgetManager, ids);

        }
        return super.onOptionsItemSelected(item);
    }

    private String buildIngredientsString() {
        StringBuilder ingredientList = new StringBuilder();
        List<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            String ingredientDescription = ingredient.getIngredient();
            String measurement = ingredient.getMeasure();
            String quantity = String.valueOf(ingredient.getQuantity());
            ingredientList.append(formatString(ingredientDescription, quantity, measurement));
        }

        return ingredientList.toString();
    }

    private String formatString(String description, String quantity, String measurement) {

        return description + " - " + quantity + " " + measurement + "\n\n";
    }
}