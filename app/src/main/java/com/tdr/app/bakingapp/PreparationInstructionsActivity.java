package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.tdr.app.bakingapp.fragments.FullDescriptionFragment;
import com.tdr.app.bakingapp.model.Step;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_STEP;

public class PreparationInstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation_instructions);

        Intent recipeData = getIntent();
        Step step = recipeData.getParcelableExtra(EXTRA_STEP);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && step != null) {
            actionBar.setTitle(step.getShortDescription());
        }

        FullDescriptionFragment fullDescriptionFragment = new FullDescriptionFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.full_description_container, fullDescriptionFragment)
                .commit();
    }
}