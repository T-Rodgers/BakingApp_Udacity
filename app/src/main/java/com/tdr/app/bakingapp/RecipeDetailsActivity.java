package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tdr.app.bakingapp.model.Recipe;
import com.tdr.app.bakingapp.utils.Constants;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_RECIPE;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent recipeIntent = getIntent();
        if (recipeIntent != null) {
            Recipe recipe = recipeIntent.getParcelableExtra(EXTRA_RECIPE);
            if (recipe != null)
            setTitle(recipe.getName());
        }

        ViewPager viewPager = findViewById(R.id.viewpager);

        DetailAdapter adapter = new DetailAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

    }
}