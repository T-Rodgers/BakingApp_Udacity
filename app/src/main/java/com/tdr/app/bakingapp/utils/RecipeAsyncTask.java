package com.tdr.app.bakingapp.utils;

import android.os.AsyncTask;

import com.tdr.app.bakingapp.model.Recipe;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RecipeAsyncTask extends AsyncTask<Void, Void, List<Recipe>> {

    @Override
    protected List<Recipe> doInBackground(Void... voids) {
        URL recipeRequestUrl = NetworkUtils.buildUrl();
        try {
            String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(recipeRequestUrl);

                return JSONUtils.extractRecipeJSON(jsonRecipeResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
